package com.ec.survey.DTO;
 import com.ec.survey.model.administration.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Skin {

 private  long serialVersionUID;

 private  int id;

 private  String name;

 private  boolean isPublic;

 private  Date updateDate;

 private  User owner;

 private  List<SkinElement> elements;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

public Skin() {
    createMissingElements();
}
@Column(name = "FILE_NAME")
public String getName(){
    return name;
}


@Transient
public String getCss(){
    return getCss(false);
}


@Id
@Column(name = "SKIN_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Transient
public String getElementValue(String name,String key){
    for (SkinElement element : elements) {
        if (element.getName().equals(name)) {
            switch(key) {
                case "color":
                    return element.getColor() != null && element.getColor().length() > 0 ? element.getColor() : getDefault(name, key);
                case "background-color":
                    return element.getBackgroundColor() != null && element.getBackgroundColor().length() > 0 ? element.getBackgroundColor() : getDefault(name, key);
                case "font-family":
                    return element.getFontFamily() != null && element.getFontFamily().length() > 0 ? element.getFontFamily() : getDefault(name, key);
                case "font-size":
                    return element.getFontSize() != null && element.getFontSize().length() > 0 ? element.getFontSize() : getDefault(name, key);
                case "font-weight":
                    return element.getFontWeight() != null && element.getFontWeight().length() > 0 ? element.getFontWeight() : getDefault(name, key);
                case "font-style":
                    return element.getFontStyle() != null && element.getFontStyle().length() > 0 ? element.getFontStyle() : getDefault(name, key);
                default:
                    break;
            }
        }
    }
    return "";
}


public String getDefault(String name,String key){
    switch(key) {
        case "color":
            if (name.equals(".sectiontitle"))
                return "004F98";
            if (name.equals(".link"))
                return "0088CC";
            if (name.equals(".questionhelp"))
                return "A6A6A6";
            return "333333";
        case "background-color":
            if (name.equals(".right-area"))
                return "ffffff";
            return "transparent";
        case "font-family":
            return "sans-serif";
        case "font-size":
            if (name.equals(".info-box"))
                return "13px";
            if (name.equals(".questionhelp"))
                return "11px";
            if (name.equals(".linkstitle"))
                return "16px";
            if (name.equals(".sectiontitle"))
                return "20px";
            if (name.equals(".surveytitle"))
                return "24px";
            return "14px";
        case "font-weight":
            if (name.equals(".linkstitle"))
                return "bold";
            return "normal";
        case "font-style":
            return "normal";
        default:
            break;
    }
    return "";
}


@ManyToOne
@JoinColumn(name = "OWNER", nullable = false)
public User getOwner(){
    return owner;
}


@Column(name = "ISPUBLIC")
public boolean getIsPublic(){
    return isPublic;
}


@Transient
public String getValue(String item,String key){
    return "";
}


@Transient
public String getDisplayName(){
    if (getOwner().getLogin().equalsIgnoreCase("admin")) {
        switch(getName()) {
            case "EUSurveyNew.css":
                return "EUSurvey";
            case "New Official EC Skin":
                return "EC Official";
            case "ECA Skin":
                return "ECA Official";
            case "EUSurvey.css":
                return "EUSurvey (obsolete)";
            case "Official EC Skin":
                return "EC Official (obsolete)";
            default:
                break;
        }
    }
    return getName();
}


@OneToMany(targetEntity = SkinElement.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "name asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<SkinElement> getElements(){
    return elements;
}


@Column(name = "UPDATE_DATE")
public Date getUpdateDate(){
    return updateDate;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Skin other = (Skin) obj;
    return (id == other.id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("obj",obj)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}