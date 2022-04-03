package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IDataSearchController {

 private IDataSearch idatasearch;


@GetMapping
("/textSearcher")
public BaseQueryResult<T> textSearcher(@RequestParam(name = "queryParams") QueryParams<T> queryParams){
  return idatasearch.textSearcher(queryParams);
}


@GetMapping
("/phraseSearcher")
public BaseQueryResult<T> phraseSearcher(@RequestParam(name = "queryParams") QueryParams<T> queryParams){
  return idatasearch.phraseSearcher(queryParams);
}


@GetMapping
("/textHistogramSearcher")
public BaseQueryResult<T> textHistogramSearcher(@RequestParam(name = "queryParams") QueryParams<T> queryParams){
  return idatasearch.textHistogramSearcher(queryParams);
}


}