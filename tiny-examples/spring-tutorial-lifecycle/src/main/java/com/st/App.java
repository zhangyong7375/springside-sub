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
        app.testLifeCycle();
    }

    public void call() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
    }

    public void callWithRefresh() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        ctx.refresh();
    }

    public void testLifeCycle() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        /*LifeCycleBean b = ctx.getBean("lifeCycleBean", LifeCycleBean.class);
        //System.out.println("running = " + b.isRunning());
        b.start();
        b.stop();*/
        ctx.start();
        try{
            for(int k=0; k<20; ++k){
                Thread.sleep(1000);
                System.out.println("sleeping " + (k+1));
            }

        }catch(InterruptedException e){
            System.exit(1);
        }
        ctx.stop();
    }
}
