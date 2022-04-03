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
public class Condition {

 private  Logger LOGGER;

 private  MondrianDef.Expression left;

 private  MondrianDef.Expression right;

 private Table table;

Condition(MondrianDef.Expression left, MondrianDef.Expression right) {
    assert left != null;
    assert right != null;
    if (!(left instanceof MondrianDef.Column)) {
        // TODO: Will this ever print?? if not then left should be
        // of type MondrianDef.Column.
        LOGGER.debug("Condition.left NOT Column: " + left.getClass().getName());
    }
    this.left = left;
    this.right = right;
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


public String getRight(SqlQuery query){
    return this.right.getExpression(query);
}


public int hashCode(){
    return left.hashCode() ^ right.hashCode();
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


public String getLeft(SqlQuery query){
    return this.left.getExpression(query);
}


}