package ru.mpei.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; //1

@Configuration //2
public class MvcConfig implements WebMvcConfigurer { //3
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //4
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    public void addViewControllers(ViewControllerRegistry registry) { //5
        registry.addViewController("/login").setViewName("login");
    }
}