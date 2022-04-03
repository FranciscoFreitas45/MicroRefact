package com.empl.mgr.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
@Entity
@Table(name = "te_employees_training_log")
public class TeEmployeesTrainingLog {

 private  long serialVersionUID;

 private  long id;

 private  Date timestamp;

 private  Integer state;

 private  long emplId;

 private  long trainingItemId;

 private  String applyTime;

 private  String note;

 private  Date createTime;

 private  String creator;

// Constructors
/**
 * default constructor
 */
public TeEmployeesTrainingLog() {
}/**
 * full constructor
 */
public TeEmployeesTrainingLog(Integer state, long emplId, long trainingItemId, String applyTime, String note, Date createTime, String creator) {
    this.state = state;
    this.emplId = emplId;
    this.trainingItemId = trainingItemId;
    this.applyTime = applyTime;
    this.note = note;
    this.createTime = createTime;
    this.creator = creator;
}
public void setTrainingItemId(long trainingItemId){
    this.trainingItemId = trainingItemId;
}


@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


public void setCreator(String creator){
    this.creator = creator;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public long getId(){
    return this.id;
}


@Column(name = "trainingItemId")
public long getTrainingItemId(){
    return this.trainingItemId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "note", length = 1024)
public String getNote(){
    return this.note;
}


@Column(name = "creator", length = 128)
public String getCreator(){
    return this.creator;
}


@Column(name = "emplId")
public long getEmplId(){
    return this.emplId;
}


@Column(name = "applyTime", length = 12)
public String getApplyTime(){
    return this.applyTime;
}


public void setApplyTime(String applyTime){
    this.applyTime = applyTime;
}


@Column(name = "state")
public Integer getState(){
    return this.state;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


public void setId(long id){
    this.id = id;
}


public void setState(Integer state){
    this.state = state;
}


public void setNote(String note){
    this.note = note;
}


@Override
public String toString(){
    return "TeEmployeesTrainingLog [id=" + id + ", timestamp=" + timestamp + ", state=" + state + ", emplId=" + emplId + ", trainingItemId=" + trainingItemId + ", applyTime=" + applyTime + ", note=" + note + ", createTime=" + createTime + ", creator=" + creator + "]";
}


public void setEmplId(long emplId){
    this.emplId = emplId;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


}