package mondrian.rolap.agg;
 import mondrian.olap.MondrianDef;
import mondrian.olap.Util;
import mondrian.rolap;
import mondrian.rolap.sql.SqlQuery;
import mondrian.util.Pair;
import java.util;
public class DrillThroughQuerySpec extends AbstractQuerySpec{

 private  DrillThroughCellRequest request;

 private  List<StarPredicate> listOfStarPredicates;

 private  List<String> columnNames;

 private  int maxColumnNameLength;

public DrillThroughQuerySpec(DrillThroughCellRequest request, StarPredicate starPredicateSlicer, boolean countOnly) {
    super(request.getMeasure().getStar(), countOnly);
    this.request = request;
    if (starPredicateSlicer != null) {
        this.listOfStarPredicates = Collections.singletonList(starPredicateSlicer);
    } else {
        this.listOfStarPredicates = Collections.emptyList();
    }
    int tmpMaxColumnNameLength = getStar().getSqlQueryDialect().getMaxColumnNameLength();
    if (tmpMaxColumnNameLength == 0) {
        // From java.sql.DatabaseMetaData: "a result of zero means that
        // there is no limit or the limit is not known"
        maxColumnNameLength = Integer.MAX_VALUE;
    } else {
        maxColumnNameLength = tmpMaxColumnNameLength;
    }
    this.columnNames = computeDistinctColumnNames();
}
@Override
public boolean isPartOfSelect(RolapStar.Measure measure){
    return request.includeInSelect(measure);
}


public void extraPredicates(SqlQuery sqlQuery){
    super.extraPredicates(sqlQuery);
    if (countOnly) {
        return;
    }
    // generate the select list
    final Set<String> columnNameSet = new HashSet<String>();
    columnNameSet.addAll(columnNames);
    List<StarPredicate> predicateList = getPredicateList();
    for (StarPredicate predicate : predicateList) {
        for (RolapStar.Column column : predicate.getConstrainedColumnList()) {
            sqlQuery.addSelect(column.generateExprString(sqlQuery), column.getInternalType(), makeAlias(column, columnNames, columnNameSet));
        }
    }
}


public int getMeasureCount(){
    return request.getDrillThroughMeasures().size() > 0 ? request.getDrillThroughMeasures().size() : 1;
}


public Pair<String,List<SqlStatement.Type>> generateSqlQuery(){
    SqlQuery sqlQuery = newSqlQuery();
    nonDistinctGenerateSql(sqlQuery);
    return sqlQuery.toSqlAndTypes();
}


public void addColumnName(RolapStar.Column column,List<String> columnNames,Set<String> columnNameSet){
    String columnName = makeAlias(column, columnNames, columnNameSet);
    columnNames.add(columnName);
}


public String getColumnAlias(int i){
    return columnNames.get(i);
}


public StarColumnPredicate getColumnPredicate(int i){
    final StarColumnPredicate constr = request.getValueAt(i);
    return (constr == null) ? LiteralStarPredicate.TRUE : constr;
}


public RolapStar.Column[] getColumns(){
    return request.getConstrainedColumns();
}


public String makeAlias(RolapStar.Column column,List<String> columnNames,Set<String> columnNameSet){
    String columnName = column.getName();
    if (columnName != null) {
    // nothing
    } else if (column.getExpression() instanceof MondrianDef.Column) {
        columnName = ((MondrianDef.Column) column.getExpression()).name;
    } else {
        columnName = "c" + Integer.toString(columnNames.size());
    }
    // Register the column name, and if it's not unique, append numeric
    // suffixes until it is. Also make sure that it is within the
    // range allowed by this SQL dialect.
    String originalColumnName = columnName;
    if (columnName.length() > maxColumnNameLength) {
        columnName = columnName.substring(0, maxColumnNameLength);
    }
    for (int j = 0; !columnNameSet.add(columnName); j++) {
        final String suffix = "_" + Integer.toString(j);
        columnName = originalColumnName;
        if (originalColumnName.length() + suffix.length() > maxColumnNameLength) {
            columnName = originalColumnName.substring(0, maxColumnNameLength - suffix.length());
        }
        columnName += suffix;
    }
    return columnName;
}


public RolapStar.Measure getMeasure(int i){
    return request.getDrillThroughMeasures().size() > 0 ? request.getDrillThroughMeasures().get(i) : request.getMeasure();
}


public void addMeasure(int i,SqlQuery sqlQuery){
    RolapStar.Measure measure = getMeasure(i);
    if (!isPartOfSelect(measure)) {
        return;
    }
    Util.assertTrue(measure.getTable() == getStar().getFactTable());
    measure.getTable().addToFrom(sqlQuery, false, true);
    if (!countOnly) {
        String expr = measure.generateExprString(sqlQuery);
        sqlQuery.addSelect(expr, null, getMeasureAlias(i));
    }
}


public List<String> computeDistinctColumnNames(){
    final List<String> columnNames = new ArrayList<String>();
    final Set<String> columnNameSet = new HashSet<String>();
    final RolapStar.Column[] columns = getColumns();
    for (RolapStar.Column column : columns) {
        addColumnName(column, columnNames, columnNameSet);
    }
    addColumnName(request.getMeasure(), columnNames, columnNameSet);
    return columnNames;
}


public String getMeasureAlias(int i){
    return request.getDrillThroughMeasures().size() > 0 ? request.getDrillThroughMeasures().get(i).getName() : columnNames.get(columnNames.size() - 1);
}


public boolean isAggregate(){
    return false;
}


public boolean isOrdered(){
    return true;
}


public List<StarPredicate> getPredicateList(){
    return listOfStarPredicates;
}


}