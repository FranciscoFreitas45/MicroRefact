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


public String getId(){
    return id;
}


public List<Long> getRiverIds(){
    return riverIds;
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


public Date getDatePublished(){
    return datePublished;
}


}