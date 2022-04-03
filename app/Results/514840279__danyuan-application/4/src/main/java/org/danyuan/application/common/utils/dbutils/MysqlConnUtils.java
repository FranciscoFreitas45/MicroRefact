package org.danyuan.application.common.utils.dbutils;
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MysqlConnUtils {

 private  String URL;

 private  String USER;

 private  String PASSWORD;


public Connection getConnection(String url,String user,String password){
    try {
        return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


public void close(ResultSet rs){
    try {
        if (rs != null) {
            rs.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}