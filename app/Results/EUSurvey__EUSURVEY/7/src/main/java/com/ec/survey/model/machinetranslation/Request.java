package com.ec.survey.model.machinetranslation;
 import com.ec.survey.tools.ConversionTools;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "MT_REQUEST")
public class Request {

 private  long serialVersionUID;

 private  Integer id;

 private  String uniqueId;

 private  String sourceLang;

 private  String targetLangs;

 private  String text;

 private  String fileURL;

 private  String username;

 private  Date created;

 private  Integer translationsID;

 private  Integer translationID;

 private  boolean isNotify;

 private  String email;

 private  Set<Response> responses;

public Request() {
    created = new Date();
}
public void setTranslationID(Integer translationsID){
    this.translationID = translationsID;
}


@OneToMany(mappedBy = "request", cascade = { CascadeType.ALL }, orphanRemoval = true)
public Set<Response> getResponses(){
    return responses;
}


public void setTranslationsID(Integer translationsID){
    this.translationsID = translationsID;
}


@Id
@Column(name = "ID", nullable = false)
@GeneratedValue
public Integer getId(){
    return id;
}


public void setSourceLang(String sourceLang){
    this.sourceLang = sourceLang;
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
}


@Column(name = "SOURCE_FILE_URL")
public String getFileURL(){
    return fileURL;
}


@Column(name = "UID", nullable = false, unique = true)
public String getUniqueId(){
    return uniqueId;
}


@Column(name = "USERNAME", nullable = false)
public String getUsername(){
    return username;
}


public void setTargetLangs(String targetLangs){
    this.targetLangs = targetLangs;
}


public void setNotify(boolean notify){
    this.isNotify = notify;
}


public void setId(Integer id){
    this.id = id;
}


public void setFileURL(String fileURL){
    this.fileURL = fileURL;
}


@Column(name = "TRANSLATION_ID")
public Integer getTranslationID(){
    return translationID;
}


@Column(name = "SOURCE_LANG", nullable = false, length = 2)
public String getSourceLang(){
    return sourceLang;
}


public void setUsername(String username){
    this.username = username;
}


@Column(name = "TEXT_TO_TRANSLATE", length = 4000)
public String getText(){
    return text;
}


@Column(name = "IS_NOTIFY")
public boolean isNotify(){
    return isNotify;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "CREATED", nullable = false)
@DateTimeFormat(pattern = ConversionTools.DateTimeFormatSmall)
public Date getCreated(){
    return created;
}


public void setCreated(Date created){
    this.created = created;
}


@Column(name = "TRANSLATIONS_ID", nullable = false)
public Integer getTranslationsID(){
    return translationsID;
}


public void setEmail(String email){
    this.email = email;
}


@Column(name = "EMAIL")
public String getEmail(){
    return email;
}


public void setResponses(Set<Response> responses){
    this.responses = responses;
}


@Column(name = "TARGET_LANGS", nullable = false)
public String getTargetLangs(){
    return targetLangs;
}


public void setText(String text){
    this.text = text;
}


}