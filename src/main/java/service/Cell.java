package service;

import dao.CellDao;

public class Cell {
    Location location;
    String value;
    Sheet sheet;
    CellDao cellDao = new CellDao();
    String[]params = new String[1];

    public Cell(Sheet sheet,Location location) {
        this.location = location;
        this.sheet = sheet;
        this.value = "";
    }
    public Cell (Sheet sheet,String value){
        this.sheet = sheet;
        this.value = value;
        this.location = getDefaultLocation();
        cellDao.create(this);
    }

    private Location getDefaultLocation(){
        int rowId = cellDao.getRows(this);
        return new  Location(rowId,1);
    }
    public Location getLocation() {

        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        params[0] = value;
        cellDao.create(this);
    }

    /**
     *
     * @param value
     * @param attribute
     * 0- to replace existing value
     * 1- to add to existing value
     */
    public void updateCell(String value,int attribute){

        if (attribute == 0 ){
            params[0] = value;
        }else {
            params[0] = getOldValue()+value;
        }
        cellDao.update(this,params);
    }

    private String getOldValue(){
        return  cellDao.getOldValue(this);
    }
    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}
