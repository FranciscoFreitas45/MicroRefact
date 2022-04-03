package com.ec.survey.model;
 import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "SETTINGS")
public class Setting {

 private  Integer id;

 private  String key;

 private  String value;

 private  String format;

 public  String LDAPsyncEnabled;

 public  String LDAPsyncFrequency;

 public  String LDAPsyncStart;

 public  String LDAPsyncTime;

 public  String LDAPsync2Enabled;

 public  String LDAPsync2Frequency;

 public  String LDAPsync2Start;

 public  String LDAPsync2Time;

 public  String SurveyMigrateStart;

 public  String SurveyMigrateTime;

 public  String LastSurveyToMigrate;

 public  String AnswerPDFDeletionStart;

 public  String AnswerPDFDeletionTime;

 public  String LastSurveyToDeleteAnswerPDFs;

 public  String ActivityLoggingEnabled;

 public  String CreateSurveysForExternalsDisabled;

 public  String ReportingMigrationEnabled;

 public  String ReportingMigrationStart;

 public  String ReportingMigrationTime;

 public  String ReportingMigrationSurveyToMigrate;

 public  String WeakAuthenticationDisabled;

 public  String MaxReports;

 public  String ReportText;

 public  String ReportRecipients;

 public  String FreezeUserTextAdminBan;

 public  String FreezeUserTextAdminUnban;

 public  String FreezeUserTextBan;

 public  String FreezeUserTextUnban;

 public  String BannedUserRecipients;

 public  String TrustValueCreatorInternal;

 public  String TrustValuePastSurveys;

 public  String TrustValuePrivilegedUser;

 public  String TrustValueNbContributions;

 public  String TrustValueMinimumPassMark;

 public  String AnswersAnonymWorkerInterval;

 public  String AnswersAnonymWorkerEnabled;


@Column(name = "SETTINGS_KEY", unique = true)
public String getKey(){
    return key;
}


@Column(name = "SETTINGS_VALUE")
public String getValue(){
    return value;
}


public void setFormat(String format){
    this.format = format;
}


public void setValue(String value){
    this.value = value;
}


public void setId(Integer id){
    this.id = id;
}


@Transient
public List<Integer> ActivityLoggingIds(){
    List<Integer> ids = new ArrayList<>();
    for (int i = 101; i < 124; i++) {
        ids.add(i);
    }
    for (int i = 201; i < 229; i++) {
        ids.add(i);
    }
    for (int i = 301; i < 315; i++) {
        ids.add(i);
    }
    ids.add(401);
    ids.add(402);
    ids.add(403);
    ids.add(404);
    ids.add(405);
    ids.add(406);
    for (int i = 501; i < 508; i++) {
        ids.add(i);
    }
    ids.add(601);
    ids.add(602);
    ids.add(603);
    ids.add(701);
    ids.add(801);
    ids.add(802);
    return ids;
}


@Id
@Column(name = "SETTINGS_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "SETTINGS_FORMAT")
public String getFormat(){
    return format;
}


public void setKey(String key){
    this.key = key;
}


}