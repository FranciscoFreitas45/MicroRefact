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
public class Table {

 private  RolapStar star;

 private  MondrianDef.Relation relation;

 private  List<Column> columnList;

 private  Table parent;

 private  List<Table> children;

 private  Condition joinCondition;

 private  String alias;

private Table(RolapStar star, MondrianDef.Relation relation, Table parent, Condition joinCondition) {
    this.star = star;
    this.relation = relation;
    this.alias = chooseAlias();
    this.parent = parent;
    final AliasReplacer aliasReplacer = new AliasReplacer(relation.getAlias(), this.alias);
    this.joinCondition = aliasReplacer.visit(joinCondition);
    if (this.joinCondition != null) {
        this.joinCondition.table = this;
    }
    this.columnList = new ArrayList<Column>();
    this.children = Collections.emptyList();
    Util.assertTrue((parent == null) == (joinCondition == null));
}
public Table findAncestor(String tableName){
    for (Table t = this; t != null; t = t.parent) {
        if (t.relation.getAlias().equals(tableName)) {
            return t;
        }
    }
    return null;
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


public Condition getJoinCondition(){
    return joinCondition;
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


public RolapStar getStar(){
    return star;
}


public List<Column> getColumns(){
    return columnList;
}


public boolean containsColumn(String columnName){
    if (relation instanceof MondrianDef.Relation) {
        return star.containsColumn(((MondrianDef.Relation) relation).getAlias(), columnName);
    } else {
        // todo: Deal with join.
        return false;
    }
}


public int hashCode(){
    return getAlias().hashCode();
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


public boolean isFunky(){
    return (relation == null);
}


public void addColumn(Column column){
    columnList.add(column);
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


public List<Table> getChildren(){
    return children;
}


public void print(PrintWriter pw,String prefix){
    pw.print(prefix);
    pw.println("Table:");
    String subprefix = prefix + "  ";
    pw.print(subprefix);
    pw.print("alias=");
    pw.println(getAlias());
    if (this.relation != null) {
        pw.print(subprefix);
        pw.print("relation=");
        pw.println(relation);
    }
    pw.print(subprefix);
    pw.println("Columns:");
    String subsubprefix = subprefix + "  ";
    for (Column column : getColumns()) {
        column.print(pw, subsubprefix);
        pw.println();
    }
    if (this.joinCondition != null) {
        this.joinCondition.print(pw, subprefix);
    }
    for (Table child : getChildren()) {
        child.print(pw, subprefix);
    }
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
    if (!(obj instanceof Table)) {
        return false;
    }
    Table other = (Table) obj;
    return getAlias().equals(other.getAlias());
}


public String toString(){
    StringWriter sw = new StringWriter(256);
    PrintWriter pw = new PrintWriter(sw);
    print(pw, "");
    pw.flush();
    return sw.toString();
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


}