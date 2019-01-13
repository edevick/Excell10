package service;

import utils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static utils.Utils.*;

public final class Schema {

    private volatile static Schema _instance = new Schema();

    Connection con = null;
    String nameSchema = "";

    public Schema() {
    }

    public static Schema getInstance() {
        return _instance;
    }

    public void setNameSchema(String nameSchema) {
        this.nameSchema = nameSchema;
    }

    public String getNameSchema() {
        return nameSchema;
    }

    public void createSchema(String nameSchema) {

        setNameSchema(nameSchema);
        connect();
        pool = new DBPool(url, user, password);
        PreparedStatement preparedStatement = null;
        try {
            Connection con = pool.getConnection();
            preparedStatement = con.prepareStatement("CREATE Database IF NOT EXISTS  `Excell` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ");
            preparedStatement.execute();
            System.out.println("Successfully created schema");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePull();
        }
    }

    private void connect() {

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/1.properties");
            property.load(fis);

            url = property.getProperty("url");
            user = property.getProperty("user");
            password = property.getProperty("password");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    private void closePull() {
        try {
            if (con != null)
                pool.putConnection(con); // кладем соединение обратно в пул
        } catch (SQLException e) {
            //ignore
        }
    }

    public void dropSchema() {
        connect();
        pool = new DBPool(url, user, password);
        PreparedStatement preparedStatement = null;
        try {
            Connection con = pool.getConnection();
            preparedStatement = con.prepareStatement("DROP DATABASE IF EXISTS `Excell` ");
            preparedStatement.execute();
            System.out.println("Successfully dropted schema");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePull();
        }
    }


    public void showTables() {
        List<Sheet> sheets = new ArrayList<>();
        String sql = "SELECT table_name FROM information_schema.tables where table_schema = \"" + Schema.getInstance().getNameSchema() + "\";";
        Connection con = null;
        try {
            con = pool.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Tables:");
            while (rs.next()) {
                sheets.add(new Sheet(rs.getString(1)));
                System.out.println("- " + rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showData(Sheet sheet,int quantity){
        List<Sheet> datas = new ArrayList<>();
        String sql = " select *  FROM `" + Schema.getInstance().getNameSchema() + "`.`" + sheet.getSheetName() + "`;";
        Connection con = null;
        try {
            con = pool.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Datas:");
            String message ="";
            int i = 0;
            while (rs.next()) {
              do{
                    if (i == quantity) {
                        message = message+'\n';
                        i=0;
                        break;
                    }

                    i++;
                    message = message + rs.getString(i) + " ";

                }while (i!=quantity);

            }
            System.out.println(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
