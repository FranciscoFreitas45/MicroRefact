package DTO;
 public class WebIMReport {

 private  String data;

 private  long users;

 private  long inviteusers;

 private  long refuseusers;

 private  long ipnums;

 private  long pvnums;


public long getPvnums(){
    return pvnums;
}


public long getInviteusers(){
    return inviteusers;
}


public long getUsers(){
    return users;
}


public long getIpnums(){
    return ipnums;
}


public String getData(){
    return data;
}


public long getRefuseusers(){
    return refuseusers;
}


}