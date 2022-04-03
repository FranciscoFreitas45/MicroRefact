package org.jeecgframework.core.common.model.common;
 import java.util.List;
import org.jeecgframework.core.util.PropertiesUtil;
import DTO.PropertiesUtil;
public class QueryCondition {

 private Integer id;

 private String state;

 private String field;

 private String type;

 private String condition;

 private String value;

 private String relation;

 private List<QueryCondition> children;


public void setField(String field){
    this.field = field;
}


public String getField(){
    return field;
}


public Integer getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public List<QueryCondition> getChildren(){
    return children;
}


public String getValue(){
    return value;
}


public String getCondition(){
    return condition;
}


public void setCondition(String condition){
    this.condition = condition;
}


public String getState(){
    return state;
}


public String getType(){
    return type;
}


public void setValue(String value){
    this.value = value;
}


public void setRelation(String relation){
    this.relation = relation;
}


public void setId(Integer id){
    this.id = id;
}


public void setState(String state){
    this.state = state;
}


public String toString(){
    // update-begin-author:jiaqiankun date:20180704 for:TASK #2881 【bug】高级查询JS错误普遍存在的问题
    if (field == null || "".equals(field)) {
        return "";
    }
    // update-end-author:jiaqiankun date:20180704 for:TASK #2881 【bug】高级查询JS错误普遍存在的问题
    StringBuffer sb = new StringBuffer();
    sb.append(this.relation).append(" ");
    sb.append(this.field).append(" ").append(this.condition).append(" ");
    if ("Integer".equals(this.type) || "BigDecimal".equals(this.type) || "Double".equals(this.type) || "Long".equals(this.type)) {
        // TODO 需要按类型处理
        sb.append(this.value);
    } else if ("Date".equals(this.type)) {
        PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
        String dbtype = util.readProperty("jdbc.url.jeecg");
        if ("oracle".equalsIgnoreCase(dbtype)) {
            sb.append("to_date(");
        }
        // mysql slqserver无须函数，其他数据库情况未处理
        sb.append("'").append(this.value).append("'");
        if ("oracle".equalsIgnoreCase(dbtype)) {
            sb.append(",'yyyy-MM-dd')");
        }
    } else {
        // TODO 需要处理特殊字符
        sb.append("'").append(this.value.replaceAll("'", "\'")).append("'");
    }
    return sb.toString();
}


public String getRelation(){
    return relation;
}


public void setChildren(List<QueryCondition> children){
    this.children = children;
}


}