package com.example;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class SpringWebConfig extends WebMvcConfigurerAdapter {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/img/**").addResourceLocations("file:///C:\\items-images\\");
        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
        registry.addResourceHandler("/pdfs/**").addResourceLocations("/static/pdf/");
        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/static/css/fonts/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/static/css/styles/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/static/css/plugins/");
        registry.addResourceHandler("/images/**").addResourceLocations("/static/css/images/");
        

        registry.addResourceHandler("/animate/**").addResourceLocations("/static/css/animate/");
        registry.addResourceHandler("/animsition/**").addResourceLocations("/static/css/animsition/");
        registry.addResourceHandler("/css-hamburgers/**").addResourceLocations("/static/css/css-hamburgers/");
        registry.addResourceHandler("/images/**").addResourceLocations("/static/css/images/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("/static/css/vendor/");
        
        
//        registry.addResourceHandler("search/img/**").addResourceLocations("file:///C:\\items-images\\");
//        registry.addResourceHandler("search/css/**").addResourceLocations("/static/css/");
//        registry.addResourceHandler("search/pdfs/**").addResourceLocations("/static/css/pdf/");
//        registry.addResourceHandler("search/js/**").addResourceLocations("/static/js/");
//        registry.addResourceHandler("search/fonts/**").addResourceLocations("/static/css/fonts/");
//        registry.addResourceHandler("search/styles/**").addResourceLocations("/static/css/styles/");
//        registry.addResourceHandler("search/plugins/**").addResourceLocations("/static/css/plugins/");
//        registry.addResourceHandler("search/images/**").addResourceLocations("/static/css/images/");
        
        
//        registry.addResourceHandler("cart/img/**").addResourceLocations("file:///C:\\items-images\\");
//        registry.addResourceHandler("cart/css/**").addResourceLocations("/static/css/");
//        registry.addResourceHandler("cart/pdfs/**").addResourceLocations("/static/css/pdf/");
//        registry.addResourceHandler("cart/js/**").addResourceLocations("/static/js/");
//        registry.addResourceHandler("cart/fonts/**").addResourceLocations("/static/css/fonts/");
//        registry.addResourceHandler("cart/styles/**").addResourceLocations("/static/css/styles/");
//        registry.addResourceHandler("cart/plugins/**").addResourceLocations("/static/css/plugins/");
//        registry.addResourceHandler("cart/images/**").addResourceLocations("/static/css/images/");
        
//        registry.addResourceHandler("checkout/img/**").addResourceLocations("file:///C:\\items-images\\");
//        registry.addResourceHandler("checkout/css/**").addResourceLocations("/static/css/");
//        registry.addResourceHandler("checkout/pdfs/**").addResourceLocations("/static/css/pdf/");
//        registry.addResourceHandler("checkout/js/**").addResourceLocations("/static/js/");
//        registry.addResourceHandler("checkout/fonts/**").addResourceLocations("/static/css/fonts/");
//        registry.addResourceHandler("checkout/styles/**").addResourceLocations("/static/css/styles/");
//        registry.addResourceHandler("checkout/plugins/**").addResourceLocations("/static/css/plugins/");
//        registry.addResourceHandler("checkout/images/**").addResourceLocations("/static/css/images/");
//        
        
        
//    	registry.addResourceHandler("**/img/**").addResourceLocations("file:///C:\\items-images\\");
//        registry.addResourceHandler("**/css/**").addResourceLocations("/static/css/");
//        registry.addResourceHandler("**/pdfs/**").addResourceLocations("/static/pdf/");
//        registry.addResourceHandler("**/js/**").addResourceLocations("/static/js/");
//        registry.addResourceHandler("**/fonts/**").addResourceLocations("/static/fonts/");
//        registry.addResourceHandler("**/styles/**").addResourceLocations("/static/css/styles/");
//        registry.addResourceHandler("**/plugins/**").addResourceLocations("/static/plugins/");
//        registry.addResourceHandler("**/images/**").addResourceLocations("/static/images/");
        
    }
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/jsp/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	// localization configuration
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("language");
		registry.addInterceptor(changeInterceptor);
	}
	
}
