package com.cym.ext;
 import java.util.List;
import com.cym.model.Admin;
public class AdminExt {

 private Admin admin;

 private List<String> groupIds;


public List<String> getGroupIds(){
    return groupIds;
}


public Admin getAdmin(){
    return admin;
}


public void setGroupIds(List<String> groupIds){
    this.groupIds = groupIds;
}


public void setAdmin(Admin admin){
    this.admin = admin;
}


}