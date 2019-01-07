package service;

import dao.SheetDao;

import java.util.ArrayList;
import java.util.List;

import static service.Param.*;

public class Sheet {
    String sheetName;
    List<String> columnNames = new ArrayList();
    SheetDao sheetDao = new SheetDao();

    public Sheet(String sheetName) {
        this.sheetName = sheetName;
    }

    public void createSheet() {
        sheetDao.create(this);
    }


    public void dropSheet() {
        int[] position = new int[1];
        position[0] = 0;
        //TODO : How to replace empty array?
        sheetDao.delete(this, position);
    }

    public List<String> getColumnNames() {
        return sheetDao.getColumns(this);
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public SheetDao getSheetDao() {
        return sheetDao;
    }

    public void setSheetDao(SheetDao sheetDao) {
        this.sheetDao = sheetDao;
    }

    public void addColumn(int quantity) {
        if (quantity < 1) {
            System.out.println("WARNING: Quantity should be bigger than 1!");
            return;
        }
        String[] params = new String[2];
        params[0] = ADD_COLUMN.toString();
        for (int i = 0; i < quantity; i++) {
            columnNames = getColumnNames();
            String nameColumn = "Column" + columnNames.size();
            columnNames.add(nameColumn);
            //TODO: do smth with not convenient array
            params[1] = nameColumn;  //nameColumn of column
            sheetDao.update(this, params);
        }
    }

    public void addRow(int quantity) {
        if (quantity < 1) {
            System.out.println("WARNING: Quantity should be bigger than 1!");
            return;
        }
        String[] params = new String[2];
        params[0] = ADD_ROW.toString();
        for (int i = 0; i < quantity; i++) {
            params[1] = Integer.toString(i);
            sheetDao.update(this, params);
        }
    }

    public void deleteColumn(int indexFrom, int indexTo) {
        if (indexFrom>indexTo){
            int temp = indexTo;
            indexTo = indexFrom;
            indexFrom = temp;
        }

        if ((indexFrom <1)|| (indexTo <1)){
            System.out.println("WARNING: Indexes should be bigger than 0!");
            return;
        }
        String[] params = new String[2];
        params[0] = DELETE_COLUMN.toString();
        for (int i = indexFrom; i <= indexTo; i++) {
            params[1] = getColumnNames().get(i);
            sheetDao.update(this, params);
        }
    }

    public void deleteRow(int indexFrom, int indexTo) {
        if ((indexFrom <1)|| (indexTo <1)){
            System.out.println("WARNING: Indexes should be bigger than 0!");
            return;
        }

        if (indexFrom>indexTo){
            int temp = indexTo;
            indexTo = indexFrom;
            indexFrom = temp;
        }
        String[] params = new String[2];
        params[0] = DELETE_ROW.toString();
        for (int i = indexFrom; i <= indexTo; i++) {
            params[1] = Integer.toString(i);
            sheetDao.update(this, params);
        }
    }
}
