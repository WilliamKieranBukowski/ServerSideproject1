package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    public static Connection c;

    // "jdbc:<dialect>://<host name>:<port>/<database name>?currentSchema=<schema name>"
    private static final String url =
            "jdbc:postgresql://postgres.ckheurx0twws.us-east-1.rds.amazonaws.com:5432/postgres"; //AWS link
    private static final String username = "postgres";
    private static final String password = "Bilbuk.1994";


    // this method creates a single instance if needed otherwise, returns the existing one.
    public static Connection getConnection() throws SQLException {

        if(c == null || c.isClosed()){
            // url, username, password ->
            //            Class.forName("org.postgresql.Driver"); <- this may be necessary to solve no suitable driver
            c = DriverManager.getConnection(url, username, password);
        }
        if(c != null){
            System.out.println("Connection established");
        }

        return c;
    }

    public ConnectionService(){}
}
