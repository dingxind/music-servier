package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
    public class MyPicSongConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:D:/vscodeSpace/music-servier/img/songPic/");
            registry.addResourceHandler("/song/**").addResourceLocations("file:D:/vscodeSpace/music-serveir/song/");
        }
    }