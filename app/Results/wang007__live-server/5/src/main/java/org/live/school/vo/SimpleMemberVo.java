package org.live.school.vo;
 public class SimpleMemberVo {

 private  String id;

 private  String memberNo;

 private  String realName;

public SimpleMemberVo(String id, String memberNo, String realName) {
    this.id = id;
    this.memberNo = memberNo;
    this.realName = realName;
}
public void setMemberNo(String memberNo){
    this.memberNo = memberNo;
}


public void setRealName(String realName){
    this.realName = realName;
}


public String getMemberNo(){
    return memberNo;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public String getRealName(){
    return realName;
}


}