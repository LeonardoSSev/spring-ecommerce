package com.leonardossev.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageSourceConfiguration {

    @Autowired
    private MessageSource messageSource;

    @Bean
    @Primary
    public MessageSourceAccessor accessor() {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public LocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en", "US"));

        return localeResolver;
    }
}
