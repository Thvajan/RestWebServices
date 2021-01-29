package com.learning.RestWebServices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.JAPAN);
		return localeResolver;
	}

//	@Bean//method name should be messageSource; alternative can be done in application.properties
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
//		bundleMessageSource.setBasename("messages");
//		return bundleMessageSource;
//	}
}
