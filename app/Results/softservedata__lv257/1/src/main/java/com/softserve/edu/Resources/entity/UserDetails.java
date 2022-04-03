package com.softserve.edu.Resources.entity;
 import com.fasterxml.jackson.annotation.JsonProperty;
import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import com.softserve.edu.Resources.Request.UserRequest;
import com.softserve.edu.Resources.Request.Impl.UserRequestImpl;
import com.softserve.edu.Resources.DTO.User;
@Entity
@Table(name = "user_details")
public class UserDetails {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
 private  Long id;

@OneToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "id_document")
 private  Avatar document;

@Column(name = "first_name")
@JsonProperty("first_name")
 private  String firstName;

@Column(name = "second_name")
@JsonProperty("second_name")
 private  String secondName;

@Column(name = "middle_name")
@JsonProperty("middle_name")
 private  String middleName;

@Column(name = "passport_series")
@JsonProperty("passport_series")
// @NotEmpty
@Length(max = 2, min = 2, message = "This is invalid passport series.")
 private  String passportSeries;

@Column(name = "passport_number")
@JsonProperty("passport_number")
@Length(max = 6, min = 6, message = "This is invalid passport number.")
 private  String passportNumber;

@Column(name = "issued_by")
@JsonProperty("issued_by")
 private  String issuedBy;

@Column(name = "date_of_issue")
@JsonProperty("date_of_issue")
 private  Date dateOfIssue;

@Column(name = "id_address")
@JsonProperty("id_address")
 private  String idAddress;

@Column(name = "phone")
@JsonProperty("phone")
// +12(345)-678-90-12
@Length(max = 18, min = 18, message = "This is invalid phone number.")
 private  String phone;

@Column(name = "bank_id")
@JsonProperty("bank_id")
 private  String bankId;

@Column(name = "code")
 private  String code;

@Column(name = "extension")
 private  String fileExtension;

@Column(name = "URL")
 private  String documentsURL;

@Transient
 private  MultipartFile file;

@Transient
 private  User user;

@Column(name = "idO2KS")
 private Long idO2KS;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public UserDetails() {
    this.code = "file" + UUID.randomUUID().toString().substring(26).toUpperCase();
}
public String getPhone(){
    return phone;
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
  this.user = userrequest.getUser(this.idO2KS);
return this.user;
}}



public void setFileExtension(String fileExtension){
    this.fileExtension = fileExtension;
}


public Long getId(){
    return id;
}


public MultipartFile getFile(){
    return file;
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


public void setIssuedBy(String issuedBy){
    this.issuedBy = issuedBy;
}


public void setUser(User user){
this.idO2KS = user.getUser() ;
userrequest.setUser(user,this.idO2KS);
 this.user = user;
}



public String getCode(){
    return code;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public void setDocumentsURL(String documentsURL){
    this.documentsURL = documentsURL;
}


public void setDateOfIssue(Date dateOfIssue){
    this.dateOfIssue = dateOfIssue;
}


public void setCode(String code){
    this.code = code;
}


public String getIssuedBy(){
    return issuedBy;
}


public String getIdAddress(){
    return idAddress;
}


public void setBankId(String bankId){
    this.bankId = bankId;
}


public void setPhone(String phone){
    this.phone = phone;
}


public String getBankId(){
    return bankId;
}


public void setFile(MultipartFile file){
    this.file = file;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    UserDetails that = (UserDetails) o;
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
    return "UserDetails{" + "id=" + id + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", middleName='" + middleName + '\'' + ", passportSeries='" + passportSeries + '\'' + ", passportNumber='" + passportNumber + '\'' + ", issuedBy='" + issuedBy + '\'' + ", dateOfIssue=" + dateOfIssue + ", idAddress='" + idAddress + '\'' + ", phone='" + phone + '\'' + ", bankId='" + bankId + '\'' + ", user=" + user + '}';
}


public String getDocumentsURL(){
    return documentsURL;
}


public String getFirstName(){
    return firstName;
}


}