/*
 * Copyright 2010 Torokina Networks
 */
package snmp4t;

import java.io.File;
import java.io.IOException;
import org.snmp4j.TransportMapping;
import org.snmp4j.agent.BaseAgent;
import org.snmp4j.agent.CommandProcessor;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.ManagedObject;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.SnmpCommunityMIB;
import org.snmp4j.agent.mo.snmp.SnmpNotificationMIB;
import org.snmp4j.agent.mo.snmp.SnmpTargetMIB;
import org.snmp4j.agent.mo.snmp.StorageType;
import org.snmp4j.agent.mo.snmp.TransportDomains;
import org.snmp4j.agent.mo.snmp.VacmMIB;
import org.snmp4j.agent.security.MutableVACM;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.AuthSHA;
import org.snmp4j.security.PrivAES128;
import org.snmp4j.security.PrivAES192;
import org.snmp4j.security.PrivAES256;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModel;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.transport.TransportMappings;

/**
 *
 * @author jeff.huang
 */
public class MyAgent extends BaseAgent {

    public MyAgent() {
        //super(configURI);
        super(new File("conf.agent"), new File("bootCounter.agent"),
                new CommandProcessor(
                new OctetString(MPv3.createLocalEngineID())));
    }

    public void unregisterManagedObjects(MOGroup moGroup) {
        moGroup.unregisterMOs(server, getContext(moGroup));
    }

    public void start() throws Exception {
        init();
        // This method reads some old config from a file and causes
        // unexpected behavior.
        // loadConfig(ImportModes.REPLACE_CREATE);
        addShutdownHook();
        getServer().addContext(new OctetString("public"));
        finishInit();
        run();
        sendColdStartNotification();
        //unregisterManagedObjects(getSnmpv2MIB());

    }

    /**
     * Clients can register the MO they need
     */
    public void registerManagedObject(ManagedObject mo) {
        try {
            server.register(mo, null);
        } catch (DuplicateRegistrationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void initTransportMappings() throws IOException {
        transportMappings = new TransportMapping[1];
        Address addr = GenericAddress.parse("0.0.0.0/161");
        TransportMapping tm = TransportMappings.getInstance().createTransportMapping(addr);
        transportMappings[0] = tm;
    }

    @Override
    protected void registerManagedObjects() {
    }

    @Override
    protected void unregisterManagedObjects() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void addUsmUser(USM usm) {
    }

    @Override
    protected void addNotificationTargets(SnmpTargetMIB targetMib, SnmpNotificationMIB notificationMib) {
        targetMib.addDefaultTDomains();

        targetMib.addTargetAddress(
                new OctetString("notificationV2c"),
                TransportDomains.transportDomainUdpIpv4,
                new OctetString(new UdpAddress("127.0.0.1/162").getValue()),
                200, 1,
                new OctetString("notify"),
                new OctetString("v2c"),
                StorageType.permanent);
        targetMib.addTargetAddress(
                new OctetString("notificationV2c2"),
                TransportDomains.transportDomainUdpIpv4,
                new OctetString(new UdpAddress("192.168.8.59/162").getValue()),
                200, 1,
                new OctetString("notify"),
                new OctetString("v2c"),
                StorageType.permanent);
        targetMib.addTargetAddress(new OctetString("notificationV3"),
                TransportDomains.transportDomainUdpIpv4,
                new OctetString(new UdpAddress("192.168.8.59/1162").getValue()),
                200, 1,
                new OctetString("notify"),
                new OctetString("v3notify"),
                StorageType.permanent);


        targetMib.addTargetParams(new OctetString("v2c"),
                MessageProcessingModel.MPv2c,
                SecurityModel.SECURITY_MODEL_SNMPv2c,
                new OctetString("secret"),
                SecurityLevel.AUTH_PRIV,
                StorageType.permanent);
        
        targetMib.addTargetParams(new OctetString("v3notify"),
                MessageProcessingModel.MPv3,
                SecurityModel.SECURITY_MODEL_USM,
                new OctetString("v3notify"),
                SecurityLevel.NOAUTH_NOPRIV,
                StorageType.permanent);

        notificationMib.addNotifyEntry(new OctetString("default"),
                new OctetString("notify"),
                SnmpNotificationMIB.SnmpNotifyTypeEnum.inform,
                StorageType.permanent);

    }

    @Override
    protected void addViews(VacmMIB vacm) {
        vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv1,
                new OctetString("secret"),
                new OctetString("v1v2group"),
                StorageType.nonVolatile);

        vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv2c,
                new OctetString("secret"),
                new OctetString("v1v2group"),
                StorageType.nonVolatile);

        vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv1,
                new OctetString("cpublic"),
                new OctetString("v1v2group"),
                StorageType.nonVolatile);

        vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv2c,
                new OctetString("cpublic"),
                new OctetString("v1v2group"),
                StorageType.nonVolatile);

        vacm.addAccess(new OctetString("v1v2group"),
                new OctetString("public"),
                SecurityModel.SECURITY_MODEL_ANY,
                SecurityLevel.NOAUTH_NOPRIV,
                MutableVACM.VACM_MATCH_EXACT,
                new OctetString("fullReadView"),
                new OctetString("fullWriteView"),
                new OctetString("fullNotifyView"),
                StorageType.nonVolatile);

        vacm.addViewTreeFamily(new OctetString("fullReadView"), new OID("1.3"),
                new OctetString(), VacmMIB.vacmViewIncluded,
                StorageType.nonVolatile);
        vacm.addViewTreeFamily(new OctetString("fullWriteView"), new OID("1.3"),
                new OctetString(), VacmMIB.vacmViewIncluded,
                StorageType.nonVolatile);
        vacm.addViewTreeFamily(new OctetString("fullNotifyView"), new OID("1.3"),
                new OctetString(), VacmMIB.vacmViewIncluded,
                StorageType.nonVolatile);


    }

    @Override
    protected void addCommunities(SnmpCommunityMIB communityMIB) {
        Variable[] com2sec = new Variable[]{
            new OctetString("public"), // community name
            new OctetString("cpublic"), // security name
            getAgent().getContextEngineID(), // local engine ID
            new OctetString("public"), // default context name
            new OctetString(), // transport tag
            new Integer32(StorageType.nonVolatile), // storage type
            new Integer32(RowStatus.active) // row status
        };
        MOTableRow row = communityMIB.getSnmpCommunityEntry().createRow(
                new OctetString("public2public").toSubIndex(true), com2sec);
        communityMIB.getSnmpCommunityEntry().addRow(row);

    }
}
