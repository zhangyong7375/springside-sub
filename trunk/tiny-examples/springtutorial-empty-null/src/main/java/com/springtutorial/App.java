package com.springtutorial;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        MyBean bean = ctx.getBean("bean", MyBean.class);

        System.out.println("s1 = [" + bean.getS1() + "]");
        System.out.println("s2 = [" + bean.getS2() + "]");

        MyBean2 bean2 = ctx.getBean("bean2", MyBean2.class);
        System.out.println(bean == bean2.getBean());
    }
}
