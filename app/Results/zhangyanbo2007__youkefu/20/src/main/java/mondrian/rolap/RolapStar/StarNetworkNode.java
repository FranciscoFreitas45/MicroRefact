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
public class StarNetworkNode {

 private  StarNetworkNode parent;

 private  MondrianDef.Relation origRel;

 private  String foreignKey;

 private  String joinKey;

private StarNetworkNode(StarNetworkNode parent, String alias, MondrianDef.Relation origRel, String foreignKey, String joinKey) {
    this.parent = parent;
    this.origRel = origRel;
    this.foreignKey = foreignKey;
    this.joinKey = joinKey;
}
public boolean isCompatible(StarNetworkNode compatibleParent,MondrianDef.Relation rel,String compatibleForeignKey,String compatibleJoinKey){
    return parent == compatibleParent && origRel.getClass().equals(rel.getClass()) && foreignKey.equals(compatibleForeignKey) && joinKey.equals(compatibleJoinKey);
}


}