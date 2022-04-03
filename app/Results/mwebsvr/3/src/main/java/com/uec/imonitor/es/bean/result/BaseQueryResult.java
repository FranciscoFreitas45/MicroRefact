package com.uec.imonitor.es.bean.result;
 import java.util.List;
import java.util.Map;
import org.elasticsearch.common.unit.TimeValue;
public class BaseQueryResult {

 private  long total;

 private  TimeValue timeTook;

 private  float maxScore;

 private  List<T> resultList;

 private  Map<String,Long> aggsMap;

/**
 * <br/>Description:无参构造函数
 * <p>Author:jlchen/陈金梁</p>
 */
public BaseQueryResult() {
}/**
 * <br/>Description: 带参构造函数
 * <p>Author:jlchen/陈金梁</p>
 * @param total
 * @param timeTook
 * @param maxScore
 */
public BaseQueryResult(long total, TimeValue timeTook, float maxScore) {
    this.total = total;
    this.timeTook = timeTook;
    this.maxScore = maxScore;
}
public void setTotal(long total){
    this.total = total;
}


public TimeValue getTimeTook(){
    return timeTook;
}


public void setMaxScore(float maxScore){
    this.maxScore = maxScore;
}


public void setTimeTook(TimeValue timeTook){
    this.timeTook = timeTook;
}


public List<T> getResultList(){
    return resultList;
}


public void setResultList(List<T> resultList){
    this.resultList = resultList;
}


public void setAggsMap(Map<String,Long> aggsMap){
    this.aggsMap = aggsMap;
}


public float getMaxScore(){
    return maxScore;
}


public Map<String,Long> getAggsMap(){
    return aggsMap;
}


public long getTotal(){
    return total;
}


}