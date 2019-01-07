package service;

import dao.CellDao;

public class Cell {
    Location location;
    String value;
    Sheet sheet;
    CellDao cellDao;
    String[]params = new String[1];

    public Cell(Sheet sheet,Location location) {
        this.location = location;
        this.sheet = sheet;
        this.value = "";
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
            params[0] = this.getValue()+value;
        }
        cellDao.update(this,params);
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}
