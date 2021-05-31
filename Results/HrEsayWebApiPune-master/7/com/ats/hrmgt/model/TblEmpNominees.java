import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.sql.Date;
@Entity
@Table(name = "tbl_emp_nominees")
public class TblEmpNominees {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "nominee_id")
 private  int nomineeId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "name")
 private  String name;

@Column(name = "relation")
 private  String relation;

@Column(name = "percentage")
 private  double percentage;

@Column(name = "dob")
 private  Date dob;

@Column(name = "name2")
 private  String name2;

@Column(name = "relation2")
 private  String relation2;

@Column(name = "percentage2")
 private  double percentage2;

@Column(name = "dob2")
 private  Date dob2;

@Column(name = "name3")
 private  String name3;

@Column(name = "relation3")
 private  String relation3;

@Column(name = "percentage3")
 private  double percentage3;

@Column(name = "dob3")
 private  Date dob3;

@Column(name = "name4")
 private  String name4;

@Column(name = "relation4")
 private  String relation4;

@Column(name = "percentage4")
 private  double percentage4;

@Column(name = "dob4")
 private  Date dob4;

@Column(name = "name5")
 private  String name5;

@Column(name = "relation5")
 private  String relation5;

@Column(name = "percentage5")
 private  double percentage5;

@Column(name = "dob5")
 private  Date dob5;

@Column(name = "name6")
 private  String name6;

@Column(name = "relation6")
 private  String relation6;

@Column(name = "percentage6")
 private  double percentage6;

@Column(name = "dob6")
 private  Date dob6;

@Column(name = "occupation1")
 private  String occupation1;

@Column(name = "occupation2")
 private  String occupation2;

@Column(name = "occupation3")
 private  String occupation3;

@Column(name = "occupation4")
 private  String occupation4;

@Column(name = "occupation5")
 private  String occupation5;

@Column(name = "occupation6")
 private  String occupation6;

@Column(name = "raw_data")
 private  String rawData;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public String getName(){
    return name;
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


public String getExVar1(){
    return exVar1;
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


public String getOccupation3(){
    return occupation3;
}


public void setOccupation6(String occupation6){
    this.occupation6 = occupation6;
}


public String getName4(){
    return name4;
}


public String getOccupation2(){
    return occupation2;
}


public String getName3(){
    return name3;
}


public String getOccupation1(){
    return occupation1;
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


public String getOccupation5(){
    return occupation5;
}


public void setDob2(Date dob2){
    this.dob2 = dob2;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setDob4(Date dob4){
    this.dob4 = dob4;
}


public void setDob3(Date dob3){
    this.dob3 = dob3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setName4(String name4){
    this.name4 = name4;
}


public double getPercentage6(){
    return percentage6;
}


public void setName3(String name3){
    this.name3 = name3;
}


public void setName2(String name2){
    this.name2 = name2;
}


public double getPercentage4(){
    return percentage4;
}


public double getPercentage5(){
    return percentage5;
}


public double getPercentage2(){
    return percentage2;
}


public double getPercentage3(){
    return percentage3;
}


public void setDob6(Date dob6){
    this.dob6 = dob6;
}


public void setDob5(Date dob5){
    this.dob5 = dob5;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
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


public void setName6(String name6){
    this.name6 = name6;
}


public void setEmpId(int empId){
    this.empId = empId;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob2(){
    return dob2;
}


public void setName5(String name5){
    this.name5 = name5;
}


public void setRelation6(String relation6){
    this.relation6 = relation6;
}


public void setNomineeId(int nomineeId){
    this.nomineeId = nomineeId;
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


public String getRawData(){
    return rawData;
}


public String getRelation(){
    return relation;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setName(String name){
    this.name = name;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public void setRawData(String rawData){
    this.rawData = rawData;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public double getPercentage(){
    return percentage;
}


public void setPercentage(double percentage){
    this.percentage = percentage;
}


public void setPercentage4(double percentage4){
    this.percentage4 = percentage4;
}


public int getDelStatus(){
    return delStatus;
}


public void setPercentage5(double percentage5){
    this.percentage5 = percentage5;
}


public void setPercentage6(double percentage6){
    this.percentage6 = percentage6;
}


public void setDob(Date dob){
    this.dob = dob;
}


public void setRelation(String relation){
    this.relation = relation;
}


public String getRelation2(){
    return relation2;
}


public String getRelation3(){
    return relation3;
}


public void setPercentage2(double percentage2){
    this.percentage2 = percentage2;
}


public String getRelation4(){
    return relation4;
}


@Override
public String toString(){
    return "TblEmpNominees [nomineeId=" + nomineeId + ", empId=" + empId + ", name=" + name + ", relation=" + relation + ", percentage=" + percentage + ", dob=" + dob + ", name2=" + name2 + ", relation2=" + relation2 + ", percentage2=" + percentage2 + ", dob2=" + dob2 + ", name3=" + name3 + ", relation3=" + relation3 + ", percentage3=" + percentage3 + ", dob3=" + dob3 + ", name4=" + name4 + ", relation4=" + relation4 + ", percentage4=" + percentage4 + ", dob4=" + dob4 + ", name5=" + name5 + ", relation5=" + relation5 + ", percentage5=" + percentage5 + ", dob5=" + dob5 + ", name6=" + name6 + ", relation6=" + relation6 + ", percentage6=" + percentage6 + ", dob6=" + dob6 + ", occupation1=" + occupation1 + ", occupation2=" + occupation2 + ", occupation3=" + occupation3 + ", occupation4=" + occupation4 + ", occupation5=" + occupation5 + ", occupation6=" + occupation6 + ", rawData=" + rawData + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setPercentage3(double percentage3){
    this.percentage3 = percentage3;
}


public String getRelation5(){
    return relation5;
}


public String getRelation6(){
    return relation6;
}


public int getNomineeId(){
    return nomineeId;
}


}