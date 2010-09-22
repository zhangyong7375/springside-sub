package st.cp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import st.cp.domain.MyCom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
        MyCom com = ctx.getBean("myCom", MyCom.class);
        System.out.println(com.getMyBean().toString() + " " + com.getMyBean2().toString() + " " + com.getMyBean3().toString() + " " + com.getMyBean4().toString());
    }
}
