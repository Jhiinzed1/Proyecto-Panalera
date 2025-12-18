package arg.org.centro35.Panalera.connectors;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    private static String url =
    "jdbc:sqlite:Escritorio/Pañalera/data/pañalera.db";
    private static String user = "root";
    private static String pass = "";
    private static Connection conn = null;
    
    private Connector() {
    }

    public static String getUrl(){
        return url;
    }

    public synchronized static Connection
    getConeConnection(){
        try {
            if (conn == null || conn.isClosed()) {
              conn= DriverManager.getConnection(url, user, pass);  
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
