package com.gbcom.common.drools.domain;
 public class DrlRuleContext {

 public  int RULE_CONTEXT_TYPE_EQ;

 public  int RULE_CONTEXT_TYPE_GE;

 public  int RULE_CONTEXT_TYPE_LE;

 public  int RULE_CONTEXT_TYPE_NOT;

 private  Long id;

 private  String item;

 private  int type;

 private  String context;

 private  DrlRule drl;


public DrlRule getDrl(){
    return drl;
}


public int getType(){
    return type;
}


public void setContext(String context){
    this.context = context;
}


public void setId(Long id){
    this.id = id;
}


public String getItem(){
    return item;
}


public Long getId(){
    return id;
}


public void setType(int type){
    this.type = type;
}


public String getContext(){
    return context;
}


public void setItem(String item){
    this.item = item;
}


public void setDrl(DrlRule drl){
    this.drl = drl;
}


}