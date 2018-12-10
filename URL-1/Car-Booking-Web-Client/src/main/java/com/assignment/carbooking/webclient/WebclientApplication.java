package com.assignment.carbooking.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.assignment.carbooking.webclient.formatter.CarFormatter;
import com.assignment.carbooking.webclient.formatter.LongToTimeFormatter;

@SpringBootApplication
public class WebclientApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebclientApplication.class, args);
    }

    @Autowired //Without autowire, this solution may not work
    private CarFormatter carFormatter;
    @Autowired
    private LongToTimeFormatter longToTimeFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(carFormatter);
        registry.addFormatter(longToTimeFormatter);
    }
}
