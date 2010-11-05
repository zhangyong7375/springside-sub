/*
 * Copyright 2010 Torokina Networks
 */

package snmp4t;

import org.snmp4j.agent.mo.snmp.SnmpTargetMIB;
import org.snmp4j.agent.mo.snmp.StorageType;
import org.snmp4j.agent.mo.snmp.TransportDomains;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;

/**
 *
 * @author jeff.huang
 */
public class MyTrapSender implements Runnable {
    private MyAgent agent;
    private int counter = 0;

    public MyTrapSender(MyAgent agent) {
        this.agent = agent;
    }



    @Override
    public void run() {
        doSender();
    }

    private void doSender() {
        while(true){
            SnmpTargetMIB targetMib = agent.getSnmpTargetMIB();
            ++counter;

            if((counter % 3) == 0){
                targetMib.removeTargetAddress(new OctetString("notificationV2c2"));
            }else if((counter % 3) == 1){
                targetMib.addTargetAddress(
                new OctetString("notificationV2c2"),
                TransportDomains.transportDomainUdpIpv4,
                new OctetString(new UdpAddress("192.168.8.59/162").getValue()),
                200, 1,
                new OctetString("notify"),
                new OctetString("v2c"),
                StorageType.permanent);
            }
            //long sleep = new Random().nextInt(5000) + 1000;
            long sleep = 2000;
            try{
                Thread.sleep(sleep);
            }catch(InterruptedException ex){
                return;
            }
            agent.getNotificationOriginator().notify(new OctetString("public"), SnmpConstants.linkUp, new VariableBinding[] {
                new VariableBinding(SnmpConstants.sysName, new OctetString("Hello = " + counter))
            });
        }
    }



}
