package com.ec.survey.model;
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
@Entity
@Table(name = "RESULTFILTER")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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


public void setDefaultQuestions(Boolean defaultQuestions){
    this.defaultQuestions = defaultQuestions;
}


@Column(name = "RESFILTER_STITLE")
public String getSurveyStatus(){
    return surveyStatus;
}


public void setSurveyEndDateTo(Date surveyEndDateTo){
    this.surveyEndDateTo = surveyEndDateTo;
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


public void setSurveyEndDateFrom(Date surveyEndDateFrom){
    this.surveyEndDateFrom = surveyEndDateFrom;
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
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


public void setCompareToECFProfileUID(String compareToECFProfileUID){
    this.compareToECFProfileUID = compareToECFProfileUID;
}


public void setVisibleDiscussions(Set<String> visibleDiscussions){
    this.visibleDiscussions = visibleDiscussions;
}


public String toAscOrDesc(){
    switch(this) {
        case NAME_ASC:
            return "ASC";
        case NAME_DESC:
            return "DESC";
        case SCORE_ASC:
            return "ASC";
        case SCORE_DESC:
            return "DESC";
        case DATE_ASC:
            return "ASC";
        case DATE_DESC:
            return "DESC";
        case REPLIES_ASC:
            return "ASC";
        case REPLIES_DESC:
            return "DESC";
        case CREATED_ASC:
            return "ASC";
        case CREATED_DESC:
            return "DESC";
        case ECFSCORE_ASC:
            return "ASC";
        case ECFSCORE_DESC:
            return "DESC";
        case ECFGAP_ASC:
            return "ASC";
        case ECFGAP_DESC:
            return "DESC";
        default:
            return "";
    }
}


@Column(name = "RESFILTER_UPDATEFROM")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getUpdatedFrom(){
    return updatedFrom;
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
}


public void setLanguages(Set<String> languages){
    this.languages = languages;
}


public void setId(Integer id){
    this.id = id;
}


public void setUser(String user){
    this.user = user;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Map<String,String> getFilterValues(){
    return filterValues;
}


@Transient
public boolean visibleSection(int sectionId,Survey survey){
    boolean correctSection = false;
    int sectionLevel = 0;
    Map<Integer, Element> elementsById = survey.getElementsById();
    for (Element element : survey.getElements()) {
        if (element.getId().equals(sectionId)) {
            correctSection = true;
            sectionLevel = ((Section) element).getLevel();
        } else if (correctSection) {
            if (element instanceof Section) {
                if (((Section) element).getLevel() <= sectionLevel) {
                    return false;
                }
            } else {
                if (visibleQuestions.contains(element.getId().toString())) {
                    Element question = elementsById.get(element.getId());
                    if (question instanceof ChoiceQuestion || question instanceof Matrix || question instanceof RatingQuestion) {
                        return true;
                    } else if (question instanceof GalleryQuestion) {
                        GalleryQuestion g = (GalleryQuestion) question;
                        if (g.getSelection()) {
                            return true;
                        }
                    } else if (question instanceof NumberQuestion) {
                        NumberQuestion n = (NumberQuestion) question;
                        if (n.showStatisticsForNumberQuestion()) {
                            return true;
                        }
                    } else if (question instanceof Question) {
                        Question q = (Question) question;
                        if (q.isDelphiElement() && q.getDelphiChartType() != DelphiChartType.None) {
                            return true;
                        }
                    }
                }
            }
        }
    }
    return false;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getExportedExplanations(){
    return exportedExplanations;
}


public void setExportedExplanations(Set<String> exportedExplanations){
    this.exportedExplanations = exportedExplanations;
}


@Column(name = "RESFILTER_SENDTO")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getSurveyEndDateTo(){
    return surveyEndDateTo;
}


public void setExportedDiscussions(Set<String> exportedDiscussions){
    this.exportedDiscussions = exportedDiscussions;
}


@Column(name = "RESFILTER_REALUPD")
public Boolean getOnlyReallyUpdated(){
    return onlyReallyUpdated;
}


@Transient
public boolean visible(String questionId){
    return visibleQuestions.contains(questionId);
}


@Column(name = "RESFILTER_DATEFROM")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getGeneratedFrom(){
    return generatedFrom;
}


public void setCaseId(String caseId){
    this.caseId = caseId;
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
}


@Transient
public boolean explanationExported(String questionId){
    return exportedExplanations.contains(questionId);
}


public void setExportedQuestions(Set<String> exportedQuestions){
    this.exportedQuestions = exportedQuestions;
}


@Transient
public void addExportedQuestion(String question){
    if (!this.exportedQuestions.contains(question)) {
        this.exportedQuestions.add(question);
    }
}


@Transient
public boolean discussionVisible(String questionId){
    return visibleDiscussions.contains(questionId);
}


public String getDraftId(){
    return draftId;
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
}


public void setSurveyTitle(String surveyTitle){
    this.surveyTitle = surveyTitle;
}


@Transient
public boolean contains(String questionId,String questionUid,String value,String paUid){
    String combined = questionId + "|" + questionUid;
    return (filterValues.containsKey(combined) && (filterValues.get(combined).contains(value + "|") || filterValues.get(combined).contains(paUid)));
}


@Transient
public boolean discussionExported(String questionId){
    return exportedDiscussions.contains(questionId);
}


public void setSurveyPublishedResults(String surveyPublishedResults){
    this.surveyPublishedResults = surveyPublishedResults;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getVisibleExplanations(){
    return visibleExplanations;
}


public void clearSelectedQuestions(){
    visibleQuestions.clear();
    exportedQuestions.clear();
    visibleExplanations.clear();
    exportedExplanations.clear();
    visibleDiscussions.clear();
    exportedDiscussions.clear();
}


public void setGeneratedTo(Date generatedTo){
    this.generatedTo = generatedTo;
}


public void setSurveyId(int surveyId){
    this.surveyId = surveyId;
}


public void setInvitation(String invitation){
    this.invitation = invitation;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


@ElementCollection
@Cascade(value = { CascadeType.ALL })
public Set<String> getExportedQuestions(){
    return exportedQuestions;
}


public void setOnlyReallyUpdated(Boolean onlyReallyUpdated){
    this.onlyReallyUpdated = onlyReallyUpdated;
}


public void setAnsweredECFProfileUID(String ecfProfileUid){
    this.answeredECFProfileUID = ecfProfileUid;
}


public void setUpdatedTo(Date updatedTo){
    this.updatedTo = updatedTo;
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
}


@Column(name = "RESFILTER_USER")
public String getUser(){
    return user;
}


public void setSurveyStatus(String surveyStatus){
    this.surveyStatus = surveyStatus;
}


@Id
@Column(name = "RESFILTER_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setNoTestAnswers(Boolean noTestAnswers){
    this.noTestAnswers = noTestAnswers;
}


public void setFilterValues(Map<String,String> filterValues){
    this.filterValues = filterValues;
}


public void setGeneratedFrom(Date generatedFrom){
    this.generatedFrom = generatedFrom;
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


@Transient
public boolean containsQuestion(String questionId,String questionUid){
    String combined = questionId + "|" + questionUid;
    return (filterValues.containsKey(combined));
}


@Transient
public boolean containsLanguage(String code){
    if (languages != null) {
        for (String c : languages) {
            if (c.equalsIgnoreCase(code))
                return true;
        }
    }
    return false;
}


public ResultFilterSortKey toResultFilterSortKey(){
    switch(this) {
        case NAME_ASC:
            return ResultFilterSortKey.NAME;
        case NAME_DESC:
            return ResultFilterSortKey.NAME;
        case SCORE_ASC:
            return ResultFilterSortKey.SCORE;
        case SCORE_DESC:
            return ResultFilterSortKey.SCORE;
        case DATE_ASC:
            return ResultFilterSortKey.DATE;
        case DATE_DESC:
            return ResultFilterSortKey.DATE;
        case REPLIES_ASC:
            return ResultFilterSortKey.REPLIES;
        case REPLIES_DESC:
            return ResultFilterSortKey.REPLIES;
        case CREATED_ASC:
            return ResultFilterSortKey.CREATED;
        case CREATED_DESC:
            return ResultFilterSortKey.CREATED;
        case ECFSCORE_ASC:
            return ResultFilterSortKey.ECFSCORE;
        case ECFSCORE_DESC:
            return ResultFilterSortKey.ECFSCORE;
        case ECFGAP_ASC:
            return ResultFilterSortKey.ECFGAP;
        case ECFGAP_DESC:
            return ResultFilterSortKey.ECFGAP;
        default:
            return ResultFilterSortKey.UNKNOWN;
    }
}


public ResultFilter copy(){
    ResultFilter copy = new ResultFilter();
    merge(copy);
    return copy;
}


public String value(){
    return this.value;
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


@Transient
public boolean exported(String questionId){
    // Fallback for old filter that have no exported questions
    if (exportedQuestions == null || exportedQuestions.isEmpty())
        return visible(questionId);
    return exportedQuestions.contains(questionId);
}


public void sortAndAppendSetIdsToStringBuilder(Set<String> set,StringBuilder builder){
    if (set != null && !set.isEmpty()) {
        final SortedSet<String> sortedSet = new TreeSet<>(set);
        for (String id : sortedSet) {
            builder.append(id);
        }
    }
}


public String getSurveyTitle(){
    return surveyTitle;
}


public void setVisibleQuestions(Set<String> visibleQuestions){
    this.visibleQuestions = visibleQuestions;
}


public void setSurveyShortname(String surveyShortname){
    this.surveyShortname = surveyShortname;
}


@Column(name = "RESFILTER_SENDFROM")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getSurveyEndDateFrom(){
    return surveyEndDateFrom;
}


public void setDraftId(String draftId){
    this.draftId = draftId;
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
}


public ResultFilterSortKey parse(String value){
    if (value.equalsIgnoreCase(NAME.value())) {
        return NAME;
    }
    if (value.equalsIgnoreCase(SCORE.value())) {
        return SCORE;
    }
    if (value.equalsIgnoreCase(DATE.value())) {
        return DATE;
    }
    if (value.equalsIgnoreCase(REPLIES.value())) {
        return REPLIES;
    }
    if (value.equalsIgnoreCase(CREATED.value())) {
        return CREATED;
    }
    if (value.equalsIgnoreCase(ECFSCORE.value())) {
        return ECFSCORE;
    }
    if (value.equalsIgnoreCase(ECFGAP.value())) {
        return ECFGAP;
    }
    return UNKNOWN;
}


public void setVisibleExplanations(Set<String> visibleExplanations){
    this.visibleExplanations = visibleExplanations;
}


public void setStatus(String status){
    this.status = status;
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


public void setCreatedOrUpdated(Boolean createdOrUpdated){
    this.createdOrUpdated = createdOrUpdated;
}


@Transient
public boolean explanationVisible(String questionId){
    return visibleExplanations.contains(questionId);
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


public void setUpdatedFrom(Date updatedFrom){
    this.updatedFrom = updatedFrom;
}


@Column(name = "RESFILTER_OWNER")
public Integer getUserId(){
    return userId;
}


}