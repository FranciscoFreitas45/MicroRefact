package org.opengeoportal.export.geocommons.SearchResponseJson;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {

@JsonProperty("detail_link")
 private String detail_link;

@JsonProperty("pk")
 private String pk;

@JsonProperty("type")
 private String type;

@JsonProperty("title")
 private String title;

@JsonProperty("data_type")
 private String data_type;

@JsonProperty("link")
 private String link;

@JsonProperty("name")
 private String name;

@JsonProperty("description")
 private String description;

@JsonProperty("feature_count")
 private int feature_count;

@JsonProperty("unique_name")
 private String unique_name;

@JsonProperty("layer_size")
 private int layer_size;

@JsonProperty("published")
 private String published;

@JsonProperty("id")
 private String id;


public void setName(String name){
    this.name = name;
}


public int getLayer_size(){
    return layer_size;
}


public String getName(){
    return name;
}


public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getPublished(){
    return published;
}


public String getDescription(){
    return description;
}


public void setFeature_count(int feature_count){
    this.feature_count = feature_count;
}


public String getDetail_link(){
    return detail_link;
}


public String getTitle(){
    return title;
}


public void setId(String id){
    this.id = id;
}


public void setDetail_link(String detail_link){
    this.detail_link = detail_link;
}


public String getUnique_name(){
    return unique_name;
}


public void setTitle(String title){
    this.title = title;
}


public String getPk(){
    return pk;
}


public void setPublished(String published){
    this.published = published;
}


public void setType(String type){
    this.type = type;
}


public int getFeature_count(){
    return feature_count;
}


public void setLink(String link){
    this.link = link;
}


public void setUnique_name(String unique_name){
    this.unique_name = unique_name;
}


public String getLink(){
    return link;
}


public String getType(){
    return type;
}


public void setData_type(String data_type){
    this.data_type = data_type;
}


public void setPk(String pk){
    this.pk = pk;
}


public void setLayer_size(int layer_size){
    this.layer_size = layer_size;
}


public String getData_type(){
    return data_type;
}


}