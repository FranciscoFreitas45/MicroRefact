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
public void setName(String name){
    this.name = name;
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


public void setReminded(Date reminded){
    this.reminded = reminded;
}


public void setAnswers(int answers){
    this.answers = answers;
}


@Column(name = "USER_EMPLOYEETYPE")
public String getEmployeeType(){
    return employeeType;
}


public void setOrganisation(String organisation){
    this.organisation = organisation;
}


@Column(name = "USER_DEPARTMENT")
public String getDepartmentNumber(){
    return departmentNumber;
}


public void setSurname(String surname){
    this.surname = surname;
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


public void setDeactivated(Boolean deactivated){
    this.deactivated = deactivated;
}


public void setId(Integer id){
    this.id = id;
}


public void setUserLDAPGroups(Set<String> userLDAPGroups){
    this.userLDAPGroups = userLDAPGroups;
}


public void setDepartmentNumber(String departmentNumber){
    this.departmentNumber = departmentNumber;
}


public void setEcMoniker(String ecMoniker){
    this.ecMoniker = ecMoniker;
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


public void setModified(Date modified){
    this.modified = modified;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Transient
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getReminded(){
    return reminded;
}


public void setInvited(Date invited){
    this.invited = invited;
}


@Column(name = "USER_DEACTIVATED")
public Boolean getDeactivated(){
    return deactivated;
}


public void setEmail(String email){
    this.email = email;
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


public void setGivenName(String givenName){
    this.givenName = givenName;
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
public boolean isECAMember(){
    if (userLDAPGroups != null) {
        for (String group : userLDAPGroups) {
            if (group.toLowerCase().startsWith("ECA.")) {
                return true;
            }
        }
    }
    return false;
}


@Transient
public String getNiceInvited(){
    return ConversionTools.getFullString(invited);
}


public void setEmployeeType(String employeeType){
    this.employeeType = employeeType;
}


@Column(name = "USER_SN")
public String getSurname(){
    return surname;
}


}