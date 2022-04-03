package mondrian.rolap.SqlStatement;
 import mondrian.olap;
import mondrian.olap.Util.Functor1;
import mondrian.server.Execution;
import mondrian.server.Locus;
import mondrian.server.monitor;
import mondrian.server.monitor.SqlStatementEvent.Purpose;
import mondrian.util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import javax.sql.DataSource;
public class MyDelegatingInvocationHandler extends DelegatingInvocationHandler{

 private  SqlStatement sqlStatement;

/**
 * Creates a MyDelegatingInvocationHandler.
 *
 * @param sqlStatement SQL statement
 */
MyDelegatingInvocationHandler(SqlStatement sqlStatement) {
    this.sqlStatement = sqlStatement;
}
public Object getTarget(){
    final ResultSet resultSet = sqlStatement.getResultSet();
    if (resultSet == null) {
        throw new InvocationTargetException(new SQLException("Invalid operation. Statement is closed."));
    }
    return resultSet;
}


public void close(){
    sqlStatement.close();
}


}