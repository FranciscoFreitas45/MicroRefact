package com.uec.imonitor.Interface;
public interface IDataSearch {

   public BaseQueryResult<T> phraseSearcher(QueryParams<T> queryParams);
   public BaseQueryResult<T> textHistogramSearcher(QueryParams<T> queryParams);
}