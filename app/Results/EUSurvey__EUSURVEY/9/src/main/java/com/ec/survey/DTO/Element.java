package com.ec.survey.DTO;
 import com.ec.survey.tools.ConversionTools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
public class Element {

 private  long serialVersionUID;

 private  Integer id;

 private  String uid;

 private  String oldId;

 private  String shortname;

 private  Integer sourceId;

 private  Survey survey;

 private  Integer position;

 private  String title;

 private  String originalTitle;

 private  boolean isDummy;

 private  String triggers;

 private  boolean hasPDFWidth;

 private  float pdfWidth;

 private  Boolean locked;

 private  String subType;

 private  Integer displayMode;

 private  Boolean useAndLogic;

 private  Map<Integer,String[]> activitiesToLog;

 private  Boolean isIsDependentMatrixQuestion;


@Transient
public String getOldId(){
    return oldId;
}


@Transient
public String getOriginalTitle(){
    return originalTitle;
}


public void setDummy(boolean isDummy){
    this.isDummy = isDummy;
}


public void setSubType(String subType){
    this.subType = subType != null ? subType : "";
}


@Transient
public boolean isUsedInResults(){
    return !(this instanceof Ruler || this instanceof Confirmation || this instanceof Image || this instanceof Download || this instanceof Text || (this instanceof GalleryQuestion && !((GalleryQuestion) this).getSelection()));
}


@Column(name = "SUBTYPE")
public String getSubType(){
    return subType;
}


public void setPosition(Integer position){
    this.position = position;
}


@Column(name = "ELEM_UID")
public String getUniqueId(){
    return uid;
}


@Lob
@Column(name = "ETITLE", length = 40000)
public String getTitle(){
    return title;
}


public void setTriggers(String triggers){
    this.triggers = triggers;
}


public void setId(Integer id){
    this.id = id;
}


public void setOldId(String oldId){
    this.oldId = oldId;
}


@Transient
public String getStrippedTitle(){
    if (title != null && title.length() > 0) {
        return ConversionTools.removeHTML(title, true).replace("\"", "'");
    }
    return "";
}


@Transient
public float getPDFWidth(){
    return pdfWidth;
}


public void presetIsDependentMatrixQuestion(Survey survey){
    if (survey != null) {
        for (Element element : survey.getElements()) {
            if (element instanceof ChoiceQuestion) {
                for (PossibleAnswer p : ((ChoiceQuestion) element).getPossibleAnswers()) {
                    if (p.getDependentElements().getDependentElements().contains(this)) {
                        isIsDependentMatrixQuestion = true;
                        return;
                    }
                }
            } else if (element instanceof Matrix) {
                for (DependencyItem dep : ((Matrix) element).getDependentElements()) {
                    if (dep != null && dep.getDependentElements().contains(this)) {
                        isIsDependentMatrixQuestion = true;
                        return;
                    }
                }
            }
        }
    }
    if (getIsDependent(survey)) {
        isIsDependentMatrixQuestion = true;
        return;
    }
    if (survey != null) {
        for (Element element : survey.getElements()) {
            if (element instanceof Matrix) {
                Matrix matrix = (Matrix) element;
                if (matrix.getQuestions().contains(this)) {
                    isIsDependentMatrixQuestion = matrix.getIsDependent(survey);
                    return;
                }
            }
        }
    }
    isIsDependentMatrixQuestion = false;
}


public void setDisplayMode(Integer displayMode){
    this.displayMode = displayMode != null ? displayMode : 0;
}


@Transient
public boolean getIsDependentMatrixQuestion(){
    if (isIsDependentMatrixQuestion != null)
        return isIsDependentMatrixQuestion;
    if (getIsDependent()) {
        return true;
    }
    if (survey != null) {
        for (Element element : survey.getElements()) {
            if (element instanceof Matrix) {
                Matrix matrix = (Matrix) element;
                if (matrix.getQuestions().contains(this)) {
                    return matrix.getIsDependent();
                }
            }
        }
    }
    return false;
}


@Transient
public String getTriggersMatrixQuestion(){
    StringBuilder result = new StringBuilder(triggers);
    if (survey != null && survey.getElements() != null) {
        for (Element element : survey.getElements()) {
            if (element instanceof Matrix) {
                Matrix matrix = (Matrix) element;
                if (matrix.getQuestions().contains(this)) {
                    String parentTriggers = matrix.getTriggers();
                    result.append(parentTriggers);
                }
            }
        }
    }
    return result.toString();
}


public void setTitle(String title){
    this.title = title;
}


@Transient
public Survey getSurvey(){
    return survey;
}


@Column(name = "ELOCKED")
public Boolean getLocked(){
    return locked != null && locked;
}


public void setShortname(String shortname){
    this.shortname = shortname;
}


@Transient
public String getStrippedTitleAtMost100(){
    String strippedTitle = getStrippedTitle();
    if (strippedTitle.length() > 100)
        return strippedTitle.substring(0, 100) + "...";
    return strippedTitle;
}


@Transient
public String getNameOrType(){
    if (title != null && title.length() > 0)
        return title;
    return getType();
}


public void setSourceId(Integer sourceId){
    this.sourceId = sourceId;
}


public void setActivitiesToLog(Map<Integer,String[]> activitiesToLog){
    this.activitiesToLog = activitiesToLog;
}


@Transient
public String getType(){
    return this.getClass().getSimpleName();
}


@Transient
public boolean getHasPDFWidth(){
    return hasPDFWidth;
}


@Transient
public boolean isDummy(){
    return isDummy;
}


@Transient
public String getTriggers(){
    return triggers;
}


@Column(name = "ANDLOGIC")
public Boolean getUseAndLogic(){
    return useAndLogic;
}


@Column(name = "ELEM_SHORTNAME")
public String getShortname(){
    return shortname;
}


@Transient
public void setHasPDFWidth(boolean b){
    hasPDFWidth = b;
}


@Transient
public boolean basicDiffersFrom(Element element){
    if (shortname != null && !shortname.equals(element.shortname))
        return true;
    if (position != null && !position.equals(element.position))
        return true;
    if (useAndLogic != null && !useAndLogic.equals(element.useAndLogic))
        return true;
    return (title != null && !title.equals(element.title));
}


@Transient
public String getStrippedTitleNoEscape(){
    if (title != null && title.length() > 0) {
        return ConversionTools.removeHTML(title, false).replace("\"", "'");
    }
    return "";
}


@Transient
public boolean getIsDependent(Survey survey){
    if (survey != null) {
        for (Element element : survey.getElements()) {
            if (element instanceof ChoiceQuestion) {
                for (PossibleAnswer p : ((ChoiceQuestion) element).getPossibleAnswers()) {
                    if (p.getDependentElements().getDependentElements().contains(this)) {
                        return true;
                    }
                }
            } else if (element instanceof Matrix) {
                for (DependencyItem dep : ((Matrix) element).getDependentElements()) {
                    if (dep != null && dep.getDependentElements().contains(this)) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}


@Id
@Column(name = "ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setUniqueId(String uid){
    this.uid = uid;
}


public void setUseAndLogic(Boolean useAndLogic){
    this.useAndLogic = useAndLogic != null ? useAndLogic : false;
}


@Column(name = "DISPLAYMODE")
public Integer getDisplayMode(){
    return displayMode;
}


public boolean differsFrom(Element element)


@Transient
public String getStrippedTitleNoEscape2(){
    if (title != null && title.length() > 0) {
        return ConversionTools.removeHTMLNoEscape(title).replace("\"", "'");
    }
    return "";
}


@Transient
public void setPDFWidth(float w){
    pdfWidth = w;
}


public Element copy(String fileDir)


public void setOriginalTitle(String originalTitle){
    this.originalTitle = originalTitle;
}


@Transient
public boolean isDelphiElement(){
    if (this instanceof Question) {
        return ((Question) this).getIsDelphiQuestion();
    }
    return false;
}


public void setLocked(Boolean locked){
    this.locked = locked != null && locked;
}


@Column(name = "EPOSITION")
public Integer getPosition(){
    return position;
}


public void setSurvey(Survey s){
    this.survey = s;
}


@Transient
public Map<Integer,String[]> getActivitiesToLog(){
    return this.activitiesToLog;
}


@Column(name = "SOURCE_ID")
public Integer getSourceId(){
    return sourceId;
}


}