package com.crazy.chapter8.duplicate.list;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class CompModelList {


public void main(String[] args){
    List<CompModel> list = new ArrayList<>();
    CompModel model1 = new CompModel();
    model1.setAge(10);
    model1.setName("libai");
    CompModel model2 = new CompModel();
    model2.setAge(30);
    model2.setName("qianyuan");
    list.add(model1);
    list.add(model2);
    Collections.sort(list);
    System.out.println(list);
}


}