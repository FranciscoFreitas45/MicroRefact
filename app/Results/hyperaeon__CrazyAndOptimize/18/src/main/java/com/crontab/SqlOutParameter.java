package com.crontab;
 public class SqlOutParameter extends SqlParameter{

 private  ResultSetHandler resultSetHandler;

public SqlOutParameter(int sqlType) {
    super(sqlType);
}public SqlOutParameter(String name, int sqlType) {
    super(name, sqlType);
}public SqlOutParameter(String name, int sqlType, ResultSetHandler resultSetHandler) {
    super(name, sqlType);
    this.resultSetHandler = resultSetHandler;
}
public boolean isResultSetSupported(){
    return this.resultSetHandler != null;
}


@Override
public String toString(){
    return "SqlOutParameter [sqlType=" + getSqlType() + ", name=" + getName() + "]";
}


public ResultSetHandler getResultSetHandler(){
    return resultSetHandler;
}


}