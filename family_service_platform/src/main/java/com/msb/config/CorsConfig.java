package com.msb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


// @Configuration 使用注解，不使用配置文件
public class CorsConfig {
    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 设置属性
        // 允许跨越请求的地址，*表示所有
        corsConfiguration.addAllowedOrigin("*");
        // 配置跨越的请求头
        corsConfiguration.addAllowedHeader("*");
        // 配置跨越的请求方法
        corsConfiguration.addAllowedMethod("*");
        // 表示跨越请求的时候是否使用的是同一个session, 前端也需要另外的设置 跨域访问需要发送cookie时一定要加axios.defaults.withCredentials = true;
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}
