package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AggsHistogramParamsController {

 private AggsHistogramParams aggshistogramparams;

 private AggsHistogramParams aggshistogramparams;


@PutMapping
("/setAggsField")
public void setAggsField(@RequestParam(name = "aggsField") String aggsField){
aggshistogramparams.setAggsField(aggsField);
}


@PutMapping
("/setInterval")
public void setInterval(@RequestParam(name = "interval") DateHistogramInterval interval){
aggshistogramparams.setInterval(interval);
}


@PutMapping
("/setTimeZone")
public void setTimeZone(@RequestParam(name = "timeZone") String timeZone){
aggshistogramparams.setTimeZone(timeZone);
}


@PutMapping
("/setFormat")
public void setFormat(@RequestParam(name = "format") String format){
aggshistogramparams.setFormat(format);
}


}