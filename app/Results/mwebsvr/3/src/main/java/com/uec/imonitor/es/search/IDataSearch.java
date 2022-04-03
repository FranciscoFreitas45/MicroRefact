package com.uec.imonitor.es.search;
 import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
public interface IDataSearch {


public BaseQueryResult<T> textSearcher(QueryParams<T> queryParams)
;

public BaseQueryResult<T> textHistogramSearcher(QueryParams<T> queryParams)
;

public BaseQueryResult<T> phraseSearcher(QueryParams<T> queryParams)
;

public BaseQueryResult<T> matchAllSearch(QueryParams<T> queryParams)
;

}