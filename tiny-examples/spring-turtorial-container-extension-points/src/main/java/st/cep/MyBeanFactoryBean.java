/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.cep;

import org.springframework.beans.factory.FactoryBean;

/**
 *
 * @author jeff.huang
 */
public class MyBeanFactoryBean implements FactoryBean<Object>{

    @Override
    public Object getObject() throws Exception {
        return new MyBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
