/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springdb.embedded2;

import com.springdb.embedded.*;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

//http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html#jdbc-embedded-database-support

/**
 *
 * @author jeff.huang
 */
public class Main {
    public static void main(String[] args) {

        //12.8.2 Creating an embedded database instance using Spring XML
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/springdb/embedded2/em-ctx1.xml");
        JdbcTemplate jdbc = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM a LIMIT 1");
        for(Entry<String, Object> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }
}
