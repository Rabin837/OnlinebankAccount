package com.bitskraft.bankaccountmock.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.CacheControl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer {
    String customerFolderName = "images";

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods(
                        "GET", "POST", "PUT", "DELETE", "HEAD").allowCredentials(true);
            }

        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imagePath = "file:" + System.getProperty("user.dir") + File.separator + customerFolderName + File.separator;
        registry.addResourceHandler("/" + customerFolderName + "/**").addResourceLocations(imagePath).setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS).cachePublic());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
}