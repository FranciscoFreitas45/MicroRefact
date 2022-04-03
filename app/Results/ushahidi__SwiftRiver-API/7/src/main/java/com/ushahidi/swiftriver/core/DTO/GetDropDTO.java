package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetDropDTO {

 private  long id;

 private  String title;

 private  String content;

 private  String channel;

 private  Identity identity;

 private  String datePublished;

 private  int userScore;

 private  String originalUrl;

 private  String originalId;

 private  int commentCount;

 private  String image;

 private  List<Bucket> buckets;

 private  List<GetTagDTO> tags;

 private  List<GetLinkDTO> links;

 private  List<GetMediaDTO> media;

 private  List<GetPlaceDTO> places;

 private  Boolean read;

 private  List<FormValueDTO> forms;

 private  Long trackingId;

 private  long id;

 private  String name;

 private  String username;

 private  String avatar;

 private  long id;

 private  String name;

 private  long id;

 private  String tag;

 private  String type;

 private  long id;

 private  String url;

 private  long id;

 private  String url;

 private  String type;

 private  List<MediaThumbnail> thumbnails;

 private  int size;

 private  String url;

 private  long id;

 private  String type;

 private  String placeName;

 private  float longitude;

 private  float latitude;


public String getName(){
    return name;
}


public String getAvatar(){
    return avatar;
}


public Boolean getRead(){
    return read;
}


public List<GetTagDTO> getTags(){
    return tags;
}


public void setForms(List<FormValueDTO> forms){
    this.forms = forms;
}


public String getTitle(){
    return title;
}


public String getChannel(){
    return channel;
}


public List<FormValueDTO> getForms(){
    return forms;
}


public List<GetPlaceDTO> getPlaces(){
    return places;
}


public void setId(long id){
    this.id = id;
}


public String getDatePublished(){
    return datePublished;
}


public String getOriginalUrl(){
    return originalUrl;
}


public void setTrackingId(Long trackingId){
    this.trackingId = trackingId;
}


public int getUserScore(){
    return userScore;
}


public void setBuckets(List<Bucket> buckets){
    this.buckets = buckets;
}


public List<Bucket> getBuckets(){
    return buckets;
}


public String getUrl(){
    return url;
}


public int getCommentCount(){
    return commentCount;
}


public String getType(){
    return type;
}


public String getOriginalId(){
    return originalId;
}


public String getImage(){
    return image;
}


public void setName(String name){
    this.name = name;
}


public List<MediaThumbnail> getThumbnails(){
    return thumbnails;
}


public String getPlaceName(){
    return placeName;
}


public List<GetLinkDTO> getLinks(){
    return links;
}


public Long getTrackingId(){
    return trackingId;
}


public String getContent(){
    return content;
}


public long getId(){
    return id;
}


public String getUsername(){
    return username;
}


public void setOriginalId(String originalId){
    this.originalId = originalId;
}


public void setRead(Boolean read){
    this.read = read;
}


public void setPlaceName(String placeName){
    this.placeName = placeName;
}


public List<GetMediaDTO> getMedia(){
    return media;
}


public float getLongitude(){
    return longitude;
}


public float getLatitude(){
    return latitude;
}


public void setCommentCount(int commentCount){
    this.commentCount = commentCount;
}


public String getTag(){
    return tag;
}


public void setOriginalUrl(String originalUrl){
    this.originalUrl = originalUrl;
}


public int getSize(){
    return size;
}


public Identity getIdentity(){
    return identity;
}


}