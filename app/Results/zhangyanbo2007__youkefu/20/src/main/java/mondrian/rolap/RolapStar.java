package mondrian.rolap;
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
public class RolapStar {

 private  Logger LOGGER;

 private  RolapSchema schema;

 private  DataSource dataSource;

 private  Table factTable;

 private  int columnCount;

 private  List<Column> columnList;

 private  Dialect sqlQueryDialect;

 private  boolean cacheAggregations;

 private  List<AggStar> aggStars;

 private  DataSourceChangeListener changeListener;

 private  StarNetworkNode factNode;

 private  Map<String,StarNetworkNode> nodeLookup;

 private  RolapStatisticsCache statisticsCache;

 private  Map<AggregationKey,Aggregation> aggregations;

 private  List<SoftReference<SegmentWithData>> segmentRefs;

 private  ThreadLocal<Bar> localBars;

 private  StarNetworkNode parent;

 private  MondrianDef.Relation origRel;

 private  String foreignKey;

 private  String joinKey;

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

 private  String cubeName;

 private  RolapAggregator aggregator;

 private  RolapStar star;

 private  MondrianDef.Relation relation;

 private  List<Column> columnList;

 private  Table parent;

 private  List<Table> children;

 private  Condition joinCondition;

 private  String alias;

 private  Logger LOGGER;

 private  MondrianDef.Expression left;

 private  MondrianDef.Expression right;

 private Table table;

 private  String oldAlias;

 private  String newAlias;

 public  ColumnComparator instance;

/**
 * Creates a RolapStar. Please use
 * {@link RolapSchema.RolapStarRegistry#getOrCreateStar} to create a
 * {@link RolapStar}.
 */
RolapStar(final RolapSchema schema, final DataSource dataSource, final MondrianDef.Relation fact) {
    this.cacheAggregations = true;
    this.schema = schema;
    this.dataSource = dataSource;
    this.factTable = new RolapStar.Table(this, fact, null, null);
    // phase out and replace with Table, Column network
    this.factNode = new StarNetworkNode(null, factTable.alias, null, null, null);
    this.sqlQueryDialect = schema.getDialect();
    this.changeListener = schema.getDataSourceChangeListener();
    this.statisticsCache = new RolapStatisticsCache(this);
}
public String generateExprString(SqlQuery query){
    return expr;
}


public void clearAggStarList(){
    aggStars.clear();
}


public Table findAncestor(String tableName){
    for (Table t = this; t != null; t = t.parent) {
        if (t.relation.getAlias().equals(tableName)) {
            return t;
        }
    }
    return null;
}


public String getName(){
    return name;
}


public RolapStar.Table findTableWithLeftJoinCondition(String columnName){
    for (Table child : getChildren()) {
        Condition condition = child.joinCondition;
        if (condition != null) {
            if (condition.left instanceof MondrianDef.Column) {
                MondrianDef.Column mcolumn = (MondrianDef.Column) condition.left;
                if (mcolumn.name.equals(columnName)) {
                    return child;
                }
            }
        }
    }
    return null;
}


public SqlQuery getSqlQuery(){
    return getStar().getSqlQuery();
}


public Aggregation lookupOrCreateAggregation(AggregationKey aggregationKey){
    Aggregation aggregation = lookupSegment(aggregationKey);
    if (aggregation != null) {
        return aggregation;
    }
    aggregation = new Aggregation(aggregationKey);
    localBars.get().aggregations.put(aggregationKey, aggregation);
    // Let the change listener get the opportunity to register the
    // first time the aggregation is used
    if (this.cacheAggregations && !isCacheDisabled() && changeListener != null) {
        Util.discard(changeListener.isAggregationChanged(aggregationKey));
    }
    return aggregation;
}


public Aggregation lookupSegment(AggregationKey aggregationKey){
    return localBars.get().aggregations.get(aggregationKey);
}


public RolapStatisticsCache getStatisticsCache(){
    return statisticsCache;
}


public List<AggStar> getAggStars(){
    return aggStars;
}


public MondrianDef.RelationOrJoin getUniqueRelation(StarNetworkNode parent,MondrianDef.RelationOrJoin relOrJoin,String foreignKey,String joinKey,String joinKeyTable){
    if (relOrJoin == null) {
        return null;
    } else if (relOrJoin instanceof MondrianDef.Relation) {
        int val = 0;
        MondrianDef.Relation rel = (MondrianDef.Relation) relOrJoin;
        String newAlias = joinKeyTable != null ? joinKeyTable : rel.getAlias();
        while (true) {
            StarNetworkNode node = nodeLookup.get(newAlias);
            if (node == null) {
                if (val != 0) {
                    rel = (MondrianDef.Relation) cloneRelation(rel, newAlias);
                }
                node = new StarNetworkNode(parent, newAlias, rel, foreignKey, joinKey);
                nodeLookup.put(newAlias, node);
                return rel;
            } else if (node.isCompatible(parent, rel, foreignKey, joinKey)) {
                return node.origRel;
            }
            newAlias = rel.getAlias() + "_" + (++val);
        }
    } else if (relOrJoin instanceof MondrianDef.Join) {
        // determine if the join starts from the left or right side
        MondrianDef.Join join = (MondrianDef.Join) relOrJoin;
        if (join.left instanceof MondrianDef.Join) {
            throw MondrianResource.instance().IllegalLeftDeepJoin.ex();
        }
        final MondrianDef.RelationOrJoin left;
        final MondrianDef.RelationOrJoin right;
        if (join.getLeftAlias().equals(joinKeyTable)) {
            // first manage left then right
            left = getUniqueRelation(parent, join.left, foreignKey, joinKey, joinKeyTable);
            parent = nodeLookup.get(((MondrianDef.Relation) left).getAlias());
            right = getUniqueRelation(parent, join.right, join.leftKey, join.rightKey, join.getRightAlias());
        } else if (join.getRightAlias().equals(joinKeyTable)) {
            // right side must equal
            right = getUniqueRelation(parent, join.right, foreignKey, joinKey, joinKeyTable);
            parent = nodeLookup.get(((MondrianDef.Relation) right).getAlias());
            left = getUniqueRelation(parent, join.left, join.rightKey, join.leftKey, join.getLeftAlias());
        } else {
            throw new MondrianException("failed to match primary key table to join tables");
        }
        if (join.left != left || join.right != right) {
            join = new MondrianDef.Join(left instanceof MondrianDef.Relation ? ((MondrianDef.Relation) left).getAlias() : null, join.leftKey, left, right instanceof MondrianDef.Relation ? ((MondrianDef.Relation) right).getAlias() : null, join.rightKey, right);
        }
        return join;
    }
    return null;
}


public boolean containsColumn(String columnName){
    if (relation instanceof MondrianDef.Relation) {
        return star.containsColumn(((MondrianDef.Relation) relation).getAlias(), columnName);
    } else {
        // todo: Deal with join.
        return false;
    }
}


public Object getCellFromExternalCache(CellRequest request){
    final SegmentWithData segment = Locus.peek().getServer().getAggregationManager().cacheMgr.peek(request);
    if (segment == null) {
        return null;
    }
    return segment.getCellValue(request.getSingleValues());
}


public boolean isCacheDisabled(){
    return MondrianProperties.instance().DisableCaching.get();
}


public Column getColumn(int bitPos){
    return columnList.get(bitPos);
}


public Table findDescendant(String seekAlias){
    if (getAlias().equals(seekAlias)) {
        return this;
    }
    for (Table child : getChildren()) {
        Table found = child.findDescendant(seekAlias);
        if (found != null) {
            return found;
        }
    }
    return null;
}


public void collectColumns(BitKey bitKey,List<Column> list){
    for (Column column : getColumns()) {
        if (bitKey.get(column.getBitPosition())) {
            list.add(column);
        }
    }
    for (Table table : getChildren()) {
        table.collectColumns(bitKey, list);
    }
}


public Bar initialValue(){
    return new Bar();
}


public void addAggStar(AggStar aggStar){
    // Add it before the first AggStar which is larger, if there is one.
    int size = aggStar.getSize();
    ListIterator<AggStar> lit = aggStars.listIterator();
    while (lit.hasNext()) {
        AggStar as = lit.next();
        if (as.getSize() >= size) {
            lit.previous();
            lit.add(aggStar);
            return;
        }
    }
    // There is no larger star. Add at the end of the list.
    aggStars.add(aggStar);
}


public Column makeColumnForLevelExpr(RolapCube cube,RolapLevel level,String name,MondrianDef.Expression xmlExpr,Dialect.Datatype datatype,SqlStatement.Type internalType,Column nameColumn,Column parentColumn,String usagePrefix){
    Table table = this;
    if (xmlExpr instanceof MondrianDef.Column) {
        final MondrianDef.Column xmlColumn = (MondrianDef.Column) xmlExpr;
        String tableName = xmlColumn.table;
        table = findAncestor(tableName);
        if (table == null) {
            throw Util.newError("Level '" + level.getUniqueName() + "' of cube '" + this + "' is invalid: table '" + tableName + "' is not found in current scope" + Util.nl + ", star:" + Util.nl + getStar());
        }
        RolapStar.AliasReplacer aliasReplacer = new RolapStar.AliasReplacer(tableName, table.getAlias());
        xmlExpr = aliasReplacer.visit(xmlExpr);
    }
    // does the column already exist??
    Column c = lookupColumnByExpression(xmlExpr);
    RolapStar.Column column;
    // Verify Column is not null and not the same as the
    // nameColumn created previously (bug 1438285)
    if (c != null && !c.equals(nameColumn)) {
        // Yes, well just reuse it
        // You might wonder why the column need be returned if it
        // already exists. Well, it might have been created for one
        // cube, but for another cube using the same fact table, it
        // still needs to be put into the cube level to column map.
        // Trust me, return null and a junit test fails.
        column = c;
    } else {
        // Make a new column and add it
        column = new RolapStar.Column(name, table, xmlExpr, datatype, internalType, nameColumn, parentColumn, usagePrefix, level.getApproxRowCount(), star.nextColumnCount());
        addColumn(column);
    }
    return column;
}


public List<String> getAliasList(){
    List<String> aliasList = new ArrayList<String>();
    if (factTable != null) {
        collectAliases(aliasList, factTable);
    }
    return aliasList;
}


public boolean isFunky(){
    return (relation == null);
}


public void addColumn(Column column){
    columnList.add(column);
}


public RolapStar.Column getNameColumn(){
    return nameColumn;
}


public void addToFrom(SqlQuery query,boolean failIfExists,boolean joinToParent){
    query.addFrom(relation, alias, failIfExists);
    Util.assertTrue((parent == null) == (joinCondition == null));
    if (joinToParent) {
        if (parent != null) {
            parent.addToFrom(query, failIfExists, joinToParent);
        }
        if (joinCondition != null) {
            query.addWhere(joinCondition.toString(query));
        }
    }
}


public void prepareToLoadAggregates(){
    aggStars.clear();
}


public SqlStatement.Type getInternalType(){
    return internalType;
}


public Measure lookupMeasureByName(String cubeName,String name){
    for (Column column : getColumns()) {
        if (column instanceof Measure) {
            Measure measure = (Measure) column;
            if (measure.getName().equals(name) && measure.getCubeName().equals(cubeName)) {
                return measure;
            }
        }
    }
    return null;
}


public Column lookupColumn(String columnName){
    for (Column column : getColumns()) {
        if (column.getExpression() instanceof MondrianDef.Column) {
            MondrianDef.Column columnExpr = (MondrianDef.Column) column.getExpression();
            if (columnExpr.name.equals(columnName)) {
                return column;
            }
        } else if (column.getExpression() instanceof MondrianDef.KeyExpression) {
            MondrianDef.KeyExpression columnExpr = (MondrianDef.KeyExpression) column.getExpression();
            if (columnExpr.toString().equals(columnName)) {
                return column;
            }
        } else if (column.getName().equals(columnName)) {
            return column;
        }
    }
    return null;
}


public int decrementColumnCount(){
    return columnCount--;
}


public String getTableName(){
    if (relation instanceof MondrianDef.Table) {
        MondrianDef.Table t = (MondrianDef.Table) relation;
        return t.name;
    } else {
        return null;
    }
}


public RolapStar.Table findTableWithLeftCondition(MondrianDef.Expression left){
    for (Table child : getChildren()) {
        Condition condition = child.joinCondition;
        if (condition != null) {
            if (condition.left instanceof MondrianDef.Column) {
                MondrianDef.Column mcolumn = (MondrianDef.Column) condition.left;
                if (mcolumn.equals(left)) {
                    return child;
                }
            }
        }
    }
    return null;
}


public int nextColumnCount(){
    return columnCount++;
}


public RolapStar.Column getParentColumn(){
    return parentColumn;
}


public int getColumnCount(){
    return columnCount;
}


public void setCacheAggregations(boolean cacheAggregations){
    // this can only change from true to false
    this.cacheAggregations = cacheAggregations;
    clearCachedAggregations(false);
}


public boolean isCompatible(StarNetworkNode compatibleParent,MondrianDef.Relation rel,String compatibleForeignKey,String compatibleJoinKey){
    return parent == compatibleParent && origRel.getClass().equals(rel.getClass()) && foreignKey.equals(compatibleForeignKey) && joinKey.equals(compatibleJoinKey);
}


public List<Table> getChildren(){
    return children;
}


public MondrianDef.Expression getExpression(){
    return expression;
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


public DataSourceChangeListener getChangeListener(){
    return changeListener;
}


public RolapStar.Table getTable(){
    return table;
}


public Table getParentTable(){
    return parent;
}


public MondrianDef.Relation getRelation(){
    return relation;
}


public String getAlias(){
    return alias;
}


public Table getFactTable(){
    return factTable;
}


public String getCubeName(){
    return cubeName;
}


public DataSource getDataSource(){
    return dataSource;
}


public Measure getStarMeasure(Member member){
    return (Measure) ((RolapStoredMeasure) member).getStarMeasure();
}


public int compare(Column o1,Column o2){
    return o1.getName().compareTo(o2.getName());
}


public String generateSql(List<Column> columnList,List<String> columnNameList){
    final SqlQuery query = new SqlQuery(sqlQueryDialect, true);
    query.addFrom(factTable.relation, factTable.relation.getAlias(), false);
    int k = -1;
    for (Column column : columnList) {
        ++k;
        column.table.addToFrom(query, false, true);
        String columnExpr = column.generateExprString(query);
        if (column instanceof Measure) {
            Measure measure = (Measure) column;
            columnExpr = measure.getAggregator().getExpression(columnExpr);
        }
        final String columnName = columnNameList.get(k);
        String alias = query.addSelect(columnExpr, null, columnName);
        if (!(column instanceof Measure)) {
            query.addGroupBy(columnExpr, alias);
        }
    }
    // remove whitespace from query - in particular, the trailing newline
    return query.toString().trim();
}


public void setChangeListener(DataSourceChangeListener changeListener){
    this.changeListener = changeListener;
}


public Column[] lookupColumns(String columnName){
    List<Column> l = new ArrayList<Column>();
    for (Column column : getColumns()) {
        if (column.getExpression() instanceof MondrianDef.Column) {
            MondrianDef.Column columnExpr = (MondrianDef.Column) column.getExpression();
            if (columnExpr.name.equals(columnName)) {
                l.add(column);
            }
        } else if (column.getExpression() instanceof MondrianDef.KeyExpression) {
            MondrianDef.KeyExpression columnExpr = (MondrianDef.KeyExpression) column.getExpression();
            if (columnExpr.toString().equals(columnName)) {
                l.add(column);
            }
        }
    }
    return l.toArray(new Column[l.size()]);
}


public void collectAliases(List<String> aliasList,Table table){
    aliasList.add(table.getAlias());
    for (Table child : table.children) {
        collectAliases(aliasList, child);
    }
}


public Condition getJoinCondition(){
    return joinCondition;
}


public Object getCellFromCache(CellRequest request,RolapAggregationManager.PinSet pinSet){
    // REVIEW: Is it possible to optimize this so not every cell lookup
    // causes an AggregationKey to be created?
    AggregationKey aggregationKey = new AggregationKey(request);
    final Bar bar = localBars.get();
    for (SegmentWithData segment : Util.GcIterator.over(bar.segmentRefs)) {
        if (!segment.getConstrainedColumnsBitKey().equals(request.getConstrainedColumnsBitKey())) {
            continue;
        }
        if (!segment.matches(aggregationKey, request.getMeasure())) {
            continue;
        }
        Object o = segment.getCellValue(request.getSingleValues());
        if (o != null) {
            if (pinSet != null) {
                ((AggregationManager.PinSetImpl) pinSet).add(segment);
            }
            return o;
        }
    }
    // No segment contains the requested cell.
    return null;
}


public BitKey getBitKey(String[] tableAlias,String[] columnName){
    BitKey bitKey = BitKey.Factory.makeBitKey(getColumnCount());
    Column starColumn;
    for (int i = 0; i < tableAlias.length; i++) {
        starColumn = lookupColumn(tableAlias[i], columnName[i]);
        if (starColumn != null) {
            bitKey.set(starColumn.getBitPosition());
        }
    }
    return bitKey;
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


public boolean isNameColumn(){
    return isNameColumn;
}


public RolapStar getStar(){
    return star;
}


public List<Column> getColumns(){
    return columnList;
}


public RolapAggregator getAggregator(){
    return aggregator;
}


public int getBitPosition(){
    return bitPosition;
}


public Dialect.Datatype getDatatype(){
    return datatype;
}


public int hashCode(){
    return left.hashCode() ^ right.hashCode();
}


public void reOrderAggStarList(){
    List<AggStar> oldList = new ArrayList<AggStar>(aggStars);
    aggStars.clear();
    for (AggStar aggStar : oldList) {
        addAggStar(aggStar);
    }
}


public MondrianDef.RelationOrJoin cloneRelation(MondrianDef.Relation rel,String possibleName){
    if (rel instanceof MondrianDef.Table) {
        MondrianDef.Table tbl = (MondrianDef.Table) rel;
        return new MondrianDef.Table(tbl, possibleName);
    } else if (rel instanceof MondrianDef.View) {
        MondrianDef.View view = (MondrianDef.View) rel;
        MondrianDef.View newView = new MondrianDef.View(view);
        newView.alias = possibleName;
        return newView;
    } else if (rel instanceof MondrianDef.InlineTable) {
        MondrianDef.InlineTable inlineTable = (MondrianDef.InlineTable) rel;
        MondrianDef.InlineTable newInlineTable = new MondrianDef.InlineTable(inlineTable);
        newInlineTable.alias = possibleName;
        return newInlineTable;
    } else {
        throw new UnsupportedOperationException();
    }
}


public void setDataSource(DataSource dataSource){
    this.dataSource = dataSource;
}


public String chooseAlias(){
    List<String> aliasList = star.getAliasList();
    for (int i = 0; ; ++i) {
        String candidateAlias = relation.getAlias();
        if (i > 0) {
            candidateAlias += "_" + i;
        }
        if (!aliasList.contains(candidateAlias)) {
            return candidateAlias;
        }
    }
}


public void makeMeasure(RolapBaseCubeMeasure measure){
    // Remove assertion to allow cube to be recreated
    // assert lookupMeasureByName(
    // measure.getCube().getName(), measure.getName()) == null;
    RolapStar.Measure starMeasure = new RolapStar.Measure(measure.getName(), measure.getCube().getName(), measure.getAggregator(), this, measure.getMondrianDefExpression(), measure.getDatatype());
    // reverse mapping
    measure.setStarMeasure(starMeasure);
    if (containsColumn(starMeasure)) {
        star.decrementColumnCount();
    } else {
        addColumn(starMeasure);
    }
}


public void clearCachedAggregations(boolean forced){
    if (forced || !cacheAggregations || isCacheDisabled()) {
        if (LOGGER.isDebugEnabled()) {
            StringBuilder buf = new StringBuilder(100);
            buf.append("RolapStar.clearCachedAggregations: schema=");
            buf.append(schema.getName());
            buf.append(", star=");
            buf.append(getFactTable().getAlias());
            LOGGER.debug(buf.toString());
        }
        // Clear aggregation cache for the current thread context.
        localBars.get().aggregations.clear();
        localBars.get().segmentRefs.clear();
    }
}


public boolean isCacheAggregations(){
    return this.cacheAggregations;
}


public Table addJoin(RolapCube cube,MondrianDef.RelationOrJoin relationOrJoin,RolapStar.Condition joinCondition){
    if (relationOrJoin instanceof MondrianDef.Relation) {
        final MondrianDef.Relation relation = (MondrianDef.Relation) relationOrJoin;
        RolapStar.Table starTable = findChild(relation, joinCondition);
        if (starTable == null) {
            starTable = new RolapStar.Table(star, relation, this, joinCondition);
            if (this.children.isEmpty()) {
                this.children = new ArrayList<Table>();
            }
            this.children.add(starTable);
        }
        return starTable;
    } else if (relationOrJoin instanceof MondrianDef.Join) {
        MondrianDef.Join join = (MondrianDef.Join) relationOrJoin;
        RolapStar.Table leftTable = addJoin(cube, join.left, joinCondition);
        String leftAlias = join.leftAlias;
        if (leftAlias == null) {
            // REVIEW: is cast to Relation valid?
            leftAlias = ((MondrianDef.Relation) join.left).getAlias();
            if (leftAlias == null) {
                throw Util.newError("missing leftKeyAlias in " + relationOrJoin);
            }
        }
        assert leftTable.findAncestor(leftAlias) == leftTable;
        // switch to uniquified alias
        leftAlias = leftTable.getAlias();
        String rightAlias = join.rightAlias;
        if (rightAlias == null) {
            // the right relation of a join may be a join
            // if so, we need to use the right relation join's
            // left relation's alias.
            if (join.right instanceof MondrianDef.Join) {
                MondrianDef.Join joinright = (MondrianDef.Join) join.right;
                // REVIEW: is cast to Relation valid?
                rightAlias = ((MondrianDef.Relation) joinright.left).getAlias();
            } else {
                // REVIEW: is cast to Relation valid?
                rightAlias = ((MondrianDef.Relation) join.right).getAlias();
            }
            if (rightAlias == null) {
                throw Util.newError("missing rightKeyAlias in " + relationOrJoin);
            }
        }
        joinCondition = new RolapStar.Condition(new MondrianDef.Column(leftAlias, join.leftKey), new MondrianDef.Column(rightAlias, join.rightKey));
        RolapStar.Table rightTable = leftTable.addJoin(cube, join.right, joinCondition);
        return rightTable;
    } else {
        throw Util.newInternal("bad relation type " + relationOrJoin);
    }
}


public Dialect getSqlQueryDialect(){
    return sqlQueryDialect;
}


public RolapSchema getSchema(){
    return schema;
}


public int getCardinality(){
    if (approxCardinality.get() < 0) {
        approxCardinality.set(table.star.getStatisticsCache().getColumnCardinality(table.relation, expression, approxCardinality.get()));
    }
    return approxCardinality.get();
}


public boolean equalsTableName(String tableName){
    if (this.relation instanceof MondrianDef.Table) {
        MondrianDef.Table mt = (MondrianDef.Table) this.relation;
        if (mt.name.equals(tableName)) {
            return true;
        }
    }
    return false;
}


public Table findChild(MondrianDef.Relation relation,Condition joinCondition){
    for (Table child : getChildren()) {
        if (child.relation.equals(relation)) {
            Condition condition = joinCondition;
            if (!Util.equalName(relation.getAlias(), child.alias)) {
                // Make the two conditions comparable, by replacing
                // occurrence of this table's alias with occurrences
                // of the child's alias.
                AliasReplacer aliasReplacer = new AliasReplacer(relation.getAlias(), child.alias);
                condition = aliasReplacer.visit(joinCondition);
            }
            if (child.joinCondition.equals(condition)) {
                return child;
            }
        }
    }
    return null;
}


public void print(PrintWriter pw,String prefix){
    SqlQuery sqlQueuy = table.getSqlQuery();
    pw.print(prefix);
    pw.println("Condition:");
    String subprefix = prefix + "  ";
    pw.print(subprefix);
    pw.print("left=");
    // print the foreign key bit position if we can figure it out
    if (left instanceof MondrianDef.Column) {
        MondrianDef.Column c = (MondrianDef.Column) left;
        Column col = table.star.getFactTable().lookupColumn(c.name);
        if (col != null) {
            pw.print(" (");
            pw.print(col.getBitPosition());
            pw.print(") ");
        }
    }
    pw.println(left.getExpression(sqlQueuy));
    pw.print(subprefix);
    pw.print("right=");
    pw.println(right.getExpression(sqlQueuy));
}


public String getUsagePrefix(){
    return usagePrefix;
}


public String getRight(SqlQuery query){
    return this.right.getExpression(query);
}


public Column lookupColumnByExpression(MondrianDef.Expression xmlExpr){
    for (Column column : getColumns()) {
        if (column instanceof Measure) {
            continue;
        }
        if (column.getExpression().equals(xmlExpr)) {
            return column;
        }
    }
    return null;
}


public Column makeColumns(RolapCube cube,RolapCubeLevel level,Column parentColumn,String usagePrefix){
    Column nameColumn = null;
    if (level.getNameExp() != null) {
        // make a column for the name expression
        nameColumn = makeColumnForLevelExpr(cube, level, level.getName(), level.getNameExp(), Dialect.Datatype.String, null, null, null, null);
    }
    // select the column's name depending upon whether or not a
    // "named" column, above, has been created.
    String name = (level.getNameExp() == null) ? level.getName() : level.getName() + " (Key)";
    // If the nameColumn is not null, then it is associated with this
    // column.
    Column column = makeColumnForLevelExpr(cube, level, name, level.getKeyExp(), level.getDatatype(), level.getInternalType(), nameColumn, parentColumn, usagePrefix);
    if (column != null) {
        level.setStarKeyColumn(column);
    }
    return column;
}


public boolean equals(Object obj){
    if (!(obj instanceof Condition)) {
        return false;
    }
    Condition that = (Condition) obj;
    return this.left.equals(that.left) && this.right.equals(that.right);
}


public String toString(){
    StringWriter sw = new StringWriter(256);
    PrintWriter pw = new PrintWriter(sw);
    print(pw, "");
    pw.flush();
    return sw.toString();
}


public String visit(String table){
    return table.equals(oldAlias) ? newAlias : table;
}


public Object getCellFromAllCaches(CellRequest request){
    // First, try the local/thread cache.
    Object result = getCellFromCache(request, null);
    if (result != null) {
        return result;
    }
    // Now ask the segment cache manager.
    return getCellFromExternalCache(request);
}


public void register(SegmentWithData segment){
    localBars.get().segmentRefs.add(new SoftReference<SegmentWithData>(segment));
}


public String getLeft(SqlQuery query){
    return this.left.getExpression(query);
}


}