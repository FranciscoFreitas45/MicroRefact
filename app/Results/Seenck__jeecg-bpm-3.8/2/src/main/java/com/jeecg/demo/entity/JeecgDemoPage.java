package com.jeecg.demo.entity;
 import java.util.List;
public class JeecgDemoPage {

 private  List<JeecgDemoEntity> demos;


public void setDemos(List<JeecgDemoEntity> demos){
    this.demos = demos;
}


public List<JeecgDemoEntity> getDemos(){
    return demos;
}


}