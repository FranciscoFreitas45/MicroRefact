package com.ec.survey.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Column(name = "PDFUNAVAIL")
public Boolean getShowPDFOnUnavailabilityPage(){
    return showPDFOnUnavailabilityPage != null && showPDFOnUnavailabilityPage;
}


@Column(name = "ESCURL")
public String getEscapeLink(){
    if (escapeLink != null && escapeLink.length() > 0 && !escapeLink.toLowerCase().startsWith("http")) {
        escapeLink = "http://" + escapeLink;
    }
    return escapeLink;
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


@Column(name = "NOTIFYALL")
public boolean getNotifyAll(){
    return notifyAll;
}


@Column(name = "ECASMODE")
public String getEcasMode(){
    return ecasMode;
}


@Column(name = "QUESTIONNUMBERING")
public int getQuestionNumbering(){
    return questionNumbering;
}


@Transient
public String getNiceFirstPublished(){
    return firstPublished != null ? Tools.formatDate(firstPublished, ConversionTools.DateFormat) : "";
}


@Lob
@Column(name = "CONFIRMATION", nullable = false, length = 40000)
public String getConfirmationPage(){
    return confirmationPage;
}


@Column(name = "SHOWCOUNTDOWN")
public Boolean getShowCountdown(){
    return isShowCountdown != null ? isShowCountdown : false;
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


@Column(name = "NOTIFICATIONVALUE")
public String getNotificationValue(){
    if (!automaticPublishing)
        return null;
    return notificationValue;
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


@ManyToOne
@JoinColumn(name = "SURVEYSKIN")
public Skin getSkin(){
    return skin;
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


@Column(name = "DOCSUNAVAIL")
public Boolean getShowDocsOnUnavailabilityPage(){
    return showDocsOnUnavailabilityPage != null && showDocsOnUnavailabilityPage;
}


@Column(name = "DELPHI")
public Boolean getIsDelphi(){
    return isDelphi;
}


@Column(name = "HASPENDINGCHANGES")
public boolean getHasPendingChanges(){
    return hasPendingChanges;
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


@Lob
@Column(name = "QUIZWELCOME", length = 40000)
public String getQuizWelcomeMessage(){
    return quizWelcomeMessage;
}


@Column(name = "CHANGECONTRIBUTION")
public boolean getChangeContribution(){
    return changeContribution;
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


@Column(name = "WCAGCOMPLIANCE")
public Boolean getWcagCompliance(){
    return wcagCompliance;
}


@Column(name = "numberOfAnswerSets")
public int getNumberOfAnswerSets(){
    return numberOfAnswerSets;
}


@Column(name = "ECF")
public Boolean getIsECF(){
    return isECF != null ? isECF : false;
}


@Column(name = "ARCHIVED")
public Boolean getArchived(){
    return isArchived;
}


@Column(name = "numberOfAnswerSetsPublished")
public int getNumberOfAnswerSetsPublished(){
    return numberOfAnswerSetsPublished > 0 ? numberOfAnswerSetsPublished : 0;
}


@Column(name = "SURVEYSECURITY")
public String getSecurity(){
    return security;
}


@Transient
public Map<String,String> getFileNamesForBackgroundDocuments(){
    return fileNamesForBackgroundDocuments;
}


@Column(name = "SCOREBYQUESTION")
public Boolean getScoresByQuestion(){
    return scoresByQuestion != null ? scoresByQuestion : true;
}


@Transient
public List<Element> getMissingElements(){
    return missingElements;
}


public Boolean getIsUseMaxNumberContribution(){
    return this.isUseMaxNumberContribution != null ? this.isUseMaxNumberContribution : false;
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


@Column(name = "DELPHIANSWERS")
public Boolean getIsDelphiShowAnswers(){
    return isDelphiShowAnswers != null ? isDelphiShowAnswers : false;
}


@Transient
public int getNumberOfDrafts(){
    return numberOfDrafts;
}


@Column(name = "TRUSTSCORE")
public Integer getTrustScore(){
    return trustScore;
}


@Column(name = "CONTACTLABEL")
public String getContactLabel(){
    return contactLabel;
}


@Column(name = "QUIZ")
public Boolean getIsQuiz(){
    return isQuiz;
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


@OneToMany(targetEntity = Element.class, cascade = CascadeType.ALL, orphanRemoval = true)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Element> getElements(){
    return elements;
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


@Column(name = "DOWNLOADCONTRIBUTION")
public Boolean getDownloadContribution(){
    return downloadContribution;
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


@Column(name = "SHOWSCORE")
public Boolean getShowTotalScore(){
    return showTotalScore == null || showTotalScore;
}


@ManyToOne
@JoinColumn(name = "LANGUAGE", nullable = false)
public Language getLanguage(){
    return language;
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


@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
public Publication getPublication(){
    return publication;
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


@Column(name = "DELPHIMINSTATISTICS")
public Integer getMinNumberDelphiStatistics(){
    return minNumberDelphiStatistics != null ? minNumberDelphiStatistics : 5;
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


@Transient
public String getElementsRecursiveUids(){
    StringBuilder result = new StringBuilder();
    for (Element element : getElementsRecursive(true)) {
        if (!(element instanceof EmptyElement))
            result.append(element.getUniqueId());
    }
    return result.toString();
}


@Temporal(TemporalType.TIMESTAMP)
@Transient
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getFirstPublished(){
    return firstPublished;
}


@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "LOGO")
public File getLogo(){
    return logo;
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


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "SURVEY_CREATED", nullable = false)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreated(){
    return created;
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


@Transient
public List<String> getTranslations(){
    return translations;
}


@Transient
public String getDeletedString(){
    return deletedString;
}


@Column(name = "AUTOMATICPUBLISHING")
public boolean getAutomaticPublishing(){
    return automaticPublishing;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateTimeFormatSmall)
@Column(name = "SURVEY_DELETED")
public Date getDeleted(){
    return deleted;
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


@Transient
public String getNicePublished(){
    return published != null ? Tools.formatDate(published, ConversionTools.DateFormat) : "";
}


@Column(name = "LISTFORM")
public boolean getListForm(){
    return listForm;
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


public void setTitle(String title){
    this.title = title;
    String t = ConversionTools.removeHTML(title);
    this.titleSort = t.length() > 100 ? t.substring(0, 100) : t;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTitle"))

.queryParam("title",title)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIntroduction(String introduction){
    this.introduction = introduction;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIntroduction"))

.queryParam("introduction",introduction)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLanguage(Language language){
    this.language = language;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLanguage"))

.queryParam("language",language)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLogoText(String logoText){
    this.logoText = logoText;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLogoText"))

.queryParam("logoText",logoText)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEscapePage(String escapePage){
    this.escapePage = escapePage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEscapePage"))

.queryParam("escapePage",escapePage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEscapeLink(String escapeLink){
    this.escapeLink = escapeLink;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEscapeLink"))

.queryParam("escapeLink",escapeLink)
;
restTemplate.put(builder.toUriString(),null);
}


public void setConfirmationPage(String confirmationPage){
    this.confirmationPage = confirmationPage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setConfirmationPage"))

.queryParam("confirmationPage",confirmationPage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setConfirmationLink(String confirmationLink){
    this.confirmationLink = confirmationLink;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setConfirmationLink"))

.queryParam("confirmationLink",confirmationLink)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQuizWelcomeMessage(String quizWelcomeMessage){
    this.quizWelcomeMessage = quizWelcomeMessage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQuizWelcomeMessage"))

.queryParam("quizWelcomeMessage",quizWelcomeMessage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQuizResultsMessage(String quizResultsMessage){
    this.quizResultsMessage = quizResultsMessage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQuizResultsMessage"))

.queryParam("quizResultsMessage",quizResultsMessage)
;
restTemplate.put(builder.toUriString(),null);
}


@Transient
public boolean isAnonymous(){
    return security != null && security.endsWith("anonymous");
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAnonymous"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/serialize"))

.queryParam("elementOrderOnly",elementOrderOnly)
;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


public void reorderElementsByPosition(){
    elements.sort(Comparator.comparing(o -> (o.getPosition())));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reorderElementsByPosition"))

;
restTemplate.put(builder.toUriString(),null);
}


public void resetElementsRecursive(){
    elementsRecursive = null;
    elementsRecursiveWithAnswers = null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/resetElementsRecursive"))

;
restTemplate.put(builder.toUriString(),null);
}


public void setActivitiesToLog(Map<Integer,String[]> activitiesToLog){
    this.activitiesToLog = activitiesToLog;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActivitiesToLog"))

.queryParam("activitiesToLog",activitiesToLog)
;
restTemplate.put(builder.toUriString(),null);
}


public void setId(Integer id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOwner(User owner){
    this.owner = owner;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOwner"))

.queryParam("owner",owner)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUniqueId"))

.queryParam("uniqueId",uniqueId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContact(String contact){
    this.contact = contact;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContact"))

.queryParam("contact",contact)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShortname(String shortname){
    this.shortname = shortname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShortname"))

.queryParam("shortname",shortname)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSecurity(String security){
    this.security = security;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSecurity"))

.queryParam("security",security)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCaptcha(boolean captcha){
    this.captcha = captcha;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCaptcha"))

.queryParam("captcha",captcha)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSectionNumbering(int sectionNumbering){
    this.sectionNumbering = sectionNumbering;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSectionNumbering"))

.queryParam("sectionNumbering",sectionNumbering)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQuestionNumbering(int questionNumbering){
    this.questionNumbering = questionNumbering;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQuestionNumbering"))

.queryParam("questionNumbering",questionNumbering)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStart(Date start){
    this.start = start;
    this.startString = ConversionTools.getFullStringSmall(start);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStart"))

.queryParam("start",start)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEnd(Date end){
    this.end = end;
    this.endString = ConversionTools.getFullStringSmall(end);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEnd"))

.queryParam("end",end)
;
restTemplate.put(builder.toUriString(),null);
}


public void setListForm(boolean listForm){
    this.listForm = listForm;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setListForm"))

.queryParam("listForm",listForm)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMultiPaging(boolean multiPaging){
    this.multiPaging = multiPaging;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMultiPaging"))

.queryParam("multiPaging",multiPaging)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsActive(Boolean isActive){
    this.isActive = isActive != null && isActive;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsActive"))

.queryParam("isActive",isActive)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSkin(Skin skin){
    this.skin = skin;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSkin"))

.queryParam("skin",skin)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumberOfAnswerSets(int numberOfAnswerSets){
    this.numberOfAnswerSets = numberOfAnswerSets;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumberOfAnswerSets"))

.queryParam("numberOfAnswerSets",numberOfAnswerSets)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumberOfAnswerSetsPublished(int numberOfAnswerSetsPublished){
    this.numberOfAnswerSetsPublished = numberOfAnswerSetsPublished;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumberOfAnswerSetsPublished"))

.queryParam("numberOfAnswerSetsPublished",numberOfAnswerSetsPublished)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsDelphi(Boolean isDelphi){
    this.isDelphi = isDelphi != null && isDelphi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsDelphi"))

.queryParam("isDelphi",isDelphi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLogo(File logo){
    this.logo = logo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLogo"))

.queryParam("logo",logo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsDraft(boolean draft){
    this.isDraft = draft;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsDraft"))

.queryParam("draft",draft)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreated(Date created){
    this.created = created;
    this.createdString = Tools.formatDate(created, ConversionTools.DateFormat);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreated"))

.queryParam("created",created)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPassword(String password){
    this.password = password;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPassword"))

.queryParam("password",password)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsPublished(boolean isPublished){
    this.isPublished = isPublished;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsPublished"))

.queryParam("isPublished",isPublished)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsECF(Boolean isECF){
    this.isECF = isECF != null ? isECF : false;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsECF"))

.queryParam("isECF",isECF)
;
restTemplate.put(builder.toUriString(),null);
}


}