package com.example.exp4.Handler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器
        LoginInterceptor registration = new LoginInterceptor();
        registry.addInterceptor(registration).addPathPatterns("/**")        // 拦截所有路径
                .excludePathPatterns("/login", "/login/input")
                .excludePathPatterns("/css/**", "/img/**");     // 不拦截登陆界面路径
        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
