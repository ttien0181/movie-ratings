package com.example.movie_ratings.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration // Đánh dấu class này là một lớp cấu hình của Spring
@RequiredArgsConstructor // Lombok tự tạo constructor cho các field final
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3001")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        System.out.println("cors config");

        // Cho phép frontend ở địa chỉ này truy cập
        configuration.setAllowedOrigins(List.of("http://localhost:3001"));
//        configuration.setAllowedOriginPatterns(List.of("*"));

        // Cho phép gửi cookie, JWT hoặc header Authorization
        configuration.setAllowCredentials(true);

        // Cho phép các phương thức HTTP cụ thể
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Cho phép tất cả các header (vd: Authorization, Content-Type, ...)
        configuration.setAllowedHeaders(List.of("*"));

        // Cho phép client đọc các header này từ response
        configuration.setExposedHeaders(List.of("Authorization"));

        // Đăng ký cấu hình này cho tất cả endpoint
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
