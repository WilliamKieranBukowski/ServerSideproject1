package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Server") //urlPatterns =
public class ServletTest extends HttpServlet {

    public static Connection c;

    // "jdbc:<dialect>://<host name>:<port>/<database name>?currentSchema=<schema name>"
    private static final String url = "jdbc:postgresql://postgres.ckheurx0twws.us-east-1.rds.amazonaws.com:5432/postgres"; //AWS link
    private static final String username = "postgres";
    private static final String password = "Bilbuk.1994";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        //String jsonExample = "<h1>Test Response From Server: GET Succeeded</h1>";
        //resp.setContentType("text/html");
        //resp.setStatus(200); // we can set the status of the response
        resp.getOutputStream().println("Testing client output...");

        //pull username data from userbank
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver"); //necessary, apparently
            c = DriverManager.getConnection(url, username, password);
            st = c.createStatement();
            rs = st.executeQuery("select * from testTable ;");

            while (rs.next()) {  //while there is record in row
                String users = rs.getString("record1");
                try {
                    resp.getOutputStream().println(users);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                resp.getOutputStream().println("Connection closed to db ");
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
