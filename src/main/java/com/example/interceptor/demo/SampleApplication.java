package com.example.interceptor.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class SampleApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Autowired
    private SampleInterceptor sampleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sampleInterceptor);

        // Please unpack this annotation and test it

//        registry.addInterceptor(sampleInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/swagger**", "/webjars/springfox-swagger-ui/**");
    }

    @GetMapping(value = "/test")
    @SampleAnnotation(name = "kkk")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}

