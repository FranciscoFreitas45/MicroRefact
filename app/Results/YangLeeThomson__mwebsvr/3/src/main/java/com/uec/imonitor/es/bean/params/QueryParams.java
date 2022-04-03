package com.uec.imonitor.es.bean.params;
 import java.util.List;
import org.apache.poi.ss.formula.functions.T;
import com.uec.imonitor.common.util.ESConstantUtil;
public class QueryParams extends BaseQueryParams{

 private  String[] ids;

 private  List<MatchParams> matchList;

 private  List<RangeParams> rangeList;

 private  AggsTermParams aggsParams;

 private  AggsHistogramParams histogramParams;

 private  ExistParams existParams;

 private  Class<T> returnClass;

/**
 * <br/>Description:
 * <p>Author:jlchen/陈金梁</p>
 */
public QueryParams() {
    super(ESConstantUtil.DEFAULT_START, ESConstantUtil.DEFAULT_SIZE, ESConstantUtil.DEFAULT_SORT_PARAMS);
}public QueryParams(Class<T> clazz) {
    this.returnClass = clazz;
}/**
 * <br/>Description:
 * <p>Author:jlchen/陈金梁</p>
 * @param matchList
 * @param rangeList
 */
public QueryParams(List<MatchParams> matchList, List<RangeParams> rangeList, Class<T> clazz) {
    super(ESConstantUtil.DEFAULT_START, ESConstantUtil.DEFAULT_SIZE, ESConstantUtil.DEFAULT_SORT_PARAMS);
    this.matchList = matchList;
    this.returnClass = clazz;
}/**
 * <br/>Description: 构造函数，使用默认的排序-降序和查询条数-0-20条
 * <p>Author:jlchen/陈金梁</p>
 * @param indexArray 查询索引集
 * @param typeArray 查询type类型集
 * @param matchList 查询条件集
 */
public QueryParams(List<MatchParams> matchList, Class<T> clazz) {
    super(ESConstantUtil.DEFAULT_START, ESConstantUtil.DEFAULT_SIZE, ESConstantUtil.DEFAULT_SORT_PARAMS);
    this.matchList = matchList;
    this.returnClass = clazz;
}/**
 * <br/>Description: 构造函数
 * <p>Author:jlchen/陈金梁</p>
 * @param indexArray 查询索引集
 * @param typeArray 查询type类型集
 * @param matchList 内容查询条件集（过滤和关键词检索）
 * @param start 起始条
 * @param size 返回条数
 * @param sortField 排序字段
 * @param sort 排序方式，desc，降序、asc，升序
 */
public QueryParams(List<MatchParams> matchList, int start, int size, List<SortParams> sortList, Class<T> clazz) {
    super(start, size, sortList);
    this.matchList = matchList;
    this.returnClass = clazz;
}/**
 * <br/>Description:构造函数
 * <p>Author:jlchen/陈金梁</p>
 * @param matchList 内容查询条件集（关键词检索）
 * @param rangeList 范围查询（时间范围等）
 * @param aggsParams 聚合查询参数
 * @param start 起始条
 * @param size 返回条数
 * @param sortField 排序字段
 * @param sort 排序方式，desc，降序、asc，升序
 */
public QueryParams(List<MatchParams> matchList, List<RangeParams> rangeList, AggsTermParams aggsParams, int start, int size, List<SortParams> sortList, Class<T> clazz) {
    super(start, size, sortList);
    this.matchList = matchList;
    this.rangeList = rangeList;
    this.aggsParams = aggsParams;
    this.returnClass = clazz;
}
public Class<T> getReturnClass(){
    return returnClass;
}


public AggsHistogramParams getHistogramParams(){
    return histogramParams;
}


public void setExistParams(ExistParams existParams){
    this.existParams = existParams;
}


public void setRangeList(List<RangeParams> rangeList){
    this.rangeList = rangeList;
}


public String[] getIds(){
    return ids;
}


public void setAggsParams(AggsTermParams aggsParams){
    this.aggsParams = aggsParams;
}


public List<MatchParams> getMatchList(){
    return matchList;
}


public void setHistogramParams(AggsHistogramParams histogramParams){
    this.histogramParams = histogramParams;
}


public void setReturnClass(Class<T> returnClass){
    this.returnClass = returnClass;
}


public void setIds(String[] ids){
    this.ids = ids;
}


public void setMatchList(List<MatchParams> matchList){
    this.matchList = matchList;
}


public List<RangeParams> getRangeList(){
    return rangeList;
}


public AggsTermParams getAggsParams(){
    return aggsParams;
}


public ExistParams getExistParams(){
    return existParams;
}


}