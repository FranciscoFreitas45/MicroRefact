package com.ec.survey.DTO;
 import com.ec.survey.model.attendees.AttributeName;
import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence;
import java.util;
import java.util.Map.Entry;
public class User {

 private  long serialVersionUID;

 private  Integer id;

 private  String login;

 private  String password;

 private  String passwordSalt;

 private  String email;

 private  String emailToValidate;

 private  String otherEmail;

 private  String comment;

 private  String language;

 private  String defaultPivotLanguage;

 private  String type;

 private  String displayName;

 private  String givenName;

 private  String surName;

 private  boolean validated;

 private  List<Role> roles;

 private  Map<GlobalPrivilege,Integer> globalPrivileges;

 private  Map<LocalPrivilege,Integer> localPrivileges;

 private  boolean privilegesLoaded;

 private  List<AttributeName> selectedAttributes;

 private  String selectedAttributesOrder;

 private  String validationCode;

 private  Date validationCodeGeneration;

 private  List<String> departments;

 private  int badLoginAttempts;

 private  int userExistsAttempts;

 private  Date userExistsAttemptDate;

 private  boolean agreedToToS;

 private  Date agreedToToSDate;

 private  String agreedToToSVersion;

 private  boolean agreedToPS;

 private  Date agreedToPSDate;

 private  String agreedToPSVersion;

 private  Integer lastEditedSurvey;

 private  boolean canCreateSurveys;

 private  boolean isFrozen;

 private  boolean deleted;

 private  boolean deleteRequested;

 private  String deleteCode;

 private  Date deleteDate;

 public  String ECAS;

 public  String SYSTEM;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

public User() {
    globalPrivileges = new HashMap<>();
    globalPrivileges.put(GlobalPrivilege.UserManagement, 0);
    globalPrivileges.put(GlobalPrivilege.FormManagement, 0);
    globalPrivileges.put(GlobalPrivilege.ContactManagement, 0);
    globalPrivileges.put(GlobalPrivilege.RightManagement, 0);
    globalPrivileges.put(GlobalPrivilege.ECAccess, 0);
    globalPrivileges.put(GlobalPrivilege.SystemManagement, 0);
    localPrivileges = new HashMap<>();
    localPrivileges.put(LocalPrivilege.AccessDraft, 0);
    localPrivileges.put(LocalPrivilege.FormManagement, 0);
    localPrivileges.put(LocalPrivilege.AccessResults, 0);
    localPrivileges.put(LocalPrivilege.ManageInvitations, 0);
    roles = new ArrayList<>();
    selectedAttributes = new ArrayList<>();
}
@Transient
public String getName(){
    if (displayName != null && displayName.length() > 0) {
        return displayName;
    } else {
        return login;
    }
}


@Transient
public String getDepartment(){
    if (departments != null && !departments.isEmpty()) {
        return "(EC-" + departments.get(departments.size() - 1) + ")";
    }
    if (ECAS.equals(type) && departments != null && departments.size() == 1 && departments.get(0).equalsIgnoreCase("external")) {
        return "(EXT)";
    }
    return "";
}


@Column(name = "USER_SURNAME")
public String getSurName(){
    return surName;
}


@Column(name = "USER_DELDATE")
public Date getDeleteDate(){
    return deleteDate;
}


@Column(name = "USER_PSDATE")
public Date getAgreedToPSDate(){
    return agreedToPSDate;
}


@Column(name = "USER_GIVENNAME")
public String getGivenName(){
    return givenName;
}


@Column(name = "USER_OTHEREMAIL")
public String getOtherEmail(){
    return otherEmail;
}


@Column(name = "USER_ATTORDER")
public String getSelectedAttributesOrder(){
    return selectedAttributesOrder;
}


@Column(name = "VALIDCODE")
public String getValidationCode(){
    return validationCode;
}


@Column(name = "USER_EXISTS_ATTEMPT_DATE")
public Date getUserExistsAttemptDate(){
    return userExistsAttemptDate;
}


@ManyToMany(targetEntity = Role.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Role> getRoles(){
    return roles;
}


@Column(name = "VALIDATED")
public boolean getValidated(){
    return validated;
}


@Column(name = "USER_TOSDATE")
public Date getAgreedToToSDate(){
    return agreedToToSDate;
}


@Transient
public int getGlobalPrivilegeValue(String key){
    return getGlobalPrivileges().get(GlobalPrivilege.valueOf(key));
}


@Transient
public int getECPrivilege(){
    return getGlobalPrivileges().get(GlobalPrivilege.ECAccess);
}


@Column(name = "USER_PSVERSION")
public String getAgreedToPSVersion(){
    return agreedToPSVersion;
}


@Column(name = "USER_TOSVERSION")
public String getAgreedToToSVersion(){
    return agreedToToSVersion;
}


@Transient
public int getFormPrivilege(){
    return getGlobalPrivileges().get(GlobalPrivilege.FormManagement);
}


@Column(name = "USER_TYPE")
public String getType(){
    return type;
}


@Column(name = "USER_DISPLAYNAME")
public String getDisplayName(){
    return displayName;
}


@Transient
public Map<GlobalPrivilege,Integer> getGlobalPrivileges(){
    if (!privilegesLoaded)
        loadPrivileges();
    return globalPrivileges;
}


@Column(name = "USER_PWSALT")
public String getPasswordSalt(){
    return passwordSalt;
}


@Column(name = "USER_EMAIL", nullable = false)
public String getEmail(){
    return email;
}


@Transient
public List<String> getDepartments(){
    return departments;
}


@Transient
public Map<LocalPrivilege,Integer> getLocalPrivileges(){
    return localPrivileges;
}


@Transient
public String getGivenNameOrLogin(){
    String result = getName();
    if (givenName != null && givenName.length() > 0)
        result = givenName;
    return WordUtils.capitalizeFully(result);
}


@Column(name = "USER_DELCODE")
public String getDeleteCode(){
    return deleteCode;
}


@Id
@Column(name = "USER_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Transient
public String getRolesAsString(){
    StringBuilder s = new StringBuilder();
    if (roles != null) {
        for (Role role : roles) {
            s.append(role.getId()).append(";");
        }
    }
    return s.toString();
}


@Transient
public boolean getShowAdmin(){
    return getGlobalPrivileges().get(GlobalPrivilege.UserManagement) > 1 || getGlobalPrivileges().get(GlobalPrivilege.RightManagement) > 0 || getGlobalPrivileges().get(GlobalPrivilege.SystemManagement) > 0;
}


@Transient
public int getLocalPrivilegeValue(String key){
    return localPrivileges.get(LocalPrivilege.valueOf(key));
}


@Transient
public List<String> getAllEmailAddresses(){
    List<String> result = new ArrayList<>();
    result.add(email);
    if (otherEmail != null && otherEmail.length() > 0) {
        String[] emails = otherEmail.split(";");
        for (int i = 0; i < emails.length; i++) {
            if (emails[i].length() > 0) {
                result.add(emails[i]);
            }
        }
    }
    return result;
}


@Column(name = "USER_COMMENT")
public String getComment(){
    return comment;
}


@Column(name = "USER_PIVOTLANGUAGE")
public String getDefaultPivotLanguage(){
    return defaultPivotLanguage != null ? defaultPivotLanguage : "EN";
}


@ManyToMany(targetEntity = AttributeName.class)
@Fetch(value = FetchMode.SELECT)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<AttributeName> getSelectedAttributes(){
    return selectedAttributes;
}


@Column(name = "ATTEMPTS")
public int getBadLoginAttempts(){
    return badLoginAttempts;
}


@Column(name = "USER_LANGUAGE")
public String getLanguage(){
    return language;
}


@Column(name = "USER_LAST_SURVEY")
public Integer getLastEditedSurvey(){
    return lastEditedSurvey;
}


@Transient
public int getContactPrivilege(){
    return getGlobalPrivileges().get(GlobalPrivilege.ContactManagement);
}


@Column(name = "VALIDDATE")
public Date getValidationCodeGeneration(){
    return validationCodeGeneration;
}


@Column(name = "USER_LOGIN")
public String getLogin(){
    return login;
}


@Column(name = "USER_PASSWORD")
public String getPassword(){
    return password;
}


@Transient
public String getFirstLastName(){
    if (surName != null && surName.length() > 0) {
        return givenName + " " + surName;
    } else {
        return getName();
    }
}


@Column(name = "USER_EXISTS_ATTEMPS")
public int getUserExistsAttempts(){
    return this.userExistsAttempts;
}


@Column(name = "USER_EMAIL_TO_VALIDATE")
public String getEmailToValidate(){
    return emailToValidate;
}


public void setEmail(String email){
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmail"))

.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurName(String surName){
    this.surName = surName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurName"))

.queryParam("surName",surName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGivenName(String givenName){
    this.givenName = givenName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGivenName"))

.queryParam("givenName",givenName)
;
restTemplate.put(builder.toUriString(),null);
}


}