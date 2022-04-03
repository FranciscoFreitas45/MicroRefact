package com.ec.survey.Interface;
public interface LdapDBService {

   public String[] getDepartments(String domain,String term,boolean prefix,boolean removeTerm);
}