package DTO;
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
public class SqlStatement {

 private  String TIMING_NAME;

 private  AtomicLong ID_GENERATOR;

 private  Semaphore querySemaphore;

 private  DataSource dataSource;

 private  Connection jdbcConnection;

 private  ResultSet resultSet;

 private  String sql;

 private  List<Type> types;

 private  int maxRows;

 private  int firstRowOrdinal;

 private  Locus locus;

 private  int resultSetType;

 private  int resultSetConcurrency;

 private  boolean haveSemaphore;

 public  int rowCount;

 private  long startTimeNanos;

 private  long startTimeMillis;

 private  List<Accessor> accessors;

 private  State state;

 private  long id;

 private  Functor1<Void,Statement> callback;

 private  SqlStatement sqlStatement;

 private  SqlStatementEvent.Purpose purpose;

 private  int cellRequestCount;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://20";

/**
 * Creates a SqlStatement.
 *
 * @param dataSource Data source
 * @param sql SQL
 * @param types Suggested types of columns, or null;
 *     if present, must have one element for each SQL column;
 *     each not-null entry overrides deduced JDBC type of the column
 * @param maxRows Maximum rows; <= 0 means no maximum
 * @param firstRowOrdinal Ordinal of first row to skip to; <= 0 do not skip
 * @param locus Execution context of this statement
 * @param resultSetType Result set type
 * @param resultSetConcurrency Result set concurrency
 */
public SqlStatement(DataSource dataSource, String sql, List<Type> types, int maxRows, int firstRowOrdinal, Locus locus, int resultSetType, int resultSetConcurrency, Util.Functor1<Void, Statement> callback) {
    this.callback = callback;
    this.id = ID_GENERATOR.getAndIncrement();
    this.dataSource = dataSource;
    this.sql = sql;
    this.types = types;
    this.maxRows = maxRows;
    this.firstRowOrdinal = firstRowOrdinal;
    this.locus = locus;
    this.resultSetType = resultSetType;
    this.resultSetConcurrency = resultSetConcurrency;
}
public SqlStatementEvent.Purpose getPurpose(){
    if (locus instanceof StatementLocus) {
        return ((StatementLocus) locus).purpose;
    } else {
        return SqlStatementEvent.Purpose.OTHER;
    }
}


public Object getTarget(){
    final ResultSet resultSet = sqlStatement.getResultSet();
    if (resultSet == null) {
        throw new InvocationTargetException(new SQLException("Invalid operation. Statement is closed."));
    }
    return resultSet;
}


public ResultSet getResultSet(){
    return resultSet;
}


public Object get()


public List<Accessor> getAccessors(){
    return accessors;
}


public ResultSet getWrappedResultSet(){
    return (ResultSet) Proxy.newProxyInstance(null, new Class<?>[] { ResultSet.class }, new MyDelegatingInvocationHandler(this));
}


public int getCellRequestCount(){
    if (locus instanceof StatementLocus) {
        return ((StatementLocus) locus).cellRequestCount;
    } else {
        return 0;
    }
}


public RuntimeException handle(Throwable e){
    RuntimeException runtimeException = Util.newError(e, locus.message + "; sql=[" + sql + "]");
    try {
        close();
    } catch (Throwable t) {
    // ignore
    }
    return runtimeException;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/handle"))

.queryParam("e",e)
;
RuntimeException aux = restTemplate.getForObject(builder.toUriString(),RuntimeException.class);
return aux;
}


public void close(){
    sqlStatement.close();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/close"))

;
restTemplate.put(builder.toUriString(),null);
}


}