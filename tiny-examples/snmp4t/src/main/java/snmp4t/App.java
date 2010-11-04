package snmp4t;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PC;
import org.snmp4j.agent.mo.DefaultMOTable;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOColumn;
import org.snmp4j.agent.mo.MOMutableTableModel;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableIndex;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.MOTableSubIndex;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.Variable;

/**
 * Hello world!
 *
 */
public class App {

    public static MOTable buildTable(OID rootOid) {
        MOTableSubIndex[] subIndexes = new MOTableSubIndex[]{new MOTableSubIndex(
            SMIConstants.SYNTAX_INTEGER)};
        MOTableIndex indexDef = new MOTableIndex(subIndexes, false);

        DefaultMOTable table = new DefaultMOTable(rootOid, indexDef, new MOColumn[]{new MyMOColumn(1)});
        table.setVolatile(true);
        return table;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world");
        MyAgent agent = new MyAgent();
        agent.start();

        agent.registerManagedObject(new MOScalar(new OID(".1.3.6.1.4.1.12111.1.1.0"), MOAccessImpl.ACCESS_READ_ONLY, new Gauge32(1)));
        MOTable table = buildTable(new OID(".1.3.6.1.4.1.12111.2"));
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new TableUpdateRunnable(table));
        service.submit(new MyTrapSender(agent));
        service.shutdown();
        agent.registerManagedObject(table);

        Thread.sleep(Long.MAX_VALUE);
    }
}
