/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import java.util.logging.Logger;
import org.springframework.context.SmartLifecycle;

/**
 *
 * @author jeff.huang
 */
public class SmartLifeCycleBeanB implements SmartLifecycle{
    private static final Logger logger = Logger.getLogger(SmartLifeCycleBeanB.class.getName());
    private boolean running;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        logger.info("stop");
        running = false;
    }

    @Override
    public void start() {
        logger.info("start");
        running = true;
    }

    @Override
    public void stop() {
        logger.info("stop");
        running = false;
    }

    @Override
    public boolean isRunning() {
        logger.info("isRunning " + running);
        return running;
    }

    @Override
    public int getPhase() {
        return 2;
    }

}
