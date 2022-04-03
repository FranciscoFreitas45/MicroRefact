package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class GetDropDTO {

 private  long id;

 private  String title;

 private  String content;

 private  String channel;

@JsonProperty("source")
 private  Identity identity;

@JsonProperty("date_published")
 private  String datePublished;

@JsonProperty("user_score")
 private  int userScore;

@JsonProperty("original_url")
 private  String originalUrl;

@JsonProperty("original_id")
 private  String originalId;

@JsonProperty("comment_count")
 private  int commentCount;

 private  String image;

 private  List<Bucket> buckets;

 private  List<GetTagDTO> tags;

 private  List<GetLinkDTO> links;

 private  List<GetMediaDTO> media;

 private  List<GetPlaceDTO> places;

 private  Boolean read;

 private  List<FormValueDTO> forms;

@JsonProperty("tracking_id")
 private  Long trackingId;

 private  long id;

 private  String name;

 private  String username;

 private  String avatar;

@JsonProperty("id")
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

@JsonProperty("name")
 private  String placeName;

 private  float longitude;

 private  float latitude;


public String getName(){
    return name;
}


public String getAvatar(){
    return avatar;
}


public void setUserScore(int userScore){
    this.userScore = userScore;
}


public Boolean getRead(){
    return read;
}


public List<GetTagDTO> getTags(){
    return tags;
}


public void setChannel(String channel){
    this.channel = channel;
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


public void setDatePublished(String datePublished){
    this.datePublished = datePublished;
}


public void setId(long id){
    this.id = id;
}


public String getDatePublished(){
    return datePublished;
}


public void setLongitude(float longitude){
    this.longitude = longitude;
}


public String getOriginalUrl(){
    return originalUrl;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public void setTrackingId(Long trackingId){
    this.trackingId = trackingId;
}


public int getUserScore(){
    return userScore;
}


public void setTitle(String title){
    this.title = title;
}


public void setBuckets(List<Bucket> buckets){
    this.buckets = buckets;
}


public void setUrl(String url){
    this.url = url;
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


public void setLinks(List<GetLinkDTO> links){
    this.links = links;
}


public String getOriginalId(){
    return originalId;
}


public void setIdentity(Identity identity){
    this.identity = identity;
}


public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
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


public void setContent(String content){
    this.content = content;
}


public List<GetLinkDTO> getLinks(){
    return links;
}


public void setPlaces(List<GetPlaceDTO> places){
    this.places = places;
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


public void setTags(List<GetTagDTO> tags){
    this.tags = tags;
}


public String getUsername(){
    return username;
}


public void setLatitude(float latitude){
    this.latitude = latitude;
}


public void setOriginalId(String originalId){
    this.originalId = originalId;
}


public void setSize(int size){
    this.size = size;
}


public void setRead(Boolean read){
    this.read = read;
}


public void setTag(String tag){
    this.tag = tag;
}


public void setPlaceName(String placeName){
    this.placeName = placeName;
}


public List<GetMediaDTO> getMedia(){
    return media;
}


public void setUsername(String username){
    this.username = username;
}


public float getLongitude(){
    return longitude;
}


public float getLatitude(){
    return latitude;
}


public void setThumbnails(List<MediaThumbnail> thumbnails){
    this.thumbnails = thumbnails;
}


public void setCommentCount(int commentCount){
    this.commentCount = commentCount;
}


public String getTag(){
    return tag;
}


public void setType(String type){
    this.type = type;
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


public void setMedia(List<GetMediaDTO> media){
    this.media = media;
}


}