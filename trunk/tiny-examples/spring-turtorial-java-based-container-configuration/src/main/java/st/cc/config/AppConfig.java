/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jeff.huang
 */
@Configuration
public class AppConfig {

        @Bean(name="s")
        public String myString(){
            return "Hello";
        }


}
