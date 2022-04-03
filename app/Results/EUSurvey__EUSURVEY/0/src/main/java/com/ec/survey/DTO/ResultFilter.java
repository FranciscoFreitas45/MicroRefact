package com.ec.survey.DTO;
 import com.ec.survey.model.survey.ChoiceQuestion;
import com.ec.survey.model.survey.DelphiChartType;
import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.FreeTextQuestion;
import com.ec.survey.model.survey.GalleryQuestion;
import com.ec.survey.model.survey.Matrix;
import com.ec.survey.model.survey.NumberQuestion;
import com.ec.survey.model.survey.Question;
import com.ec.survey.model.survey.RatingQuestion;
import com.ec.survey.model.survey.Section;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.Tools;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util;
import java.util.Map.Entry;
public class ResultFilter {

 private  long serialVersionUID;

 private String value;

 private String value;

 private  int id;

 private  Integer userId;

 private  String invitation;

 private  String caseId;

 private  String draftId;

 private  String user;

 private  int surveyId;

 private  String surveyUid;

 private  String surveyShortname;

 private  String surveyTitle;

 private  String surveyStatus;

 private  String status;

 private  String surveyPublishedResults;

 private  Date surveyEndDateFrom;

 private  Date surveyEndDateTo;

 private  Date generatedFrom;

 private  Date generatedTo;

 private  Date updatedFrom;

 private  Date updatedTo;

 private  Set<String> languages;

 private  String sortKey;

 private  String sortOrder;

 private  Map<String,String> filterValues;

 private  Set<String> visibleQuestions;

 private  Set<String> exportedQuestions;

 private  Set<String> visibleExplanations;

 private  Set<String> exportedExplanations;

 private  Set<String> visibleDiscussions;

 private  Set<String> exportedDiscussions;

 private  Boolean createdOrUpdated;

 private  Boolean onlyReallyUpdated;

 private  Boolean noTestAnswers;

 private  Boolean defaultQuestions;

 private  String answeredECFProfileUID;

 private  String compareToECFProfileUID;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


@Column(name = "RESFILTER_STITLE")
public String getSurveyStatus(){
    return surveyStatus;
}


@Column(name = "RESFILTER_DATETO")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getGeneratedTo(){
    return generatedTo;
}


@Transient
public String getFromValue(String questionId,String questionUid){
    String combined = questionId + "|" + questionUid + "from";
    if (filterValues.containsKey(combined))
        return filterValues.get(combined);
    return "";
}


@Transient
public String getHash(boolean allAnswers){
    StringBuilder result = new StringBuilder();
    result.append(this.caseId);
    result.append(this.invitation);
    result.append(this.surveyId);
    result.append(this.user);
    result.append(this.generatedFrom);
    result.append(this.generatedTo);
    result.append(this.updatedFrom);
    result.append(this.updatedTo);
    result.append(this.createdOrUpdated);
    result.append(this.onlyReallyUpdated);
    result.append(this.answeredECFProfileUID == null ? "" : this.answeredECFProfileUID);
    result.append(this.languages == null ? "" : StringUtils.join(this.languages, ""));
    result.append(StringUtils.join(this.filterValues.keySet(), ""));
    result.append(StringUtils.join(this.filterValues.values(), ""));
    sortAndAppendSetIdsToStringBuilder(visibleQuestions, result);
    sortAndAppendSetIdsToStringBuilder(exportedQuestions, result);
    sortAndAppendSetIdsToStringBuilder(visibleExplanations, result);
    sortAndAppendSetIdsToStringBuilder(exportedExplanations, result);
    sortAndAppendSetIdsToStringBuilder(visibleDiscussions, result);
    sortAndAppendSetIdsToStringBuilder(exportedDiscussions, result);
    if (allAnswers) {
        result.append("aa");
    }
    return Tools.md5hash(result.toString());
}


@ElementCollection
public Set<String> getLanguages(){
    return languages;
}


@Column(name = "RESFILTER_SORTKEY")
public String getSortKey(){
    return sortKey;
}


@Column(name = "RESFILTER_STATUS")
public String getStatus(){
    return status;
}


@Column(name = "RESFILTER_INV")
public String getInvitation(){
    return invitation;
}


public String getSurveyShortname(){
    return surveyShortname;
}


@Column(name = "RESFILTER_DEFAULT")
public Boolean getDefaultQuestions(){
    return defaultQuestions;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getExportedDiscussions(){
    return exportedDiscussions;
}


@Column(name = "RESFILTER_UPDATEFROM")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getUpdatedFrom(){
    return updatedFrom;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Map<String,String> getFilterValues(){
    return filterValues;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getExportedExplanations(){
    return exportedExplanations;
}


@Column(name = "RESFILTER_SENDTO")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getSurveyEndDateTo(){
    return surveyEndDateTo;
}


@Column(name = "RESFILTER_REALUPD")
public Boolean getOnlyReallyUpdated(){
    return onlyReallyUpdated;
}


@Column(name = "RESFILTER_DATEFROM")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getGeneratedFrom(){
    return generatedFrom;
}


public String getDraftId(){
    return draftId;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getVisibleExplanations(){
    return visibleExplanations;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getExportedQuestions(){
    return exportedQuestions;
}


@Column(name = "RESFILTER_USER")
public String getUser(){
    return user;
}


@Id
@Column(name = "RESFILTER_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "RESFILTER_SORTORDER")
public String getSortOrder(){
    return sortOrder;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getVisibleQuestions(){
    return visibleQuestions;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getVisibleDiscussions(){
    return visibleDiscussions;
}


@Column(name = "RESFILTER_SURPUBRES")
public String getSurveyPublishedResults(){
    return surveyPublishedResults;
}


public String getSurveyUid(){
    return surveyUid;
}


@Column(name = "RESFILTER_ANS_ECF_PROFILE_UID")
public String getAnsweredECFProfileUID(){
    return answeredECFProfileUID;
}


@Column(name = "RESFILTER_UPDATETO")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getUpdatedTo(){
    return updatedTo;
}


public String getSurveyTitle(){
    return surveyTitle;
}


@Column(name = "RESFILTER_SENDFROM")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getSurveyEndDateFrom(){
    return surveyEndDateFrom;
}


@Column(name = "RESFILTER_CRORUPD")
public Boolean getCreatedOrUpdated(){
    return createdOrUpdated;
}


@Transient
public String getValue(String questionId,String questionUid){
    String combined = questionId + "|" + questionUid;
    if (filterValues.containsKey(combined))
        return filterValues.get(combined);
    return "";
}


@Column(name = "RESFILTER_CASE")
public String getCaseId(){
    return caseId;
}


@Transient
public String getToValue(String questionId,String questionUid){
    String combined = questionId + "|" + questionUid + "to";
    if (filterValues.containsKey(combined))
        return filterValues.get(combined);
    return "";
}


@Column(name = "RESFILTER_NOTESTANS")
public Boolean getNoTestAnswers(){
    return noTestAnswers;
}


@Column(name = "RESFILTER_COMP_ECF_PROFILE_UID")
public String getCompareToECFProfileUID(){
    return compareToECFProfileUID;
}


public int getSurveyId(){
    return surveyId;
}


@Column(name = "RESFILTER_OWNER")
public Integer getUserId(){
    return userId;
}


@Transient
public boolean isEmpty(){
    if (caseId != null && caseId.length() > 0)
        return false;
    if (invitation != null && invitation.length() > 0)
        return false;
    if (user != null && user.length() > 0)
        return false;
    if (generatedFrom != null)
        return false;
    if (generatedTo != null)
        return false;
    if (updatedFrom != null)
        return false;
    if (updatedTo != null)
        return false;
    if (languages != null && !languages.isEmpty())
        return false;
    if (filterValues != null && !filterValues.isEmpty())
        return false;
    if (answeredECFProfileUID != null && !answeredECFProfileUID.isEmpty())
        return false;
    return true;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


@Transient
public boolean contains(String questionId,String questionUid,String value,String paUid){
    String combined = questionId + "|" + questionUid;
    return (filterValues.containsKey(combined) && (filterValues.get(combined).contains(value + "|") || filterValues.get(combined).contains(paUid)));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/contains"))

.queryParam("questionId",questionId)
.queryParam("questionUid",questionUid)
.queryParam("value",value)
.queryParam("paUid",paUid)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public ResultFilter copy(){
    ResultFilter copy = new ResultFilter();
    merge(copy);
    return copy;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/copy"))

;
ResultFilter aux = restTemplate.getForObject(builder.toUriString(),ResultFilter.class);
return aux;
}


public void setVisibleQuestions(Set<String> visibleQuestions){
    this.visibleQuestions = visibleQuestions;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVisibleQuestions"))

.queryParam("visibleQuestions",visibleQuestions)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExportedQuestions(Set<String> exportedQuestions){
    this.exportedQuestions = exportedQuestions;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExportedQuestions"))

.queryParam("exportedQuestions",exportedQuestions)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyId(int surveyId){
    this.surveyId = surveyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyId"))

.queryParam("surveyId",surveyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDefaultQuestions(Boolean defaultQuestions){
    this.defaultQuestions = defaultQuestions;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDefaultQuestions"))

.queryParam("defaultQuestions",defaultQuestions)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(Integer userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public ResultFilter merge(ResultFilter copy){
    copy.caseId = caseId;
    if (filterValues != null) {
        Map<String, String> newFilterValues = new HashMap<>();
        for (Entry<String, String> entry : filterValues.entrySet()) {
            newFilterValues.put(entry.getKey(), entry.getValue());
        }
        copy.filterValues = newFilterValues;
    }
    copy.generatedFrom = generatedFrom;
    copy.generatedTo = generatedTo;
    copy.invitation = invitation;
    if (languages != null) {
        Set<String> newLanguages = new HashSet<>();
        newLanguages.addAll(languages);
        copy.languages = newLanguages;
    }
    copy.sortKey = sortKey;
    copy.sortOrder = sortOrder;
    copy.updatedFrom = updatedFrom;
    copy.updatedTo = updatedTo;
    copy.user = user;
    Set<String> newVisibleQuestions = new HashSet<>();
    newVisibleQuestions.addAll(visibleQuestions);
    copy.visibleQuestions = newVisibleQuestions;
    Set<String> newExportedQuestions = new HashSet<>();
    newExportedQuestions.addAll(exportedQuestions);
    copy.exportedQuestions = newExportedQuestions;
    Set<String> newVisibleExplanations = new HashSet<>();
    newVisibleExplanations.addAll(visibleExplanations);
    copy.visibleExplanations = newVisibleExplanations;
    Set<String> newExportedExplanations = new HashSet<>();
    newExportedExplanations.addAll(exportedExplanations);
    copy.exportedExplanations = newExportedExplanations;
    Set<String> newVisibleDiscussions = new HashSet<>();
    newVisibleDiscussions.addAll(visibleDiscussions);
    copy.visibleDiscussions = newVisibleDiscussions;
    Set<String> newExportedDiscussions = new HashSet<>();
    newExportedDiscussions.addAll(exportedDiscussions);
    copy.exportedDiscussions = newExportedDiscussions;
    copy.surveyId = surveyId;
    copy.userId = userId;
    copy.createdOrUpdated = createdOrUpdated;
    copy.onlyReallyUpdated = onlyReallyUpdated;
    copy.surveyStatus = surveyStatus;
    copy.status = status;
    copy.surveyEndDateFrom = surveyEndDateFrom;
    copy.surveyEndDateTo = surveyEndDateTo;
    copy.defaultQuestions = defaultQuestions;
    copy.answeredECFProfileUID = answeredECFProfileUID;
    return copy;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/merge"))

.queryParam("copy",copy)
;
ResultFilter aux = restTemplate.getForObject(builder.toUriString(),ResultFilter.class);
return aux;
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyUid"))

.queryParam("surveyUid",surveyUid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyShortname(String surveyShortname){
    this.surveyShortname = surveyShortname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyShortname"))

.queryParam("surveyShortname",surveyShortname)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyTitle(String surveyTitle){
    this.surveyTitle = surveyTitle;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyTitle"))

.queryParam("surveyTitle",surveyTitle)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDraftId(String draftId){
    this.draftId = draftId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDraftId"))

.queryParam("draftId",draftId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCaseId(String caseId){
    this.caseId = caseId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCaseId"))

.queryParam("caseId",caseId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNoTestAnswers(Boolean noTestAnswers){
    this.noTestAnswers = noTestAnswers;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNoTestAnswers"))

.queryParam("noTestAnswers",noTestAnswers)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpdatedFrom(Date updatedFrom){
    this.updatedFrom = updatedFrom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpdatedFrom"))

.queryParam("updatedFrom",updatedFrom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpdatedTo(Date updatedTo){
    this.updatedTo = updatedTo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpdatedTo"))

.queryParam("updatedTo",updatedTo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAnsweredECFProfileUID(String ecfProfileUid){
    this.answeredECFProfileUID = ecfProfileUid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAnsweredECFProfileUID"))

.queryParam("ecfProfileUid",ecfProfileUid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCompareToECFProfileUID(String compareToECFProfileUID){
    this.compareToECFProfileUID = compareToECFProfileUID;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCompareToECFProfileUID"))

.queryParam("compareToECFProfileUID",compareToECFProfileUID)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSortOrder"))

.queryParam("sortOrder",sortOrder)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSortKey"))

.queryParam("sortKey",sortKey)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLanguages(Set<String> languages){
    this.languages = languages;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLanguages"))

.queryParam("languages",languages)
;
restTemplate.put(builder.toUriString(),null);
}


public void clearResultFilter(){
    invitation = null;
    caseId = null;
    draftId = null;
    user = null;
    generatedFrom = null;
    generatedTo = null;
    updatedFrom = null;
    updatedTo = null;
    if (languages != null)
        languages.clear();
    sortKey = "created";
    sortOrder = "DESC";
    if (filterValues != null)
        filterValues.clear();
    createdOrUpdated = false;
    onlyReallyUpdated = false;
    noTestAnswers = false;
    answeredECFProfileUID = null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clearResultFilter"))

;
restTemplate.put(builder.toUriString(),null);
}


public void clearSelectedQuestions(){
    visibleQuestions.clear();
    exportedQuestions.clear();
    visibleExplanations.clear();
    exportedExplanations.clear();
    visibleDiscussions.clear();
    exportedDiscussions.clear();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clearSelectedQuestions"))

;
restTemplate.put(builder.toUriString(),null);
}


@Transient
public void addExportedQuestion(String question){
    if (!this.exportedQuestions.contains(question)) {
        this.exportedQuestions.add(question);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addExportedQuestion"))

.queryParam("question",question)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGeneratedTo(Date generatedTo){
    this.generatedTo = generatedTo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGeneratedTo"))

.queryParam("generatedTo",generatedTo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGeneratedFrom(Date generatedFrom){
    this.generatedFrom = generatedFrom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGeneratedFrom"))

.queryParam("generatedFrom",generatedFrom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUser(String user){
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInvitation(String invitation){
    this.invitation = invitation;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInvitation"))

.queryParam("invitation",invitation)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyStatus(String surveyStatus){
    this.surveyStatus = surveyStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyStatus"))

.queryParam("surveyStatus",surveyStatus)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyEndDateFrom(Date surveyEndDateFrom){
    this.surveyEndDateFrom = surveyEndDateFrom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyEndDateFrom"))

.queryParam("surveyEndDateFrom",surveyEndDateFrom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyEndDateTo(Date surveyEndDateTo){
    this.surveyEndDateTo = surveyEndDateTo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyEndDateTo"))

.queryParam("surveyEndDateTo",surveyEndDateTo)
;
restTemplate.put(builder.toUriString(),null);
}


}