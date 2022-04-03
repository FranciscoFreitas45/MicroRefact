package edu.nju.careerbridge.youth.bean;
 public class JobDetailBean {

 private  String jobId;

 private  String jobName;

 private  String jobLocation;

 private  String salary;

 private  String companyName;

 private  String companyNature;

 private  String companyPeopleNum;

 private  String companyIndustry;

 private  String jobExperience;

 private  String educationDegree;

 private  String jobPeopleNum;

 private  String jobTime;

 private  String welfare;

 private  String jobDescriptionHtml;

 private  String companyDescriptionHtml;

 private  String jobCity;

 private  Integer salaryLow;

 private  Integer salaryHigh;

 private  Integer companyPeopleNumLow;

 private  Integer companyPeopleNumHigh;

public JobDetailBean() {
}public JobDetailBean(String jobId, String jobName, String jobLocation, String salary, String companyName, String companyNature, String companyPeopleNum, String companyIndustry, String jobExperience, String educationDegree, String jobPeopleNum, String jobTime, String welfare, String jobDescriptionHtml, String companyDescriptionHtml, String jobCity, Integer salaryLow, Integer salaryHigh, Integer companyPeopleNumLow, Integer companyPeopleNumHigh) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobLocation = jobLocation;
    this.salary = salary;
    this.companyName = companyName;
    this.companyNature = companyNature;
    this.companyPeopleNum = companyPeopleNum;
    this.companyIndustry = companyIndustry;
    this.jobExperience = jobExperience;
    this.educationDegree = educationDegree;
    this.jobPeopleNum = jobPeopleNum;
    this.jobTime = jobTime;
    this.welfare = welfare;
    this.jobDescriptionHtml = jobDescriptionHtml;
    this.companyDescriptionHtml = companyDescriptionHtml;
    this.jobCity = jobCity;
    this.salaryLow = salaryLow;
    this.salaryHigh = salaryHigh;
    this.companyPeopleNumLow = companyPeopleNumLow;
    this.companyPeopleNumHigh = companyPeopleNumHigh;
}
public String getJobCity(){
    return jobCity;
}


public void setJobId(String jobId){
    this.jobId = jobId;
}


public void setJobCity(String jobCity){
    this.jobCity = jobCity;
}


public void setCompanyIndustry(String companyIndustry){
    this.companyIndustry = companyIndustry;
}


public void setCompanyPeopleNum(String companyPeopleNum){
    this.companyPeopleNum = companyPeopleNum;
}


public void setJobExperience(String jobExperience){
    this.jobExperience = jobExperience;
}


public void setEducationDegree(String educationDegree){
    this.educationDegree = educationDegree;
}


public void setCompanyPeopleNumLow(Integer companyPeopleNumLow){
    this.companyPeopleNumLow = companyPeopleNumLow;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public String getCompanyPeopleNum(){
    return companyPeopleNum;
}


public void setCompanyNature(String companyNature){
    this.companyNature = companyNature;
}


public String getCompanyDescriptionHtml(){
    return companyDescriptionHtml;
}


public String getJobDescriptionHtml(){
    return jobDescriptionHtml;
}


public Integer getSalaryLow(){
    return salaryLow;
}


public String getWelfare(){
    return welfare;
}


public void setCompanyDescriptionHtml(String companyDescriptionHtml){
    this.companyDescriptionHtml = companyDescriptionHtml;
}


public String getEducationDegree(){
    return educationDegree;
}


public String getSalary(){
    return salary;
}


public void setSalaryLow(Integer salaryLow){
    this.salaryLow = salaryLow;
}


public void setSalary(String salary){
    this.salary = salary;
}


public void setJobName(String jobName){
    this.jobName = jobName;
}


public String getJobLocation(){
    return jobLocation;
}


public String getCompanyIndustry(){
    return companyIndustry;
}


public void setJobPeopleNum(String jobPeopleNum){
    this.jobPeopleNum = jobPeopleNum;
}


public void setJobTime(String jobTime){
    this.jobTime = jobTime;
}


public void setCompanyPeopleNumHigh(Integer companyPeopleNumHigh){
    this.companyPeopleNumHigh = companyPeopleNumHigh;
}


public String getJobPeopleNum(){
    return jobPeopleNum;
}


public String getJobId(){
    return jobId;
}


public String getJobName(){
    return jobName;
}


public Integer getCompanyPeopleNumLow(){
    return companyPeopleNumLow;
}


public String getJobTime(){
    return jobTime;
}


public void setSalaryHigh(Integer salaryHigh){
    this.salaryHigh = salaryHigh;
}


public void setJobLocation(String jobLocation){
    this.jobLocation = jobLocation;
}


public String getCompanyNature(){
    return companyNature;
}


public void setJobDescriptionHtml(String jobDescriptionHtml){
    this.jobDescriptionHtml = jobDescriptionHtml;
}


public void setWelfare(String welfare){
    this.welfare = welfare;
}


public String getCompanyName(){
    return companyName;
}


public Integer getSalaryHigh(){
    return salaryHigh;
}


public String getJobExperience(){
    return jobExperience;
}


@Override
public String toString(){
    return "JobDetailBean{" + "jobId='" + jobId + '\'' + ", jobName='" + jobName + '\'' + ", jobLocation='" + jobLocation + '\'' + ", salary='" + salary + '\'' + ", companyName='" + companyName + '\'' + ", companyNature='" + companyNature + '\'' + ", companyPeopleNum='" + companyPeopleNum + '\'' + ", companyIndustry='" + companyIndustry + '\'' + ", jobExperience='" + jobExperience + '\'' + ", educationDegree='" + educationDegree + '\'' + ", jobPeopleNum='" + jobPeopleNum + '\'' + ", jobTime='" + jobTime + '\'' + ", welfare='" + welfare + '\'' + ", jobDescriptionHtml='" + jobDescriptionHtml + '\'' + ", companyDescriptionHtml='" + companyDescriptionHtml + '\'' + ", jobCity='" + jobCity + '\'' + ", salaryLow=" + salaryLow + ", salaryHigh=" + salaryHigh + ", companyPeopleNumLow=" + companyPeopleNumLow + ", companyPeopleNumHigh=" + companyPeopleNumHigh + '}';
}


public Integer getCompanyPeopleNumHigh(){
    return companyPeopleNumHigh;
}


}