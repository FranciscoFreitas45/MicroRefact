package mondrian.rolap.RolapStar;
 import mondrian.olap;
import mondrian.resource.MondrianResource;
import mondrian.rolap.agg;
import mondrian.rolap.aggmatcher.AggStar;
import mondrian.rolap.sql.SqlQuery;
import mondrian.server.Locus;
import mondrian.spi;
import mondrian.util.Bug;
import org.apache.commons.collections.map.ReferenceMap;
import org.apache.log4j.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql;
import java.util;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;
public class Column {

 public  Comparator<Column> COMPARATOR;

 private  Table table;

 private  MondrianDef.Expression expression;

 private  Dialect.Datatype datatype;

 private  SqlStatement.Type internalType;

 private  String name;

 private  Column parentColumn;

 private  String usagePrefix;

 private  Column nameColumn;

 private  boolean isNameColumn;

 private  int bitPosition;

 private  AtomicInteger approxCardinality;

private Column(String name, Table table, MondrianDef.Expression expression, Dialect.Datatype datatype) {
    this(name, table, expression, datatype, null, null, null, null, Integer.MIN_VALUE, table.star.nextColumnCount());
}private Column(String name, Table table, MondrianDef.Expression expression, Dialect.Datatype datatype, SqlStatement.Type internalType, Column nameColumn, Column parentColumn, String usagePrefix, int approxCardinality, int bitPosition) {
    this.name = name;
    this.table = table;
    this.expression = expression;
    assert expression == null || expression.getGenericExpression() != null;
    this.datatype = datatype;
    this.internalType = internalType;
    this.bitPosition = bitPosition;
    this.nameColumn = nameColumn;
    this.parentColumn = parentColumn;
    this.usagePrefix = usagePrefix;
    this.approxCardinality.set(approxCardinality);
    if (nameColumn != null) {
        nameColumn.isNameColumn = true;
    }
    if (table != null) {
        table.star.addColumn(this);
    }
}/**
 * Fake column.
 *
 * @param datatype Datatype
 */
protected Column(Dialect.Datatype datatype) {
    this(null, null, null, datatype, null, null, null, null, Integer.MIN_VALUE, 0);
}
public String generateExprString(SqlQuery query){
    return expr;
}


public RolapStar.Column getNameColumn(){
    return nameColumn;
}


public int compare(Column object1,Column object2){
    return Util.compare(object1.getBitPosition(), object2.getBitPosition());
}


public String getName(){
    return name;
}


public SqlStatement.Type getInternalType(){
    return internalType;
}


public String createInExpr(String expr,StarColumnPredicate predicate,Dialect.Datatype datatype,SqlQuery sqlQuery){
    // Sometimes a column predicate is created without a column. This
    // is unfortunate, and we will fix it some day. For now, create
    // a fake column with all of the information needed by the toSql
    // method, and a copy of the predicate wrapping that fake column.
    if (!Bug.BugMondrian313Fixed || !Bug.BugMondrian314Fixed && predicate.getConstrainedColumn() == null) {
        Column column = new Column(datatype) {

            public String generateExprString(SqlQuery query) {
                return expr;
            }
        };
        predicate = predicate.cloneWithColumn(column);
    }
    StringBuilder buf = new StringBuilder(64);
    predicate.toSql(sqlQuery, buf);
    return buf.toString();
}


public RolapStar.Column getParentColumn(){
    return parentColumn;
}


public SqlQuery getSqlQuery(){
    return getTable().getStar().getSqlQuery();
}


public boolean isNameColumn(){
    return isNameColumn;
}


public RolapStar getStar(){
    return table.star;
}


public int getCardinality(){
    if (approxCardinality.get() < 0) {
        approxCardinality.set(table.star.getStatisticsCache().getColumnCardinality(table.relation, expression, approxCardinality.get()));
    }
    return approxCardinality.get();
}


public int getBitPosition(){
    return bitPosition;
}


public Dialect.Datatype getDatatype(){
    return datatype;
}


public String getUsagePrefix(){
    return usagePrefix;
}


public MondrianDef.Expression getExpression(){
    return expression;
}


public void print(PrintWriter pw,String prefix){
    SqlQuery sqlQuery = getSqlQuery();
    pw.print(prefix);
    pw.print(getName());
    pw.print(" (");
    pw.print(getBitPosition());
    pw.print("): ");
    pw.print(generateExprString(sqlQuery));
}


public String getDatatypeString(Dialect dialect){
    final SqlQuery query = new SqlQuery(dialect);
    query.addFrom(table.star.factTable.relation, table.star.factTable.alias, false);
    query.addFrom(table.relation, table.alias, false);
    query.addSelect(expression.getExpression(query), null);
    final String sql = query.toString();
    Connection jdbcConnection = null;
    try {
        jdbcConnection = table.star.dataSource.getConnection();
        final PreparedStatement pstmt = jdbcConnection.prepareStatement(sql);
        final ResultSetMetaData resultSetMetaData = pstmt.getMetaData();
        assert resultSetMetaData.getColumnCount() == 1;
        final String type = resultSetMetaData.getColumnTypeName(1);
        int precision = resultSetMetaData.getPrecision(1);
        final int scale = resultSetMetaData.getScale(1);
        if (type.equals("DOUBLE")) {
            precision = 0;
        }
        String typeString;
        if (precision == 0) {
            typeString = type;
        } else if (scale == 0) {
            typeString = type + "(" + precision + ")";
        } else {
            typeString = type + "(" + precision + ", " + scale + ")";
        }
        pstmt.close();
        jdbcConnection.close();
        jdbcConnection = null;
        return typeString;
    } catch (SQLException e) {
        throw Util.newError(e, "Error while deriving type of column " + toString());
    } finally {
        if (jdbcConnection != null) {
            try {
                jdbcConnection.close();
            } catch (SQLException e) {
            // ignore
            }
        }
    }
}


public RolapStar.Table getTable(){
    return table;
}


public int hashCode(){
    int h = name.hashCode();
    h = Util.hash(h, table);
    return h;
}


public boolean equals(Object obj){
    if (!(obj instanceof RolapStar.Column)) {
        return false;
    }
    RolapStar.Column other = (RolapStar.Column) obj;
    // Note: both columns have to be from the same table
    return other.table == this.table && Util.equals(other.expression, this.expression) && other.datatype == this.datatype && other.name.equals(this.name);
}


public String toString(){
    StringWriter sw = new StringWriter(256);
    PrintWriter pw = new PrintWriter(sw);
    print(pw, "");
    pw.flush();
    return sw.toString();
}


}