package com.uec.imonitor.es.search.impl;
 import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.map.LinkedMap;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uec.imonitor.common.util.ESUtil;
import com.uec.imonitor.es.bean.params.AggsHistogramParams;
import com.uec.imonitor.es.bean.params.AggsTermParams;
import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.es.search.IBaseSearch;
import com.uec.imonitor.es.search.IDataSearch;
@Service("dataSearch")
public class DataSearchImpl implements IDataSearch{

@Autowired
 private  IBaseSearch baseSearch;


@Override
public BaseQueryResult<T> textSearcher(QueryParams<T> queryParams){
    // 最新新闻聚类检索参数
    AggsTermParams aggsParams = queryParams.getAggsParams();
    // 设置检索源index和类型type
    SearchResponse response = baseSearch.textSearcher(queryParams);
    // 结果集
    SearchHits hits = response.getHits();
    long total = hits.getTotalHits();
    TimeValue timeTook = response.getTook();
    float maxScore = hits.getMaxScore();
    @SuppressWarnings({ "unchecked", "rawtypes" })
    Map<String, Long> aggsMap = new LinkedMap();
    // 分组聚合
    if (null != aggsParams && null != response.getAggregations()) {
        if (response.getAggregations() != null) {
            Terms genders = response.getAggregations().get(aggsParams.getAggName());
            for (Terms.Bucket entry : genders.getBuckets()) {
                // Term
                String term = entry.getKeyAsString();
                // Doc count
                Long count = entry.getDocCount();
                aggsMap.put(term, count);
            }
        }
    }
    Class<T> e = queryParams.getReturnClass();
    List<T> clusterResultList = ESUtil.parseESHits(hits, e);
    BaseQueryResult<T> result = new BaseQueryResult<T>(total, timeTook, maxScore);
    result.setResultList(clusterResultList);
    result.setAggsMap(aggsMap);
    return result;
}


@Override
public BaseQueryResult<T> textHistogramSearcher(QueryParams<T> queryParams){
    // 设置检索源index和类型type
    SearchRequestBuilder srb = baseSearch.getSearchRequestBuilder(queryParams);
    SearchResponse response = srb.execute().actionGet();
    SearchHits hits = response.getHits();
    long total = hits.getTotalHits();
    TimeValue timeTook = response.getTook();
    float maxScore = hits.getMaxScore();
    Map<String, Long> aggsMap = new LinkedMap<String, Long>();
    // 最新新闻聚类检索参数
    AggsHistogramParams histogram = queryParams.getHistogramParams();
    if (histogram != null && response.getAggregations() != null) {
        Histogram genders = response.getAggregations().get(histogram.getAggsName());
        for (Histogram.Bucket entry : genders.getBuckets()) {
            // Term
            String term = entry.getKeyAsString();
            // Doc count
            Long count = entry.getDocCount();
            // System.out.println(term + ", " + count);
            aggsMap.put(term, count);
        }
    }
    Class<T> e = queryParams.getReturnClass();
    List<T> objList = ESUtil.parseESHits(hits, e);
    BaseQueryResult<T> result = new BaseQueryResult<T>(total, timeTook, maxScore);
    result.setAggsMap(aggsMap);
    result.setResultList(objList);
    return result;
}


@Override
public BaseQueryResult<T> phraseSearcher(QueryParams<T> queryParams){
    // 最新新闻聚类检索参数
    AggsTermParams aggsParams = queryParams.getAggsParams();
    // 设置检索源index和类型type
    SearchResponse response = baseSearch.phraseSearcher(queryParams);
    SearchHits hits = response.getHits();
    long total = hits.getTotalHits();
    TimeValue timeTook = response.getTook();
    float maxScore = hits.getMaxScore();
    // 分组聚合
    @SuppressWarnings({ "unchecked", "rawtypes" })
    Map<String, Long> aggsMap = new LinkedMap();
    if (null != aggsParams && null != response.getAggregations()) {
        Terms genders = response.getAggregations().get(aggsParams.getAggName());
        for (Terms.Bucket entry : genders.getBuckets()) {
            // Term
            String term = entry.getKeyAsString();
            // Doc count
            Long count = entry.getDocCount();
            aggsMap.put(term, count);
        }
    }
    Class<T> e = queryParams.getReturnClass();
    List<T> objList = ESUtil.parseESHits(hits, e);
    BaseQueryResult<T> result = new BaseQueryResult<T>(total, timeTook, maxScore);
    result.setResultList(objList);
    result.setAggsMap(aggsMap);
    return result;
}


@Override
public BaseQueryResult<T> matchAllSearch(QueryParams<T> queryParams){
    // Client client = ESUtil.getESClient(); // es连接
    // 设置检索源index和类型type
    SearchResponse response = baseSearch.matchAllSearch(queryParams);
    SearchHits hits = response.getHits();
    long total = hits.getTotalHits();
    TimeValue timeTook = response.getTook();
    float maxScore = hits.getMaxScore();
    Class<T> e = queryParams.getReturnClass();
    List<T> objList = ESUtil.parseESHits(hits, e);
    BaseQueryResult<T> result = new BaseQueryResult<T>(total, timeTook, maxScore);
    result.setResultList(objList);
    return result;
}


}