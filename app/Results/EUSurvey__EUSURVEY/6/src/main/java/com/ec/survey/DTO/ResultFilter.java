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


public void setSurveyId(int surveyId){
    this.surveyId = surveyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyId"))

.queryParam("surveyId",surveyId)
;
restTemplate.put(builder.toUriString(),null);
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


public void setGeneratedFrom(Date generatedFrom){
    this.generatedFrom = generatedFrom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGeneratedFrom"))

.queryParam("generatedFrom",generatedFrom)
;
restTemplate.put(builder.toUriString(),null);
}


}