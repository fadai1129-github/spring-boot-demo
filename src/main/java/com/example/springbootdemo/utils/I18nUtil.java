package com.example.springbootdemo.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18nUtil implements ApplicationContextAware {

    private static I18nUtil instance;

    @Autowired
    private MessageSource messageSource;

    public static String getMessage(String key) {
        return instance.seflGetMessage(key, null);
    }


    public String getKey(String key, Object[] args) {
        return instance.seflGetMessage(key, args);
    }



    public String seflGetMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(key, args, key, locale);
        return message;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        instance = (I18nUtil) applicationContext.getBean("i18nUtil");
    }
}
