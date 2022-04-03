package com.uec.imonitor.peopledaily.bean;
 import java.util.List;
public class PeoplesDaily {

 private  String title;

 private  String tags;

 private  String authors;

 private  String source;

 private  String news_id;

 private  String channel;

 private  Integer original;

 private  String keywords;

 private  String summary;

 private  String content;

 private  List<PeoplesDailyImg> image_list;

 private  String pub_time;

 private  String m_url;

 private  String url;

 private  String source_url;

 private  String entity;

 private  String requestBody;

 private  String org;

 private  Integer verify;

 private  Integer contenttype;

 private  String vidioImg;

 private  Integer to_top;

 private  Integer sort;

 private  List<PeoplesDailyRelated> related;

 private  List<PeoplesDailyVideos> videos;


public void setM_url(String m_url){
    this.m_url = m_url;
}


public String getNews_id(){
    return news_id;
}


public String getPub_time(){
    return pub_time;
}


public String getTags(){
    return tags;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setNews_id(String news_id){
    this.news_id = news_id;
}


public void setEntity(String entity){
    this.entity = entity;
}


public Integer getSort(){
    return sort;
}


public String getTitle(){
    return title;
}


public void setTo_top(Integer to_top){
    this.to_top = to_top;
}


public String getChannel(){
    return channel;
}


public void setOriginal(Integer original){
    this.original = original;
}


public void setVerify(Integer verify){
    this.verify = verify;
}


public void setOrg(String org){
    this.org = org;
}


public void setVidioImg(String vidioImg){
    this.vidioImg = vidioImg;
}


public String getOrg(){
    return org;
}


public List<PeoplesDailyVideos> getVideos(){
    return videos;
}


public String getKeywords(){
    return keywords;
}


public void setTitle(String title){
    this.title = title;
}


public void setPub_time(String pub_time){
    this.pub_time = pub_time;
}


public String getM_url(){
    return m_url;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public void setAuthors(String authors){
    this.authors = authors;
}


public String getSource(){
    return source;
}


public List<PeoplesDailyRelated> getRelated(){
    return related;
}


public String getRequestBody(){
    return requestBody;
}


public void setSource(String source){
    this.source = source;
}


public void setContent(String content){
    this.content = content;
}


public void setVideos(List<PeoplesDailyVideos> videos){
    this.videos = videos;
}


public List<PeoplesDailyImg> getImage_list(){
    return image_list;
}


public String getContent(){
    return content;
}


public String getSource_url(){
    return source_url;
}


public void setTags(String tags){
    this.tags = tags;
}


public Integer getOriginal(){
    return original;
}


public Integer getContenttype(){
    return contenttype;
}


public void setRequestBody(String requestBody){
    this.requestBody = requestBody;
}


public Integer getTo_top(){
    return to_top;
}


public String getAuthors(){
    return authors;
}


public void setRelated(List<PeoplesDailyRelated> related){
    this.related = related;
}


public Integer getVerify(){
    return verify;
}


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return summary;
}


public void setImage_list(List<PeoplesDailyImg> image_list){
    this.image_list = image_list;
}


public void setSource_url(String source_url){
    this.source_url = source_url;
}


public void setContenttype(Integer contenttype){
    this.contenttype = contenttype;
}


public String getEntity(){
    return entity;
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public String getVidioImg(){
    return vidioImg;
}


}