package mondrian.olap.fun.FunUtil;
 import mondrian.calc;
import mondrian.calc.impl;
import mondrian.mdx;
import mondrian.olap;
import mondrian.olap.type;
import mondrian.resource.MondrianResource;
import mondrian.rolap;
import mondrian.util;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.log4j.Logger;
import java.util;
public class NullMember implements Member{


public void setName(String name){
    throw new UnsupportedOperationException();
}


public List<Member> getAncestorMembers(){
    throw new UnsupportedOperationException();
}


public boolean isCalculatedInQuery(){
    throw new UnsupportedOperationException();
}


public int getDepth(){
    throw new UnsupportedOperationException();
}


public String getName(){
    throw new UnsupportedOperationException();
}


public boolean isCalculated(){
    throw new UnsupportedOperationException();
}


public boolean isMeasure(){
    throw new UnsupportedOperationException();
}


public int compareTo(Object o){
    throw new UnsupportedOperationException();
}


public boolean isParentChildLeaf(){
    return false;
}


public Comparable getOrderKey(){
    throw new UnsupportedOperationException();
}


public String getCaption(){
    throw new UnsupportedOperationException();
}


public String getDescription(){
    throw new UnsupportedOperationException();
}


public boolean isEvaluated(){
    throw new UnsupportedOperationException();
}


public Member getDataMember(){
    throw new UnsupportedOperationException();
}


public String getQualifiedName(){
    throw new UnsupportedOperationException();
}


public OlapElement lookupChild(SchemaReader schemaReader,Id.Segment s,MatchType matchType){
    throw new UnsupportedOperationException();
}


public int hashCode(){
    throw new UnsupportedOperationException();
}


public String getPropertyFormattedValue(String propertyName){
    throw new UnsupportedOperationException();
}


public Member getParentMember(){
    throw new UnsupportedOperationException();
}


public Dimension getDimension(){
    throw new UnsupportedOperationException();
}


public int getOrdinal(){
    throw new UnsupportedOperationException();
}


public String getLocalized(LocalizedProperty prop,Locale locale){
    throw new UnsupportedOperationException();
}


public String getParentUniqueName(){
    throw new UnsupportedOperationException();
}


public Property[] getProperties(){
    throw new UnsupportedOperationException();
}


public boolean isAll(){
    return false;
}


public boolean isVisible(){
    throw new UnsupportedOperationException();
}


public boolean isChildOrEqualTo(Member member){
    throw new UnsupportedOperationException();
}


public Map<String,Annotation> getAnnotationMap(){
    throw new UnsupportedOperationException();
}


public boolean isHidden(){
    throw new UnsupportedOperationException();
}


public Level getLevel(){
    throw new UnsupportedOperationException();
}


public Exp getExpression(){
    throw new UnsupportedOperationException();
}


public Object getPropertyValue(String propertyName,boolean matchCase){
    throw new UnsupportedOperationException();
}


public int getSolveOrder(){
    throw new UnsupportedOperationException();
}


public boolean isNull(){
    return true;
}


public void setProperty(String name,Object value){
    throw new UnsupportedOperationException();
}


public boolean equals(Object obj){
    throw new UnsupportedOperationException();
}


public MemberType getMemberType(){
    throw new UnsupportedOperationException();
}


public String getUniqueName(){
    throw new UnsupportedOperationException();
}


public Hierarchy getHierarchy(){
    throw new UnsupportedOperationException();
}


}