package ru.clevertec.springbootsessionstarter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import ru.clevertec.springbootsessionstarter.annotation.SessionProvider;
import ru.clevertec.springbootsessionstarter.dto.Session;
import ru.clevertec.springbootsessionstarter.dto.SessionRequest;
import ru.clevertec.springbootsessionstarter.service.impl.DefaultBlackListProvider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SessionInterceptor implements MethodInterceptor {

    private final Object originalBean;
    private final SessionService sessionService;
    private final DefaultPropertyProvider defaultPropertyProvider;
    private final BeanFactory beanFactory;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if(method.isAnnotationPresent(SessionProvider.class)) {
            SessionProvider annotation = method.getAnnotation(SessionProvider.class);
            Boolean b = argsHasSessionRequest(args);
            String[] strings = annotation.blackLists();
            Class<? extends BlackListProvider>[] classes = annotation.blackListProviders();
            Set<Class<? extends BlackListProvider>> propertyProviders = defaultPropertyProvider.getPropertyProviders();
            Set<Class<? extends BlackListProvider>> resultedProviders = getResultedProviders(annotation);

            Set<String> strings1 = collectAllBlackListLogins(resultedProviders);
            Set<String> strings2 = combineBlackListLogins(strings, strings1);

            Session test = sessionService.getOrCreateAndGetSession("test");

            System.out.println();

        }
        return method.invoke(originalBean, args);
    }

    private Set<Class<? extends BlackListProvider>> getResultedProviders(SessionProvider annotation) {
        Class<? extends BlackListProvider>[] providersFromAnnotation = annotation.blackListProviders();
        return Stream.concat(
                        Arrays.stream(providersFromAnnotation),
                        defaultPropertyProvider.getPropertyProviders().stream()
                )
//                .filter(provider -> !provider.equals(DefaultBlackListProvider.class))
                .collect(Collectors.toSet());
    }

    private Set<String> collectAllBlackListLogins(Set<Class<? extends BlackListProvider>> providers){
        return providers.stream()
                .map(provider -> {
                    if(provider.equals(DefaultBlackListProvider.class)){
                        DefaultBlackListProvider bean = beanFactory.getBean(DefaultBlackListProvider.class);
                        return bean.getBlackList();
                    } else {
                        BlackListProvider bean = beanFactory.getBean(provider);
                        return bean.getBlackList();
                    }
                })
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    private Set<String> combineBlackListLogins(String[] fromAnnotation, Set<String> fromProviders){
        Set<String> collect = Arrays.stream(fromAnnotation).collect(Collectors.toSet());
        fromProviders.addAll(collect);

        return fromProviders;
    }

    private Boolean argsHasSessionRequest(Object[] args) {
        return Arrays.stream(args)
                .anyMatch(arg -> arg instanceof SessionRequest);
    }
}
