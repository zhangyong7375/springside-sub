package com.st;

import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    private ApplicationContext ctx;

    public App(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public int methodInject() {
        return ctx.getBean("bean", MyBean.class).update();
    }
}
