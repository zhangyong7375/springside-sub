/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springioc;

import java.util.Random;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author jeff.huang
 */
public class MyBeanWithApplicationContextAware implements ApplicationContextAware{
    private ApplicationContext ctx;

    public void sayHello() {
        Random r = ctx.getBean("random", Random.class);
        MyBean bean = new MyBean("Application-Context-Aware " + r.nextInt());
        bean.sayHello();
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ctx = ac;
    }

}
