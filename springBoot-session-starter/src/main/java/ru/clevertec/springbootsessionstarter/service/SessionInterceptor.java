package ru.clevertec.springbootsessionstarter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import ru.clevertec.springbootsessionstarter.annotation.SessionProvider;
import ru.clevertec.springbootsessionstarter.dto.Session;
import ru.clevertec.springbootsessionstarter.dto.SessionRequest;
import ru.clevertec.springbootsessionstarter.exception.BlackListedLoginException;
import ru.clevertec.springbootsessionstarter.exception.SessionRequestExtensionException;
import ru.clevertec.springbootsessionstarter.service.impl.DefaultBlackListProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class SessionInterceptor implements MethodInterceptor {

    private final Object originalBean;
    private final SessionService sessionService;
    private final DefaultPropertiesProvider defaultPropertyProvider;
    private final BeanFactory beanFactory;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (method.isAnnotationPresent(SessionProvider.class)) {
            validateArgsHasSessionRequest(args);

            SessionProvider annotation = method.getAnnotation(SessionProvider.class);
            Set<String> blackLists = getCombinedBlackLists(annotation);

            Arrays.stream(args)
                    .filter(arg -> arg instanceof SessionRequest)
                    .map(arg -> (SessionRequest) arg)
                    .peek(sessionRequest -> checkBlackList(sessionRequest, blackLists))
                    .forEach(this::updateSessionRequest);
        }
        return method.invoke(originalBean, args);
    }

    private void checkBlackList(SessionRequest sessionRequest, Set<String> blackLists) {
        String loginValue = sessionRequest.getLogin();
        if (blackLists.contains(loginValue)) {
            log.error("Blacklist already contains login value {}", loginValue);
            throw new BlackListedLoginException("Login " + loginValue + " is blacklisted");
        }
    }

    private void updateSessionRequest(SessionRequest sessionRequest) {
        try {
            Field sessionField = getSessionField(sessionRequest);
            String loginValue = sessionRequest.getLogin();
            Session newSession = sessionService.getOrCreateAndGetSession(loginValue);
            sessionField.set(sessionRequest, newSession);

            sessionField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Failed to manipulate field : {}", e.getMessage());
            e.printStackTrace();
        }
    }

    private Field getSessionField(Object obj) throws NoSuchFieldException {
        Field field = obj.getClass().getSuperclass().getDeclaredField("session");
        field.setAccessible(true);
        return field;
    }

    private Set<String> getCombinedBlackLists(SessionProvider annotation) {
        Set<Class<? extends BlackListProvider>> providers = combineProviders(annotation);
        return extractBlockedLoginsFromProviders(providers, annotation);
    }

    private Set<Class<? extends BlackListProvider>> combineProviders(SessionProvider annotation) {
        Class<? extends BlackListProvider>[] providersFromAnnotation = annotation.blackListProviders();
        return Stream.concat(
                        Arrays.stream(providersFromAnnotation),
                        defaultPropertyProvider.getPropertyProviders().stream()
                )
                .filter(provider -> annotation.defaultBlackListProviderEnabled() || !provider.equals(DefaultBlackListProvider.class))
                .collect(Collectors.toSet());
    }

    private Set<String> extractBlockedLoginsFromProviders(Set<Class<? extends BlackListProvider>> providers, SessionProvider annotation) {
        return Stream.concat(
                        providers.stream()
                                .map(this::getBlackListFromProvider)
                                .flatMap(Set::stream),
                        Arrays.stream(annotation.blackLists()))
                .collect(Collectors.toSet());
    }

    private Set<String> getBlackListFromProvider(Class<? extends BlackListProvider> provider) {
        BlackListProvider bean = beanFactory.getBean(provider);
        return bean.getBlackList();
    }

    private void validateArgsHasSessionRequest(Object[] args) {
        Arrays.stream(args)
                .filter(arg -> arg instanceof SessionRequest)
                .findAny()
                .orElseThrow(() -> {
                    String errorMessage = "Extended object from SessionRequest wasn't found";
                    log.error(errorMessage);
                    return new SessionRequestExtensionException(errorMessage);
                });
    }
}
