package com.gs.common.entity;
 import java.util.ArrayList;
import java.util.List;
public class EchartData {

 public  List<String> legend;

 public  List<Double> category;

 public  List<Series> series;

public EchartData(List<String> legendList, List<Double> categoryList, List<Series> seriesList) {
    super();
    this.legend = legendList;
    this.category = categoryList;
    this.series = seriesList;
}
}