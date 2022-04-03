package edu.nju.careerbridge.youth.bean;
 public class JobListBean {

 private  String jobId;

 private  String jobName;

 private  String jobLocation;

 private  String salary;

 private  String companyName;

 private  Double matchingDegree;

public JobListBean() {
}public JobListBean(String jobId, String jobName, String jobLocation, String salary, String companyName, Double matchingDegree) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobLocation = jobLocation;
    this.salary = salary;
    this.companyName = companyName;
    this.matchingDegree = matchingDegree;
}
public void setJobName(String jobName){
    this.jobName = jobName;
}


public String getJobLocation(){
    return jobLocation;
}


public void setJobLocation(String jobLocation){
    this.jobLocation = jobLocation;
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


public String getCompanyName(){
    return companyName;
}


public String getSalary(){
    return salary;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setMatchingDegree(Double matchingDegree){
    this.matchingDegree = matchingDegree;
}


public void setSalary(String salary){
    this.salary = salary;
}


public Double getMatchingDegree(){
    return matchingDegree;
}


}