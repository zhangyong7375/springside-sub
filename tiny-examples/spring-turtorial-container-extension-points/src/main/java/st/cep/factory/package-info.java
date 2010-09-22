/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.cep.factory;

/**
 * Configuration management with Spring
 *
 * http://blog.arendsen.net/index.php/2005/03/12/configuration-management-with-spring/
 * <p> For every project we have a staging environment that is almost always
 * pretty much identical to the production environment. Of course you can’t
 * completely mimick the production environment since systems we’re integrating
 * with are not available (in which case they’re mocked) or located in a different
 * place. We’re doing a lot Spring-based apps and some of the features Spring
 * offers are really useful when distinguishing between a local environment,
 * a staging environment and the final production environment. The following
 * discusses the possibilities you have for setting up a Spring ApplicationContext
 * to be able to deploy it to different environments without having to change the
 * context itself.
 * </p>
 */