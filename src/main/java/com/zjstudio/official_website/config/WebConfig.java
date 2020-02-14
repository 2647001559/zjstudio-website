package com.zjstudio.official_website.config;/**
 * @author 添柴灬少年
 * @date 2019/12/2 - 21:49
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * TODO
 * @author  添柴灬少年
 * @date  2019/12/2 21:49
 * @ver 1.0
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
