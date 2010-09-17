/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.properties;

import java.util.Properties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jeff.huang
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/properties/app.xml");
        Properties p = ctx.getBean("config", Properties.class);
        System.out.println(p.getProperty("my.hello"));
        System.out.println(p.getProperty("my.world"));
        String hello = ctx.getBean("hello", String.class);
        System.out.println("hello = " + hello);
    }

}
