package com.ukefu.util;
 public class WebIMReport {

 private  String data;

 private  long users;

 private  long inviteusers;

 private  long refuseusers;

 private  long ipnums;

 private  long pvnums;


public void setIpnums(long ipnums){
    this.ipnums = ipnums;
}


public long getPvnums(){
    return pvnums;
}


public long getInviteusers(){
    return inviteusers;
}


public void setUsers(long users){
    this.users = users;
}


public void setRefuseusers(long refuseusers){
    this.refuseusers = refuseusers;
}


public void setData(String data){
    this.data = data;
}


public long getUsers(){
    return users;
}


public long getIpnums(){
    return ipnums;
}


public void setPvnums(long pvnums){
    this.pvnums = pvnums;
}


public void setInviteusers(long inviteusers){
    this.inviteusers = inviteusers;
}


public String getData(){
    return data;
}


public long getRefuseusers(){
    return refuseusers;
}


}