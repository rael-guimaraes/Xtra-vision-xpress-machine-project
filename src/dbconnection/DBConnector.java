/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author raelg
 */
public class DBConnector {
    
        public static Connection getConnection() {
        Connection conn = null;
        
        try {

            String dbServer = "jdbc:mysql://apontejaj.com:3306/Rael_2019216?useSSL=false";
            String dbUser = "Rael_2019216";
            String dbPassword = "2019216";

            // Get a connection to the database
            conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;

    }
    
}
