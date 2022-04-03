package com.ec.survey.model.survey;
 import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.ec.survey.model.ECFProfile;
import com.ec.survey.model.Language;
import com.ec.survey.model.Publication;
import com.ec.survey.model.Skin;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.service.SurveyService;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "SURVEYS", indexes = { @Index(name = "SH_IDX", columnList = "SURVEYNAME"), @Index(name = "DRA_IDX", columnList = "ISDRAFT") })
@Cacheable("com.ec.survey.model.survey.Survey")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Survey {

 private  long serialVersionUID;

 public  String TITLE;

 public  String ESCAPEPAGE;

 public  String ESCAPELINK;

 public  String CONFIRMATIONPAGE;

 public  String CONFIRMATIONLINK;

 public  String INTRODUCTION;

 public  String LOGOTEXT;

 public  String IPMINTRODUCTION;

 public  String HELP;

 public  String QUIZWELCOMEMESSAGE;

 public  String QUIZRESULTSMESSAGE;

 public  String CONFIRMATIONTEXT;

 public  String ESCAPETEXT;

 public  String RESULTSMESSAGETEXT;

 public  String MAXNUMBEROFRESULTSTEXT;

 private  Integer id;

 private  String uniqueId;

 private  int version;

 private  int DBVersion;

 private  User owner;

 private  String password;

 private  String title;

 private  String titleSort;

 private  String shortname;

 private  String introduction;

 private  Language language;

 private  String notificationValue;

 private  String notificationUnit;

 private  boolean notifyAll;

 private  boolean notified;

 private  Boolean confirmationPageLink;

 private  Boolean escapePageLink;

 private  List<Element> elements;

 private  List<Element> missingElements;

 private  Map<String,String> usefulLinks;

 private  Map<String,String> backgroundDocuments;

 private  Map<String,String> fileNamesForBackgroundDocuments;

 private  boolean listForm;

 private  boolean listFormValidated;

 private  Date publicationRequestedDate;

 private  String contact;

 private  String contactLabel;

 private  String security;

 private  Date created;

 private  Date published;

 private  Date firstPublished;

 private  String createdString;

 private  String startString;

 private  String endString;

 private  String deletedString;

 private  Date updated;

 private  Date start;

 private  Date end;

 private  Date deleted;

 private  int numberOfAnswerSets;

 private  int numberOfDrafts;

 private  int numberOfInvitations;

 private  int numberOfAnswerSetsPublished;

 private  int numberOfReports;

 private  int compulsoryStyle;

 private  boolean isActive;

 private  boolean isDraft;

 private  boolean isPublished;

 private  boolean hasPendingChanges;

 private  boolean validatedPerPage;

 private  Boolean saveAsDraft;

 private  boolean automaticPublishing;

 private  boolean changeContribution;

 private  boolean downloadContribution;

 private  boolean registrationForm;

 private  boolean multiPaging;

 private  boolean captcha;

 private  boolean missingElementsChecked;

 private  int sectionNumbering;

 private  int questionNumbering;

 private  Skin skin;

 private  File logo;

 private  String confirmationPage;

 private  String escapePage;

 private  String confirmationLink;

 private  String escapeLink;

 private  List<String> translations;

 private  List<String> completeTranslations;

 private  Publication publication;

 private  Map<Integer,String[]> activitiesToLog;

 private  String audience;

 private  boolean wcagCompliance;

 private  boolean isArchived;

 private  Boolean isDeleted;

 private  Boolean isFrozen;

 private  boolean ecasSecurity;

 private  String ecasMode;

 private  Boolean logoInInfo;

 private  Boolean isQuiz;

 private  Boolean isDelphi;

 private  Boolean isOPC;

 private  Boolean isECF;

 private  Boolean showQuizIcons;

 private  Boolean showTotalScore;

 private  Boolean scoresByQuestion;

 private  String quizWelcomeMessage;

 private  String quizResultsMessage;

 private  Boolean showPDFOnUnavailabilityPage;

 private  Boolean showDocsOnUnavailabilityPage;

 private  boolean fullFormManagementRights;

 private  boolean formManagementRights;

 private  boolean accessResultsRights;

 private  Integer allowedContributionsPerUser;

 private  boolean canCreateSurveys;

 private  Integer trustScore;

 private  Boolean isUseMaxNumberContribution;

 private  String maxNumberContributionText;

 private  Long maxNumberContribution;

 private  Boolean isUseMaxNumberContributionLink;

 private  String maxNumberContributionLink;

 private  Boolean sendConfirmationEmail;

 private  Boolean isDelphiShowAnswersAndStatisticsInstantly;

 private  Boolean isDelphiShowAnswers;

 private  Integer minNumberDelphiStatistics;

 private  String logoText;

 private  Boolean isShowCountdown;

 private  String timeLimit;

 private  Map<Integer,Element> missingElementsById;

 private  Map<String,Element> missingElementsByUniqueId;

 private  List<Element> elementsRecursive;

 private  List<Element> elementsRecursiveWithAnswers;

 private  Map<Integer,Element> _elementsById;

 private  Boolean hasUploadElement;


public void setPassword(String password){
    this.password = password;
}


@Column(name = "PDFUNAVAIL")
public Boolean getShowPDFOnUnavailabilityPage(){
    return showPDFOnUnavailabilityPage != null && showPDFOnUnavailabilityPage;
}


public void setIsDraft(boolean draft){
    this.isDraft = draft;
}


@Column(name = "ESCURL")
public String getEscapeLink(){
    if (escapeLink != null && escapeLink.length() > 0 && !escapeLink.toLowerCase().startsWith("http")) {
        escapeLink = "http://" + escapeLink;
    }
    return escapeLink;
}


public void reorderElementsByPosition(){
    elements.sort(Comparator.comparing(o -> (o.getPosition())));
}


@Column(name = "LISTFORMREQUESTEDDATE")
@DateTimeFormat(pattern = "dd/MM/yyyy")
@Temporal(TemporalType.TIMESTAMP)
public Date getPublicationRequestedDate(){
    return publicationRequestedDate;
}


@ManyToOne
@JoinColumn(name = "OWNER", nullable = false)
public User getOwner(){
    return owner;
}


@Column(name = "ISDRAFT", nullable = false)
public boolean getIsDraft(){
    return isDraft;
}


public void setOwner(User owner){
    this.owner = owner;
}


@Column(name = "SURVEY_UID", nullable = false)
public String getUniqueId(){
    return uniqueId;
}


@Transient
public List<Element> getElementsRecursive(boolean answers){
    if (answers) {
        if (elementsRecursiveWithAnswers != null) {
            return elementsRecursiveWithAnswers;
        }
    } else {
        if (elementsRecursive != null) {
            return elementsRecursive;
        }
    }
    elementsRecursive = new ArrayList<>();
    elementsRecursiveWithAnswers = new ArrayList<>();
    for (Element element : elements) {
        elementsRecursive.add(element);
        elementsRecursiveWithAnswers.add(element);
        if (element instanceof MatrixOrTable) {
            elementsRecursive.addAll(((MatrixOrTable) element).getChildElements());
            elementsRecursiveWithAnswers.addAll(((MatrixOrTable) element).getChildElements());
        }
        if (element instanceof ChoiceQuestion) {
            elementsRecursiveWithAnswers.addAll(((ChoiceQuestion) element).getPossibleAnswers());
        }
        if (element instanceof RatingQuestion) {
            elementsRecursive.addAll(((RatingQuestion) element).getChildElements());
            elementsRecursiveWithAnswers.addAll(((RatingQuestion) element).getChildElements());
        }
        if (element instanceof RankingQuestion) {
            elementsRecursive.addAll(((RankingQuestion) element).getChildElements());
            elementsRecursiveWithAnswers.addAll(((RankingQuestion) element).getChildElements());
        }
    }
    if (answers) {
        return elementsRecursiveWithAnswers;
    } else {
        return elementsRecursive;
    }
}


@Column(name = "MAXNUMBERCONTRIBUTION")
public Long getMaxNumberContribution(){
    return this.maxNumberContribution != null ? this.maxNumberContribution : 0L;
}


@Transient
public Map<Integer,Element> getElementsById(){
    if (_elementsById == null) {
        _elementsById = new HashMap<>();
        for (Element element : getElementsRecursive()) {
            _elementsById.put(element.getId(), element);
        }
    }
    return _elementsById;
}


@Transient
public Map<String,String> getBackgroundDocumentsAlphabetical(){
    return new TreeMap<>(backgroundDocuments);
}


public void setMaxNumberContributionText(String maxNumberContributionText){
    this.maxNumberContributionText = maxNumberContributionText != null ? Tools.filterHTML(maxNumberContributionText) : MAXNUMBEROFRESULTSTEXT;
}


public void setArchived(Boolean isArchived){
    this.isArchived = isArchived != null && isArchived;
}


@Transient
public boolean isMissingElementsChecked(){
    return missingElementsChecked;
}


public void setEscapeLink(String escapeLink){
    this.escapeLink = escapeLink;
}


public void setVersion(Integer version){
    this.version = version != null ? version : 0;
}


@Column(name = "MAXNUMBERCONTRIBUTIONLINK", length = 255)
public String getMaxNumberContributionLink(){
    return this.maxNumberContributionLink != null && this.maxNumberContributionLink.length() > 0 ? this.maxNumberContributionLink : "";
}


@Lob
// , columnDefinition = "TEXT")
@Column(name = "INTRODUCTION", length = 40000)
public String getIntroduction(){
    return introduction;
}


@Column(name = "ISUSEMAXNUMBERCONTRIBUTIONLINK")
public Boolean getIsUseMaxNumberContributionLink(){
    return this.isUseMaxNumberContributionLink != null ? this.isUseMaxNumberContributionLink : false;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateTimeFormatSmall)
@Column(name = "SURVEY_END_DATE")
public Date getEnd(){
    return end;
}


@Column(name = "CONFLINK")
public Boolean getConfirmationPageLink(){
    return confirmationPageLink;
}


public void setShortname(String shortname){
    this.shortname = shortname;
}


@Column(name = "NOTIFYALL")
public boolean getNotifyAll(){
    return notifyAll;
}


@Column(name = "ECASMODE")
public String getEcasMode(){
    return ecasMode;
}


public void setCompleteTranslations(List<String> translations){
    this.completeTranslations = translations;
}


@Column(name = "QUESTIONNUMBERING")
public int getQuestionNumbering(){
    return questionNumbering;
}


public void setNumberOfReports(int numberOfReports){
    this.numberOfReports = numberOfReports;
}


@Transient
public String getNiceFirstPublished(){
    return firstPublished != null ? Tools.formatDate(firstPublished, ConversionTools.DateFormat) : "";
}


@Transient
public boolean hasNoQuestionsForStatistics(){
    for (Element question : elements) {
        if (question instanceof Matrix || question instanceof ChoiceQuestion || (question instanceof GalleryQuestion && ((GalleryQuestion) question).getSelection()) || question instanceof RatingQuestion) {
            return false;
        }
        if (question instanceof NumberQuestion) {
            NumberQuestion number = (NumberQuestion) question;
            if (number.showStatisticsForNumberQuestion()) {
                return false;
            }
        }
    }
    return true;
}


@Lob
@Column(name = "CONFIRMATION", nullable = false, length = 40000)
public String getConfirmationPage(){
    return confirmationPage;
}


public void setLogoInInfo(Boolean logoInInfo){
    this.logoInInfo = logoInInfo;
}


public void setMissingElements(List<Element> missingElements){
    this.missingElements = missingElements;
    missingElementsById = null;
}


public void setAudience(String audience){
    this.audience = audience;
}


@Column(name = "SHOWCOUNTDOWN")
public Boolean getShowCountdown(){
    return isShowCountdown != null ? isShowCountdown : false;
}


public void setSaveAsDraft(Boolean saveAsDraft){
    this.saveAsDraft = saveAsDraft == null || saveAsDraft;
}


@Transient
public Map<String,Element> getQuestionMapByUniqueId(){
    Map<String, Element> result = new HashMap<>();
    for (Element element : elements) {
        if (element instanceof Question) {
            result.put(element.getUniqueId(), element);
        }
        if (element instanceof MatrixOrTable) {
            MatrixOrTable matrix = (MatrixOrTable) element;
            for (Element child : matrix.getChildElements()) {
                if (!(child instanceof EmptyElement))
                    result.put(child.getUniqueId(), child);
            }
        }
        if (element instanceof RatingQuestion) {
            RatingQuestion rating = (RatingQuestion) element;
            for (Element child : rating.getChildElements()) {
                result.put(child.getUniqueId(), child);
            }
        }
    }
    return result;
}


@Transient
public int getNumberOfInvitations(){
    return numberOfInvitations;
}


@Transient
public int getTimeLimitInSeconds(){
    if (timeLimit == null || timeLimit.length() == 0)
        return -1;
    String[] arr = timeLimit.split(":");
    return Integer.parseInt(arr[0]) * 3600 + Integer.parseInt(arr[1]) * 60 + Integer.parseInt(arr[2]);
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
}


public void setMinNumberDelphiStatistics(Integer minNumberDelphiStatistics){
    this.minNumberDelphiStatistics = minNumberDelphiStatistics != null ? Math.max(minNumberDelphiStatistics, 1) : 5;
}


public void setIsUseMaxNumberContributionLink(Boolean useMaxNumberContributionLink){
    this.isUseMaxNumberContributionLink = useMaxNumberContributionLink != null ? useMaxNumberContributionLink : false;
}


public void setStartString(String startString){
    this.startString = startString;
}


public void setIsQuiz(Boolean isQuiz){
    this.isQuiz = isQuiz != null && isQuiz;
}


@Transient
public String getFixedContact(){
    if (contact != null && contact.length() > 0 && !contact.contains("@") && !contact.toLowerCase().startsWith("http")) {
        return "http://" + contact;
    }
    return contact;
}


@Column(name = "LOGOTEXT")
public String getLogoText(){
    return logoText;
}


@Column(name = "ECASSEC")
public Boolean getEcasSecurity(){
    return ecasSecurity;
}


@Transient
public String getFinalConfirmationLink(String language){
    return getFinalConfirmationLink(getConfirmationLink(), language);
}


@Column(name = "SURVEY_VERSION")
public Integer getVersion(){
    return version;
}


public void setContactLabel(String contactLabel){
    this.contactLabel = contactLabel;
}


public void setValidatedPerPage(boolean validatedPerPage){
    this.validatedPerPage = validatedPerPage;
}


@Column(name = "NOTIFICATIONVALUE")
public String getNotificationValue(){
    if (!automaticPublishing)
        return null;
    return notificationValue;
}


@Transient
public boolean containsCompleteTranslations(String code){
    return (completeTranslations != null && completeTranslations.contains(code));
}


@Transient
public Map<String,Element> getMatrixMapByAlias(){
    Map<String, Element> result = new HashMap<>();
    for (Element element : elements) {
        if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element child : matrix.getAllChildElements()) {
                if (!(child instanceof EmptyElement) && !result.containsKey(child.getShortname()))
                    result.put(child.getShortname(), child);
            }
        }
    }
    for (Element element : missingElements) {
        if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element child : matrix.getAllChildElements()) {
                if (!(child instanceof EmptyElement) && !result.containsKey(child.getShortname()))
                    result.put(child.getShortname(), child);
            }
        }
    }
    return result;
}


@Column(name = "PASSWORD")
public String getPassword(){
    return password;
}


public void setNotificationUnit(String notificationUnit){
    this.notificationUnit = notificationUnit;
}


public void setTimeLimit(String timeLimit){
    this.timeLimit = timeLimit != null ? timeLimit : "";
}


@ManyToOne
@JoinColumn(name = "SURVEYSKIN")
public Skin getSkin(){
    return skin;
}


public void setNotifyAll(boolean notifyAll){
    this.notifyAll = notifyAll;
}


@Column(name = "SECTIONNUMBERING")
public int getSectionNumbering(){
    return sectionNumbering;
}


@Column(name = "USEFULLINKS")
@ElementCollection()
public Map<String,String> getUsefulLinks(){
    return usefulLinks;
}


@Transient
public String getNiceNotificationUnit(){
    if (getNotificationUnit() == null)
        return "";
    if (getNotificationUnit().equalsIgnoreCase("0")) {
        return "hours";
    } else if (getNotificationUnit().equalsIgnoreCase("1")) {
        return "days";
    } else if (getNotificationUnit().equalsIgnoreCase("2")) {
        return "weeks";
    } else if (getNotificationUnit().equalsIgnoreCase("3")) {
        return "months";
    }
    return "unknow";
}


public void setDownloadContribution(Boolean downloadContribution){
    this.downloadContribution = downloadContribution != null ? downloadContribution : false;
}


public void setShowDocsOnUnavailabilityPage(Boolean showDocsOnUnavailabilityPage){
    this.showDocsOnUnavailabilityPage = showDocsOnUnavailabilityPage;
}


@Transient
public List<String> getCompleteTranslations(){
    return completeTranslations;
}


@Temporal(TemporalType.TIMESTAMP)
@Transient
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getPublished(){
    return published;
}


public void setNumberOfAnswerSetsPublished(int numberOfAnswerSetsPublished){
    this.numberOfAnswerSetsPublished = numberOfAnswerSetsPublished;
}


@Column(name = "ALLOWEDCONTRIBUTIONS")
public Integer getAllowedContributionsPerUser(){
    return allowedContributionsPerUser != null ? allowedContributionsPerUser : 1;
}


@Transient
public Map<Integer,Element> getElementsBySourceId(){
    Map<Integer, Element> result = new HashMap<>();
    for (Element element : getElementsRecursive(true)) {
        result.put(element.getSourceId(), element);
    }
    return result;
}


public void setStart(Date start){
    this.start = start;
    this.startString = ConversionTools.getFullStringSmall(start);
}


public void setHasPendingChanges(boolean hasPendingChanges){
    this.hasPendingChanges = hasPendingChanges;
}


public void setLogoText(String logoText){
    this.logoText = logoText;
}


@Transient
public Map<Integer,Element> getMatrixMap(){
    Map<Integer, Element> result = new HashMap<>();
    for (Element element : elements) {
        if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element child : matrix.getAllChildElements()) {
                if (!(child instanceof EmptyElement))
                    result.put(child.getId(), child);
            }
        }
    }
    for (Element element : missingElements) {
        if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element child : matrix.getAllChildElements()) {
                if (!(child instanceof EmptyElement))
                    result.put(child.getId(), child);
            }
        }
    }
    return result;
}


@Column(name = "CONFURL")
public String getConfirmationLink(){
    if (confirmationLink != null && confirmationLink.length() > 0 && !confirmationLink.toLowerCase().startsWith("http")) {
        confirmationLink = "http://" + confirmationLink;
    }
    return confirmationLink;
}


public void setIsPublished(boolean isPublished){
    this.isPublished = isPublished;
}


public void setMissingElementsChecked(boolean missingElementsChecked){
    this.missingElementsChecked = missingElementsChecked;
}


@Column(name = "DOCSUNAVAIL")
public Boolean getShowDocsOnUnavailabilityPage(){
    return showDocsOnUnavailabilityPage != null && showDocsOnUnavailabilityPage;
}


public void setQuizResultsMessage(String quizResultsMessage){
    this.quizResultsMessage = quizResultsMessage;
}


public void setTitle(String title){
    this.title = title;
    String t = ConversionTools.removeHTML(title);
    this.titleSort = t.length() > 100 ? t.substring(0, 100) : t;
}


@Transient
public String mediumCleanTitle(){
    String result = cleanTitle();
    if (result.length() > 115) {
        return result.substring(0, 115) + "<span class='titletooltip'>...</span>";
    }
    return result;
}


public void setCreated(Date created){
    this.created = created;
    this.createdString = Tools.formatDate(created, ConversionTools.DateFormat);
}


public void setContact(String contact){
    this.contact = contact;
}


@Column(name = "DELPHI")
public Boolean getIsDelphi(){
    return isDelphi;
}


public void setHasUploadElement(Boolean hasUploadElement){
    this.hasUploadElement = hasUploadElement;
}


@Column(name = "HASPENDINGCHANGES")
public boolean getHasPendingChanges(){
    return hasPendingChanges;
}


public void setSkin(Skin skin){
    this.skin = skin;
}


@Transient
public String getFixedContactLabel(){
    if (contactLabel != null && contactLabel.length() > 0 && contact != null && contact.length() > 0 && !contact.contains("@")) {
        return contactLabel;
    }
    return contact;
}


@Column(name = "SENDCONFIRMATION")
public Boolean getSendConfirmationEmail(){
    return sendConfirmationEmail != null ? sendConfirmationEmail : false;
}


public void setSendConfirmationEmail(Boolean sendConfirmationEmail){
    this.sendConfirmationEmail = sendConfirmationEmail != null ? sendConfirmationEmail : false;
}


@Transient
public Map<String,Integer> getReferencedFileUIDs(String contextpath){
    Map<String, Integer> referencedFiles = new HashMap<>();
    if (logo != null) {
        referencedFiles.put(logo.getUid(), logo.getId());
    }
    if (backgroundDocuments != null && backgroundDocuments.size() > 0) {
        for (String url : backgroundDocuments.values()) {
            String uid = url.replace(contextpath + "/files/", "");
            referencedFiles.put(uid, null);
        }
    }
    for (Element element : elements) {
        if (element instanceof Download) {
            Download download = (Download) element;
            for (File f : download.getFiles()) {
                referencedFiles.put(f.getUid(), f.getId());
            }
        } else if (element instanceof Confirmation) {
            Confirmation confirmation = (Confirmation) element;
            for (File f : confirmation.getFiles()) {
                referencedFiles.put(f.getUid(), f.getId());
            }
        } else if (element instanceof Image) {
            Image image = (Image) element;
            if (image.getUrl() != null) {
                String fileUID = image.getUrl().replace(contextpath + "/files/", "");
                if (fileUID.length() > 0) {
                    if (fileUID.contains(Constants.PATH_DELIMITER)) {
                        fileUID = fileUID.substring(fileUID.indexOf('/') + 1);
                    }
                    referencedFiles.put(fileUID, null);
                }
            }
        } else if (element instanceof GalleryQuestion) {
            GalleryQuestion gallery = (GalleryQuestion) element;
            for (File f : gallery.getFiles()) {
                referencedFiles.put(f.getUid(), f.getId());
            }
        }
    }
    return referencedFiles;
}


@Column(name = "TIMELIMIT")
public String getTimeLimit(){
    return timeLimit != null ? timeLimit : "";
}


@Transient
public Map<Integer,Element> getMissingElementsById(){
    if (missingElementsById != null)
        return missingElementsById;
    missingElementsById = new HashMap<>();
    for (Element newElement : missingElements) {
        if (newElement != null) {
            missingElementsById.put(newElement.getId(), newElement);
        }
        if (newElement instanceof MatrixOrTable) {
            for (Element child : ((MatrixOrTable) newElement).missingAnswers) {
                missingElementsById.put(child.getId(), newElement);
            }
        }
    }
    return missingElementsById;
}


@Lob
@Column(name = "ESCAPE", nullable = false, length = 40000)
public String getEscapePage(){
    return escapePage;
}


@Transient
public String getNiceContact(){
    String result = "";
    if (contact != null && contact.contains("@")) {
        result += "Email: " + contact;
    } else {
        result += "Webpage: ";
        if (contactLabel != null && contactLabel.length() > 0) {
            result += contactLabel + ": " + contact;
        } else {
            result += contact;
        }
    }
    return result;
}


public void setDeletedString(String deletedString){
    this.deletedString = deletedString;
}


public void setUpdated(Date updated){
    this.updated = updated;
}


public void setIsActive(Boolean isActive){
    this.isActive = isActive != null && isActive;
}


public void resetElementsRecursive(){
    elementsRecursive = null;
    elementsRecursiveWithAnswers = null;
}


@Lob
@Column(name = "QUIZWELCOME", length = 40000)
public String getQuizWelcomeMessage(){
    return quizWelcomeMessage;
}


public void setIsDelphiShowAnswers(Boolean isDelphiShowAnswers){
    this.isDelphiShowAnswers = isDelphiShowAnswers != null ? isDelphiShowAnswers : false;
}


@Column(name = "CHANGECONTRIBUTION")
public boolean getChangeContribution(){
    return changeContribution;
}


public void setMaxNumberContributionLink(String maxNumberContributionLink){
    this.maxNumberContributionLink = maxNumberContributionLink != null ? Tools.filterHTML(maxNumberContributionLink) : "";
}


@Column(name = "SURVEYNAME", nullable = false)
public String getShortname(){
    return shortname;
}


@Column(name = "MULTIPAGING")
public boolean getMultiPaging(){
    return multiPaging;
}


@Id
@Column(name = "SURVEY_ID", nullable = false)
@GeneratedValue
public Integer getId(){
    return id;
}


public void setWcagCompliance(Boolean wcagCompliance){
    this.wcagCompliance = wcagCompliance != null && wcagCompliance;
}


@Transient
public boolean isFormManagementRights(){
    return formManagementRights;
}


@Column(name = "WCAGCOMPLIANCE")
public Boolean getWcagCompliance(){
    return wcagCompliance;
}


public void setFormManagementRights(boolean formManagementRights){
    this.formManagementRights = formManagementRights;
}


@Transient
public boolean isCanCreateSurveys(){
    return canCreateSurveys;
}


@Column(name = "numberOfAnswerSets")
public int getNumberOfAnswerSets(){
    return numberOfAnswerSets;
}


public void setIsDeleted(Boolean isDeleted){
    this.isDeleted = isDeleted != null && isDeleted;
}


@Column(name = "ECF")
public Boolean getIsECF(){
    return isECF != null ? isECF : false;
}


@Column(name = "ARCHIVED")
public Boolean getArchived(){
    return isArchived;
}


public void setAccessResultsRights(boolean accessResultsRights){
    this.accessResultsRights = accessResultsRights;
}


@Transient
public boolean containsMandatoryQuestion(){
    for (Question question : getQuestions()) {
        if (!question.getOptional()) {
            return true;
        }
        if (question instanceof Matrix) {
            for (Element matrixquestion : ((Matrix) question).getQuestions()) {
                if (matrixquestion instanceof Question && !((Question) matrixquestion).getOptional()) {
                    return true;
                }
            }
        }
    }
    return false;
}


@Column(name = "numberOfAnswerSetsPublished")
public int getNumberOfAnswerSetsPublished(){
    return numberOfAnswerSetsPublished > 0 ? numberOfAnswerSetsPublished : 0;
}


public void setCreatedString(String createdString){
    this.createdString = createdString;
}


@Column(name = "ISUSEMAXNUMBERCONTRIBUTION")
public void setIsUseMaxNumberContribution(Boolean useMaxNumberContribution){
    this.isUseMaxNumberContribution = useMaxNumberContribution != null ? useMaxNumberContribution : false;
}


@Column(name = "SURVEYSECURITY")
public String getSecurity(){
    return security;
}


@Transient
public Map<String,String> getFileNamesForBackgroundDocuments(){
    return fileNamesForBackgroundDocuments;
}


public void setEcasMode(String ecasMode){
    this.ecasMode = ecasMode != null && ecasMode.length() > 0 ? ecasMode : "all";
}


public void setSecurity(String security){
    this.security = security;
}


@Column(name = "SCOREBYQUESTION")
public Boolean getScoresByQuestion(){
    return scoresByQuestion != null ? scoresByQuestion : true;
}


public void setFullFormManagementRights(boolean fullFormManagementRights){
    this.fullFormManagementRights = fullFormManagementRights;
}


public void setShowCountdown(Boolean isShowCountdown){
    this.isShowCountdown = isShowCountdown != null ? isShowCountdown : false;
}


@Transient
public List<Element> getMissingElements(){
    return missingElements;
}


public void setRegistrationForm(boolean registrationForm){
    this.registrationForm = registrationForm;
}


public Boolean getIsUseMaxNumberContribution(){
    return this.isUseMaxNumberContribution != null ? this.isUseMaxNumberContribution : false;
}


public void setShowQuizIcons(Boolean showQuizIcons){
    this.showQuizIcons = showQuizIcons;
}


public void setMultiPaging(boolean multiPaging){
    this.multiPaging = multiPaging;
}


public void setDBVersion(int DBVersion){
    this.DBVersion = DBVersion;
}


public void setListFormValidated(Boolean listFormValidated){
    this.listFormValidated = listFormValidated != null ? listFormValidated : false;
}


@Transient
public LinkedHashMap<String,String> getAdvancedUsefulLinks(){
    LinkedHashMap<String, String> result = new LinkedHashMap<>();
    SortedMap<Integer, String> sortedUsefullinks = new TreeMap<>();
    boolean skip = false;
    for (String key : usefulLinks.keySet()) {
        if (key.contains("#")) {
            try {
                int i = Integer.parseInt(key.substring(0, key.indexOf('#')));
                sortedUsefullinks.put(i, key);
            } catch (Exception e) {
                skip = true;
                break;
            }
        } else {
            skip = true;
            break;
        }
    }
    if (!skip) {
        for (String key : sortedUsefullinks.values()) {
            String value = usefulLinks.get(key);
            if (value != null && value.length() > 0 && !value.toLowerCase().startsWith("http")) {
                value = "http://" + value;
            }
            String label = key.substring(key.indexOf('#') + 1);
            if (label.trim().length() == 0 || org.apache.commons.lang3.StringUtils.isNumeric(label)) {
                label = value;
            }
            result.put(label, value);
        }
        return result;
    }
    for (Entry<String, String> entry : usefulLinks.entrySet()) {
        String value = entry.getValue();
        if (value != null && value.length() > 0 && !value.toLowerCase().startsWith("http")) {
            value = "http://" + value;
        }
        result.put(entry.getKey(), value);
    }
    return result;
}


@Transient
public Map<String,String> getUniqueIDsByID(){
    Map<String, String> result = new HashMap<>();
    for (Element element : getElementsRecursive(true)) {
        result.put(element.getId().toString(), element.getUniqueId());
    }
    return result;
}


public void setEscapePage(String escapePage){
    this.escapePage = escapePage;
}


public void setIsDelphi(Boolean isDelphi){
    this.isDelphi = isDelphi != null && isDelphi;
}


@Transient
public Map<Integer,String[]> getActivitiesToLog(){
    return this.activitiesToLog;
}


@Column(name = "ISPUBLISHED")
public boolean getIsPublished(){
    return isPublished;
}


@Column(name = "CAPTCHA")
public boolean getCaptcha(){
    // always activate captcha for open surveys that are public
    if (this.listForm && this.security.startsWith("open")) {
        return true;
    }
    return captcha;
}


public void setIsOPC(Boolean isOPC){
    this.isOPC = isOPC != null && isOPC;
}


public void setDeleted(Date deleted){
    this.deleted = deleted;
    this.deletedString = Tools.formatDate(deleted, ConversionTools.DateFormat);
}


public void setNumberOfDrafts(int numberOfDrafts){
    this.numberOfDrafts = numberOfDrafts;
}


@Transient
public List<Element> getQuestionsAndSections(){
    List<Element> result = new ArrayList<>();
    for (Element element : elements) {
        if (element instanceof Question || element instanceof Section) {
            result.add(element);
        }
    }
    for (Element element : missingElements) {
        if (element instanceof Question || element instanceof Section) {
            result.add((Question) element);
        }
    }
    // sort collection by position
    if (!missingElements.isEmpty()) {
        result.sort(newElementByPositionComparator());
    }
    return result;
}


public void setNotified(boolean notified){
    this.notified = notified;
}


public void setQuestionNumbering(int questionNumbering){
    this.questionNumbering = questionNumbering;
}


@Column(name = "DELPHIANSWERS")
public Boolean getIsDelphiShowAnswers(){
    return isDelphiShowAnswers != null ? isDelphiShowAnswers : false;
}


public void setAllowedContributionsPerUser(Integer allowedContributionsPerUser){
    this.allowedContributionsPerUser = allowedContributionsPerUser;
}


@Transient
public int getNumberOfDrafts(){
    return numberOfDrafts;
}


@Column(name = "TRUSTSCORE")
public Integer getTrustScore(){
    return trustScore;
}


public void setEnd(Date end){
    this.end = end;
    this.endString = ConversionTools.getFullStringSmall(end);
}


public void setEndString(String endString){
    this.endString = endString;
}


@Column(name = "CONTACTLABEL")
public String getContactLabel(){
    return contactLabel;
}


public void setBackgroundDocuments(Map<String,String> backgroundDocuments){
    this.backgroundDocuments = backgroundDocuments;
}


@Column(name = "QUIZ")
public Boolean getIsQuiz(){
    return isQuiz;
}


public void setConfirmationPageLink(Boolean confirmationPageLink){
    this.confirmationPageLink = confirmationPageLink;
}


@Transient
public Map<Integer,Question> getQuestionMap(){
    Map<Integer, Question> result = new HashMap<>();
    for (Element element : elements) {
        if (element instanceof Question) {
            result.put(element.getId(), (Question) element);
            if (element instanceof RatingQuestion) {
                for (Element child : ((RatingQuestion) element).getQuestions()) {
                    result.put(child.getId(), (Question) child);
                }
            }
        }
    }
    for (Element element : missingElements) {
        if (element instanceof Question) {
            result.put(element.getId(), (Question) element);
        }
    }
    return result;
}


@Transient
public String serialize(boolean elementOrderOnly){
    StringBuilder result = new StringBuilder();
    if (!elementOrderOnly) {
        result.append("id: ").append(id).append(";");
        result.append(" contact: ").append(contact).append(";");
        result.append(" automaticPublishing: ").append(automaticPublishing).append(";");
        result.append(" start: ").append(ConversionTools.getFullString(start)).append(";");
        result.append(" end: ").append(ConversionTools.getFullString(end)).append(";");
        result.append(" draft: ").append(isDraft).append(";");
        result.append(" language: ").append(language.getCode()).append(";");
        result.append(" security: ").append(security).append(";");
        result.append(" alias: ").append(shortname).append(";");
        result.append(" title: ").append(title).append(";");
        result.append(" registrationForm: ").append(registrationForm).append(";");
        result.append(" changeContribution: ").append(changeContribution).append(";");
        if (logo != null) {
            result.append(" logo: ").append(logo.getName()).append(";");
            result.append(" logoInInfo: ").append(logoInInfo).append(";");
            result.append(" logoText: ").append(logoText).append(";");
        }
        result.append(" confirmationPage: ").append(confirmationPage).append(";");
        result.append(" confirmationLink: ").append(this.getConfirmationLink()).append(";");
        result.append(" confirmationPageLink: ").append(confirmationPageLink).append(";");
        result.append(" escapePage: ").append(escapePage).append(";");
        result.append(" escapeLink: ").append(this.getEscapeLink()).append(";");
        result.append(" escapePageLink: ").append(escapePageLink).append(";");
        result.append(" password: ").append(password).append(";");
        result.append(" multiPaging: ").append(multiPaging).append(";");
        result.append(" validatedPerPage: ").append(validatedPerPage).append(";");
        result.append(" captcha: ").append(captcha).append(";");
        result.append(" questionNumbering: ").append(questionNumbering).append(";");
        result.append(" sectionNumbering: ").append(sectionNumbering).append(";");
        result.append(" notificationValue: ").append(notificationValue).append(";");
        result.append(" notificationUnit: ").append(notificationUnit).append(";");
        result.append(" notifyAll: ").append(notifyAll).append(";");
        result.append(" showPDFOnUnavailabilityPage: ").append(showPDFOnUnavailabilityPage).append(";");
        result.append(" showDocsOnUnavailabilityPage: ").append(showDocsOnUnavailabilityPage).append(";");
        try {
            if (backgroundDocuments != null)
                for (Entry<String, String> entry : backgroundDocuments.entrySet()) {
                    result.append(" BackgroundDocument: ").append(entry.getKey()).append(" - ").append(entry.getValue()).append(";");
                }
        } catch (Exception e) {
        // ignore
        }
        try {
            if (usefulLinks != null)
                for (Entry<String, String> entry : usefulLinks.entrySet()) {
                    result.append(" UsefulLink: ").append(entry.getKey()).append(" - ").append(entry.getValue()).append(";");
                }
        } catch (Exception e) {
        // ignore
        }
    }
    result.append(" Elements: ");
    for (Element element : getElementsRecursive()) {
        result.append(element.getUniqueId()).append(" ");
    }
    result.append(";");
    return result.toString();
}


public void setTrustScore(Integer trustScore){
    this.trustScore = trustScore;
}


@Transient
public boolean isAnonymous(){
    return security != null && security.endsWith("anonymous");
}


public void setActivitiesToLog(Map<Integer,String[]> activitiesToLog){
    this.activitiesToLog = activitiesToLog;
}


@OneToMany(targetEntity = Element.class, cascade = CascadeType.ALL, orphanRemoval = true)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Element> getElements(){
    return elements;
}


public void setConfirmationPage(String confirmationPage){
    this.confirmationPage = confirmationPage;
}


@Transient
public Map<Integer,Element> copyElements(Survey surveyCopy,SurveyService surveyService,boolean makeQuestionsLocked){
    for (Element element : elements) {
        Element copiedElement = element.copy(surveyService.getFileDir());
        copiedElement.setLocked(makeQuestionsLocked);
        surveyCopy.elements.add(copiedElement);
    }
    Map<Integer, Element> elementsBySourceId = new HashMap<>();
    for (Element newElement : surveyCopy.elements) {
        elementsBySourceId.put(newElement.getSourceId(), newElement);
        if (newElement instanceof ChoiceQuestion) {
            for (PossibleAnswer answer : ((ChoiceQuestion) newElement).getPossibleAnswers()) {
                elementsBySourceId.put(answer.getSourceId(), answer);
            }
        } else if (newElement instanceof MatrixOrTable) {
            MatrixOrTable t = (MatrixOrTable) newElement;
            for (Element child : t.getChildElements()) {
                elementsBySourceId.put(child.getSourceId(), child);
            }
        }
    }
    // recreate dependencies
    for (Element element : elements) {
        if (element instanceof ChoiceQuestion) {
            ChoiceQuestion question = (ChoiceQuestion) element;
            for (PossibleAnswer answer : question.getPossibleAnswers()) {
                if (answer.getDependentElements() != null) {
                    // find new possible answer
                    PossibleAnswer newPossibleAnswer = (PossibleAnswer) elementsBySourceId.get(answer.getId());
                    DependencyItem newDep = new DependencyItem();
                    newPossibleAnswer.setDependentElements(newDep);
                    for (Element dependent : answer.getDependentElements().getDependentElements()) {
                        // find new dependent element
                        Element newDependent = elementsBySourceId.get(dependent.getId());
                        newDep.getDependentElements().add(newDependent);
                    }
                }
            }
        } else if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            // find new matrix
            Matrix newMatrix = (Matrix) elementsBySourceId.get(matrix.getId());
            newMatrix.setDependentElements(new ArrayList<>());
            for (DependencyItem dep : matrix.getDependentElements()) {
                DependencyItem newDep = new DependencyItem();
                newDep.setPosition(dep.getPosition());
                for (Element elem : dep.getDependentElements()) {
                    // find new dependent element
                    Element newDependent = elementsBySourceId.get(elem.getId());
                    newDep.getDependentElements().add(newDependent);
                }
                newMatrix.getDependentElements().add(newDep);
            }
        }
    }
    return elementsBySourceId;
}


@Transient
public boolean hasSections(){
    for (Element element : elements) {
        if (element instanceof Section) {
            return true;
        }
    }
    return false;
}


public void setNumberOfInvitations(int numberOfInvitations){
    this.numberOfInvitations = numberOfInvitations;
}


@Column(name = "TITLESORT", nullable = false)
public String getTitleSort(){
    return titleSort;
}


@Column(name = "ESCLINK")
public Boolean getEscapePageLink(){
    return escapePageLink;
}


@Transient
public String getEndString(){
    return endString;
}


@Transient
public String getStartString(){
    return startString;
}


public void setPublicationRequestedDate(Date publicationRequestedDate){
    this.publicationRequestedDate = publicationRequestedDate;
}


@Column(name = "DOWNLOADCONTRIBUTION")
public Boolean getDownloadContribution(){
    return downloadContribution;
}


public void setTranslations(List<String> translations){
    this.translations = translations;
}


@Transient
public String cleanTitle(){
    return ConversionTools.removeHTML(title, true);
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "SURVEY_UPDATED", nullable = false)
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getUpdated(){
    return updated;
}


@Column(name = "VALIDATEDPERPAGE")
public boolean getValidatedPerPage(){
    return validatedPerPage && multiPaging;
}


@Transient
public String getCreatedString(){
    return createdString;
}


@Column(name = "NOTIFIED")
public boolean getNotified(){
    return notified;
}


public void setShowTotalScore(Boolean showTotalScore){
    this.showTotalScore = showTotalScore;
}


public void setUsefulLinks(Map<String,String> usefulLinks){
    this.usefulLinks = usefulLinks;
}


@Transient
public Survey copy(SurveyService surveyService,User powner,String fileDir,boolean copyNumberOfAnswerSets,int pnumberOfAnswerSets,int pnumberOfAnswerSetsPublished,boolean copyPublication,boolean resetSourceIds,boolean keepuid,String newshortname,String newuid){
    Survey copy = new Survey();
    copy.contact = contact;
    copy.contactLabel = contactLabel;
    copy.audience = audience;
    copy.automaticPublishing = automaticPublishing;
    copy.sendConfirmationEmail = sendConfirmationEmail;
    copy.setStart(start);
    copy.setEnd(end);
    copy.introduction = Tools.filterHTML(introduction);
    copy.isDraft = true;
    copy.language = language;
    copy.listForm = listForm;
    copy.setListFormValidated(listFormValidated);
    copy.publicationRequestedDate = publicationRequestedDate;
    copy.owner = powner;
    copy.security = security;
    copy.shortname = newshortname != null ? newshortname : Tools.escapeHTML(shortname);
    copy.uniqueId = newuid != null ? newuid : uniqueId;
    copy.setTitle(Tools.filterHTML(title));
    copy.registrationForm = registrationForm;
    copy.saveAsDraft = saveAsDraft;
    copy.changeContribution = changeContribution;
    copy.downloadContribution = downloadContribution;
    if (logo != null) {
        File copyLogo = logo.copy(fileDir);
        copy.logo = copyLogo;
    }
    copy.logoInInfo = logoInInfo;
    copy.logoText = logoText;
    copy.confirmationPage = Tools.filterHTML(confirmationPage);
    copy.confirmationLink = this.getConfirmationLink();
    copy.confirmationPageLink = confirmationPageLink;
    copy.escapePage = Tools.filterHTML(escapePage);
    copy.escapeLink = this.getEscapeLink();
    copy.escapePageLink = escapePageLink;
    copy.password = password;
    copy.setEcasSecurity(ecasSecurity);
    copy.setEcasMode(ecasMode);
    copy.ecasMode = ecasMode;
    copy.multiPaging = multiPaging;
    copy.validatedPerPage = validatedPerPage;
    copy.setWcagCompliance(wcagCompliance);
    copy.captcha = captcha;
    copy.setCompulsoryStyle(compulsoryStyle);
    copy.questionNumbering = questionNumbering;
    copy.sectionNumbering = sectionNumbering;
    copy.notificationValue = notificationValue;
    copy.notificationUnit = notificationUnit;
    copy.notifyAll = notifyAll;
    copy.notified = notified;
    copy.isQuiz = isQuiz;
    copy.quizWelcomeMessage = quizWelcomeMessage;
    copy.quizResultsMessage = quizResultsMessage;
    copy.showQuizIcons = showQuizIcons;
    copy.showTotalScore = showTotalScore;
    copy.scoresByQuestion = scoresByQuestion;
    copy.showPDFOnUnavailabilityPage = showPDFOnUnavailabilityPage;
    copy.showDocsOnUnavailabilityPage = showDocsOnUnavailabilityPage;
    copy.isOPC = isOPC;
    copy.isECF = isECF;
    copy.setAllowedContributionsPerUser(allowedContributionsPerUser);
    copy.setIsUseMaxNumberContribution(isUseMaxNumberContribution);
    copy.setIsUseMaxNumberContributionLink(isUseMaxNumberContributionLink);
    copy.setMaxNumberContribution(maxNumberContribution);
    copy.setMaxNumberContributionText(Tools.filterHTML(maxNumberContributionText));
    copy.setMaxNumberContributionLink(Tools.filterHTML(maxNumberContributionLink));
    copy.isDelphi = isDelphi;
    copy.isDelphiShowAnswersAndStatisticsInstantly = isDelphiShowAnswersAndStatisticsInstantly;
    copy.isDelphiShowAnswers = isDelphiShowAnswers;
    copy.minNumberDelphiStatistics = minNumberDelphiStatistics;
    copy.timeLimit = timeLimit;
    copy.isShowCountdown = isShowCountdown;
    if (copyNumberOfAnswerSets) {
        int numberOfAnswerSets1 = pnumberOfAnswerSets > -1 ? pnumberOfAnswerSets : numberOfAnswerSetsPublished;
        copy.numberOfAnswerSets = numberOfAnswerSets1;
        int numberOfAnswerSetsPublished1 = pnumberOfAnswerSetsPublished > -1 ? pnumberOfAnswerSetsPublished : numberOfAnswerSetsPublished;
        copy.numberOfAnswerSetsPublished = numberOfAnswerSetsPublished1;
    }
    try {
        if (backgroundDocuments != null)
            for (Entry<String, String> entry : backgroundDocuments.entrySet()) {
                copy.backgroundDocuments.put(entry.getKey(), entry.getValue());
            }
    } catch (Exception e) {
    // ignore
    }
    try {
        if (usefulLinks != null)
            for (Entry<String, String> entry : usefulLinks.entrySet()) {
                copy.usefulLinks.put(entry.getKey(), entry.getValue());
            }
    } catch (Exception e) {
    // ignore
    }
    Map<Integer, Element> elementsBySourceId = copyElements(copy, surveyService, false);
    // this is necessary to generate the ids of the newly created elements
    surveyService.add(copy, false, owner.getId());
    if (copyPublication) {
        copy.publication.setShowContent(publication.isShowContent());
        copy.publication.setShowStatistics(publication.isShowStatistics());
        copy.publication.setShowCharts(publication.isShowCharts());
        copy.publication.setShowSearch(publication.isShowSearch());
        copy.publication.setAllQuestions(publication.isAllQuestions());
        copy.publication.setAllContributions(publication.isAllContributions());
        copy.publication.setPassword(publication.getPassword());
        for (String questionId : publication.getFilter().getVisibleQuestions()) {
            if (elementsBySourceId.containsKey(Integer.parseInt(questionId))) {
                copy.publication.getFilter().getVisibleQuestions().add(elementsBySourceId.get(Integer.parseInt(questionId)).getId().toString());
            }
        }
        for (String questionId : publication.getFilter().getExportedQuestions()) {
            if (elementsBySourceId.containsKey(Integer.parseInt(questionId))) {
                copy.publication.getFilter().getExportedQuestions().add(elementsBySourceId.get(Integer.parseInt(questionId)).getId().toString());
            }
        }
        for (String questionId : publication.getFilter().getFilterValues().keySet()) {
            copy.publication.getFilter().getFilterValues().put(questionId, publication.getFilter().getFilterValues().get(questionId));
        }
    }
    try {
        if (skin != null) {
            copy.skin = skin;
        }
    } catch (Exception e) {
    // ignore
    }
    if (resetSourceIds) {
        // this means we are in a clearChanges call
        // we need to set the source id of the elements in the original to that of the
        // copy (the new draft)
        for (Element element : getElementsRecursive(true)) {
            if (elementsBySourceId.containsKey(element.getId())) {
                element.setSourceId(elementsBySourceId.get(element.getId()).getId());
            }
        }
    }
    return copy;
}


public void clearMissingData(){
    missingElements.clear();
    for (Element element : elements) {
        if (element instanceof MatrixOrTable) {
            ((MatrixOrTable) element).missingAnswers.clear();
            ((MatrixOrTable) element).missingQuestions.clear();
        } else if (element instanceof ChoiceQuestion) {
            ((ChoiceQuestion) element).getMissingPossibleAnswers().clear();
        } else if (element instanceof RatingQuestion) {
            ((RatingQuestion) element).getMissingQuestions().clear();
        }
    }
    missingElementsChecked = false;
}


@Column(name = "SHOWSCORE")
public Boolean getShowTotalScore(){
    return showTotalScore == null || showTotalScore;
}


public void setLogo(File logo){
    this.logo = logo;
}


@ManyToOne
@JoinColumn(name = "LANGUAGE", nullable = false)
public Language getLanguage(){
    return language;
}


public void setIntroduction(String introduction){
    this.introduction = introduction;
}


@Column(name = "NOTIFICATIONUNIT")
public String getNotificationUnit(){
    if (!automaticPublishing)
        return null;
    return notificationUnit;
}


@Transient
public int getNumberOfReports(){
    return numberOfReports;
}


@Transient
public boolean isFullFormManagementRights(){
    return fullFormManagementRights;
}


@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
public Publication getPublication(){
    return publication;
}


public void setShowPDFOnUnavailabilityPage(Boolean showPDFOnUnavailabilityPage){
    this.showPDFOnUnavailabilityPage = showPDFOnUnavailabilityPage;
}


public void setElements(List<Element> elements){
    this.elements = elements;
}


@Column(name = "DELPHIANSWERSANDSTATISTICSINSTANTLY")
public Boolean getIsDelphiShowAnswersAndStatisticsInstantly(){
    return isDelphiShowAnswersAndStatisticsInstantly != null ? isDelphiShowAnswersAndStatisticsInstantly : false;
}


@Transient
public Map<String,Element> getElementsByAlias(){
    Map<String, Element> result = new HashMap<>();
    for (Element element : getElementsRecursive(true)) {
        if (element.getShortname() != null && !result.containsKey(element.getShortname())) {
            result.put(element.getShortname(), element);
        }
    }
    return result;
}


@Column(name = "CONTACT")
public String getContact(){
    return contact;
}


@Column(name = "ACTIVE")
public Boolean getIsActive(){
    return isActive;
}


@Column(name = "FROZEN")
public Boolean getIsFrozen(){
    return isFrozen != null && isFrozen;
}


@Column(name = "SAVEASDRAFT")
public Boolean getSaveAsDraft(){
    return saveAsDraft;
}


@Transient
public Map<String,Element> getMatrixMapByUid(){
    Map<String, Element> result = new HashMap<>();
    for (Element element : elements) {
        if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element child : matrix.getAllChildElements()) {
                if (!(child instanceof EmptyElement))
                    result.put(child.getUniqueId(), child);
            }
        }
    }
    for (Element element : missingElements) {
        if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element child : matrix.getAllChildElements()) {
                if (!(child instanceof EmptyElement))
                    result.put(child.getUniqueId(), child);
            }
        }
    }
    return result;
}


public void setAutomaticPublishing(boolean automaticPublishing){
    this.automaticPublishing = automaticPublishing;
}


@Transient
public Map<Element,List<Element>> getTriggersByDependantElement(){
    HashMap<Element, List<Element>> result = new HashMap<>();
    for (Element element : elements) {
        if (element instanceof ChoiceQuestion) {
            for (PossibleAnswer p : ((ChoiceQuestion) element).getPossibleAnswers()) {
                if (!p.getDependentElements().getDependentElements().isEmpty()) {
                    List<Element> list;
                    for (Element t : p.getDependentElements().getDependentElements()) {
                        if (result.containsKey(t)) {
                            list = result.get(t);
                        } else {
                            list = new ArrayList<>();
                            result.put(t, list);
                        }
                        list.add(p);
                    }
                }
            }
        }
        if (element instanceof Matrix) {
            Matrix m = (Matrix) element;
            if (!m.getDependentElements().isEmpty()) {
                List<Element> list;
                for (DependencyItem d : m.getDependentElements()) {
                    for (Element t : d.getDependentElements()) {
                        if (result.containsKey(t)) {
                            list = result.get(t);
                        } else {
                            list = new ArrayList<>();
                            result.put(t, list);
                        }
                        list.add(m);
                    }
                }
            }
        }
    }
    return result;
}


public void setSectionNumbering(int sectionNumbering){
    this.sectionNumbering = sectionNumbering;
}


@Column(name = "AUDIENCE")
public String getAudience(){
    return audience;
}


@Column(name = "DBVERSION")
public int getDBVersion(){
    return DBVersion;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "SURVEY_START_DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormatSmall)
public Date getStart(){
    return start;
}


@Transient
public Set<ECFProfile> getEcfProfiles(){
    if (this.getIsECF()) {
        Set<Element> questionSet = this.getElements().stream().filter(element -> element instanceof SingleChoiceQuestion).collect(Collectors.toSet());
        for (Element questionElement : questionSet) {
            SingleChoiceQuestion question = (SingleChoiceQuestion) questionElement;
            if (!question.getPossibleAnswers().isEmpty() && question.getPossibleAnswers().get(0).getEcfProfile() != null) {
                List<PossibleAnswer> possibleAnswers = question.getPossibleAnswers();
                return possibleAnswers.stream().map(possibleAnswer -> {
                    return possibleAnswer.getEcfProfile();
                }).collect(Collectors.toSet());
            }
        }
    }
    return new HashSet<>();
}


@Column(name = "SHOWICONS")
public Boolean getShowQuizIcons(){
    return showQuizIcons == null || showQuizIcons;
}


public void setCanCreateSurveys(boolean canCreateSurveys){
    this.canCreateSurveys = canCreateSurveys;
}


public void setConfirmationLink(String confirmationLink){
    this.confirmationLink = confirmationLink;
}


public Comparator<Element> newElementByPositionComparator(){
    return (first, second) -> {
        int result = 0;
        if (first.getPosition() != null && second.getPosition() != null) {
            result = first.getPosition().compareTo(second.getPosition());
        }
        // if both elements have the same position, the older one should be first
        if (result == 0) {
            result = first.getId().compareTo(second.getId());
        }
        return result;
    };
}


@Column(name = "DELPHIMINSTATISTICS")
public Integer getMinNumberDelphiStatistics(){
    return minNumberDelphiStatistics != null ? minNumberDelphiStatistics : 5;
}


public void setCompulsoryStyle(Integer compulsoryStyle){
    if (compulsoryStyle != null) {
        this.compulsoryStyle = compulsoryStyle;
    }
}


@Transient
public Map<String,Element> getMissingElementsByUniqueId(){
    if (missingElementsByUniqueId != null)
        return missingElementsByUniqueId;
    missingElementsByUniqueId = new HashMap<>();
    for (Element newElement : missingElements) {
        missingElementsByUniqueId.put(newElement.getUniqueId(), newElement);
        if (newElement instanceof MatrixOrTable) {
            for (Element child : ((MatrixOrTable) newElement).missingAnswers) {
                missingElementsById.put(child.getId(), newElement);
            }
        }
    }
    return missingElementsByUniqueId;
}


@Column(name = "DELETED")
public Boolean getIsDeleted(){
    return isDeleted != null && isDeleted;
}


@Lob
@Column(name = "TITLE", nullable = false)
public String getTitle(){
    return title;
}


public void setIsECF(Boolean isECF){
    this.isECF = isECF != null ? isECF : false;
}


public void setNotificationValue(String notificationValue){
    this.notificationValue = notificationValue;
}


public void setCaptcha(boolean captcha){
    this.captcha = captcha;
}


public void setId(Integer id){
    this.id = id;
}


@Transient
public String getElementsRecursiveUids(){
    StringBuilder result = new StringBuilder();
    for (Element element : getElementsRecursive(true)) {
        if (!(element instanceof EmptyElement))
            result.append(element.getUniqueId());
    }
    return result.toString();
}


public void setPublication(Publication publication){
    this.publication = publication;
}


@Temporal(TemporalType.TIMESTAMP)
@Transient
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getFirstPublished(){
    return firstPublished;
}


public void setFirstPublished(Date firstPublished){
    this.firstPublished = firstPublished;
}


public void setQuizWelcomeMessage(String quizWelcomeMessage){
    this.quizWelcomeMessage = quizWelcomeMessage;
}


@Column(name = "LISTFORMVALIDATED")
public boolean isListFormValidated(){
    return listFormValidated;
}


@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "LOGO")
public File getLogo(){
    return logo;
}


public void setIsFrozen(Boolean isFrozen){
    this.isFrozen = isFrozen != null && isFrozen;
}


@Transient
public List<Question> getQuestions(){
    List<Question> result = new ArrayList<>();
    for (Element element : missingElements) {
        if (element instanceof Question) {
            result.add((Question) element);
        }
    }
    for (Element element : elements) {
        if (element instanceof Question) {
            result.add((Question) element);
        }
    }
    // sort collection by position
    if (missingElements.size() > 0) {
        result.sort(newElementByPositionComparator());
    }
    return result;
}


public void setTitleSort(String titleSort){
    this.titleSort = titleSort;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "SURVEY_CREATED", nullable = false)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreated(){
    return created;
}


public void setPublished(Date published){
    this.published = published;
}


@Transient
public String getFileNameForBackgroundDocument(String key){
    if (fileNamesForBackgroundDocuments.containsKey(key)) {
        return fileNamesForBackgroundDocuments.get(key);
    }
    return key;
}


@Transient
public Map<String,Element> getElementsByUniqueId(){
    Map<String, Element> result = new HashMap<>();
    for (Element element : getElementsRecursive(true)) {
        result.put(element.getUniqueId(), element);
    }
    return result;
}


public void setEscapePageLink(Boolean escapePageLink){
    this.escapePageLink = escapePageLink;
}


@Transient
public List<String> getTranslations(){
    return translations;
}


public void setLanguage(Language language){
    this.language = language;
}


public void setFileNamesForBackgroundDocuments(Map<String,String> fileNamesForBackgroundDocuments){
    this.fileNamesForBackgroundDocuments = fileNamesForBackgroundDocuments;
}


public void setListForm(boolean listForm){
    this.listForm = listForm;
}


@Transient
public String getDeletedString(){
    return deletedString;
}


@Transient
public String shortCleanTitle(){
    String result = cleanTitle();
    if (result.length() > 50) {
        return result.substring(0, 50) + "...";
    }
    return result;
}


@Column(name = "AUTOMATICPUBLISHING")
public boolean getAutomaticPublishing(){
    return automaticPublishing;
}


public void setScoresByQuestion(Boolean scoresByQuestion){
    this.scoresByQuestion = scoresByQuestion;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateTimeFormatSmall)
@Column(name = "SURVEY_DELETED")
public Date getDeleted(){
    return deleted;
}


public void computeTriggers(){
    Map<Integer, String> triggers = new HashMap<>();
    for (Element element : elements) {
        if (element instanceof ChoiceQuestion) {
            for (PossibleAnswer p : ((ChoiceQuestion) element).getPossibleAnswers()) {
                for (Element dependent : p.getDependentElements().getDependentElements()) {
                    if (!triggers.containsKey(dependent.getId())) {
                        triggers.put(dependent.getId(), p.getId() + ";");
                    } else {
                        triggers.put(dependent.getId(), triggers.get(dependent.getId()) + p.getId() + ";");
                    }
                }
            }
        } else if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (DependencyItem dep : matrix.getDependentElements()) {
                for (Element dependent : dep.getDependentElements()) {
                    try {
                        Element q = matrix.getQuestions().get(dep.getPosition() / matrix.getAnswers().size());
                        Element a = matrix.getAnswers().get(dep.getPosition() % matrix.getAnswers().size());
                        if (!triggers.containsKey(dependent.getId())) {
                            triggers.put(dependent.getId(), q.getId().toString() + "|" + a.getId().toString() + ";");
                        } else {
                            triggers.put(dependent.getId(), triggers.get(dependent.getId()) + q.getId().toString() + "|" + a.getId().toString() + ";");
                        }
                    } catch (Exception e) {
                    // invalid dependency: ignore
                    }
                }
            }
        }
    }
    for (Element element : getElementsRecursive()) {
        if (triggers.containsKey(element.getId())) {
            element.setTriggers(triggers.get(element.getId()));
        }
    }
}


@Transient
public Boolean getHasUploadElement(){
    if (hasUploadElement == null) {
        hasUploadElement = false;
        for (Element question : elements) {
            if (question.getType().equalsIgnoreCase("UPLOAD")) {
                hasUploadElement = true;
                break;
            }
        }
    }
    return hasUploadElement;
}


@Column(name = "MAXNUMBERCONTRIBUTIONTEXT", length = 255)
public String getMaxNumberContributionText(){
    return this.maxNumberContributionText != null && this.maxNumberContributionText.length() > 0 ? this.maxNumberContributionText : MAXNUMBEROFRESULTSTEXT;
}


public void setMaxNumberContribution(Long maxNumberContribution){
    this.maxNumberContribution = maxNumberContribution != null && maxNumberContribution >= 0L ? maxNumberContribution : 0L;
}


public void setChangeContribution(boolean changeContribution){
    this.changeContribution = changeContribution;
}


@Transient
public String cleanTitleForMailSubject(){
    String result = ConversionTools.removeHTMLNoEscape(title);
    if (result.length() > 25) {
        return result.substring(0, 25) + "...";
    }
    return result;
}


@Column(name = "COMPULSORYSTYLE")
public Integer getCompulsoryStyle(){
    return compulsoryStyle;
}


@Column(name = "ISREGFORM")
public boolean getRegistrationForm(){
    return registrationForm;
}


@Column(name = "LOGOPOS")
public Boolean getLogoInInfo(){
    return logoInInfo != null && logoInInfo;
}


public void setNumberOfAnswerSets(int numberOfAnswerSets){
    this.numberOfAnswerSets = numberOfAnswerSets;
}


@Transient
public String getNicePublished(){
    return published != null ? Tools.formatDate(published, ConversionTools.DateFormat) : "";
}


@Column(name = "LISTFORM")
public boolean getListForm(){
    return listForm;
}


public void setEcasSecurity(Boolean ecasSecurity){
    this.ecasSecurity = ecasSecurity != null && ecasSecurity;
}


@Transient
public String getState(){
    Date now = new Date();
    if (automaticPublishing && end != null && end.before(now)) {
        return "Finished";
    }
    if (isActive && (!automaticPublishing || start == null || start.before(now))) {
        return "Running";
    }
    return "Draft";
}


@Column(name = "OPC")
public Boolean getIsOPC(){
    return isOPC;
}


public void setIsDelphiShowAnswersAndStatisticsInstantly(Boolean isDelphiShowAnswersAndStatisticsInstantly){
    this.isDelphiShowAnswersAndStatisticsInstantly = isDelphiShowAnswersAndStatisticsInstantly != null ? isDelphiShowAnswersAndStatisticsInstantly : false;
}


@Transient
public Map<String,String> getIDsByUniqueID(){
    Map<String, String> result = new HashMap<>();
    for (Element element : getElementsRecursive(true)) {
        result.put(element.getUniqueId(), element.getId().toString());
    }
    return result;
}


@Column(name = "BACKGROUNDDOCUMENTS")
@ElementCollection()
public Map<String,String> getBackgroundDocuments(){
    return backgroundDocuments;
}


@Lob
@Column(name = "QUIZRESULTS", length = 40000)
public String getQuizResultsMessage(){
    return quizResultsMessage;
}


@Transient
public boolean isAccessResultsRights(){
    return accessResultsRights;
}


}