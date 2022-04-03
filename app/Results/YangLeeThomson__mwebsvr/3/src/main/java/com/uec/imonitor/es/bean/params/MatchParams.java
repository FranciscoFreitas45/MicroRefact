package com.uec.imonitor.es.bean.params;
 import java.util.List;
import org.elasticsearch.index.query.MatchQueryBuilder.Type;
public class MatchParams {

 private  List<String> fieldList;

 private  List<String> valueList;

 private  String anaylzer;

 private  String inOpt;

 private  String outOpt;

 private  String fieldOpt;

 private  Type type;

public MatchParams() {
}/**
 * <br/>Description: 构造函数，默认字段内采用or连接，字段间采用and连接
 * <p>Author:jlchen/陈金梁</p>
 * @param fieldList
 * @param valueList
 */
public MatchParams(List<String> fieldList, List<String> valueList) {
    this.fieldList = fieldList;
    this.valueList = valueList;
}/**
 * <br/>Description:构造函数
 * <p>Author:jlchen/陈金梁</p>
 * @param fieldList
 * @param valueList
 * @param inOpt
 * @param outOpt
 * @param analyzer
 */
public MatchParams(List<String> fieldList, List<String> valueList, String inOpt, String outOpt, String analyzer) {
    this.fieldList = fieldList;
    this.valueList = valueList;
    this.inOpt = inOpt;
    this.outOpt = outOpt;
    this.anaylzer = analyzer;
}/**
 * <br/>Description:构造函数
 * <p>Author:jlchen/陈金梁</p>
 * @param fieldList
 * @param valueList
 * @param inOpt
 * @param outOpt
 * @param fieldOpt
 * @param analyzer
 */
public MatchParams(List<String> fieldList, List<String> valueList, String inOpt, String outOpt, String fieldOpt, String analyzer) {
    this.fieldList = fieldList;
    this.valueList = valueList;
    this.inOpt = inOpt;
    this.outOpt = outOpt;
    this.fieldOpt = fieldOpt;
    this.anaylzer = analyzer;
}
public String getInOpt(){
    return inOpt;
}


public void setFieldOpt(String fieldOpt){
    this.fieldOpt = fieldOpt;
}


public void setInOpt(String inOpt){
    this.inOpt = inOpt;
}


public void setType(Type type){
    this.type = type;
}


public String getAnaylzer(){
    return anaylzer;
}


public void setFieldList(List<String> fieldList){
    this.fieldList = fieldList;
}


public List<String> getFieldList(){
    return fieldList;
}


public void setValueList(List<String> valueList){
    this.valueList = valueList;
}


public String getFieldOpt(){
    return fieldOpt;
}


public Type getType(){
    return type;
}


public void setAnaylzer(String anaylzer){
    this.anaylzer = anaylzer;
}


public void setOutOpt(String outOpt){
    this.outOpt = outOpt;
}


public String getOutOpt(){
    return outOpt;
}


public List<String> getValueList(){
    return valueList;
}


}