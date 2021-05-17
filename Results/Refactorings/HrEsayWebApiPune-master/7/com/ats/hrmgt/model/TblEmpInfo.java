import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.sql.Date;
@Entity
@Table(name = "tbl_emp_info")
public class TblEmpInfo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_info_id")
 private  int empInfoId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "middle_name")
 private  String middleName;

@Column(name = "middle_person_name")
 private  String middlePersonName;

@Column(name = "middle_name_relation")
 private  String middleNameRelation;

@Column(name = "dob")
 private  Date dob;

@Column(name = "gender")
 private  String gender;

@Column(name = "marital_status")
 private  String maritalStatus;

@Column(name = "address")
 private  String address;

@Column(name = "permanent_address")
 private  String permanentAddress;

@Column(name = "emp_qualification")
 private  String empQualification;

@Column(name = "emer_contact_no_1")
 private  String emerContactNo1;

@Column(name = "emer_contact_addr")
 private  String emerContactAddr;

@Column(name = "blood_group")
 private  String bloodGroup;

@Column(name = "uniform_size")
 private  String uniformSize;

@Column(name = "photo")
 private  String photo;

@Column(name = "photo_pan")
 private  String photoPan;

@Column(name = "photo_adhar")
 private  String photoAdhar;

@Column(name = "photo_voterid")
 private  String photoVoterid;

@Column(name = "photo_lightbill")
 private  String photoLightbill;

@Column(name = "photo_rationcard")
 private  String photoRationcard;

@Column(name = "photo_bankpassbook")
 private  String photoBankpassbook;

@Column(name = "email")
 private  String email;

@Column(name = "emer_name")
 private  String emerName;

@Column(name = "emer_contact_no_2")
 private  String emerContactNo2;

@Column(name = "photo_signature")
 private  String photoSignature;

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


public String getPhoto(){
    return photo;
}


public String getPhotoBankpassbook(){
    return photoBankpassbook;
}


public String getExVar1(){
    return exVar1;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob(){
    return dob;
}


public String getEmerName(){
    return emerName;
}


public String getEmerContactNo2(){
    return emerContactNo2;
}


public String getEmerContactNo1(){
    return emerContactNo1;
}


public void setEmerContactAddr(String emerContactAddr){
    this.emerContactAddr = emerContactAddr;
}


public void setUniformSize(String uniformSize){
    this.uniformSize = uniformSize;
}


public void setGender(String gender){
    this.gender = gender;
}


public void setPhotoPan(String photoPan){
    this.photoPan = photoPan;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public String getEmerContactAddr(){
    return emerContactAddr;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public String getMiddleNameRelation(){
    return middleNameRelation;
}


public String getPhotoVoterid(){
    return photoVoterid;
}


public void setEmpInfoId(int empInfoId){
    this.empInfoId = empInfoId;
}


public String getUniformSize(){
    return uniformSize;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public String getGender(){
    return gender;
}


public void setMaritalStatus(String maritalStatus){
    this.maritalStatus = maritalStatus;
}


public String getEmpQualification(){
    return empQualification;
}


public void setPhotoLightbill(String photoLightbill){
    this.photoLightbill = photoLightbill;
}


public String getPhotoAdhar(){
    return photoAdhar;
}


public void setPhotoBankpassbook(String photoBankpassbook){
    this.photoBankpassbook = photoBankpassbook;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getPhotoLightbill(){
    return photoLightbill;
}


public void setEmail(String email){
    this.email = email;
}


public void setEmerName(String emerName){
    this.emerName = emerName;
}


public void setPhotoRationcard(String photoRationcard){
    this.photoRationcard = photoRationcard;
}


public String getEmail(){
    return email;
}


public String getRawData(){
    return rawData;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setBloodGroup(String bloodGroup){
    this.bloodGroup = bloodGroup;
}


public void setMiddleNameRelation(String middleNameRelation){
    this.middleNameRelation = middleNameRelation;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public String getMaritalStatus(){
    return maritalStatus;
}


public void setRawData(String rawData){
    this.rawData = rawData;
}


public String getPermanentAddress(){
    return permanentAddress;
}


public int getEmpInfoId(){
    return empInfoId;
}


public String getMiddleName(){
    return middleName;
}


public String getPhotoSignature(){
    return photoSignature;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setMiddlePersonName(String middlePersonName){
    this.middlePersonName = middlePersonName;
}


public String getMiddlePersonName(){
    return middlePersonName;
}


public String getAddress(){
    return address;
}


public void setEmpQualification(String empQualification){
    this.empQualification = empQualification;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public void setAddress(String address){
    this.address = address;
}


public void setEmerContactNo2(String emerContactNo2){
    this.emerContactNo2 = emerContactNo2;
}


public String getPhotoPan(){
    return photoPan;
}


public void setPhotoSignature(String photoSignature){
    this.photoSignature = photoSignature;
}


public void setEmerContactNo1(String emerContactNo1){
    this.emerContactNo1 = emerContactNo1;
}


public String getPhotoRationcard(){
    return photoRationcard;
}


public String getBloodGroup(){
    return bloodGroup;
}


public void setPhotoAdhar(String photoAdhar){
    this.photoAdhar = photoAdhar;
}


public int getDelStatus(){
    return delStatus;
}


public void setPhoto(String photo){
    this.photo = photo;
}


public void setDob(Date dob){
    this.dob = dob;
}


public void setPermanentAddress(String permanentAddress){
    this.permanentAddress = permanentAddress;
}


@Override
public String toString(){
    return "TblEmpInfo [empInfoId=" + empInfoId + ", empId=" + empId + ", middleName=" + middleName + ", middlePersonName=" + middlePersonName + ", middleNameRelation=" + middleNameRelation + ", dob=" + dob + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", address=" + address + ", permanentAddress=" + permanentAddress + ", empQualification=" + empQualification + ", emerContactNo1=" + emerContactNo1 + ", emerContactAddr=" + emerContactAddr + ", bloodGroup=" + bloodGroup + ", uniformSize=" + uniformSize + ", photo=" + photo + ", photoPan=" + photoPan + ", photoAdhar=" + photoAdhar + ", photoVoterid=" + photoVoterid + ", photoLightbill=" + photoLightbill + ", photoRationcard=" + photoRationcard + ", photoBankpassbook=" + photoBankpassbook + ", email=" + email + ", emerName=" + emerName + ", emerContactNo2=" + emerContactNo2 + ", photoSignature=" + photoSignature + ", rawData=" + rawData + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setPhotoVoterid(String photoVoterid){
    this.photoVoterid = photoVoterid;
}


}