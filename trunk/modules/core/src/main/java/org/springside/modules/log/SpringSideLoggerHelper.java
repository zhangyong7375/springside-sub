/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * $Id$
 */

package org.springside.modules.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A helper class to manipulate the logger
 *
 * @author jeff.huang
 */
public class SpringSideLoggerHelper {

    /**
     * Create a slf4j logger based on the caller's class name.
     *
     * This method utilizes the current thread's stack trace to locate the caller
     * class name and create a logger based on it.
     * <p>
     * Usage:
     * <pre>
     *   Logger logger = SpringSideLoggerHelper.getLoggerForCurrentClass();
     *   ...
     * </pre>
     * </p>
     * 
     * @return
     */
    public static Logger getLoggerForCurrentClass() {
        Thread currThread = Thread.currentThread();
        StackTraceElement[] trace = currThread.getStackTrace();
        return LoggerFactory.getLogger(trace[2].getClassName());
    }
}
