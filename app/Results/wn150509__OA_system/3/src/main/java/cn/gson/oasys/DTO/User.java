package cn.gson.oasys.DTO;
 import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import cn.gson.oasys.model.entity.note.Director;
import cn.gson.oasys.model.entity.note.Note;
import cn.gson.oasys.model.entity.attendce.Attends;
import cn.gson.oasys.model.entity.discuss.Discuss;
import cn.gson.oasys.model.entity.discuss.Reply;
import cn.gson.oasys.model.entity.role.Role;
import cn.gson.oasys.model.entity.schedule.ScheduleList;
import cn.gson.oasys.Request.PositionRequest;
import cn.gson.oasys.Request.Impl.PositionRequestImpl;
import cn.gson.oasys.DTO.Position;
import cn.gson.oasys.Request.DeptRequest;
import cn.gson.oasys.Request.Impl.DeptRequestImpl;
import cn.gson.oasys.DTO.Dept;
import cn.gson.oasys.Request.RoleRequest;
import cn.gson.oasys.Request.Impl.RoleRequestImpl;
import cn.gson.oasys.DTO.Role;
public class User {

 private  Long userId;

 private  String userName;

 private  String userTel;

 private  String realName;

 private  String pinyin;

 private  String eamil;

 private  String address;

 private  String userEdu;

 private  Boolean superman;

 private  String school;

 private  String idCard;

 private  String bank;

 private  String sex;

 private  String themeSkin;

 private  Date birth;

 private  String userSign;

 private  String password;

 private  String salary;

 private  String imgPath;

 private  Date hireTime;

 private  Integer isLock;

 private  String lastLoginIp;

 private  Date lastLoginTime;

 private  Date modifyTime;

 private  Long modifyUserId;

 private  Long fatherId;

 private  Integer holiday;

 private  Position position;

 private  Dept dept;

 private  Role role;

 private  List<ScheduleList> scheduleLists;

 private  List<Reply> replys;

 private  List<Discuss> discuss;

 private  List<Note> note;

 private  Set<Attends> aSet;

 private Long id;

 private Long deptId;

 private Long roleId;

public User() {
}
public Long getModifyUserId(){
    return modifyUserId;
}


public String getUserTel(){
    return userTel;
}


public String getSchool(){
    return school;
}


public String getUserName(){
    return userName;
}


public Set<Attends> getaSet(){
    return aSet;
}


public String getSalary(){
    return salary;
}


public String getEamil(){
    return eamil;
}


public String getIdCard(){
    return idCard;
}


public Long getFatherId(){
    return fatherId;
}


public String getRealName(){
    return realName;
}


public String getPinyin(){
    return pinyin;
}


public String getUserEdu(){
    return userEdu;
}


public String getSex(){
    return sex;
}


public Date getBirth(){
    return birth;
}


public Integer getHoliday(){
    return holiday;
}


public Dept getDept(){
  this.dept = deptrequest.getDept(this.deptId);
return this.dept;
}


public List<Discuss> getDiscuss(){
    return discuss;
}


public String getThemeSkin(){
    return themeSkin;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public String getBank(){
    return bank;
}


public Date getHireTime(){
    return hireTime;
}


public Integer getIsLock(){
    return isLock;
}


public List<ScheduleList> getScheduleLists(){
    return scheduleLists;
}


public String getAddress(){
    return address;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public List<Reply> getReplys(){
    return replys;
}


public String getImgPath(){
    return imgPath;
}


public Boolean getSuperman(){
    return superman;
}


public Date getModifyTime(){
    return modifyTime;
}


public Role getRole(){
  this.role = rolerequest.getRole(this.roleId);
return this.role;
}


public String getUserSign(){
    return userSign;
}


public List<Note> getNote(){
    return note;
}


public String getPassword(){
    return password;
}


public Position getPosition(){
  this.position = positionrequest.getPosition(this.id);
return this.position;
}


public Long getUserId(){
    return userId;
}


}