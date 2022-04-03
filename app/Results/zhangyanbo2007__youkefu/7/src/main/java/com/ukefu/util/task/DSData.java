package com.ukefu.util.task;
 import java.io.File;
import com.ukefu.util.task.process.JPAProcess;
import com.ukefu.webim.web.model.Database;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.Reporter;
import com.ukefu.webim.web.model.User;
public class DSData {

 private  MetadataTable task;

 private  File file;

 private  String contentType;

 private  Class<?> clazz;

 private  JPAProcess process;

 private  Database database;

 private  User user;

 private  Reporter report;

 private  JobDetail jobDetail;

public DSData() {
}public DSData(MetadataTable task, File file, String contentType, User user) {
    this.task = task;
    this.file = file;
    this.contentType = contentType;
    this.user = user;
}public DSData(MetadataTable task, JobDetail jobDetail, Database database) {
    this.task = task;
    this.database = database;
    this.jobDetail = jobDetail;
    if (this.jobDetail != null) {
        this.report = this.jobDetail.getReport();
    }
}
public Class<?> getClazz(){
    return clazz;
}


public JPAProcess getProcess(){
    return process;
}


public void setProcess(JPAProcess process){
    this.process = process;
}


public void setDatabase(Database database){
    this.database = database;
}


public User getUser(){
    return user;
}


public void setClazz(Class<?> clazz){
    this.clazz = clazz;
}


public void setJobDetail(JobDetail jobDetail){
    this.jobDetail = jobDetail;
}


public Reporter getReport(){
    return report;
}


public File getFile(){
    return file;
}


public void setReport(Reporter report){
    this.report = report;
}


public JobDetail getJobDetail(){
    return jobDetail;
}


public Database getDatabase(){
    return database;
}


public void setFile(File file){
    this.file = file;
}


public void setTask(MetadataTable task){
    this.task = task;
}


public String getContentType(){
    return contentType;
}


public void setContentType(String contentType){
    this.contentType = contentType;
}


public void setUser(User user){
    this.user = user;
}


public MetadataTable getTask(){
    return task;
}


}