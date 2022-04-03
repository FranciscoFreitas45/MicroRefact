package com.ec.survey.model;
 public class UserFilter {

 private  String login;

 private  String email;

 private  String comment;

 private  Boolean banned;

 private  Boolean unbanned;

 private  Boolean ECAS;

 private  Boolean system;

 private  Boolean ECASaccess;

 private  Boolean NoECASaccess;

 private  Boolean ECaccess;

 private  Boolean NoECaccess;

 private  String[] roles;

 private  String[] languages;

 private  String sortKey;

 private  String sortOrder;


public Boolean getECaccess(){
    return ECaccess;
}


public String[] getLanguages(){
    return languages;
}


public Boolean getBanned(){
    return banned;
}


public String getSortKey(){
    return sortKey;
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
}


public String getSortOrder(){
    return sortOrder;
}


public void setNoECASaccess(Boolean noECASaccess){
    NoECASaccess = noECASaccess;
}


public Boolean getSystem(){
    return system;
}


public Boolean getECASaccess(){
    return ECASaccess;
}


public void setUnbanned(Boolean unbanned){
    this.unbanned = unbanned;
}


public void setECASaccess(Boolean eCASaccess){
    ECASaccess = eCASaccess;
}


public void setLanguages(String[] languages){
    this.languages = languages;
}


public Boolean getECAS(){
    return ECAS;
}


public void setSystem(Boolean system){
    this.system = system;
}


public boolean containsLanguage(String code){
    if (languages != null) {
        for (String c : languages) {
            if (c.equalsIgnoreCase(code))
                return true;
        }
    }
    return false;
}


public String getComment(){
    return comment;
}


public Boolean getUnbanned(){
    return this.unbanned;
}


public void setRoles(String[] roles){
    this.roles = roles;
}


public String[] getRoles(){
    return roles;
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
}


public boolean containsRole(String id){
    if (roles != null) {
        for (String c : roles) {
            if (c.equalsIgnoreCase(id))
                return true;
        }
    }
    return false;
}


public Boolean getNoECaccess(){
    return NoECaccess;
}


public String getLogin(){
    return login;
}


public void setBanned(Boolean banned){
    this.banned = banned;
}


public void setEmail(String email){
    this.email = email;
}


public Boolean getNoECASaccess(){
    return NoECASaccess;
}


public void setLogin(String login){
    this.login = login;
}


public void setECaccess(Boolean eCaccess){
    ECaccess = eCaccess;
}


public String getEmail(){
    return email;
}


public void setComment(String comment){
    this.comment = comment;
}


public void setECAS(Boolean eCAS){
    ECAS = eCAS;
}


public void setNoECaccess(Boolean noECaccess){
    NoECaccess = noECaccess;
}


}