/*
 * http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/expressions.html
 */

package com.spel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 *
 * @author jeff.huang
 */
public class Main {

    public static void main(String[] args) throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!').toUpperCase()");
        String message = exp.getValue(String.class);

        System.out.println("message = " + message);

        exp = parser.parseExpression("'Hello world'.bytes");
        byte[] bytes = (byte[])exp.getValue();
        System.out.println("byte length = " + bytes.length);

        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        exp = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(tesla);

        String name = exp.getValue(context, String.class);
        System.out.println("name = " + name);

        name = exp.getValue(tesla, String.class);
        System.out.println("name = " + name);

        Inventor jeff = new Inventor("Jeff Huang", new Date(), "China");
        
        /*
         * The StandardEvaluationContext is where you may specify the root object to evaluate against via the method setRootObject()
         */
        ((StandardEvaluationContext)context).setRootObject(jeff);
        System.out.println("name = " + name);

        /*
         * http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/expressions.html#expressions-ref-variables
         * Variables can be referenced in the expression using the syntax #variableName. Variables are set using the method setVariable on the StandardEvaluationContext.
         */
        context.setVariable("newName", "Mike Huang");
        parser.parseExpression("name = #newName").getValue(context);
        System.out.println("New name = " + jeff.getName());

        /*
         * 6.5.10.1 The #this and #root variables
         *
         * The variable #this is always defined and refers to the current
         * evaluation object (against which unqualified references are resolved).
         * The variable #root is always defined and refers to the root context
         * object. Although #this may vary as components of an expression are
         * evaluated, #root always refers to the root.
         */

        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        context = new StandardEvaluationContext();
        context.setVariable("primes", primes);

        List<Integer> primesGreaterThanTen = (List<Integer>)parser.parseExpression("#primes.?[#this>10]").getValue(context);
        System.out.println("primes greater than then = " + primesGreaterThanTen);

        /*
         * 6.5.11 Functions
         */
        ((StandardEvaluationContext)context).setRootObject(jeff);
        ((StandardEvaluationContext)context).registerFunction("rs", Main.class.getDeclaredMethod("reverseString", new Class[] { String.class } ));

        System.out.println("reversed name = " + parser.parseExpression("#rs(#root.name)").getValue(context, String.class));

        /*
         * 6.3.1.1 Type Conversion
         */
        class Simple {

            public List<Boolean> booleanList = new ArrayList<Boolean>();
        }

        Simple simple = new Simple();

        simple.booleanList.add(true);

        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);

        // false is passed in here as a string.  SpEL and the conversion service will
        // correctly recognize that it needs to be a Boolean and convert it
        parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");

        // b will be false
        Boolean b = simple.booleanList.get(0);
        System.out.println(" b = " + b);

        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("com/spel/spel-app.xml"));
        Integer v = null;
        try{
            v = factory.getBean("v", Integer.class);
        }catch(BeansException ex){
            Logger.getAnonymousLogger().log(Level.SEVERE, "1", ex);
        }
        System.out.println("v = " + v);

        ApplicationContext appCtx = new ClassPathXmlApplicationContext("com/spel/spel-app.xml");
        v = appCtx.getBean("v", Integer.class);
        System.out.println("v = " + v);
    }
    
    public static String reverseString(String input) {
        StringBuilder backwards = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            backwards.append(input.charAt(input.length() - 1 - i));
        }
        return backwards.toString();
    }


}
