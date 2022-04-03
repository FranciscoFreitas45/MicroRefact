package com.uec.imonitor.es.search;
 import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import com.uec.imonitor.es.bean.params.QueryParams;
public interface IBaseSearch {


public SearchResponse textSearcher(QueryParams<T> queryParams)
;

public SearchResponse phraseSearcher(QueryParams<T> queryParams)
;

public SearchRequestBuilder getSearchRequestBuilder(QueryParams<T> queryParams)
;

public SearchResponse matchAllSearch(QueryParams<T> queryParams)
;

}