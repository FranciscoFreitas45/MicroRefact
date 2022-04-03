package cn.gson.oasys.model.entity.user;
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
import cn.gson.oasys.Request.AttendsRequest;
import cn.gson.oasys.Request.Impl.AttendsRequestImpl;
import cn.gson.oasys.DTO.Attends;
@Entity
@Table(name = "aoa_user")
public class User {

@Id
@Column(name = "user_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long userId;

@Column(name = "user_name")
@NotEmpty(message = "用户名不能为空")
 private  String userName;

@Column(name = "user_tel")
@NotEmpty(message = "电话不能为空")
 private  String userTel;

@Column(name = "real_name")
@NotEmpty(message = "真实姓名不能为空")
 private  String realName;

 private  String pinyin;

@NotEmpty(message = "邮箱不能为空")
@Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$", message = "请填写正确邮箱号")
 private  String eamil;

@NotEmpty(message = "地址不能为空")
 private  String address;

@Column(name = "user_edu")
@NotEmpty(message = "学历不能为空")
 private  String userEdu;

 private  Boolean superman;

@Column(name = "user_school")
@NotEmpty(message = "毕业院校不能为空")
 private  String school;

@Column(name = "user_idcard")
@Pattern(regexp = "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$", message = "请填写正确身份证号")
 private  String idCard;

@NotEmpty(message = "卡号不能为空")
@Length(min = 16, max = 19, message = "银行卡号长度必须在16到19之间!")
 private  String bank;

 private  String sex;

@Column(name = "theme_skin")
 private  String themeSkin;

 private  Date birth;

@Column(name = "user_sign")
 private  String userSign;

 private  String password;

 private  String salary;

@Column(name = "img_path")
 private  String imgPath;

@Column(name = "hire_time")
 private  Date hireTime;

@Column(name = "is_lock")
 private  Integer isLock;

@Column(name = "last_login_ip")
 private  String lastLoginIp;

@Column(name = "last_login_time")
 private  Date lastLoginTime;

@Column(name = "modify_time")
 private  Date modifyTime;

@Column(name = "modify_user_id")
 private  Long modifyUserId;

@Column(name = "father_id")
 private  Long fatherId;

 private  Integer holiday;

@Transient
 private  Position position;

@Transient
 private  Dept dept;

@Transient
 private  Role role;

@ManyToMany(mappedBy = "users")
 private  List<ScheduleList> scheduleLists;

@ManyToMany(mappedBy = "users")
 private  List<Reply> replys;

@ManyToMany(mappedBy = "users")
 private  List<Discuss> discuss;

@ManyToMany(mappedBy = "userss")
 private  List<Note> note;

@Transient
 private  Set<Attends> aSet;

@Column(name = "id")
 private Long id;

@Transient
 private PositionRequest positionrequest = new PositionRequestImpl();;

@Column(name = "deptId")
 private Long deptId;

@Transient
 private DeptRequest deptrequest = new DeptRequestImpl();;

@Column(name = "roleId")
 private Long roleId;

@Transient
 private RoleRequest rolerequest = new RoleRequestImpl();;

@Transient
 private AttendsRequest attendsrequest = new AttendsRequestImpl();;

public User() {
}
public void setRealName(String realName){
    this.realName = realName;
}


public void setPassword(String password){
    this.password = password;
}


public void setUserSign(String userSign){
    this.userSign = userSign;
}


public void setIsLock(Integer isLock){
    this.isLock = isLock;
}


public Long getModifyUserId(){
    return modifyUserId;
}


public void setPosition(Position position){
 positionrequest.setPosition(position,this.id);
}



public void setDept(Dept dept){
 deptrequest.setDept(dept,this.deptId);
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


public void setEamil(String eamil){
    this.eamil = eamil;
}


public Set<Attends> getaSet(){
  this.aSet = attendsrequest.getaSet(this.userId);
return this.aSet;
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


public void setSex(String sex){
    this.sex = sex;
}


public String getRealName(){
    return realName;
}


public String getPinyin(){
    return pinyin;
}


public void setModifyTime(Date modifyTime){
    this.modifyTime = modifyTime;
}


public String getUserEdu(){
    return userEdu;
}


public void setRole(Role role){
 rolerequest.setRole(role,this.roleId);
}



public String getSex(){
    return sex;
}


public void setImgPath(String imgPath){
    this.imgPath = imgPath;
}


public void setNote(List<Note> note){
    this.note = note;
}


public Date getBirth(){
    return birth;
}


public void setUserId(Long userId){
    this.userId = userId;
}


public void setBirth(Date birth){
    this.birth = birth;
}


public Integer getHoliday(){
    return holiday;
}


public void setDiscuss(List<Discuss> discuss){
    this.discuss = discuss;
}


public Dept getDept(){
  this.dept = deptrequest.getDept(this.deptId);
return this.dept;
}


public void setHoliday(Integer holiday){
    this.holiday = holiday;
}


public List<Discuss> getDiscuss(){
    return discuss;
}


public String getThemeSkin(){
    return themeSkin;
}


public void setModifyUserId(Long modifyUserId){
    this.modifyUserId = modifyUserId;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public void setLastLoginIp(String lastLoginIp){
    this.lastLoginIp = lastLoginIp;
}


public void setPinyin(String pinyin){
    this.pinyin = pinyin;
}


public String getBank(){
    return bank;
}


public void setUserEdu(String userEdu){
    this.userEdu = userEdu;
}


public void setReplys(List<Reply> replys){
    this.replys = replys;
}


public void setUserName(String userName){
    this.userName = userName;
}


public Date getHireTime(){
    return hireTime;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
}


public void setSuperman(Boolean superman){
    this.superman = superman;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public Integer getIsLock(){
    return isLock;
}


public List<ScheduleList> getScheduleLists(){
    return scheduleLists;
}


public void setHireTime(Date hireTime){
    this.hireTime = hireTime;
}


public String getAddress(){
    return address;
}


public void setSchool(String school){
    this.school = school;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public void setScheduleLists(List<ScheduleList> scheduleLists){
    this.scheduleLists = scheduleLists;
}


public List<Reply> getReplys(){
    return replys;
}


public void setSalary(String salary){
    this.salary = salary;
}


public String getImgPath(){
    return imgPath;
}


public void setAddress(String address){
    this.address = address;
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


public void setThemeSkin(String themeSkin){
    this.themeSkin = themeSkin;
}


public String getPassword(){
    return password;
}


public Position getPosition(){
  this.position = positionrequest.getPosition(this.id);
return this.position;
}


public void setaSet(Set<Attends> aSet){
 attendsrequest.setaSet(aSet,this.userId);
}



public void setBank(String bank){
    this.bank = bank;
}


public void setUserTel(String userTel){
    this.userTel = userTel;
}


@Override
public String toString(){
    return "User [userId=" + userId + ", userName=" + userName + ", userTel=" + userTel + ", realName=" + realName + ", eamil=" + eamil + ", address=" + address + ", userEdu=" + userEdu + ", school=" + school + ", idCard=" + idCard + ", bank=" + bank + ", sex=" + sex + ", themeSkin=" + themeSkin + ", birth=" + birth + ", userSign=" + userSign + ", password=" + password + ", salary=" + salary + ", imgPath=" + imgPath + ", hireTime=" + hireTime + ", isLock=" + isLock + ", lastLoginIp=" + lastLoginIp + ", lastLoginTime=" + lastLoginTime + ", modifyTime=" + modifyTime + ", modifyUserId=" + modifyUserId + ", fatherId=" + fatherId + ", holiday=" + holiday + ",superman=" + superman + ",pinyin=" + pinyin + "]";
}


public void setFatherId(Long fatherId){
    this.fatherId = fatherId;
}


public Long getUserId(){
    return userId;
}


}