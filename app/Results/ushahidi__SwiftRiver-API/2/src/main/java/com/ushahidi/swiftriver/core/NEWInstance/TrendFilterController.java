package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrendFilterController {

 private TrendFilter trendfilter;

 private TrendFilter trendfilter;


@PutMapping
("/setDateTo")
public void setDateTo(@RequestParam(name = "dateTo") Date dateTo){
trendfilter.setDateTo(dateTo);
}


@PutMapping
("/setDateFrom")
public void setDateFrom(@RequestParam(name = "dateFrom") Date dateFrom){
trendfilter.setDateFrom(dateFrom);
}


@PutMapping
("/setCount")
public void setCount(@RequestParam(name = "count") int count){
trendfilter.setCount(count);
}


@PutMapping
("/setPage")
public void setPage(@RequestParam(name = "page") int page){
trendfilter.setPage(page);
}


}