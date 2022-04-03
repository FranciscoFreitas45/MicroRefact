package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QueryParamsController {

 private QueryParams queryparams;

 private QueryParams queryparams;


@PutMapping
("/setHistogramParams")
public void setHistogramParams(@RequestParam(name = "histogramParams") AggsHistogramParams histogramParams){
queryparams.setHistogramParams(histogramParams);
}


}