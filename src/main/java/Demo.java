import service.Schema;
import service.Sheet;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Schema schema = Schema.getInstance();
        schema.createSchema("Excell");
        Sheet sheet = new Sheet("Sheet");
        sheet.createSheet();
        sheet.addColumn(0);
        sheet.deleteColumn(1,2);
        List<Sheet> tables = new ArrayList();
        int quantitySheets = 5;
        for (int i= 0; i< quantitySheets;i++) {
           tables.add(new Sheet("Sheet"+(i+1)));
           tables.get(i).createSheet();
        }

        schema.showTables();

    }
}
