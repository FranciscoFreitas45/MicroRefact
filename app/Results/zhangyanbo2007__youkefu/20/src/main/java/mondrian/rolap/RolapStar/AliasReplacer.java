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
public class AliasReplacer {

 private  String oldAlias;

 private  String newAlias;

public AliasReplacer(String oldAlias, String newAlias) {
    this.oldAlias = oldAlias;
    this.newAlias = newAlias;
}
public String visit(String table){
    return table.equals(oldAlias) ? newAlias : table;
}


}