package com.ukefu.webim.service.es;
 import org.elasticsearch.index.query.QueryBuilders.termQuery;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;
import com.ukefu.webim.service.repository.XiaoEUKResultMapper;
import com.ukefu.webim.web.model.Topic;
@Component
public class TopicRepositoryImpl implements TopicEsCommonRepository{

 private  ElasticsearchTemplate elasticsearchTemplate;


@Override
public Page<Topic> getTopicByCateAndOrgi(String cate,String orgi,String q,int p,int ps){
    Page<Topic> pages = null;
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    if (!StringUtils.isBlank(cate)) {
        boolQueryBuilder.must(termQuery("cate", cate));
    }
    boolQueryBuilder.must(termQuery("orgi", orgi));
    if (!StringUtils.isBlank(q)) {
        boolQueryBuilder.must(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND));
    }
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("top").unmappedType("boolean").order(SortOrder.DESC)).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
    searchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("title").fragmentSize(200));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(Topic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, Topic.class, new XiaoEUKResultMapper());
    }
    return pages;
}


@Override
public List<Topic> getTopicByOrgi(String orgi,String type,String q){
    List<Topic> list = null;
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(termQuery("orgi", orgi));
    if (!StringUtils.isBlank(type)) {
        boolQueryBuilder.must(termQuery("cate", type));
    }
    if (!StringUtils.isBlank(q)) {
        boolQueryBuilder.must(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND));
    }
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("top").unmappedType("boolean").order(SortOrder.DESC)).withSort(new FieldSortBuilder("updatetime").unmappedType("date").order(SortOrder.DESC));
    SearchQuery searchQuery = searchQueryBuilder.build();
    if (elasticsearchTemplate.indexExists(Topic.class)) {
        list = elasticsearchTemplate.queryForList(searchQuery, Topic.class);
    }
    return list;
}


@Autowired
public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate){
    this.elasticsearchTemplate = elasticsearchTemplate;
}


@SuppressWarnings("deprecation")
@Override
public Page<Topic> getTopicByTopAndOrgi(boolean top,String orgi,String aiid,int p,int ps){
    Page<Topic> pages = null;
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(termQuery("top", top));
    boolQueryBuilder.must(termQuery("orgi", orgi));
    if (!StringUtils.isBlank(aiid)) {
        boolQueryBuilder.must(termQuery("aiid", aiid));
    }
    QueryBuilder beginFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("begintime")).should(QueryBuilders.rangeQuery("begintime").to(new Date().getTime()));
    QueryBuilder endFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("endtime")).should(QueryBuilders.rangeQuery("endtime").from(new Date().getTime()));
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withFilter(QueryBuilders.boolQuery().must(beginFilter).must(endFilter)).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
    searchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("title").fragmentSize(200));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(Topic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, Topic.class, new XiaoEUKResultMapper());
    }
    return pages;
}


@SuppressWarnings("deprecation")
@Override
public Page<Topic> getTopicByCon(BoolQueryBuilder boolQueryBuilder,int p,int ps){
    Page<Topic> pages = null;
    QueryBuilder beginFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("begintime")).should(QueryBuilders.rangeQuery("begintime").to(new Date().getTime()));
    QueryBuilder endFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("endtime")).should(QueryBuilders.rangeQuery("endtime").from(new Date().getTime()));
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withFilter(QueryBuilders.boolQuery().must(beginFilter).must(endFilter)).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(Topic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, Topic.class);
    }
    return pages;
}


@Override
public Page<Topic> getTopicByCateAndUser(String cate,String q,String user,int p,int ps){
    Page<Topic> pages = null;
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(termQuery("cate", cate));
    if (!StringUtils.isBlank(q)) {
        boolQueryBuilder.must(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND));
    }
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withQuery(termQuery("creater", user)).withSort(new FieldSortBuilder("top").unmappedType("boolean").order(SortOrder.DESC)).withSort(new FieldSortBuilder("updatetime").unmappedType("date").order(SortOrder.DESC));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(Topic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, Topic.class, new XiaoEUKResultMapper());
    }
    return pages;
}


}