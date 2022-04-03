package edu.nju.careerbridge.youth.bean;
 import java.util.List;
public class ExpectationBean {

 private  String phone;

 private  String salary;

 private  Integer lowSalary;

 private  Integer highSalary;

 private  List<ExpectLocationBean> expectLocationBeans;

 private  List<ExpectCompanyQualityBean> expectCompanyQualityBeans;

 private  List<ExpectCompanyLevelBean> expectCompanyLevelBeans;

 private  List<ExpectJobTypeBean> expectJobTypeBeans;

// private List<String> location;
// private String[] location;
// private String[] companyQuality;
// private String[] companyLevel;
// private String[] jobType;
public ExpectationBean() {
}public ExpectationBean(String phone, String salary, Integer lowSalary, Integer highSalary, List<ExpectLocationBean> expectLocationBeans, List<ExpectCompanyQualityBean> expectCompanyQualityBeans, List<ExpectCompanyLevelBean> expectCompanyLevelBeans, List<ExpectJobTypeBean> expectJobTypeBeans) {
    this.phone = phone;
    this.salary = salary;
    this.lowSalary = lowSalary;
    this.highSalary = highSalary;
    this.expectLocationBeans = expectLocationBeans;
    this.expectCompanyQualityBeans = expectCompanyQualityBeans;
    this.expectCompanyLevelBeans = expectCompanyLevelBeans;
    this.expectJobTypeBeans = expectJobTypeBeans;
}
public String getPhone(){
    return phone;
}


public List<ExpectJobTypeBean> getExpectJobTypeBeans(){
    return expectJobTypeBeans;
}


public void setExpectJobTypeBeans(List<ExpectJobTypeBean> expectJobTypeBeans){
    this.expectJobTypeBeans = expectJobTypeBeans;
}


public List<ExpectCompanyLevelBean> getExpectCompanyLevelBeans(){
    return expectCompanyLevelBeans;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setLowSalary(Integer lowSalary){
    this.lowSalary = lowSalary;
}


public List<ExpectCompanyQualityBean> getExpectCompanyQualityBeans(){
    return expectCompanyQualityBeans;
}


public List<ExpectLocationBean> getExpectLocationBeans(){
    return expectLocationBeans;
}


public void setExpectCompanyQualityBeans(List<ExpectCompanyQualityBean> expectCompanyQualityBeans){
    this.expectCompanyQualityBeans = expectCompanyQualityBeans;
}


public Integer getLowSalary(){
    return lowSalary;
}


public Integer getHighSalary(){
    return highSalary;
}


public void setHighSalary(Integer highSalary){
    this.highSalary = highSalary;
}


@Override
public String toString(){
    return "ExpectationBean{" + "phone='" + phone + '\'' + ", salary='" + salary + '\'' + ", lowSalary=" + lowSalary + ", highSalary=" + highSalary + ", expectLocationBeans=" + expectLocationBeans + ", expectCompanyQualityBeans=" + expectCompanyQualityBeans + ", expectCompanyLevelBeans=" + expectCompanyLevelBeans + ", expectJobTypeBeans=" + expectJobTypeBeans + '}';
}


public void setExpectCompanyLevelBeans(List<ExpectCompanyLevelBean> expectCompanyLevelBeans){
    this.expectCompanyLevelBeans = expectCompanyLevelBeans;
}


public void setExpectLocationBeans(List<ExpectLocationBean> expectLocationBeans){
    this.expectLocationBeans = expectLocationBeans;
}


public String getSalary(){
    return salary;
}


public void setSalary(String salary){
    this.salary = salary;
}


}