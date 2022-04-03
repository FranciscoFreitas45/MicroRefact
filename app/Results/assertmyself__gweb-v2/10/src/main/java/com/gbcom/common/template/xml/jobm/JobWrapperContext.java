package com.gbcom.common.template.xml.jobm;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("JobWrapperContext")
public class JobWrapperContext {

 private  List<JobWrapperInfo> list;


public List<JobWrapperInfo> getList(){
    return list;
}


public void setList(List<JobWrapperInfo> list){
    this.list = list;
}


}