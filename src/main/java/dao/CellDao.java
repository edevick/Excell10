package dao;

import service.Cell;
import service.Schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static utils.Utils.executeQuery;

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

    }

    public void delete(Cell cell, int[] coordsOfPosition) {

    }

    @Override
    public void create(Cell cell) {
        sql = "INSERT INTO `" + Schema.getInstance().getNameSchema() + "`.`" + cell.getSheet().getSheetName() + "` () values();";
    }

    public int  getIdRow(Cell cell){
        int i=1;
        int rowLocation = cell.getLocation().getRowIndex();
        int idRow = 0;
        sql = "Select * `" + Schema.getInstance().getNameSchema() + "`.`" +cell.getSheet().getSheetName() + "` ;";
        try {
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
                if (i==rowLocation) {
                   idRow = rs.getInt(1);
                   break;
                }
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
     return idRow;
    }

}
