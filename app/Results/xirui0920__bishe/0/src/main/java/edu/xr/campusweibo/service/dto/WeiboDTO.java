package edu.xr.campusweibo.service.dto;
 import edu.xr.campusweibo.domain.MyReply;
import edu.xr.campusweibo.domain.Weibo;
import java.io.Serializable;
import java.util.List;
public class WeiboDTO implements Serializable{

 private  Weibo weibo;

 private  String nickname;

 private  String image_url;

 private  List<MyReply> myReplyList;

public WeiboDTO(Weibo weibo, String nickname) {
    this.weibo = weibo;
    this.nickname = nickname;
}public WeiboDTO(Weibo weibo, String nickname, String image_url) {
    this.weibo = weibo;
    this.nickname = nickname;
    this.image_url = image_url;
}public WeiboDTO(Weibo weibo, String nickname, List<MyReply> myReplyList) {
    this.weibo = weibo;
    this.nickname = nickname;
    this.myReplyList = myReplyList;
}public WeiboDTO(Weibo weibo, String nickname, String image_url, List<MyReply> myReplyList) {
    this.weibo = weibo;
    this.nickname = nickname;
    this.image_url = image_url;
    this.myReplyList = myReplyList;
}
public String getNickname(){
    return nickname;
}


public void setImage_url(String image_url){
    this.image_url = image_url;
}


public List<MyReply> getMyReplyList(){
    return myReplyList;
}


public Weibo getWeibo(){
    return weibo;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


@Override
public String toString(){
    return "WeiboDTO{" + "weibo=" + weibo + ", nickname='" + nickname + '\'' + ", image_url='" + image_url + '\'' + ", myReplyList=" + myReplyList + '}';
}


public void setMyReplyList(List<MyReply> myReplyList){
    this.myReplyList = myReplyList;
}


public void setWeibo(Weibo weibo){
    this.weibo = weibo;
}


public String getImage_url(){
    return image_url;
}


}