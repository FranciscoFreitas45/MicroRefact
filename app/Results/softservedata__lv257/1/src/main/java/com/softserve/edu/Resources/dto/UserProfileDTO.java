package com.softserve.edu.Resources.dto;
 import com.softserve.edu.Resources.Constants;
import com.softserve.edu.Resources.entity.Avatar;
import com.softserve.edu.Resources.entity.User;
import javax.persistence;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;
import com.softserve.edu.Resources.Interface.User;
public class UserProfileDTO {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
 private  Long id;

 private  String firstName;

 private  String secondName;

 private  String middleName;

@Size(min = 2, max = 2, message = "This is invalid passport series.UserProfileDTO")
 private  String passportSeries;

@Size(min = 6, max = 6, message = "This is invalid passport series.UserProfileDTO")
 private  String passportNumber;

 private  String issuedBy;

@Past
 private  Date dateOfIssue;

 private  String idAddress;

@Size(min = 18, max = 18, message = "This is invalid phone.UserProfileDTO")
 private  String phone;

 private  String bankId;

 private  User user;

 private  String gender;

 private  String code;

 private  String fileExtension;

 private  String documentsURL;

@DecimalMax("999")
 private  String phoneCountry;

 private  String phoneOperator;

 private  String phoneNumber;

 private  Avatar document;

public UserProfileDTO() {
}
public String getPhone(){
    return phone;
}


public void setPhoneOperator(String phoneOperator){
    this.phoneOperator = phoneOperator;
}


public String getPassportNumber(){
    return passportNumber;
}


public String getPassportSeries(){
    return passportSeries;
}


public void setSecondName(String secondName){
    this.secondName = secondName;
}


public User getUser(){
    return user;
}


public String getPhoneCountry(){
    return phoneCountry;
}


public void setPhoneCountry(String phoneCountry){
    this.phoneCountry = phoneCountry;
}


public Long getId(){
    return id;
}


public void setFileExtension(String fileExtension){
    this.fileExtension = fileExtension;
}


public String getMiddleName(){
    return middleName;
}


public void setIdAddress(String idAddress){
    this.idAddress = idAddress;
}


public Date getDateOfIssue(){
    return dateOfIssue;
}


public void setGender(String gender){
    this.gender = gender;
}


public String getSecondName(){
    return secondName;
}


public void setPassportNumber(String passportNumber){
    this.passportNumber = passportNumber;
}


@Override
public int hashCode(){
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
    result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
    result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
    result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
    result = 31 * result + (issuedBy != null ? issuedBy.hashCode() : 0);
    result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
    result = 31 * result + (idAddress != null ? idAddress.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    result = 31 * result + (bankId != null ? bankId.hashCode() : 0);
    result = 31 * result + (user != null ? user.hashCode() : 0);
    return result;
}


public void setId(Long id){
    this.id = id;
}


public String getPhoneNumber(){
    return phoneNumber;
}


public void setIssuedBy(String issuedBy){
    this.issuedBy = issuedBy;
}


public void setUser(User user){
    this.user = user;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public String getCode(){
    return code;
}


public void setDocumentsURL(String documentsURL){
    this.documentsURL = documentsURL;
}


public void setDateOfIssue(Date dateOfIssue){
    this.dateOfIssue = dateOfIssue;
}


public Avatar getDocument(){
    return document;
}


public String getPhoneOperator(){
    return phoneOperator;
}


public String getIssuedBy(){
    return issuedBy;
}


public String getIdAddress(){
    return idAddress;
}


public void setCode(String code){
    this.code = code;
}


public void setBankId(String bankId){
    this.bankId = bankId;
}


public void setPhone(String phone){
    this.phone = phone;
}


public String getGender(){
    return gender;
}


public String getBankId(){
    return bankId;
}


public void setDocument(Avatar document){
    this.document = document;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    UserProfileDTO that = (UserProfileDTO) o;
    if (id != null ? !id.equals(that.id) : that.id != null)
        return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
        return false;
    if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null)
        return false;
    if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null)
        return false;
    if (passportSeries != null ? !passportSeries.equals(that.passportSeries) : that.passportSeries != null)
        return false;
    if (passportNumber != null ? !passportNumber.equals(that.passportNumber) : that.passportNumber != null)
        return false;
    if (issuedBy != null ? !issuedBy.equals(that.issuedBy) : that.issuedBy != null)
        return false;
    if (dateOfIssue != null ? !dateOfIssue.equals(that.dateOfIssue) : that.dateOfIssue != null)
        return false;
    if (idAddress != null ? !idAddress.equals(that.idAddress) : that.idAddress != null)
        return false;
    if (phone != null ? !phone.equals(that.phone) : that.phone != null)
        return false;
    if (bankId != null ? !bankId.equals(that.bankId) : that.bankId != null)
        return false;
    return user != null ? user.equals(that.user) : that.user == null;
}


public String getFileExtension(){
    return fileExtension;
}


public void setPassportSeries(String passportSeries){
    this.passportSeries = passportSeries;
}


@Override
public String toString(){
    return "UserProfileDTO{" + "id=" + id + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", middleName='" + middleName + '\'' + ", passportSeries='" + passportSeries + '\'' + ", passportNumber='" + passportNumber + '\'' + ", issuedBy='" + issuedBy + '\'' + ", dateOfIssue=" + dateOfIssue + ", idAddress='" + idAddress + '\'' + ", phone='" + phone + '\'' + ", bankId='" + bankId + '\'' + ", user=" + user + '}';
}


public String getFirstName(){
    return firstName;
}


public String getDocumentsURL(){
    return documentsURL;
}


}