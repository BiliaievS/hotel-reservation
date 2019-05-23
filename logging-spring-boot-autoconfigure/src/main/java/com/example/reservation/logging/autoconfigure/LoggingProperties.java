package com.example.reservation.logging.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Sergii Biliaiev
 * Created on 22/05/2019.
 */
@ConfigurationProperties("logging")
public class LoggingProperties {

    private String loggerName = "AuditLogger";

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
