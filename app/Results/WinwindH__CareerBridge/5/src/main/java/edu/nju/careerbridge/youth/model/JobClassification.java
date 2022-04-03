package edu.nju.careerbridge.youth.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "job_classification")
public class JobClassification {

@Column(name = "job_id")
@Id
 private  String jobId;

@Column(name = "job_name")
 private  String jobName;

@Column(name = "classification")
 private  Integer classification;

public JobClassification() {
}public JobClassification(String jobId, String jobName, Integer classification) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.classification = classification;
}
public void setJobName(String jobName){
    this.jobName = jobName;
}


public void setClassification(Integer classification){
    this.classification = classification;
}


public void setJobId(String jobId){
    this.jobId = jobId;
}


public String getJobId(){
    return jobId;
}


public String getJobName(){
    return jobName;
}


@Override
public String toString(){
    return "JobClassification{" + "jobId='" + jobId + '\'' + ", jobName='" + jobName + '\'' + ", classification=" + classification + '}';
}


public Integer getClassification(){
    return classification;
}


}