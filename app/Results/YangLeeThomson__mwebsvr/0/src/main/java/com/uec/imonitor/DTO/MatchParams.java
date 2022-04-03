package com.uec.imonitor.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

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


public String getAnaylzer(){
    return anaylzer;
}


public List<String> getFieldList(){
    return fieldList;
}


public String getFieldOpt(){
    return fieldOpt;
}


public Type getType(){
    return type;
}


public String getOutOpt(){
    return outOpt;
}


public List<String> getValueList(){
    return valueList;
}


public void setOutOpt(String outOpt){
    this.outOpt = outOpt;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOutOpt"))

.queryParam("outOpt",outOpt)
;
restTemplate.put(builder.toUriString(),null);
}


}