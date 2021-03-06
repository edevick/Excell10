package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Utils {
    public static DBPool pool;
    public static String url = "";
    public static String user = "";
    public static String password = "";
    public static final int DEFAULT_NUMBER_COLUMNS = 0;
    public static final int DEFAULT_NUMBER_ROWS = 0;

    static Properties readPropertiesForDB(String filename) {
        Properties properties = new Properties();
        if (!new File(filename).exists()) {
            System.out.println("Properties file do not exist.");
            System.exit(1);
        } else {


            try (FileReader fileReader = new FileReader(filename)) {
                properties.load(fileReader);
                //  System.out.println(Properties.);
            } catch (IOException e) {
                System.out.println("Error with reading file properties.");
                e.printStackTrace();
            }
        }
        return properties;

    }

    public static int executeUpdateQuery(String sql) throws SQLException {
        Connection con = pool.getConnection();
        Statement statement = con.createStatement();
        return statement.executeUpdate(sql);

    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection con = pool.getConnection();
        Statement statement = con.createStatement();
        return statement.executeQuery(sql);

    }
}