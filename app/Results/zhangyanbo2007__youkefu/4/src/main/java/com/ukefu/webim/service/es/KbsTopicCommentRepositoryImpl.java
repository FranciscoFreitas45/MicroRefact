package com.ukefu.webim.service.es;
 import org.elasticsearch.index.query.QueryBuilders.termQuery;
import java.util.List;
import org.apache.commons.lang.StringUtils;
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
import com.ukefu.webim.web.model.KbsTopicComment;
import com.ukefu.webim.web.model.Topic;
@Component
public class KbsTopicCommentRepositoryImpl implements KbsTopicCommentEsCommonRepository{

 private  ElasticsearchTemplate elasticsearchTemplate;


@Override
public Page<KbsTopicComment> findByDataid(String id,int p,int ps){
    Page<KbsTopicComment> pages = null;
    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("dataid", id)).withSort(new FieldSortBuilder("optimal").unmappedType("boolean").order(SortOrder.DESC)).withSort(new FieldSortBuilder("updatetime").unmappedType("date").order(SortOrder.DESC)).build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(KbsTopicComment.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, KbsTopicComment.class);
    }
    return pages;
}


@Override
public Page<KbsTopicComment> findByCon(NativeSearchQueryBuilder searchQueryBuilder,String q,int p,int ps){
    searchQueryBuilder.withPageable(new PageRequest(p, ps)).withSort(new FieldSortBuilder("updatetime").unmappedType("date").order(SortOrder.DESC));
    searchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("content").fragmentSize(200));
    if (!StringUtils.isBlank(q)) {
        searchQueryBuilder.withQuery(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND));
    }
    return elasticsearchTemplate.queryForPage(searchQueryBuilder.build(), KbsTopicComment.class, new UKResultMapper());
}


@Autowired
public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate){
    this.elasticsearchTemplate = elasticsearchTemplate;
}


@Override
public List<KbsTopicComment> findByOptimal(String dataid){
    List<KbsTopicComment> commentList = null;
    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("dataid", dataid)).withQuery(termQuery("optimal", true)).build();
    if (elasticsearchTemplate.indexExists(KbsTopicComment.class)) {
        commentList = elasticsearchTemplate.queryForList(searchQuery, KbsTopicComment.class);
    }
    return commentList;
}


@Override
public Page<KbsTopicComment> countByCon(NativeSearchQueryBuilder searchQueryBuilder,String q,int p,int ps){
    Page<KbsTopicComment> pages = null;
    if (!StringUtils.isBlank(q)) {
        searchQueryBuilder.withQuery(new QueryStringQueryBuilder(q).defaultOperator(Operator.AND));
    }
    SearchQuery searchQuery = searchQueryBuilder.build().setPageable(new PageRequest(p, ps));
    if (elasticsearchTemplate.indexExists(Topic.class)) {
        pages = elasticsearchTemplate.queryForPage(searchQuery, KbsTopicComment.class, new UKAggResultExtractor("creater"));
    }
    return pages;
}


}