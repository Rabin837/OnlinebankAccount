package com.bitskraft.bankaccountmock;

import com.bitskraft.bankaccountmock.dto.CountryDTO;
import com.bitskraft.bankaccountmock.service.CountryService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.CacheControl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages ={"com.bitskraft.bankaccountmock.controller","com.bitskraft.bankaccountmock.Advice","com.bitskraft.bankaccountmock.serviceImpl",
"com.bitskraft.bankaccountmock.service","com.bitskraft.bankaccountmock.repository","com.bitskraft.bankaccountmock.entity",
"com.bitskraft.bankaccountmock.dto"})
@EnableSwagger2
public class bankBackend {
    private final CountryService countryService;

    public bankBackend(CountryService countryService) {
        this.countryService=countryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(bankBackend.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            // read json and write to db
            try {
                if(countryService.countAll() == 0) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                            Objects.requireNonNull(this.getClass().
                                    getResourceAsStream("/nepal-data.json"))));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    CountryDTO countryDetails = new Gson().fromJson(stringBuilder.toString(), CountryDTO.class);
                    countryService.save(countryDetails);
                    log.info("Address saved");
                }
            }
            catch (Exception e) {
               log.error(e.getMessage());
            }
        };
    }
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.bitskraft.bankaccountmock")).build();
    }
    @Configuration
    @EnableSpringDataWebSupport
    public class WebSecurityConfig implements WebMvcConfigurer {
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
    }

    @Configuration
    public class WebConfig {
        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder builder) {
            return builder
                    .setConnectTimeout(Duration.ofMillis(3000))
                    .setReadTimeout(Duration.ofMillis(3000))
                    .build();
        }
    }
}
