package ru.clevertec.springbootsessionstarter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class SessionInterceptor implements MethodInterceptor {

    private final Object originalBean;
    private final SessionService metricSenderHandler;
    private final DefaultPropertyProvider metricSenderProperties;
    private final BeanFactory beanFactory;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("aggas");
        return null;
    }
}
