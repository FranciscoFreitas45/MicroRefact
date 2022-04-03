package cn.gson.oasys.model.entity.task;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "aoa_task_logger")
public class Tasklogger {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "logger_id")
 private  Long loggerId;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "logger_ticking")
 private  String loggerTicking;

@ManyToOne
@JoinColumn(name = "task_id")
 private  Tasklist taskId;

@Column(name = "username")
 private  String username;

@Column(name = "logger_statusid")
 private  Integer loggerStatusid;

public Tasklogger() {
}
public Tasklist getTaskId(){
    return taskId;
}


public Date getCreateTime(){
    return createTime;
}


public void setLoggerTicking(String loggerTicking){
    this.loggerTicking = loggerTicking;
}


public void setUsername(String username){
    this.username = username;
}


public void setTaskId(Tasklist taskId){
    this.taskId = taskId;
}


public Long getLoggerId(){
    return loggerId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setLoggerStatusid(Integer loggerStatusid){
    this.loggerStatusid = loggerStatusid;
}


public void setLoggerId(Long loggerId){
    this.loggerId = loggerId;
}


public Integer getLoggerStatusid(){
    return loggerStatusid;
}


public String getUsername(){
    return username;
}


@Override
public String toString(){
    return "Tasklogger [loggerId=" + loggerId + ", createTime=" + createTime + ", loggerTicking=" + loggerTicking + ", username=" + username + ", loggerStatusid=" + loggerStatusid + "]";
}


public String getLoggerTicking(){
    return loggerTicking;
}


}