package com.example.interceptor.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Optional;

@Component
public class SampleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // HandlerMethod cast exception
        SampleAnnotation annotation = getAnnotation((HandlerMethod) handler, SampleAnnotation.class);
        if (annotation != null) {
            return true;
        }

        return true;
    }

    private <A extends Annotation> A getAnnotation(HandlerMethod handlerMethod, Class<A> annotationType) {
        return Optional.ofNullable(handlerMethod.getMethodAnnotation(annotationType))
                .orElse(handlerMethod.getBeanType().getAnnotation(annotationType));
    }
}
