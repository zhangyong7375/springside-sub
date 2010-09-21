package com.st;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.call();
        System.out.println("======================");
        app.callWithRefresh();
    }

    public void call() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
    }

    public void callWithRefresh() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        ctx.refresh();
    }
}
