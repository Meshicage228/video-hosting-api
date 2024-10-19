package ru.clevertec.springbootsessionstarter.annotation;

import ru.clevertec.springbootsessionstarter.service.BlackListProvider;
import ru.clevertec.springbootsessionstarter.service.impl.DefaultBlackListProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SessionProvider {
    String[] blackLists() default {};
    boolean defaultBlackListProviderEnabled() default true;
    Class<? extends BlackListProvider>[] blackListProviders() default DefaultBlackListProvider.class;
}
