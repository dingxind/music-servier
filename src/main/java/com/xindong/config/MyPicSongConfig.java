package com.xindong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
    public class MyPicSongConfig implements WebMvcConfigurer {

    @Value("${file.url}")
    String url;
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:"+url+"/music-server/img/songPic/");
            registry.addResourceHandler("/song/**").addResourceLocations("file:"+url+"/music-server/song/");
        }
    }