package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class CreateDropDTO {

 private  String title;

 private  String content;

 private  String channel;

@JsonProperty("source")
 private  Identity identity;

@JsonProperty("date_published")
 private  String datePublished;

@JsonProperty("original_url")
 private  String originalUrl;

@JsonProperty("original_id")
 private  String originalId;

 private  String image;

 private  List<Tag> tags;

 private  List<Link> links;

 private  List<Media> media;

 private  List<Place> places;

@JsonProperty("rivers")
 private  List<Long> riverIds;

@JsonProperty("buckets")
 private  List<Long> bucketIds;

@JsonProperty("mark_as_read")
 private  List<Long> markAsRead;

@JsonProperty("channel_ids")
 private  List<Long> channelIds;

 private  String name;

 private  String username;

 private  String avatar;

@JsonProperty("origin_id")
 private  String originId;

 private  String name;

 private  String tag;

 private  String type;

 private  String url;

 private  String url;

 private  String type;

 private  List<MediaThumbnail> thumbnails;

 private  int size;

 private  String url;

 private  String type;

@JsonProperty("name")
 private  String placeName;

 private  float longitude;

 private  float latitude;


public List<Long> getChannelIds(){
    return channelIds;
}


public String getName(){
    return name;
}


public String getAvatar(){
    return avatar;
}


public List<Tag> getTags(){
    return tags;
}


public void setChannel(String channel){
    this.channel = channel;
}


public String getTitle(){
    return title;
}


public String getChannel(){
    return channel;
}


public List<Place> getPlaces(){
    return places;
}


public void setDatePublished(String datePublished){
    this.datePublished = datePublished;
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


public void setOriginId(String originId){
    this.originId = originId;
}


public void setTitle(String title){
    this.title = title;
}


public List<Long> getRiverIds(){
    return riverIds;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public String getType(){
    return type;
}


public void setLinks(List<Link> links){
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


public List<Link> getLinks(){
    return links;
}


public void setPlaces(List<Place> places){
    this.places = places;
}


public String getContent(){
    return content;
}


public void setTags(List<Tag> tags){
    this.tags = tags;
}


public String getUsername(){
    return username;
}


public List<Long> getMarkAsRead(){
    return markAsRead;
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


public List<Long> getBucketIds(){
    return bucketIds;
}


public void setTag(String tag){
    this.tag = tag;
}


public String getOriginId(){
    return originId;
}


public void setPlaceName(String placeName){
    this.placeName = placeName;
}


public List<Media> getMedia(){
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


public void setRiverIds(List<Long> riverIds){
    this.riverIds = riverIds;
}


public void setMarkAsRead(List<Long> markAsRead){
    this.markAsRead = markAsRead;
}


public void setThumbnails(List<MediaThumbnail> thumbnails){
    this.thumbnails = thumbnails;
}


public void setOriginalUrl(String originalUrl){
    this.originalUrl = originalUrl;
}


public String getTag(){
    return tag;
}


public void setType(String type){
    this.type = type;
}


public void setBucketIds(List<Long> bucketIds){
    this.bucketIds = bucketIds;
}


public int getSize(){
    return size;
}


public Identity getIdentity(){
    return identity;
}


public void setMedia(List<Media> media){
    this.media = media;
}


public void setChannelIds(List<Long> channelIds){
    this.channelIds = channelIds;
}


}