package cn.maxcj.core.shiro;
 import java.io.Serializable;
import java.util.List;
public class ShiroUser implements Serializable{

 private  long serialVersionUID;

 public  Integer id;

 public  String account;

 public  String name;

 public  Integer deptId;

 public  List<Integer> roleList;

 public  String deptName;

 public  List<String> roleNames;


public void setName(String name){
    this.name = name;
}


public List<String> getRoleNames(){
    return roleNames;
}


public void setRoleNames(List<String> roleNames){
    this.roleNames = roleNames;
}


public String getName(){
    return name;
}


public Integer getDeptId(){
    return deptId;
}


public Integer getId(){
    return id;
}


public String getDeptName(){
    return deptName;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setDeptId(Integer deptId){
    this.deptId = deptId;
}


public String getAccount(){
    return account;
}


public List<Integer> getRoleList(){
    return roleList;
}


public void setId(Integer id){
    this.id = id;
}


public void setAccount(String account){
    this.account = account;
}


public void setRoleList(List<Integer> roleList){
    this.roleList = roleList;
}


}