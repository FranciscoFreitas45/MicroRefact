package com.softserve.edu.Resources.DTO;
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
public class UserDetails {

 private  Long id;

 private  Avatar document;

 private  String firstName;

 private  String secondName;

 private  String middleName;

 private  String passportSeries;

 private  String passportNumber;

 private  String issuedBy;

 private  Date dateOfIssue;

 private  String idAddress;

 private  String phone;

 private  String bankId;

 private  String code;

 private  String fileExtension;

 private  String documentsURL;

 private  MultipartFile file;

 private  User user;

 private Long idO2KS;

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


public User getUser(){
  this.user = userrequest.getUser(this.idO2KS);
return this.user;
}}



public Long getId(){
    return id;
}


public MultipartFile getFile(){
    return file;
}


public String getMiddleName(){
    return middleName;
}


public Date getDateOfIssue(){
    return dateOfIssue;
}


public String getSecondName(){
    return secondName;
}


public String getCode(){
    return code;
}


public String getIssuedBy(){
    return issuedBy;
}


public String getIdAddress(){
    return idAddress;
}


public String getBankId(){
    return bankId;
}


public String getFileExtension(){
    return fileExtension;
}


public String getDocumentsURL(){
    return documentsURL;
}


public String getFirstName(){
    return firstName;
}


}