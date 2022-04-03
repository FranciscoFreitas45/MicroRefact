package com.ukefu.webim.web.model;
 import java.util.List;
import com.ukefu.webim.util.server.message.OtherMessageItem;
public class MessageOutContent extends MessageInContent{

 private  long serialVersionUID;

 private  List<OtherMessageItem> suggest;


public List<OtherMessageItem> getSuggest(){
    return suggest;
}


public void setSuggest(List<OtherMessageItem> suggest){
    this.suggest = suggest;
}


}