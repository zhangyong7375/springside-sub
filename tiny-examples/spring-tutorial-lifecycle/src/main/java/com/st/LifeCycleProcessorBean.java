/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import java.util.logging.Logger;
import org.springframework.context.LifecycleProcessor;

/**
 *
 * @author jeff.huang
 */
public class LifeCycleProcessorBean implements LifecycleProcessor{
    private static final Logger logger = Logger.getLogger(LifecycleProcessor.class.getName());
    private boolean running = false;

    @Override
    public void onRefresh() {
        logger.info("onRefresh");
    }

    @Override
    public void onClose() {
        logger.info("onClose");        
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

}
