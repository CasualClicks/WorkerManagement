package com.officialshivam.HomeLab.WorkerManagement.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLogger {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationLogger.class);

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public void logDebug(String message) {
        logger.debug(message);
    }

    public void logWarn(String message) {
        logger.warn(message);
    }

    // You can add other levels like trace as well
    public void logTrace(String message) {
        logger.trace(message);
    }
}
