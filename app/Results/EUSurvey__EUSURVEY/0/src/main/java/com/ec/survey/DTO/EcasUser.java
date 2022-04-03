package com.ec.survey.DTO;
 import com.ec.survey.tools.ConversionTools;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
public class EcasUser {

 private  Integer id;

 private  String name;

 private  String email;

 private  String givenName;

 private  String surname;

 private  String phone;

 private  String ecMoniker;

 private  String employeeType;

 private  String organisation;

 private  String departmentNumber;

 private  Set<String> userLDAPGroups;

 private  Boolean deactivated;

 private  Date modified;

 private  Date invited;

 private  Date reminded;

 private  int answers;

public EcasUser(String name, String email, String giveName, String surname, String phone, Set<String> userLDAPGroups, String ecMoniker, String employeeType, String organisation, String department, Boolean deactivated, Date modified) {
    this.name = name;
    this.email = email;
    this.givenName = giveName;
    this.surname = surname;
    this.phone = phone;
    this.organisation = organisation;
    this.ecMoniker = ecMoniker;
    this.employeeType = employeeType;
    this.userLDAPGroups = userLDAPGroups;
    this.departmentNumber = department;
    this.deactivated = deactivated;
    this.modified = modified;
}public EcasUser() {
}
@Column(name = "USER_PHONE")
public String getPhone(){
    return phone;
}


@Column(name = "USER_LOGIN", unique = true)
public String getName(){
    return name;
}


@Id
@Column(name = "USER_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "USER_ECMONIKER")
public String getEcMoniker(){
    return ecMoniker;
}


@Column(name = "USER_GN")
public String getGivenName(){
    return givenName;
}


@Column(name = "USER_EMPLOYEETYPE")
public String getEmployeeType(){
    return employeeType;
}


@Column(name = "USER_DEPARTMENT")
public String getDepartmentNumber(){
    return departmentNumber;
}


@Column(name = "USER_ORGANISATION")
public String getOrganisation(){
    return organisation;
}


@Column(name = "USER_MODIFIED")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getModified(){
    return modified;
}


@Transient
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getInvited(){
    return invited;
}


@ElementCollection
@CollectionTable(name = "ECASGROUPS", joinColumns = @JoinColumn(name = "eg_id"))
@Column(name = "GRPS")
public Set<String> getUserLDAPGroups(){
    return userLDAPGroups;
}


@Transient
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getReminded(){
    return reminded;
}


@Column(name = "USER_DEACTIVATED")
public Boolean getDeactivated(){
    return deactivated;
}


@Transient
public String getDisplayName(){
    if (givenName != null && givenName.length() > 0) {
        return givenName + " " + surname;
    }
    return name;
}


@Transient
public int getAnswers(){
    return answers;
}


@Column(name = "USER_EMAIL", nullable = false)
public String getEmail(){
    return email;
}


@Transient
public String getNiceReminded(){
    return ConversionTools.getFullString(reminded);
}


@Transient
public String getNiceInvited(){
    return ConversionTools.getFullString(invited);
}


@Column(name = "USER_SN")
public String getSurname(){
    return surname;
}


}