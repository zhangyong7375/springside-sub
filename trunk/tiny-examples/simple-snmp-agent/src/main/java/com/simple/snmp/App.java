package com.simple.snmp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SimpleSnmpAgent agent = new SimpleSnmpAgent("udp:127.0.0.1/2010");
        agent.start();

        Thread.sleep(100 * 1000);
    }
}
