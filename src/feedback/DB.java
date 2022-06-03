
package feedback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    public Connection con;
    public Statement stmt;
    public String query;

    public DB() {

        con = null;
        stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/feed","root", "root21485");

            if (con != null) {
                 System.out.println("Connected");
                stmt = con.createStatement();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

    }

   
    
}

