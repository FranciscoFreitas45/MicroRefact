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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


@Transient
public String getOldId(){
    return oldId;
}


@Transient
public String getOriginalTitle(){
    return originalTitle;
}


@Column(name = "SUBTYPE")
public String getSubType(){
    return subType;
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


@Transient
public Survey getSurvey(){
    return survey;
}


@Column(name = "ELOCKED")
public Boolean getLocked(){
    return locked != null && locked;
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


@Transient
public String getType(){
    return this.getClass().getSimpleName();
}


@Transient
public boolean getHasPDFWidth(){
    return hasPDFWidth;
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


@Column(name = "DISPLAYMODE")
public Integer getDisplayMode(){
    return displayMode;
}


@Transient
public String getStrippedTitleNoEscape2(){
    if (title != null && title.length() > 0) {
        return ConversionTools.removeHTMLNoEscape(title).replace("\"", "'");
    }
    return "";
}


@Column(name = "EPOSITION")
public Integer getPosition(){
    return position;
}


@Transient
public Map<Integer,String[]> getActivitiesToLog(){
    return this.activitiesToLog;
}


@Column(name = "SOURCE_ID")
public Integer getSourceId(){
    return sourceId;
}


@Transient
public boolean isDelphiElement(){
    if (this instanceof Question) {
        return ((Question) this).getIsDelphiQuestion();
    }
    return false;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isDelphiElement"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}