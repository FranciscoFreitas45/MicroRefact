package mondrian.rolap;
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


public RuntimeException handle(Throwable e){
    RuntimeException runtimeException = Util.newError(e, locus.message + "; sql=[" + sql + "]");
    try {
        close();
    } catch (Throwable t) {
    // ignore
    }
    return runtimeException;
}


public List<Accessor> getAccessors(){
    return accessors;
}


public void execute(){
    assert state == State.FRESH : "cannot re-execute";
    state = State.ACTIVE;
    Counters.SQL_STATEMENT_EXECUTE_COUNT.incrementAndGet();
    Counters.SQL_STATEMENT_EXECUTING_IDS.add(id);
    String status = "failed";
    Statement statement = null;
    try {
        // Check execution state
        locus.execution.checkCancelOrTimeout();
        this.jdbcConnection = dataSource.getConnection();
        querySemaphore.acquire();
        haveSemaphore = true;
        // Trace start of execution.
        if (RolapUtil.SQL_LOGGER.isDebugEnabled()) {
            StringBuilder sqllog = new StringBuilder();
            sqllog.append(id).append(": ").append(locus.component).append(": executing sql [");
            if (sql.indexOf('\n') >= 0) {
                // SQL appears to be formatted as multiple lines. Make it
                // start on its own line.
                sqllog.append("\n");
            }
            sqllog.append(sql);
            sqllog.append(']');
            RolapUtil.SQL_LOGGER.debug(sqllog.toString());
        }
        // Execute hook.
        RolapUtil.ExecuteQueryHook hook = RolapUtil.getHook();
        if (hook != null) {
            hook.onExecuteQuery(sql);
        }
        // Check execution state
        locus.execution.checkCancelOrTimeout();
        startTimeNanos = System.nanoTime();
        startTimeMillis = System.currentTimeMillis();
        if (resultSetType < 0 || resultSetConcurrency < 0) {
            statement = jdbcConnection.createStatement();
        } else {
            statement = jdbcConnection.createStatement(resultSetType, resultSetConcurrency);
        }
        if (maxRows > 0) {
            statement.setMaxRows(maxRows);
        }
        // First make sure to register with the execution instance.
        if (getPurpose() != Purpose.CELL_SEGMENT) {
            locus.execution.registerStatement(locus, statement);
        } else {
            if (callback != null) {
                callback.apply(statement);
            }
        }
        locus.getServer().getMonitor().sendEvent(new SqlStatementStartEvent(startTimeMillis, id, locus, sql, getPurpose(), getCellRequestCount()));
        this.resultSet = statement.executeQuery(sql.replaceAll("\"", ""));
        // skip to first row specified in request
        this.state = State.ACTIVE;
        if (firstRowOrdinal > 0) {
            if (resultSetType == ResultSet.TYPE_FORWARD_ONLY) {
                for (int i = 0; i < firstRowOrdinal; ++i) {
                    if (!this.resultSet.next()) {
                        this.state = State.DONE;
                        break;
                    }
                }
            } else {
                if (!this.resultSet.absolute(firstRowOrdinal)) {
                    this.state = State.DONE;
                }
            }
        }
        long timeMillis = System.currentTimeMillis();
        long timeNanos = System.nanoTime();
        final long executeNanos = timeNanos - startTimeNanos;
        final long executeMillis = executeNanos / 1000000;
        Util.addDatabaseTime(executeMillis);
        status = ", exec " + executeMillis + " ms";
        locus.getServer().getMonitor().sendEvent(new SqlStatementExecuteEvent(timeMillis, id, locus, sql, getPurpose(), executeNanos));
        // Compute accessors. They ensure that we use the most efficient
        // method (e.g. getInt, getDouble, getObject) for the type of the
        // column. Even if you are going to box the result into an object,
        // it is better to use getInt than getObject; the latter might
        // return something daft like a BigDecimal (does, on the Oracle JDBC
        // driver).
        accessors.clear();
        for (Type type : guessTypes()) {
            accessors.add(createAccessor(accessors.size(), type));
        }
    } catch (Throwable e) {
        status = ", failed (" + e + ")";
        // This statement was leaked to us. It is our responsibility
        // to dispose of it.
        Util.close(null, statement, null);
        // Now handle this exception.
        throw handle(e);
    } finally {
        RolapUtil.SQL_LOGGER.debug(id + ": " + status);
        if (RolapUtil.LOGGER.isDebugEnabled()) {
            RolapUtil.LOGGER.debug(locus.component + ": executing sql [" + sql + "]" + status);
        }
    }
}


public void close(){
    sqlStatement.close();
}


public Accessor createAccessor(int column,Type type){
    final int columnPlusOne = column + 1;
    switch(type) {
        case OBJECT:
            return new Accessor() {

                public Object get() throws SQLException {
                    return resultSet.getObject(columnPlusOne);
                }
            };
        case STRING:
            return new Accessor() {

                public Object get() throws SQLException {
                    return resultSet.getString(columnPlusOne);
                }
            };
        case INT:
            return new Accessor() {

                public Object get() throws SQLException {
                    final int val = resultSet.getInt(columnPlusOne);
                    if (val == 0 && resultSet.wasNull()) {
                        return null;
                    }
                    return val;
                }
            };
        case LONG:
            return new Accessor() {

                public Object get() throws SQLException {
                    final long val = resultSet.getLong(columnPlusOne);
                    if (val == 0 && resultSet.wasNull()) {
                        return null;
                    }
                    return val;
                }
            };
        case DOUBLE:
            return new Accessor() {

                public Object get() throws SQLException {
                    final double val = resultSet.getDouble(columnPlusOne);
                    if (val == 0 && resultSet.wasNull()) {
                        return null;
                    }
                    return val;
                }
            };
        default:
            throw Util.unexpected(type);
    }
}


public List<Type> guessTypes(){
    final ResultSetMetaData metaData = resultSet.getMetaData();
    final int columnCount = metaData.getColumnCount();
    assert this.types == null || this.types.size() == columnCount;
    List<Type> types = new ArrayList<Type>();
    for (int i = 0; i < columnCount; i++) {
        final Type suggestedType = this.types == null ? null : this.types.get(i);
        // There might not be a schema constructed yet,
        // so watch out here for NPEs.
        RolapSchema schema = locus.execution.getMondrianStatement().getMondrianConnection().getSchema();
        if (suggestedType != null) {
            types.add(suggestedType);
        } else if (schema != null && schema.getDialect() != null) {
            types.add(schema.getDialect().getType(metaData, i));
        } else {
            types.add(Type.OBJECT);
        }
    }
    return types;
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


}