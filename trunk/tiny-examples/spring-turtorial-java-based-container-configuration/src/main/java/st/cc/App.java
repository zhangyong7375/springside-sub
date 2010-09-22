package st.cc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import st.cc.config.AppConfig;
import st.cc.config.MyCom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, MyCom.class);
        String s = ctx.getBean("s", String.class);
        System.out.println("s = " + s);
        MyCom com = ctx.getBean("myCom", MyCom.class);
        com.sayHello();
    }
}
