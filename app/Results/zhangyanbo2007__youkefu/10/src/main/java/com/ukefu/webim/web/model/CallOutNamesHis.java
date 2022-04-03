package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_act_callnames_his")
@org.hibernate.annotations.Proxy(lazy = false)
public class CallOutNamesHis {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String batid;

 private  String calltype;

 private  Date createtime;

 private  Date updatetime;

 private  String datastatus;

 private  String status;

 private  int calls;

 private  int faildcalls;

 private  boolean invalid;

 private  boolean failed;

 private  String workstatus;

 private  boolean reservation;

 private  Date optime;

 private  String memo;

 private  String batname;

 private  String taskname;

 private  String servicetype;

 private  int leavenum;

 private  String metaname;

 private  String owneruser;

 private  String ownerdept;

 private  String dataid;

 private  String taskid;

 private  String filterid;

 private  String actid;

 private  String name;

 private  String phonenumber;

 private  String distype;

 private  int previewtimes;

 private  int previewtime;

 private  Date firstcalltime;

 private  String firstcallstatus;


public void setPreviewtime(int previewtime){
    this.previewtime = previewtime;
}


public void setFilterid(String filterid){
    this.filterid = filterid;
}


public String getName(){
    return name;
}


public String getFirstcallstatus(){
    return firstcallstatus;
}


public int getLeavenum(){
    return leavenum;
}


public String getBatid(){
    return batid;
}


public String getMetaname(){
    return metaname;
}


public void setDatastatus(String datastatus){
    this.datastatus = datastatus;
}


public boolean isReservation(){
    return reservation;
}


public String getStatus(){
    return status;
}


public void setBatid(String batid){
    this.batid = batid;
}


@Transient
public String getCalltype(){
    return calltype;
}


public void setFirstcallstatus(String firstcallstatus){
    this.firstcallstatus = firstcallstatus;
}


public void setId(String id){
    this.id = id;
}


public void setReservation(boolean reservation){
    this.reservation = reservation;
}


public int getCalls(){
    return calls;
}


public void setBatname(String batname){
    this.batname = batname;
}


public void setPreviewtimes(int previewtimes){
    this.previewtimes = previewtimes;
}


public void setOptime(Date optime){
    this.optime = optime;
}


public Date getUpdatetime(){
    return updatetime;
}


public int getPreviewtimes(){
    return previewtimes;
}


public Date getFirstcalltime(){
    return firstcalltime;
}


public void setTaskid(String taskid){
    this.taskid = taskid;
}


public boolean isInvalid(){
    return invalid;
}


public int getPreviewtime(){
    return previewtime;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getMemo(){
    return memo;
}


public String getOrgan(){
    return organ;
}


public int getFaildcalls(){
    return faildcalls;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setInvalid(boolean invalid){
    this.invalid = invalid;
}


public void setTaskname(String taskname){
    this.taskname = taskname;
}


public String getTaskid(){
    return taskid;
}


public void setName(String name){
    this.name = name;
}


public String getOwneruser(){
    return owneruser;
}


public String getOwnerdept(){
    return ownerdept;
}


public boolean isFailed(){
    return failed;
}


public Date getOptime(){
    return optime;
}


public void setWorkstatus(String workstatus){
    this.workstatus = workstatus;
}


public void setFaildcalls(int faildcalls){
    this.faildcalls = faildcalls;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getServicetype(){
    return servicetype;
}


public void setCalltype(String calltype){
    this.calltype = calltype;
}


public void setPhonenumber(String phonenumber){
    this.phonenumber = phonenumber;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public void setMetaname(String metaname){
    this.metaname = metaname;
}


public String getFilterid(){
    return filterid;
}


public String getPhonenumber(){
    return phonenumber;
}


public void setActid(String actid){
    this.actid = actid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setFailed(boolean failed){
    this.failed = failed;
}


public void setCalls(int calls){
    this.calls = calls;
}


public void setOwneruser(String owneruser){
    this.owneruser = owneruser;
}


public String getDatastatus(){
    return datastatus;
}


public void setOwnerdept(String ownerdept){
    this.ownerdept = ownerdept;
}


public void setDistype(String distype){
    this.distype = distype;
}


public String getBatname(){
    return batname;
}


public String getDistype(){
    return distype;
}


public String getWorkstatus(){
    return workstatus;
}


public void setLeavenum(int leavenum){
    this.leavenum = leavenum;
}


public void setStatus(String status){
    this.status = status;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getDataid(){
    return dataid;
}


public void setServicetype(String servicetype){
    this.servicetype = servicetype;
}


public String getTaskname(){
    return taskname;
}


public String getOrgi(){
    return orgi;
}


public void setFirstcalltime(Date firstcalltime){
    this.firstcalltime = firstcalltime;
}


}