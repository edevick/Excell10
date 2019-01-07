package dao;

import service.Param;
import service.Schema;
import service.Sheet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static service.Param.*;
import static utils.Utils.pool;

public class SheetDao implements Dao<Sheet> {
    String sql;

    public Sheet get(long id) {
        return null;
    }

    public List<Sheet> getAll() {
        return null;
    }


    public void save(Sheet sheet) {

    }

    public List<String> getColumns(Sheet sheet){
        List<String> columnNames = new ArrayList();
      sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA =\""+ Schema.getInstance().getNameSchema() +"\" and table_name = \""+sheet.getSheetName()+"\" ;";
        try {
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
               columnNames.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }


    public void update(Sheet sheet, String[] params) {
        String message="";
        if (params[0].equals(ADD_ROW.toString())) {

        }

        if (params[0].equals(ADD_COLUMN.toString())) {
           sql = "ALTER TABLE `" + Schema.getInstance().getNameSchema() + "`.`" + sheet.getSheetName() + "` ADD " + params[1] + " VARCHAR(128);";
           message = "Added column " + params[1]+ ". Table " + sheet.getSheetName()+ " is updated";
        }

        if (params[0].equals(DELETE_ROW.toString())) {

        }

        if (params[0].equals(DELETE_COLUMN.toString())) {
          sql = "ALTER TABLE `" + Schema.getInstance().getNameSchema() + "`.`" + sheet.getSheetName() + "`  DROP COLUMN " + params[1];
          message = "Deleted column " + params[1]+ ". Table " + sheet.getSheetName()+ " is updated";
        }

        try {
            executeUpdateQuery(sql);
            System.out.println(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Sheet sheet, int[] coordsOfPosition) {
        sql = "drop table if exists `" + Schema.getInstance().getNameSchema() + "`.`" + sheet.getSheetName() + "`;";
        try {
            executeUpdateQuery(sql);
            System.out.println("Successfully dropted table " + sheet.getSheetName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Sheet sheet) {
        sql = "create table if not exists `" + Schema.getInstance().getNameSchema() + "`.`" + sheet.getSheetName() + "` (`id` INT NOT NULL AUTO_INCREMENT,primary key(`id`));";
        try {
            executeUpdateQuery(sql);
            System.out.println("Successfully created table " + sheet.getSheetName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int executeUpdateQuery(String sql) throws SQLException {
        Connection con = pool.getConnection();
        Statement statement = con.createStatement();
        return statement.executeUpdate(sql);

    }

    private ResultSet executeQuery(String sql) throws SQLException{
        Connection con = pool.getConnection();
        Statement statement = con.createStatement();
        return  statement.executeQuery(sql);

    }
}
