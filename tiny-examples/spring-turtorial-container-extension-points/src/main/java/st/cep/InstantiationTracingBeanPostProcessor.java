/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.cep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 *
 * @author jeff.huang
 */
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor, Ordered {
    Logger logger = LoggerFactory.getLogger(InstantiationTracingBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessBeforeInitialization Bean Name = " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessAfterInitialization Bean Name = " + beanName);
        return bean;
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
