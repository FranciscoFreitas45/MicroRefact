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
import com.ukefu.webim.web.model.KbsTopic;
@Component
public class KbsTopicRepositoryImpl implements KbsTopicEsCommonRepository{

 private  ElasticsearchTemplate elasticsearchTemplate;


@Override
public Page<KbsTopic> getTopicByCate(String cate,String q,int p,int ps){
    Page<KbsTopic> pages = null;
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(termQuery("cate", cate));
    if (!StringUtils.isBlank(q)) {
        boolQueryBuilder.must(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND));
    }
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
    searchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("title").fragmentSize(200));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(KbsTopic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, KbsTopic.class, new UKResultMapper());
    }
    return pages;
}


@SuppressWarnings("deprecation")
@Override
public Page<KbsTopic> getTopicByTop(boolean top,int p,int ps){
    Page<KbsTopic> pages = null;
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(termQuery("top", top));
    QueryBuilder beginFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("begintime")).should(QueryBuilders.rangeQuery("begintime").from(new Date().getTime()));
    QueryBuilder endFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("endtime")).should(QueryBuilders.rangeQuery("endtime").to(new Date().getTime()));
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withFilter(QueryBuilders.boolQuery().must(beginFilter).must(endFilter)).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
    searchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("title").fragmentSize(200));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(KbsTopic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, KbsTopic.class, new UKResultMapper());
    }
    return pages;
}


@Override
public List<KbsTopic> getTopicByOrgi(String orgi,String type,String q){
    List<KbsTopic> list = null;
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
    if (elasticsearchTemplate.indexExists(KbsTopic.class)) {
        list = elasticsearchTemplate.queryForList(searchQuery, KbsTopic.class);
    }
    return list;
}


@Autowired
public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate){
    this.elasticsearchTemplate = elasticsearchTemplate;
}


@SuppressWarnings("deprecation")
@Override
public Page<KbsTopic> getTopicByCon(BoolQueryBuilder boolQueryBuilder,int p,int ps){
    Page<KbsTopic> pages = null;
    QueryBuilder beginFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("begintime")).should(QueryBuilders.rangeQuery("begintime").from(new Date().getTime()));
    QueryBuilder endFilter = QueryBuilders.boolQuery().should(QueryBuilders.missingQuery("endtime")).should(QueryBuilders.rangeQuery("endtime").to(new Date().getTime()));
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withFilter(QueryBuilders.boolQuery().must(beginFilter).must(endFilter)).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(KbsTopic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, KbsTopic.class);
    }
    return pages;
}


@Override
public Page<KbsTopic> getTopicByCateAndUser(String cate,String q,String user,int p,int ps){
    Page<KbsTopic> pages = null;
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(termQuery("cate", cate));
    if (!StringUtils.isBlank(q)) {
        boolQueryBuilder.must(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND));
    }
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withQuery(termQuery("creater", user)).withSort(new FieldSortBuilder("top").unmappedType("boolean").order(SortOrder.DESC)).withSort(new FieldSortBuilder("updatetime").unmappedType("date").order(SortOrder.DESC));
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(KbsTopic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, KbsTopic.class, new UKResultMapper());
    }
    return pages;
}


}