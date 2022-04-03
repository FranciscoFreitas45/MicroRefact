package com.jeecg.demo.page;
 import java.util.List;
import com.jeecg.demo.entity.JformOrderCustomerEntity;
public class JformOrderCustomerPage {

 private  List<JformOrderCustomerEntity> demos;


public void setDemos(List<JformOrderCustomerEntity> demos){
    this.demos = demos;
}


public List<JformOrderCustomerEntity> getDemos(){
    return demos;
}


}