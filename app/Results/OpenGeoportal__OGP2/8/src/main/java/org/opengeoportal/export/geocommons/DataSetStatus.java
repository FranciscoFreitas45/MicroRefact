package org.opengeoportal.export.geocommons;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSetStatus {

 private String ogpLayerId;

 private String data_type;

 private String title;

 private String link;

 private String name;

 private String state;

 private int feature_count;

 private String id;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setTitle(String title){
    this.title = title;
}


public String getId(){
    return id;
}


public int getFeature_count(){
    return feature_count;
}


public void setLink(String link){
    this.link = link;
}


public void setFeature_count(int feature_count){
    this.feature_count = feature_count;
}


public String getLink(){
    return link;
}


public String getTitle(){
    return title;
}


public String getState(){
    return state;
}


public void setData_type(String data_type){
    this.data_type = data_type;
}


public String getOgpLayerId(){
    return ogpLayerId;
}


public void setOgpLayerId(String ogpLayerId){
    this.ogpLayerId = ogpLayerId;
}


public void setId(String id){
    this.id = id;
}


public void setState(String state){
    this.state = state;
}


public String getData_type(){
    return data_type;
}


}