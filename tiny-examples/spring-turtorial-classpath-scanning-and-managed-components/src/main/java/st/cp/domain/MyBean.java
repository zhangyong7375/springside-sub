/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.cp.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeff.huang
 */
@Component @Qualifier("default")
public class MyBean {
    private String s = "Hello";

    public MyBean() {
    }

    public MyBean(String s) {
        this.s = s;
    }


    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "MyBean{" + "s=" + s + '}';
    }

    


}
