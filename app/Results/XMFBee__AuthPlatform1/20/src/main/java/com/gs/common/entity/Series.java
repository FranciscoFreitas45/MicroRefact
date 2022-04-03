package com.gs.common.entity;
 import java.util.List;
public class Series {

 public  String name;

 public  String type;

 public  List<Double> data;

public Series(String name, String type, List<Double> data) {
    super();
    this.name = name;
    this.type = type;
    this.data = data;
}
}