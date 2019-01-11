package dao;

import service.Cell;
import service.Schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static utils.Utils.executeQuery;
import static utils.Utils.executeUpdateQuery;

public class CellDao implements Dao<Cell> {
    String sql;

    public Cell get(long id) {
        return null;
    }

    public List<Cell> getAll() {
        return null;
    }

    public void save(Cell cell) {

    }

    public void update(Cell cell, String[] params) {
        sql = "UPDATE `" + Schema.getInstance().getNameSchema() + "`.`" + cell.getSheet().getSheetName() + "` SET column"+cell.getLocation().getColumnIndex()+"  = \""+params[0] +"\" where id = "+cell.getLocation().getRowIndex()+";";
        try {
            executeUpdateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Cell cell, int[] coordsOfPosition) {

    }

    @Override
    public void create(Cell cell) {
      sql = "Insert INTO `" + Schema.getInstance().getNameSchema() + "`.`" + cell.getSheet().getSheetName() + "` (`column"+cell.getLocation().getColumnIndex()+"`) values(\""+cell.getValue()+"\");";
        try {
            executeUpdateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getOldValue(Cell cell){
        String value="";
        sql = "Select column"+cell.getLocation().getColumnIndex()+"  from `" + Schema.getInstance().getNameSchema() + "`.`" +cell.getSheet().getSheetName() + "  where id = "+cell.getLocation().getRowIndex()+"` ;";
        try {
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
                 value = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int  getRows(Cell cell){
        int count = 0;
        sql = "Select COUNT(*) from `" + Schema.getInstance().getNameSchema() + "`.`" +cell.getSheet().getSheetName() + "` ;";
        try {
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
     return count;
    }

}
