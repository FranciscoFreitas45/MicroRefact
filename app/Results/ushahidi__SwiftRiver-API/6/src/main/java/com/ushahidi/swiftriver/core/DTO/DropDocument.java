package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;
import com.ushahidi.swiftriver.core.model.Drop;
public class DropDocument {

 private  String id;

 private  String channel;

 private  String title;

 private  String content;

 private  Date datePublished;

 private  List<Long> riverIds;

 private  List<Long> bucketIds;

 private  List<String> geo;


public String getContent(){
    return content;
}


public void setTitle(String title){
    this.title = title;
}


public String getId(){
    return id;
}


public List<Long> getRiverIds(){
    return riverIds;
}


public void setGeo(List<String> geo){
    this.geo = geo;
}


public String getTitle(){
    return title;
}


public List<String> getGeo(){
    return geo;
}


public String getChannel(){
    return channel;
}


public List<Long> getBucketIds(){
    return bucketIds;
}


public void setId(String id){
    this.id = id;
}


public Date getDatePublished(){
    return datePublished;
}


}