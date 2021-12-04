package com.mibcxb.tool.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MctLog {
    private static final Logger LOGGER = LoggerFactory.getLogger("McTool@1.0.0");

    public static Logger logger() {
        return LOGGER;
    }

    public static void printE(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
