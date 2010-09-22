/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.cp.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeff.huang
 */
@Component
public class MyBeanFactory {

    @Bean(name={"myBean2"})
    public MyBean createMyBean2(){
        return new MyBean("bean2");
    }

    @Bean @Qualifier("main")
    public MyBean createMyBean3() {
        return new MyBean("bean3");
    }

}
