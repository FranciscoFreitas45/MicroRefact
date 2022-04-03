package com.ukefu.util.es;
 import com.ukefu.webim.web.model.EkmDimension;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.User;
public class EkmDataBean {

 private  long serialVersionUID;

 private  User user;

 private  Organ organ;

 private  EkmDimension ekmdimension;

 private  String type;

 private  String id;

 private  int docs;


public void setOrgan(Organ organ){
    this.organ = organ;
}


public Organ getOrgan(){
    return organ;
}


public void setDocs(int docs){
    this.docs = docs;
}


public String getType(){
    return type;
}


public EkmDimension getEkmdimension(){
    return ekmdimension;
}


public User getUser(){
    return user;
}


public void setEkmdimension(EkmDimension ekmdimension){
    this.ekmdimension = ekmdimension;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setUser(User user){
    this.user = user;
}


public void setType(String type){
    this.type = type;
}


public int getDocs(){
    return docs;
}


}