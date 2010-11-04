/*
 * Copyright 2010 Torokina Networks
 */

package snmp4t;

import java.util.Random;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

/**
 *
 * @author jeff.huang
 */
public class MyTrapSender implements Runnable {
    private MyAgent agent;

    public MyTrapSender(MyAgent agent) {
        this.agent = agent;
    }



    @Override
    public void run() {
        doSender();
    }

    private void doSender() {
        while(true){
            long sleep = new Random().nextInt(5000) + 1000;
            try{
                Thread.sleep(sleep);
            }catch(InterruptedException ex){
                return;
            }
            agent.getNotificationOriginator().notify(new OctetString("public"), SnmpConstants.linkUp, new VariableBinding[0]);
        }
    }



}
