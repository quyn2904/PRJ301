/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Goby
 */
public class DBHelper implements Serializable {

    public static Connection getConnection()
            throws /*ClassNotFoundException*/ SQLException, NamingException {
//        //1.Load driver (driver phai co san o ben trong ung dung cua minh)
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.Create connection String
//        String url = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=PRJSE1850";
//        //3.Open connection 
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        return con;

        //1.get current context
        Context currentContext = new InitialContext();
        //2.Lookup tomcat context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        //3.Lookup DS
        DataSource ds = (DataSource) tomcatContext.lookup("DS007");
        //4.Open connection from DS
        Connection con = ds.getConnection();
        
        return con;
    }
}
