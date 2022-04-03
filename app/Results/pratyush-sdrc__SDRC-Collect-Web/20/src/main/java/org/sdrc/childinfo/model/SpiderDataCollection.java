package org.sdrc.childinfo.model;
 import java.util.ArrayList;
import java.util.List;
public class SpiderDataCollection {

 public  List<List<SpiderDataModel>> dataCollection;

public SpiderDataCollection() {
    dataCollection = new ArrayList<List<SpiderDataModel>>();
}
public List<List<SpiderDataModel>> getDataCollection(){
    return dataCollection;
}


public void setDataCollection(List<List<SpiderDataModel>> dataCollection){
    this.dataCollection = dataCollection;
}


}