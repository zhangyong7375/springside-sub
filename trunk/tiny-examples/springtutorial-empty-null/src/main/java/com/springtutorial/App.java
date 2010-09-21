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
        ApplicationContext ctxParent = new ClassPathXmlApplicationContext("app2-context.xml");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(ctxParent);
        ctx.setConfigLocation("app-context.xml");
        ctx.refresh();
        MyBean bean = ctx.getBean("bean", MyBean.class);

        System.out.println("s1 = [" + bean.getS1() + "]");
        System.out.println("s2 = [" + bean.getS2() + "]");

        MyBean2 bean2 = ctx.getBean("bean2", MyBean2.class);
        System.out.println(bean == bean2.getBean());

        MyBean2 b2 = ctx.getBean("myBean2", MyBean2.class);
        System.out.println("s1 = " + b2.getBean().getS1() + " s2 = " + b2.getBean().getS2());

        MyCollection col = ctx.getBean("col", MyCollection.class);
        System.out.println(col.getList());
        System.out.println(col.getSet());
        System.out.println(col.getMap());

        try{
            MyParent p = ctx.getBean("parent", MyParent.class);
            System.out.println("Should not be here");
        }catch(Exception ex){
            System.out.println("Should have exception : " + ex.getMessage());
        }

        MyChild c = ctx.getBean("child", MyChild.class);
        System.out.println(c.toString());
    }
}
