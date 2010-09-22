package st.cep;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
        FactoryBean fb = (FactoryBean)ctx.getBean("&beanFromFactory");
        System.out.println("Factory Bean = " + fb.getClass().getName() + " : return type = " + fb.getObjectType().getName());
        MyBean bean = ctx.getBean("beanFromFactory", MyBean.class);
        System.out.println("My Bean = " + bean.toString());
    }
}
