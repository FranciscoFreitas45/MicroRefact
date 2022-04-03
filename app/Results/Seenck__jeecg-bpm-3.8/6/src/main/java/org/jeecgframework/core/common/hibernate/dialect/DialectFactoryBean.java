package org.jeecgframework.core.common.hibernate.dialect;
 import org.springframework.beans.factory.FactoryBean;
public class DialectFactoryBean implements FactoryBean<Dialect>{

 public  String ORACLE;

 public  String MYSQL;

 public  String SQLSERVER;

 public  String DB2;

 public  String POSTGRES;

 private  Dialect dialect;

 private  String dbType;


public Dialect getObject(){
    if (this.dbType.equals("oracle")) {
        this.dialect = new OracleDialect();
    } else if (this.dbType.equals("sqlserver")) {
        this.dialect = new SQLServer2005Dialect();
    } else if (this.dbType.equals("db2")) {
        this.dialect = new DB2Dialect();
    } else if (this.dbType.equals("mysql")) {
        this.dialect = new MySQLDialect();
    } else if (this.dbType.equals("postgres")) {
        this.dialect = new PostgreSQLDialect();
    } else {
        throw new Exception("没有设置合适的数据库类型");
    }
    return this.dialect;
}


public void setDbType(String dbType){
    this.dbType = dbType;
}


public Class<?> getObjectType(){
    return Dialect.class;
}


public boolean isSingleton(){
    return true;
}


}