package snmp4t;

import java.util.Random;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PC;
import org.snmp4j.agent.mo.MOMutableTableModel;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

public class TableUpdateRunnable implements Runnable {

    private MOTable table;

    public TableUpdateRunnable(MOTable table) {
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    return;
                }
                doUpdate();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void doUpdate() throws Exception {
        Random rand = new Random();
        int rowNum = rand.nextInt(15) + 5;
        int rowOidSize = rand.nextInt(5) + 5; //5 to 10
        MOMutableTableModel model = (MOMutableTableModel) table.getModel();
        model.clear();
        for (int k = 0; k < rowNum; ++k) {
            StringBuilder oidSb = new StringBuilder(rand.nextInt(256));
            for (int m = 0; m < rowOidSize - 1; ++m) {
                oidSb.append(".").append(String.valueOf(rand.nextInt(256)));
            }
            model.addRow(new DefaultMOMutableRow2PC(new OID(oidSb.toString()), new Variable[]{new Integer32(k * rowNum)}));
        }
    }
}
