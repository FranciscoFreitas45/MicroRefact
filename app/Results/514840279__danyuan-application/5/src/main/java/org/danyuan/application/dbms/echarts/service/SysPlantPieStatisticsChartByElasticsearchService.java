package org.danyuan.application.dbms.echarts.service;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimension;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
@Service
public class SysPlantPieStatisticsChartByElasticsearchService {

@Autowired
 private ElasticsearchTemplate elasticsearchTemplate;

@Value(value = "${elasticsearch.index.name}")
 public  String ELASTICSEARCH_INDEX_NAME;

@Value(value = "${elasticsearch.index.type}")
 public  String ELASTICSEARCH_INDEX_TYPE;

@Value(value = "${elasticsearch.index.amount}")
 public  String ELASTICSEARCH_INDEX_AMOUNT;


public void buildPie(Map<String,Object> map,SysDbmsChartDimension info,QueryBuilder queryBuilder,String type1){
    Client client = elasticsearchTemplate.getClient();
    SearchRequestBuilder requestBuilder = client.prepareSearch(ELASTICSEARCH_INDEX_NAME).setTypes(ELASTICSEARCH_INDEX_TYPE);
    TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms(type1 + "_count").field(type1).size(0);
    requestBuilder.setQuery(queryBuilder).addAggregation(termsAggregationBuilder);
    SearchResponse response = requestBuilder.execute().actionGet();
    Terms aggregation = response.getAggregations().get(type1 + "_count");
    List<String> legend_data = new ArrayList<>();
    List<Map<String, Object>> series_data = new ArrayList<>();
    for (Terms.Bucket bucket : aggregation.getBuckets()) {
        if (bucket.getKey() == null || "".equals(bucket.getKey().toString())) {
            continue;
        }
        legend_data.add(bucket.getKey().toString());
        // {value:92503371, name:'男'}
        Map<String, Object> data = new HashMap<>();
        data.put("value", bucket.getDocCount());
        data.put("name", bucket.getKey().toString());
        series_data.add(data);
    }
    map.put("legend_data", legend_data);
    map.put("series_data", series_data);
    map.put("chartType", info.getChartType());
}


public void buildPieSum(Map<String,Object> map,SysDbmsChartDimension info,QueryBuilder queryBuilder,String type1){
    Client client = elasticsearchTemplate.getClient();
    SearchRequestBuilder requestBuilder = client.prepareSearch(ELASTICSEARCH_INDEX_NAME).setTypes(ELASTICSEARCH_INDEX_TYPE);
    TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms(type1 + "_count").field(type1).size(0).subAggregation(AggregationBuilders.sum(ELASTICSEARCH_INDEX_AMOUNT + "_SUM").field(ELASTICSEARCH_INDEX_AMOUNT));
    requestBuilder.setQuery(queryBuilder).addAggregation(termsAggregationBuilder);
    SearchResponse response = requestBuilder.get();
    Terms aggregation = response.getAggregations().get(type1 + "_count");
    List<String> legend_data = new ArrayList<>();
    List<Map<String, Object>> series_data = new ArrayList<>();
    for (Terms.Bucket bucket : aggregation.getBuckets()) {
        if (bucket.getKey() == null || "".equals(bucket.getKey().toString())) {
            continue;
        }
        legend_data.add(bucket.getKey().toString());
        // {value:92503371, name:'男'}
        Map<String, Object> data = new HashMap<>();
        Sum sum = bucket.getAggregations().get(ELASTICSEARCH_INDEX_AMOUNT + "_SUM");
        data.put("value", sum.getValue());
        data.put("name", bucket.getKey().toString());
        series_data.add(data);
    }
    map.put("legend_data", legend_data);
    map.put("series_data", series_data);
    map.put("chartType", info.getChartType());
}


}