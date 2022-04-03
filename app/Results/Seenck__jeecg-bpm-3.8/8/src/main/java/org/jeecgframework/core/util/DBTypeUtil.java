package org.jeecgframework.core.util;
 import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
public class DBTypeUtil {

 private  Logger log;


public String getDBType(){
    String retStr = "";
    ApplicationContext ctx = ApplicationContextUtil.getContext();
    if (ctx == null) {
        // 如果ctx为空，则服务器异常了
        return retStr;
    } else {
        org.springframework.orm.hibernate4.LocalSessionFactoryBean sf = (org.springframework.orm.hibernate4.LocalSessionFactoryBean) ctx.getBean("&sessionFactory");
        String dbdialect = sf.getHibernateProperties().getProperty("hibernate.dialect");
        log.debug(dbdialect);
        if (dbdialect.equals("org.hibernate.dialect.MySQLDialect")) {
            retStr = "mysql";
        } else if (dbdialect.contains("Oracle")) {
            // oracle有多个版本的方言
            retStr = "oracle";
        } else if (dbdialect.equals("org.hibernate.dialect.SQLServerDialect")) {
            retStr = "sqlserver";
        } else if (dbdialect.equals("org.hibernate.dialect.PostgreSQLDialect")) {
            retStr = "postgres";
        } else if (dbdialect.equals("org.jeecgframework.core.common.hibernate.dialect.MySQLServer2008Dialect")) {
            retStr = "sqlserver";
        }
        return retStr;
    }
}


}