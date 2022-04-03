package com.ukefu.util.event;
 import com.ukefu.webim.service.hibernate.BaseService;
public class MultiUpdateEvent implements UserEvent{

 private  long serialVersionUID;

 private  S data;

 private  BaseService<?> crudRes;

 private  String eventype;

public MultiUpdateEvent(S data, BaseService<?> crudRes, String eventype) {
    this.data = data;
    this.crudRes = crudRes;
    this.eventype = eventype;
}
public void setEventype(String eventype){
    this.eventype = eventype;
}


public BaseService<?> getCrudRes(){
    return crudRes;
}


public String getEventype(){
    return eventype;
}


public void setData(S data){
    this.data = data;
}


public S getData(){
    return data;
}


public void setCrudRes(BaseService<?> crudRes){
    this.crudRes = crudRes;
}


}