package com.tttn.spring.webshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerExample {
    public static final Logger LOGGER= LoggerFactory.getLogger("com.tttn.spring.webshop");

    public void info(String message){
        LOGGER.info(message);
    }
    public void warn(String message){
        LOGGER.warn(message);
    }
    public void error(String message){
        LOGGER.error(message);
    }
    public void debug(String message){
        LOGGER.debug(message);
    }

}
