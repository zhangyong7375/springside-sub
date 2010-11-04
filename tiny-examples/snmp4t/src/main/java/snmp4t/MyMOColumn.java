package snmp4t;

import org.snmp4j.agent.mo.MOColumn;
import org.snmp4j.smi.SMIConstants;

public class MyMOColumn extends MOColumn {

    public MyMOColumn(int columnID) {
        super(columnID, SMIConstants.SYNTAX_INTEGER32);
    }
}
