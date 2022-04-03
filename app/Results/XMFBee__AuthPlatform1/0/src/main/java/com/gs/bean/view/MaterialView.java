package com.gs.bean.view;
 import com.gs.bean;
import com.gs.Interface.User;
public class MaterialView {

 private  String carPlate;

 private  String recordId;

 private  String accId;

 private  String accName;

 private  int accTotal;

 private  int needAccCount;

 private  int useAccCount;

 private  int returnAccCount;

 private  User user;


public void setCarPlate(String carPlate){
    this.carPlate = carPlate;
}


public void setRecordId(String recordId){
    this.recordId = recordId;
}


public int getAccTotal(){
    return accTotal;
}


public int getReturnAccCount(){
    return returnAccCount;
}


public String getAccId(){
    return accId;
}


public String getAccName(){
    return accName;
}


public User getUser(){
    return user;
}


public void setAccName(String accName){
    this.accName = accName;
}


public void setAccId(String accId){
    this.accId = accId;
}


public void setReturnAccCount(int returnAccCount){
    this.returnAccCount = returnAccCount;
}


public void setNeedAccCount(int needAccCount){
    this.needAccCount = needAccCount;
}


public String getRecordId(){
    return recordId;
}


public void setAccTotal(int accTotal){
    this.accTotal = accTotal;
}


public String getCarPlate(){
    return carPlate;
}


public int getNeedAccCount(){
    return needAccCount;
}


public void setUseAccCount(int useAccCount){
    this.useAccCount = useAccCount;
}


public int getUseAccCount(){
    return useAccCount;
}


public void setUser(User user){
    this.user = user;
}


}