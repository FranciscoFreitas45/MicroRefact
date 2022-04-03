package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "job_vector_2")
public class JobVector {

@Id
@Column(name = "job_id")
 private  Integer jobId;

@Column(name = "salary_mid")
 private  Integer salaryMid;

@Column(name = "company_nature")
 private  Integer companyNature;

@Column(name = "education_degree")
 private  Integer educationDegree;

@Column(name = "job_exp")
 private  Double jobExp;

@Column(name = "lng")
 private  Double lng;

@Column(name = "lat")
 private  Double lat;

@Column(name = "job_city")
 private  String jobCity;

@Column(name = "job_name")
 private  String jobName;

public JobVector() {
}public JobVector(Integer jobId, Integer salaryMid, Integer companyNature, Integer educationDegree, Double jobExp, Double lng, Double lat, String jobCity, String jobName) {
    this.jobId = jobId;
    this.salaryMid = salaryMid;
    this.companyNature = companyNature;
    this.educationDegree = educationDegree;
    this.jobExp = jobExp;
    this.lng = lng;
    this.lat = lat;
    this.jobCity = jobCity;
    this.jobName = jobName;
}
public void setJobName(String jobName){
    this.jobName = jobName;
}


public void setLng(Double lng){
    this.lng = lng;
}


public Double getLat(){
    return lat;
}


public String getJobCity(){
    return jobCity;
}


public void setJobId(Integer jobId){
    this.jobId = jobId;
}


public void setJobCity(String jobCity){
    this.jobCity = jobCity;
}


public Integer getJobId(){
    return jobId;
}


public Double getLng(){
    return lng;
}


public String getJobName(){
    return jobName;
}


public void setEducationDegree(Integer educationDegree){
    this.educationDegree = educationDegree;
}


public void setSalaryMid(Integer salaryMid){
    this.salaryMid = salaryMid;
}


public Integer getSalaryMid(){
    return salaryMid;
}


public void setCompanyNature(Integer companyNature){
    this.companyNature = companyNature;
}


public Integer getCompanyNature(){
    return companyNature;
}


public Double getJobExp(){
    return jobExp;
}


public void setJobExp(Double jobExp){
    this.jobExp = jobExp;
}


@Override
public String toString(){
    return "JobVector{" + ", jobId='" + jobId + '\'' + ", salaryMid='" + salaryMid + '\'' + ", companyNature='" + companyNature + '\'' + ", educationDegree='" + educationDegree + '\'' + ", jobExp='" + jobExp + '\'' + ", lng=" + lng + ", lat=" + lat + ", jobCity='" + jobCity + '\'' + '}';
}


public Integer getEducationDegree(){
    return educationDegree;
}


public void setLat(Double lat){
    this.lat = lat;
}


}