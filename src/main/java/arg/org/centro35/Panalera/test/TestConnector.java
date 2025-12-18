package arg.org.centro35.Panalera.test;

import java.sql.Connection;
import java.sql.ResultSet;

import arg.org.centro35.Panalera.connectors.Connector;

public class TestConnector {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
        Connection conn=Connector.getConeConnection();
        try (ResultSet rs=conn.createStatement().executeQuery("select 'hola'")){
            if(rs.next()){
                System.out.println(ANSI_GREEN+"OK - se conecto a "+rs.getString(1));
            }else{
                System.out.println(ANSI_RED+"ERROR - no se pudo conectar a la BD  ");
            }
        } catch (Exception e) {
            System.out.println(ANSI_RED+"ERROR - no se pudo conectar a la BD  ");
            System.out.println(e);
        }
        System.out.println(ANSI_RESET);

    }
}
