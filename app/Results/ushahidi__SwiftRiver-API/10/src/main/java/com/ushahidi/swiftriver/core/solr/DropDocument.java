package com.ushahidi.swiftriver.core.solr;
 import java.util.Date;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;
import com.ushahidi.swiftriver.core.model.Drop;
public class DropDocument {

@Field
 private  String id;

@Field
 private  String channel;

@Field
 private  String title;

@Field
 private  String content;

@Field
 private  Date datePublished;

@Field("riverId")
 private  List<Long> riverIds;

@Field("bucketId")
 private  List<Long> bucketIds;

@Field
 private  List<String> geo;


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public void setRiverIds(List<Long> riverIds){
    this.riverIds = riverIds;
}


public void setTitle(String title){
    this.title = title;
}


public String getId(){
    return id;
}


public void setChannel(String channel){
    this.channel = channel;
}


public List<Long> getRiverIds(){
    return riverIds;
}


public void setBucketIds(List<Long> bucketIds){
    this.bucketIds = bucketIds;
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


public void setDatePublished(Date datePublished){
    this.datePublished = datePublished;
}


public void setId(String id){
    this.id = id;
}


public Date getDatePublished(){
    return datePublished;
}


}