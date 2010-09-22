/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import java.util.logging.Logger;
import org.springframework.context.Lifecycle;

/**
 *
 * @author jeff.huang
 */
public class LifeCycleBean implements Lifecycle{
    private static final Logger logger = Logger.getLogger(LifeCycleBean.class.getName());
    private boolean running = false;

    @Override
    public void start() {
        System.out.println("start");
        logger.info("start");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("stop");
        logger.info("stop");
        running = false;
    }

    @Override
    public boolean isRunning() {
        System.out.println("isRunning : " + running);

        logger.info("isRunning : " + running);
        return running;
    }

}
