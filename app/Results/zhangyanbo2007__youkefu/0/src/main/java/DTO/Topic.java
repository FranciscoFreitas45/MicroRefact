package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
public class Topic {

 private  long serialVersionUID;

 private  String id;

 private  String sessionid;

 private  String title;

 private  String content;

 private  String weixin;

 private  String email;

 private  String sms;

 private  String tts;

 private  float price;

 private  String keyword;

 private  String summary;

 private  boolean anonymous;

 private  Date begintime;

 private  Date endtime;

 private  boolean top;

 private  boolean essence;

 private  boolean accept;

 private  boolean finish;

 private  List<String> silimar;

 private  int answers;

 private  int views;

 private  int followers;

 private  int collections;

 private  int comments;

 private  boolean mobile;

 private  String status;

 private  String tptype;

 private  String cate;

 private  String username;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String memo;

 private  String organ;

 private  String aiid;

 private  User user;

 private  String relevance;


public int getCollections(){
    return collections;
}


public String getStatus(){
    return status;
}


public String getTitle(){
    return title;
}


public int getComments(){
    return comments;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getSms(){
    return sms;
}


public String getWeixin(){
    return weixin;
}


public String getMemo(){
    return memo;
}


public String getOrgan(){
    return organ;
}


public String getTts(){
    return tts;
}


public String getCate(){
    return cate;
}


public String getEmail(){
    return email;
}


public String getAiid(){
    return aiid;
}


public String getTptype(){
    return tptype;
}


public String getContent(){
    return content;
}


@Transient
public User getUser(){
    return user;
}


public String getRelevance(){
    return relevance;
}


public Date getBegintime(){
    return begintime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public int getFollowers(){
    return followers;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getCreater(){
    return creater;
}


@Transient
public String getSessionid(){
    return sessionid;
}


public String getSummary(){
    return summary;
}


public float getPrice(){
    return price;
}


public String getKeyword(){
    return keyword;
}


public int getAnswers(){
    return answers;
}


@Column(name = "sviews")
public int getViews(){
    return views;
}


public String getOrgi(){
    return orgi;
}


@Transient
public List<String> getSilimar(){
    return silimar;
}


public Date getEndtime(){
    return endtime;
}


}