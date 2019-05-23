package com.example.reservation.logging.autoconfigure;

import com.example.reservation.aop.LoggableAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergii Biliaiev
 * Created on 22/05/2019.
 */
@Configuration
@ConditionalOnClass(LoggableAspect.class)
@EnableConfigurationProperties(LoggingProperties.class)
public class ReservationLoggingProperties {

    @Autowired
    private LoggingProperties properties;

    @Bean
    public LoggableAspect getLoggableAspect() {
        return new LoggableAspect(properties.getLoggerName());
    }
}
