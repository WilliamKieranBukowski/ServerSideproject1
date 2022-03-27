package controller;

import DB.ConnectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/Server") //urlPatterns =
public class ServletTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //String jsonExample = "<h1>Test Response From Server: GET Succeeded</h1>";
        //resp.setContentType("text/html");
        //resp.setStatus(200); // we can set the status of the response
        //resp.getOutputStream().println(jsonExample);

        //pull username data from userbank
        try {
            ConnectionService.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement st = null;
        ResultSet rs = null;
        try {
            st = ConnectionService.c.createStatement();
            rs = st.executeQuery("select * from testTable ;");

            while (rs.next()) {  //while there is record in row
                String users = rs.getString("record1");
                resp.getOutputStream().println(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                System.out.print("Connection is closing");
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
