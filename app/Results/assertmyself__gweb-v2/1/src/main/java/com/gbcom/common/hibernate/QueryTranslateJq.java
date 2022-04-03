package com.gbcom.common.hibernate;
 import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import flexjson.JSONDeserializer;
public class QueryTranslateJq {

 private  String basicQry;

 private  String dot;

 private  String blank;

 private  Group group;

 private  QueryCondition queryCondition;

/**
 * QueryTranslateJq
 * @param basicHql String
 * @param group  group
 */
public QueryTranslateJq(String basicHql, Group group) {
    this.basicQry = basicHql;
    this.group = group;
}/**
 * QueryTranslateJq
 * @param basicHql String
 * @param queryCondition QueryCondition
 */
public QueryTranslateJq(String basicHql, QueryCondition queryCondition) {
    this.basicQry = basicHql;
    this.queryCondition = queryCondition;
    this.group = queryCondition.getGroup();
}/**
 * 根据json格式的字符串进行构造
 * @param basicHql String
 * @param filters String
 */
public QueryTranslateJq(String basicHql, String filters) {
    this.basicQry = basicHql;
    if (!StringUtils.isEmpty(filters)) {
        this.queryCondition = new JSONDeserializer<QueryCondition>().use(null, QueryCondition.class).deserialize(filters);
        if (this.queryCondition != null) {
            this.group = this.queryCondition.getGroup();
        }
    }
}
public void setGroup(Group group){
    this.group = group;
}


public String getStringForRule(Rule rule){
    String code = rule.getOp();
    String operator = JqGridOpsEnum.getJqGridOpsEnumByCode(rule.getOp()).getOperator();
    String ret;
    String val = rule.getData();
    try {
        // 对通过servlet传递过来的数据进行UTF-8编码
        val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
    } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    String[] numtypes = { "int", "integer", "float", "number", "currency" };
    String[] datetypes = { "date", "datetime" };
    if (JqGridOpsEnum.BW.getCode().equals(code) || JqGridOpsEnum.BN.getCode().equals(code)) {
        val = val + "%";
    }
    if (JqGridOpsEnum.EW.getCode().equals(code) || JqGridOpsEnum.EN.getCode().equals(code)) {
        val = "%" + val;
    }
    if (JqGridOpsEnum.CN.getCode().equals(code) || JqGridOpsEnum.NC.getCode().equals(code)) {
        val = "%" + val + "%";
    }
    if (JqGridOpsEnum.IN.getCode().equals(code) || JqGridOpsEnum.NI.getCode().equals(code)) {
        val = " (" + val + ")";
    }
    if (ArrayUtils.indexOf(numtypes, rule.getSearchtype()) != -1 || JqGridOpsEnum.NN.getCode().equals(code) || JqGridOpsEnum.NU.getCode().equals(code)) {
        ret = rule.getField() + " " + operator + " " + val;
    } else if (ArrayUtils.indexOf(datetypes, rule.getSearchtype()) != -1) {
        ret = processDataType(rule);
    } else {
        ret = rule.getField() + " " + operator + " \'" + val + "\'";
    // ret = rule.getField() + " " + operator + " " + val + "";
    }
    return ret;
}


public Group getGroup(){
    return group;
}


public void setBasicQry(String basicSql){
    this.basicQry = basicSql;
}


public String getStringForGroup(Group group){
    if (group == null) {
        return "";
    }
    StringBuilder s = new StringBuilder();
    List<Group> groups = group.getGroups();
    s.append("(");
    if (groups != null && groups.size() > 0) {
        for (int i = 0; i < groups.size(); i++) {
            Group gp = groups.get(i);
            if (s.length() > 1) {
                s.append(" " + gp.getGroupOp() + " ");
            }
            s.append(this.getStringForGroup(gp));
        }
    }
    List<Rule> rules = group.getRules();
    if (rules != null && rules.size() > 0) {
        for (int i = 0; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            if (isNullRule(rule)) {
                continue;
            }
            if (s.length() > 1) {
                s.append(" " + group.getGroupOp() + " ");
            }
            s.append(this.getStringForRule(rule));
        }
    }
    s.append(")");
    if ("()".equals(s.toString())) {
        return "";
    } else {
        return s.toString();
    }
}


public String toString(){
    if (StringUtils.isEmpty(basicQry)) {
        return "";
    }
    int orderByLocation = basicQry.toUpperCase().indexOf("ORDER BY");
    String orderByClase = "";
    String ret = basicQry;
    if (orderByLocation > -1) {
        ret = basicQry.substring(0, orderByLocation);
        orderByClase = basicQry.substring(orderByLocation, basicQry.length());
    } else {
        // 默认按第1个字段排序，如果不加排序，那么在分页时会有重复记录出现
        // todo 如果排序的字段对应的值相同，那么在分页时也可能有重复记录，最好是再按ID排序
        orderByClase = "order by 1";
    }
    if (queryCondition != null && StringUtils.isNotEmpty(queryCondition.getOrderColumn()) && StringUtils.isNotEmpty(queryCondition.getOrderType())) {
        orderByClase = "order by " + queryCondition.getOrderColumn() + blank + queryCondition.getOrderType();
    }
    String coditionString = getStringForGroup(this.group);
    if (!StringUtils.isEmpty(coditionString)) {
        if (!"".equals(coditionString)) {
            if (basicQry.toUpperCase().indexOf("WHERE") == -1) {
                ret = ret + " WHERE ";
            }
            if (!basicQry.endsWith(" ")) {
                ret = ret + " ";
            }
            if (!ret.trim().toUpperCase().endsWith("WHERE")) {
                ret = ret + " AND ";
            }
            ret += getStringForGroup(this.group);
        }
    }
    ret += blank + orderByClase;
    return ret;
}


public String processDataType(Rule rule){
    String code = rule.getOp();
    String operator = JqGridOpsEnum.getJqGridOpsEnumByCode(rule.getOp()).getOperator();
    String data = rule.getData().trim();
    if (data.length() <= 10) {
        data += " 00:00:00";
    }
    // 当小于等于（<=）某个时间或者大于(>)某个时间时，必须设置第1个值的最大时间
    if (JqGridOpsEnum.LE.getCode().equals(code) || JqGridOpsEnum.GT.getCode().equals(code)) {
        data = StringUtils.replace(data, "00:00:00", "23:59:59");
    } else if (JqGridOpsEnum.EQ.getCode().equals(code)) {
        // 当天查询使用like
        data = StringUtils.replace(data, " 00:00:00", " %");
        operator = " like ";
    } else if (JqGridOpsEnum.NE.getCode().equals(code)) {
        // 非当天查询使用not like
        data = StringUtils.replace(data, " 00:00:00", " %");
        operator = " not like ";
    }
    return rule.getField() + operator + "'" + data + "'";
}


public boolean isNullRule(Rule rule){
    return StringUtils.isEmpty(rule.getData()) && !JqGridOpsEnum.NN.getCode().equals(rule.getOp()) && !JqGridOpsEnum.NU.getCode().equals(rule.getOp());
}


public String getBasicQry(){
    return basicQry;
}


}