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
public class Measure extends Column{

 private  String cubeName;

 private  RolapAggregator aggregator;

public Measure(String name, String cubeName, RolapAggregator aggregator, Table table, MondrianDef.Expression expression, Dialect.Datatype datatype) {
    super(name, table, expression, datatype);
    this.cubeName = cubeName;
    this.aggregator = aggregator;
}
public RolapAggregator getAggregator(){
    return aggregator;
}


public String getCubeName(){
    return cubeName;
}


public void print(PrintWriter pw,String prefix){
    SqlQuery sqlQuery = getSqlQuery();
    pw.print(prefix);
    pw.print(getName());
    pw.print(" (");
    pw.print(getBitPosition());
    pw.print("): ");
    pw.print(aggregator.getExpression(getExpression() == null ? null : generateExprString(sqlQuery)));
}


public int hashCode(){
    int h = super.hashCode();
    h = Util.hash(h, aggregator);
    return h;
}


public boolean equals(Object o){
    if (!(o instanceof RolapStar.Measure)) {
        return false;
    }
    RolapStar.Measure that = (RolapStar.Measure) o;
    if (!super.equals(that)) {
        return false;
    }
    // Measure names are only unique within their cube - and remember
    // that a given RolapStar can support multiple cubes if they have
    // the same fact table.
    if (!cubeName.equals(that.cubeName)) {
        return false;
    }
    // Note: both measure have to have the same aggregator
    return (that.aggregator == this.aggregator);
}


}