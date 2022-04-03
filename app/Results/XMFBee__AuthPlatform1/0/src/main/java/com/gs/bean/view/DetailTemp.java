package com.gs.bean.view;
 import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainRecord;
import java.util.List;
import com.gs.Interface.MaintainRecord;
public class DetailTemp extends MaintainDetail{

 private  MaintainRecord record;


public void setRecord(MaintainRecord record){
    this.record = record;
}


public MaintainRecord getRecord(){
    return record;
}


}