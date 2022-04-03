package com.gbcom.common.template.xml.tpl;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("TplInfoContext")
public class TplInfoContext {

 private  List<TplInfo> list;


public List<TplInfo> getList(){
    return list;
}


public void setList(List<TplInfo> list){
    this.list = list;
}


}