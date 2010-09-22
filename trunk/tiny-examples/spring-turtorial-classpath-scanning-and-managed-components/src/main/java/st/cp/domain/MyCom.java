/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.cp.domain;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeff.huang
 */
@Component
public class MyCom {
    @Autowired @Qualifier("main")
    private MyBean myBean;
    @Autowired
    private MyBean myBean2;
    @Resource(name="myBean") @Qualifier("main")
    private MyBean myBean3;
    @Autowired @Qualifier("main")
    private MyBean myBean4;

    public MyBean getMyBean() {
        return myBean;
    }

    public void setMyBean(MyBean myBean) {
        this.myBean = myBean;
    }

    public MyBean getMyBean2() {
        return myBean2;
    }

    public void setMyBean2(MyBean myBean2) {
        this.myBean2 = myBean2;
    }

    public MyBean getMyBean3() {
        return myBean3;
    }

    public void setMyBean3(MyBean myBean3) {
        this.myBean3 = myBean3;
    }

    public MyBean getMyBean4() {
        return myBean4;
    }

    public void setMyBean4(MyBean myBean4) {
        this.myBean4 = myBean4;
    }

    
    
}
