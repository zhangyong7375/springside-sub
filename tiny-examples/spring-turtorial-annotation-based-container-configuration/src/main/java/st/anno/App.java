package st.anno;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import st.anno.model.SimpleMovieListener;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
        System.out.println(ctx.getBean("ml3", SimpleMovieListener.class).getMovieFinder());
    }
}
