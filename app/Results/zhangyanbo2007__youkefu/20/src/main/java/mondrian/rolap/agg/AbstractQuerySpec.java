package mondrian.rolap.agg;
 import mondrian.olap.Util;
import mondrian.rolap;
import mondrian.rolap.sql.SqlQuery;
import mondrian.spi.Dialect;
import mondrian.util.Pair;
import java.util;
public class AbstractQuerySpec implements QuerySpec{

 private  RolapStar star;

 protected  boolean countOnly;

/**
 * Creates an AbstractQuerySpec.
 *
 * @param star Star which defines columns of interest and their
 * relationships
 *
 * @param countOnly If true, generate no GROUP BY clause, so the query
 * returns a single row containing a grand total
 */
protected AbstractQuerySpec(final RolapStar star, boolean countOnly) {
    this.star = star;
    this.countOnly = countOnly;
}
public boolean isPartOfSelect(RolapStar.Measure measure){
    return true;
}


public void extraPredicates(SqlQuery sqlQuery){
    List<StarPredicate> predicateList = getPredicateList();
    for (StarPredicate predicate : predicateList) {
        for (RolapStar.Column column : predicate.getConstrainedColumnList()) {
            final RolapStar.Table table = column.getTable();
            table.addToFrom(sqlQuery, false, true);
        }
        StringBuilder buf = new StringBuilder();
        predicate.toSql(sqlQuery, buf);
        final String where = buf.toString();
        if (!where.equals("true")) {
            sqlQuery.addWhere(where);
        }
    }
}


public Pair<String,List<SqlStatement.Type>> generateSqlQuery(){
    SqlQuery sqlQuery = newSqlQuery();
    int k = getDistinctMeasureCount();
    final Dialect dialect = sqlQuery.getDialect();
    final Map<String, String> groupingSetsAliases;
    if (!dialect.allowsCountDistinct() && k > 0 || !dialect.allowsMultipleCountDistinct() && k > 1) {
        groupingSetsAliases = distinctGenerateSql(sqlQuery, countOnly);
    } else {
        groupingSetsAliases = nonDistinctGenerateSql(sqlQuery);
    }
    if (!countOnly) {
        addGroupingFunction(sqlQuery);
        addGroupingSets(sqlQuery, groupingSetsAliases);
    }
    return sqlQuery.toSqlAndTypes();
}


public int getDistinctMeasureCount(){
    int k = 0;
    for (int i = 0, count = getMeasureCount(); i < count; i++) {
        RolapStar.Measure measure = getMeasure(i);
        if (measure.getAggregator().isDistinct()) {
            ++k;
        }
    }
    return k;
}


public RolapStar getStar(){
    return star;
}


public void addMeasure(int i,SqlQuery sqlQuery){
    RolapStar.Measure measure = getMeasure(i);
    if (!isPartOfSelect(measure)) {
        return;
    }
    Util.assertTrue(measure.getTable() == getStar().getFactTable());
    measure.getTable().addToFrom(sqlQuery, false, true);
    String exprInner = measure.getExpression() == null ? "*" : measure.generateExprString(sqlQuery);
    String exprOuter = measure.getAggregator().getExpression(exprInner);
    sqlQuery.addSelect(exprOuter, measure.getInternalType(), getMeasureAlias(i));
}


public Map<String,String> distinctGenerateSql(SqlQuery outerSqlQuery,boolean countOnly){
    final Dialect dialect = outerSqlQuery.getDialect();
    final Dialect.DatabaseProduct databaseProduct = dialect.getDatabaseProduct();
    final Map<String, String> groupingSetsAliases = new HashMap<String, String>();
    // Generate something like
    // 
    // select d0, d1, count(m0)
    // from (
    // select distinct dim1.x as d0, dim2.y as d1, f.z as m0
    // from f, dim1, dim2
    // where dim1.k = f.k1
    // and dim2.k = f.k2) as dummyname
    // group by d0, d1
    // 
    // or, if countOnly=true
    // 
    // select count(m0)
    // from (
    // select distinct f.z as m0
    // from f, dim1, dim2
    // where dim1.k = f.k1
    // and dim2.k = f.k2) as dummyname
    final SqlQuery innerSqlQuery = newSqlQuery();
    if (databaseProduct == Dialect.DatabaseProduct.GREENPLUM) {
        innerSqlQuery.setDistinct(false);
    } else {
        innerSqlQuery.setDistinct(true);
    }
    // add constraining dimensions
    RolapStar.Column[] columns = getColumns();
    int arity = columns.length;
    for (int i = 0; i < arity; i++) {
        RolapStar.Column column = columns[i];
        RolapStar.Table table = column.getTable();
        if (table.isFunky()) {
            // this is a funky dimension -- ignore for now
            continue;
        }
        table.addToFrom(innerSqlQuery, false, true);
        String expr = column.generateExprString(innerSqlQuery);
        StarColumnPredicate predicate = getColumnPredicate(i);
        final String where = RolapStar.Column.createInExpr(expr, predicate, column.getDatatype(), innerSqlQuery);
        if (!where.equals("true")) {
            innerSqlQuery.addWhere(where);
        }
        if (countOnly) {
            continue;
        }
        String alias = "d" + i;
        alias = innerSqlQuery.addSelect(expr, null, alias);
        if (databaseProduct == Dialect.DatabaseProduct.GREENPLUM) {
            innerSqlQuery.addGroupBy(expr, alias);
        }
        final String quotedAlias = dialect.quoteIdentifier(alias);
        outerSqlQuery.addSelectGroupBy(quotedAlias, null);
        // Add this alias to the map of grouping sets aliases
        groupingSetsAliases.put(expr, dialect.quoteIdentifier("dummyname." + alias));
    }
    // add predicates not associated with columns
    extraPredicates(innerSqlQuery);
    // add measures
    for (int i = 0, count = getMeasureCount(); i < count; i++) {
        RolapStar.Measure measure = getMeasure(i);
        Util.assertTrue(measure.getTable() == getStar().getFactTable());
        measure.getTable().addToFrom(innerSqlQuery, false, true);
        String alias = getMeasureAlias(i);
        String expr = measure.generateExprString(outerSqlQuery);
        innerSqlQuery.addSelect(expr, measure.getInternalType(), alias);
        if (databaseProduct == Dialect.DatabaseProduct.GREENPLUM) {
            innerSqlQuery.addGroupBy(expr, alias);
        }
        outerSqlQuery.addSelect(measure.getAggregator().getNonDistinctAggregator().getExpression(dialect.quoteIdentifier(alias)), measure.getInternalType());
    }
    outerSqlQuery.addFrom(innerSqlQuery, "dummyname", true);
    return groupingSetsAliases;
}


public boolean isAggregate()


public Map<String,String> nonDistinctGenerateSql(SqlQuery sqlQuery){
    // add constraining dimensions
    RolapStar.Column[] columns = getColumns();
    int arity = columns.length;
    if (countOnly) {
        sqlQuery.addSelect("count(*)", SqlStatement.Type.INT);
    }
    for (int i = 0; i < arity; i++) {
        RolapStar.Column column = columns[i];
        RolapStar.Table table = column.getTable();
        if (table.isFunky()) {
            // this is a funky dimension -- ignore for now
            continue;
        }
        table.addToFrom(sqlQuery, false, true);
        String expr = column.generateExprString(sqlQuery);
        StarColumnPredicate predicate = getColumnPredicate(i);
        final String where = RolapStar.Column.createInExpr(expr, predicate, column.getDatatype(), sqlQuery);
        if (!where.equals("true")) {
            sqlQuery.addWhere(where);
        }
        if (countOnly) {
            continue;
        }
        if (!isPartOfSelect(column)) {
            continue;
        }
        // some DB2 (AS400) versions throw an error, if a column alias is
        // there and *not* used in a subsequent order by/group by
        final Dialect dialect = sqlQuery.getDialect();
        final String alias;
        final Dialect.DatabaseProduct databaseProduct = dialect.getDatabaseProduct();
        if (databaseProduct == Dialect.DatabaseProduct.DB2_AS400) {
            alias = sqlQuery.addSelect(expr, column.getInternalType(), null);
        } else {
            alias = sqlQuery.addSelect(expr, column.getInternalType(), getColumnAlias(i));
        }
        if (isAggregate()) {
            sqlQuery.addGroupBy(expr, alias);
        }
        // Add ORDER BY clause to make the results deterministic.
        // Derby has a bug with ORDER BY, so ignore it.
        if (isOrdered()) {
            sqlQuery.addOrderBy(expr, alias, true, false, false, true);
        }
    }
    // Add compound member predicates
    extraPredicates(sqlQuery);
    // add measures
    for (int i = 0, count = getMeasureCount(); i < count; i++) {
        addMeasure(i, sqlQuery);
    }
    return Collections.emptyMap();
}


public void addGroupingSets(SqlQuery sqlQuery,Map<String,String> groupingSetsAliases){
    throw new UnsupportedOperationException();
}


public SqlQuery newSqlQuery(){
    return getStar().getSqlQuery();
}


public boolean isOrdered(){
    return false;
}


public void addGroupingFunction(SqlQuery sqlQuery){
    throw new UnsupportedOperationException();
}


public List<StarPredicate> getPredicateList(){
    return Collections.emptyList();
}


}