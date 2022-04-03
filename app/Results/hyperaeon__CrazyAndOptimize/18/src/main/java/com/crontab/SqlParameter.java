package com.crontab;
 public class SqlParameter {

 private  int sqlType;

 private  String name;

/**
 * Create a new anonymous SqlParameter, supplying the SQL type.
 * @param sqlType SQL type of the parameter according to java.sql.Types
 */
public SqlParameter(int sqlType) {
    this.sqlType = sqlType;
}/**
 * Create a new SqlParameter, supplying name and SQL type.
 * @param name name of the parameter
 * @param sqlType SQL type of the parameter according to java.sql.Types
 */
public SqlParameter(String name, int sqlType) {
    this.name = name;
    this.sqlType = sqlType;
}
public String getName(){
    return this.name;
}


public int getSqlType(){
    return this.sqlType;
}


@Override
public String toString(){
    return "SqlParameter [sqlType=" + sqlType + ", name=" + name + "]";
}


}