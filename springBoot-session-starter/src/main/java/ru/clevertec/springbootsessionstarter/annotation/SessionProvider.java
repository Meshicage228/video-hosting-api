package ru.clevertec.springbootsessionstarter.annotation;

import ru.clevertec.springbootsessionstarter.service.BlackListPropertiesProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SessionProvider {
    String[] blackLists() default {};
    Class<? extends BlackListPropertiesProvider>[] propertyProviders() default {};
}
