import javax.persistence;
@Entity
@Table(name = "logs_freezeunfreeze")
public class FreezeLogs {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "logid")
 private  int logid;

@Column(name = "ip_address")
 private  String ipAddress;

@Column(name = "user_agent")
 private  String userAgent;

@Column(name = "user_id")
 private  int userId;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "employee_ids")
 private  String employeeIds;

@Column(name = "freeze_type")
 private  String freezeType;

@Column(name = "freeze_month")
 private  String freezeMonth;

@Column(name = "comments")
 private  String comments;


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public String getIpAddress(){
    return ipAddress;
}


public String getEmployeeIds(){
    return employeeIds;
}


public void setFreezeType(String freezeType){
    this.freezeType = freezeType;
}


public String getFreezeType(){
    return freezeType;
}


public void setUserAgent(String userAgent){
    this.userAgent = userAgent;
}


public int getLogid(){
    return logid;
}


public void setLogid(int logid){
    this.logid = logid;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setFreezeMonth(String freezeMonth){
    this.freezeMonth = freezeMonth;
}


public String getFreezeMonth(){
    return freezeMonth;
}


public String getComments(){
    return comments;
}


public String getUserAgent(){
    return userAgent;
}


@Override
public String toString(){
    return "FreezeLogs [logid=" + logid + ", ipAddress=" + ipAddress + ", userAgent=" + userAgent + ", userId=" + userId + ", makerEnterDatetime=" + makerEnterDatetime + ", employeeIds=" + employeeIds + ", freezeType=" + freezeType + ", freezeMonth=" + freezeMonth + ", comments=" + comments + "]";
}


public void setComments(String comments){
    this.comments = comments;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


public void setEmployeeIds(String employeeIds){
    this.employeeIds = employeeIds;
}


}