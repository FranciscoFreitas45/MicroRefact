package com.ec.survey.DTO;
 import com.ec.survey.model.administration.LocalPrivilege;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.Survey;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
public class Access {

 private  int id;

 private  User user;

 private  String department;

 private  Survey survey;

 private  Map<LocalPrivilege,Integer> localPrivileges;

 private  boolean readonly;

 protected  Logger logger;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public Access() {
    localPrivileges = new HashMap<>();
    localPrivileges.put(LocalPrivilege.AccessDraft, 0);
    localPrivileges.put(LocalPrivilege.FormManagement, 0);
    localPrivileges.put(LocalPrivilege.AccessResults, 0);
    localPrivileges.put(LocalPrivilege.ManageInvitations, 0);
}
@Transient
public String getInfo(){
    String result = "";
    if (user != null) {
        result = user.getName();
    } else if (department != null) {
        result = department;
    }
    result += " - " + getPrivileges();
    return result;
}


@Column(name = "ACCESS_DEPARTMENT", nullable = true)
public String getDepartment(){
    return department;
}


@ManyToOne
@JoinColumn(name = "ACCESS_USER", nullable = true)
public User getUser(){
    return user;
}


@Id
@Column(name = "ACCESS_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@ManyToOne
@JoinColumn(name = "SURVEY")
public Survey getSurvey(){
    return survey;
}


@Transient
public int getLocalPrivilegeValue(String key){
    return localPrivileges.get(LocalPrivilege.valueOf(key));
}


@Column(name = "ACCESS_PRIVILEGES")
public String getPrivileges(){
    StringBuilder result = new StringBuilder();
    for (Entry<LocalPrivilege, Integer> entry : localPrivileges.entrySet()) {
        result.append(entry.getKey().toString()).append(":").append(entry.getValue()).append(";");
    }
    return result.toString();
}


@Transient
public Map<LocalPrivilege,Integer> getLocalPrivileges(){
    return localPrivileges;
}


@Transient
public boolean hasAnyPrivileges(){
    return localPrivileges.get(LocalPrivilege.AccessDraft) > 0 || localPrivileges.get(LocalPrivilege.AccessResults) > 0 || localPrivileges.get(LocalPrivilege.FormManagement) > 0 || localPrivileges.get(LocalPrivilege.ManageInvitations) > 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasAnyPrivileges"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}