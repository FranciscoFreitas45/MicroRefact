import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
@Entity
public class ViewEmployee {

@Id
 private  int empId;

 private  String empCode;

 private  String firstName;

 private  String empMidName;

 private  String surname;

@Column(name = "mobile_no_1")
 private  String mobileNo1;

@Column(name = "mobile_no_2")
 private  String mobileNo2;

 private  String companyName;

 private  String uan;

 private  String esicNo;

 private  String aadharNo;

 private  String panCardNo;

 private  String pfNo;

 private  String contractorName;

 private  String skill;

 private  String desingnation;

 private  String location;

 private  String departName;

 private  int authorityDesigType;

 private  String empType;

 private  String empCategory;

 private  String middleName;

 private  String middleNameRelation;

 private  Date dob;

 private  String gender;

 private  String maritalStatus;

 private  String email;

 private  String address;

 private  String permanentAddress;

 private  String empQualification;

 private  String emerName;

@Column(name = "emer_contact_no_1")
 private  String emerContactNo1;

@Column(name = "emer_contact_no_2")
 private  String emerContactNo2;

 private  String emerContactAddr;

 private  String bloodGroup;

 private  String uniformSize;

 private  String name1;

 private  String relation1;

 private  Date dob1;

 private  String name2;

 private  String relation2;

 private  Date dob2;

 private  String name3;

 private  String relation3;

 private  Date dob3;

 private  String name4;

 private  String relation4;

 private  Date dob4;

 private  String name5;

 private  String relation5;

 private  Date dob5;

 private  String name6;

 private  String relation6;

 private  Date dob6;

 private  String occupation1;

 private  String occupation2;

 private  String occupation3;

 private  String occupation4;

 private  String occupation5;

 private  String occupation6;

 private  String bankName;

 private  String accNo;

 private  double grossSalary;

 private  double basic;

 private  double societyContribution;

 private  String pfApplicable;

 private  String pfType;

 private  double pfEmpPer;

 private  String esicApplicable;

 private  String mlwfApplicable;

 private  String ptApplicable;

 private  String salBasis;

 private  Date cmpJoiningDate;

 private  Date cmpLeavingDate;

 private  Date epfJoiningDate;

 private  String leavingReason;

 private  String leavingReasonEsic;

 private  String leavingReasonPf;


public void setEsicNo(String esicNo){
    this.esicNo = esicNo;
}


public String getLocation(){
    return location;
}


public double getGrossSalary(){
    return grossSalary;
}


public void setCmpLeavingDate(Date cmpLeavingDate){
    this.cmpLeavingDate = cmpLeavingDate;
}


public void setEmpType(String empType){
    this.empType = empType;
}


public String getRelation1(){
    return relation1;
}


public String getDesingnation(){
    return desingnation;
}


public void setEmerContactAddr(String emerContactAddr){
    this.emerContactAddr = emerContactAddr;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setMlwfApplicable(String mlwfApplicable){
    this.mlwfApplicable = mlwfApplicable;
}


public void setGender(String gender){
    this.gender = gender;
}


public void setDob2(Date dob2){
    this.dob2 = dob2;
}


public void setEmpMidName(String empMidName){
    this.empMidName = empMidName;
}


public void setDob1(Date dob1){
    this.dob1 = dob1;
}


public void setDob4(Date dob4){
    this.dob4 = dob4;
}


public void setDob3(Date dob3){
    this.dob3 = dob3;
}


public String getAadharNo(){
    return aadharNo;
}


public String getEmpType(){
    return empType;
}


public String getLeavingReasonPf(){
    return leavingReasonPf;
}


public String getMiddleNameRelation(){
    return middleNameRelation;
}


public void setDob6(Date dob6){
    this.dob6 = dob6;
}


public void setDob5(Date dob5){
    this.dob5 = dob5;
}


public String getGender(){
    return gender;
}


public void setMaritalStatus(String maritalStatus){
    this.maritalStatus = maritalStatus;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob5(){
    return dob5;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob6(){
    return dob6;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob3(){
    return dob3;
}


public int getEmpId(){
    return empId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob4(){
    return dob4;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob1(){
    return dob1;
}


public void setEmpId(int empId){
    this.empId = empId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob2(){
    return dob2;
}


public String getSalBasis(){
    return salBasis;
}


public void setGrossSalary(double grossSalary){
    this.grossSalary = grossSalary;
}


public void setRelation6(String relation6){
    this.relation6 = relation6;
}


public void setEmerName(String emerName){
    this.emerName = emerName;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpLeavingDate(){
    return cmpLeavingDate;
}


public void setRelation2(String relation2){
    this.relation2 = relation2;
}


public void setRelation3(String relation3){
    this.relation3 = relation3;
}


public void setRelation4(String relation4){
    this.relation4 = relation4;
}


public void setRelation5(String relation5){
    this.relation5 = relation5;
}


public void setBloodGroup(String bloodGroup){
    this.bloodGroup = bloodGroup;
}


public void setRelation1(String relation1){
    this.relation1 = relation1;
}


public String getLeavingReason(){
    return leavingReason;
}


public void setDepartName(String departName){
    this.departName = departName;
}


public String getPanCardNo(){
    return panCardNo;
}


public String getMaritalStatus(){
    return maritalStatus;
}


public double getBasic(){
    return basic;
}


public void setEsicApplicable(String esicApplicable){
    this.esicApplicable = esicApplicable;
}


public String getPermanentAddress(){
    return permanentAddress;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public double getPfEmpPer(){
    return pfEmpPer;
}


public String getPfNo(){
    return pfNo;
}


public String getAccNo(){
    return accNo;
}


public void setAccNo(String accNo){
    this.accNo = accNo;
}


public void setBasic(double basic){
    this.basic = basic;
}


public int getAuthorityDesigType(){
    return authorityDesigType;
}


public void setAadharNo(String aadharNo){
    this.aadharNo = aadharNo;
}


public String getDepartName(){
    return departName;
}


public void setContractorName(String contractorName){
    this.contractorName = contractorName;
}


public void setEmpQualification(String empQualification){
    this.empQualification = empQualification;
}


public String getBankName(){
    return bankName;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public void setPfType(String pfType){
    this.pfType = pfType;
}


public void setEmerContactNo2(String emerContactNo2){
    this.emerContactNo2 = emerContactNo2;
}


public void setEmerContactNo1(String emerContactNo1){
    this.emerContactNo1 = emerContactNo1;
}


public String getPtApplicable(){
    return ptApplicable;
}


public void setLeavingReasonEsic(String leavingReasonEsic){
    this.leavingReasonEsic = leavingReasonEsic;
}


public String getEmpCode(){
    return empCode;
}


public void setDob(Date dob){
    this.dob = dob;
}


public String getLeavingReasonEsic(){
    return leavingReasonEsic;
}


public String getPfApplicable(){
    return pfApplicable;
}


public String getMobileNo2(){
    return mobileNo2;
}


public void setPermanentAddress(String permanentAddress){
    this.permanentAddress = permanentAddress;
}


@Override
public String toString(){
    return "ViewEmployee [empId=" + empId + ", empCode=" + empCode + ", firstName=" + firstName + ", empMidName=" + empMidName + ", surname=" + surname + ", mobileNo1=" + mobileNo1 + ", mobileNo2=" + mobileNo2 + ", companyName=" + companyName + ", uan=" + uan + ", esicNo=" + esicNo + ", aadharNo=" + aadharNo + ", panCardNo=" + panCardNo + ", pfNo=" + pfNo + ", contractorName=" + contractorName + ", skill=" + skill + ", desingnation=" + desingnation + ", location=" + location + ", departName=" + departName + ", authorityDesigType=" + authorityDesigType + ", empType=" + empType + ", empCategory=" + empCategory + ", middleName=" + middleName + ", middleNameRelation=" + middleNameRelation + ", dob=" + dob + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", email=" + email + ", address=" + address + ", permanentAddress=" + permanentAddress + ", empQualification=" + empQualification + ", emerName=" + emerName + ", emerContactNo1=" + emerContactNo1 + ", emerContactNo2=" + emerContactNo2 + ", emerContactAddr=" + emerContactAddr + ", bloodGroup=" + bloodGroup + ", uniformSize=" + uniformSize + ", name1=" + name1 + ", relation1=" + relation1 + ", dob1=" + dob1 + ", name2=" + name2 + ", relation2=" + relation2 + ", dob2=" + dob2 + ", name3=" + name3 + ", relation3=" + relation3 + ", dob3=" + dob3 + ", name4=" + name4 + ", relation4=" + relation4 + ", dob4=" + dob4 + ", name5=" + name5 + ", relation5=" + relation5 + ", dob5=" + dob5 + ", name6=" + name6 + ", relation6=" + relation6 + ", dob6=" + dob6 + ", occupation1=" + occupation1 + ", occupation2=" + occupation2 + ", occupation3=" + occupation3 + ", occupation4=" + occupation4 + ", occupation5=" + occupation5 + ", occupation6=" + occupation6 + ", bankName=" + bankName + ", accNo=" + accNo + ", grossSalary=" + grossSalary + ", basic=" + basic + ", societyContribution=" + societyContribution + ", pfApplicable=" + pfApplicable + ", pfType=" + pfType + ", pfEmpPer=" + pfEmpPer + ", esicApplicable=" + esicApplicable + ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", salBasis=" + salBasis + ", cmpJoiningDate=" + cmpJoiningDate + ", cmpLeavingDate=" + cmpLeavingDate + ", epfJoiningDate=" + epfJoiningDate + ", leavingReason=" + leavingReason + ", leavingReasonEsic=" + leavingReasonEsic + ", leavingReasonPf=" + leavingReasonPf + "]";
}


public String getMobileNo1(){
    return mobileNo1;
}


public void setPanCardNo(String panCardNo){
    this.panCardNo = panCardNo;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpJoiningDate(){
    return cmpJoiningDate;
}


public void setOccupation1(String occupation1){
    this.occupation1 = occupation1;
}


public void setOccupation2(String occupation2){
    this.occupation2 = occupation2;
}


public void setOccupation3(String occupation3){
    this.occupation3 = occupation3;
}


public void setOccupation4(String occupation4){
    this.occupation4 = occupation4;
}


public void setSocietyContribution(double societyContribution){
    this.societyContribution = societyContribution;
}


public String getName2(){
    return name2;
}


public String getOccupation4(){
    return occupation4;
}


public void setOccupation5(String occupation5){
    this.occupation5 = occupation5;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob(){
    return dob;
}


public String getName1(){
    return name1;
}


public String getOccupation3(){
    return occupation3;
}


public void setOccupation6(String occupation6){
    this.occupation6 = occupation6;
}


public void setPfEmpPer(double pfEmpPer){
    this.pfEmpPer = pfEmpPer;
}


public String getName4(){
    return name4;
}


public String getOccupation2(){
    return occupation2;
}


public String getEmerContactNo2(){
    return emerContactNo2;
}


public String getEmerName(){
    return emerName;
}


public String getName3(){
    return name3;
}


public String getOccupation1(){
    return occupation1;
}


public String getEmerContactNo1(){
    return emerContactNo1;
}


public String getName6(){
    return name6;
}


public String getName5(){
    return name5;
}


public String getOccupation6(){
    return occupation6;
}


public void setUniformSize(String uniformSize){
    this.uniformSize = uniformSize;
}


public String getOccupation5(){
    return occupation5;
}


public String getEmpCategory(){
    return empCategory;
}


public void setSalBasis(String salBasis){
    this.salBasis = salBasis;
}


public void setPfNo(String pfNo){
    this.pfNo = pfNo;
}


public String getContractorName(){
    return contractorName;
}


public String getEmerContactAddr(){
    return emerContactAddr;
}


public void setName4(String name4){
    this.name4 = name4;
}


public void setName3(String name3){
    this.name3 = name3;
}


public void setUan(String uan){
    this.uan = uan;
}


public void setName2(String name2){
    this.name2 = name2;
}


public void setName1(String name1){
    this.name1 = name1;
}


public String getUan(){
    return uan;
}


public String getUniformSize(){
    return uniformSize;
}


public void setBankName(String bankName){
    this.bankName = bankName;
}


public void setCmpJoiningDate(Date cmpJoiningDate){
    this.cmpJoiningDate = cmpJoiningDate;
}


public String getEmpQualification(){
    return empQualification;
}


public void setPfApplicable(String pfApplicable){
    this.pfApplicable = pfApplicable;
}


public void setEmpCategory(String empCategory){
    this.empCategory = empCategory;
}


public void setName6(String name6){
    this.name6 = name6;
}


public void setName5(String name5){
    this.name5 = name5;
}


public void setEmail(String email){
    this.email = email;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getEsicApplicable(){
    return esicApplicable;
}


public String getEmail(){
    return email;
}


public void setMobileNo2(String mobileNo2){
    this.mobileNo2 = mobileNo2;
}


public void setMobileNo1(String mobileNo1){
    this.mobileNo1 = mobileNo1;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getPfType(){
    return pfType;
}


public String getSkill(){
    return skill;
}


public void setMiddleNameRelation(String middleNameRelation){
    this.middleNameRelation = middleNameRelation;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getEpfJoiningDate(){
    return epfJoiningDate;
}


public String getMiddleName(){
    return middleName;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setLocation(String location){
    this.location = location;
}


public void setDesingnation(String desingnation){
    this.desingnation = desingnation;
}


public void setLeavingReasonPf(String leavingReasonPf){
    this.leavingReasonPf = leavingReasonPf;
}


public void setPtApplicable(String ptApplicable){
    this.ptApplicable = ptApplicable;
}


public String getAddress(){
    return address;
}


public void setAuthorityDesigType(int authorityDesigType){
    this.authorityDesigType = authorityDesigType;
}


public void setLeavingReason(String leavingReason){
    this.leavingReason = leavingReason;
}


public void setAddress(String address){
    this.address = address;
}


public String getEsicNo(){
    return esicNo;
}


public String getMlwfApplicable(){
    return mlwfApplicable;
}


public String getBloodGroup(){
    return bloodGroup;
}


public double getSocietyContribution(){
    return societyContribution;
}


public void setEpfJoiningDate(Date epfJoiningDate){
    this.epfJoiningDate = epfJoiningDate;
}


public String getEmpMidName(){
    return empMidName;
}


public String getCompanyName(){
    return companyName;
}


public String getRelation2(){
    return relation2;
}


public String getRelation3(){
    return relation3;
}


public String getRelation4(){
    return relation4;
}


public String getRelation5(){
    return relation5;
}


public String getRelation6(){
    return relation6;
}


public String getFirstName(){
    return firstName;
}


public String getSurname(){
    return surname;
}


}