package com.crontab;
 public class SqlInParameter extends SqlParameter{

 private  Object value;

/**
 * Create a new anonymous SqlInParameter.
 * @param sqlType SQL type of the parameter according to java.sql.Types
 * @param value the value object
 */
public SqlInParameter(int sqlType, Object value) {
    super(sqlType);
    this.value = value;
}/**
 * Create a new SqlInParameter.
 * @param name name of the parameter
 * @param sqlType SQL type of the parameter according to java.sql.Types
 * @param value the value object
 */
public SqlInParameter(String name, int sqlType, Object value) {
    super(name, sqlType);
    this.value = value;
}
public Object getValue(){
    return this.value;
}


@Override
public String toString(){
    return "SqlInParameter [sqlType=" + getSqlType() + ", name=" + getName() + ", value=" + value + "]";
}


}