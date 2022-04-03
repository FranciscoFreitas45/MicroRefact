package com.gs.common.entity;
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBManager {


public ResultSet query(Connection conn,String sql){
    ResultSet rs = null;
    try {
        // 3、通过连接对象创建一个语句对象stmt，用来执行sql语句
        Statement stmt = conn.createStatement();
        // 4、执行sql语句，得到一个rs（结果集对象）
        rs = stmt.executeQuery(sql);
    } catch (Exception e) {
        // 错误处理，暂时不用理会
        e.printStackTrace();
    }
    // 将查询得到的结果集对象返回
    return rs;
}


public int update(Connection conn,String sql){
    // 执行sql语句前先连接到数据库
    Statement stmt = null;
    int i = 0;
    try {
        // 通过连接对象创建一个语句对象stmt，用来执行sql语句
        stmt = conn.createStatement();
        // 执行更新语句，并返回影响了多少行
        i = stmt.executeUpdate(sql);
    } catch (Exception e) {
        // 错误处理，暂时不用理会
        e.printStackTrace();
    } finally {
        try {
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    return i;
}


public Connection mysql(String host,String database,String user,String pass){
    String url = "jdbc:mysql://" + host + "/" + database + "?useUnicode=true&characterEncoding=UTF-8";
    return mysql(url, user, pass);
}


public void close(Connection conn,Statement stmt,ResultSet rs){
    try {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


public Connection mssql(String url,String user,String pass){
    Connection conn = null;
    try {
        // 1、加载连接驱动
        // "jdbc:odbc:bookdemo"
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        // 2、连接到数据库（获得连接对象）
        // 通过连接管理器(DriverManager)类的一个方法来获得连接对象，里面的参数表示我们连接到数据源bookdemo
        conn = DriverManager.getConnection(url, user, pass);
    } catch (ClassNotFoundException e) {
        // 以堆栈的方式将错误信息打印出来
        e.printStackTrace();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    // 将连接对象返回
    return conn;
}


}