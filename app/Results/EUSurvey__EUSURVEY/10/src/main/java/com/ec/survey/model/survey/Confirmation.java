package com.ec.survey.model.survey;
 import com.ec.survey.model.survey.base.File;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@DiscriminatorValue("CONFIRMATION")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Confirmation extends Question{

 private  long serialVersionUID;

 public  String TEXT;

 public  String LABEL;

 private  List<File> files;

 private  String confirmationtext;

 private  String confirmationlabel;

 private  boolean usetext;

 private  Boolean useupload;

public Confirmation() {
}public Confirmation(String text, String shortname, String uid) {
    setTitle(text);
    setUniqueId(uid);
    setShortname(shortname);
}
@Lob
@Column(name = "CONFLABEL", length = 40000)
public String getConfirmationlabel(){
    return confirmationlabel != null ? confirmationlabel : "Show";
}


@Column(name = "ISUSETEXT")
public boolean isUsetext(){
    return usetext;
}


public void setUsetext(Boolean usetext){
    this.usetext = usetext == null || usetext;
}


@Lob
@Column(name = "CONFTEXT", length = 40000)
public String getConfirmationtext(){
    return confirmationtext;
}


public void setConfirmationtext(String confirmationtext){
    this.confirmationtext = confirmationtext;
}


@Column(name = "ISUSEUPLOAD")
public boolean isUseupload(){
    return useupload != null ? useupload : !usetext;
}


public Confirmation copy(String fileDir){
    Confirmation copy = new Confirmation();
    baseCopy(copy);
    copy.setUsetext(usetext);
    copy.confirmationtext = confirmationtext;
    copy.confirmationlabel = confirmationlabel;
    copy.useupload = useupload;
    try {
        for (File file : files) {
            File copyFile = file.copy(fileDir);
            copy.files.add(copyFile);
        }
    } catch (org.hibernate.LazyInitializationException e) {
    // ignore
    }
    return copy;
}


@SuppressWarnings("deprecation")
@ManyToMany(targetEntity = File.class, cascade = CascadeType.ALL)
@JoinTable(foreignKey = @ForeignKey(javax.persistence.ConstraintMode.NO_CONSTRAINT))
@org.hibernate.annotations.ForeignKey(name = "none")
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<File> getFiles(){
    return files;
}


public void setConfirmationlabel(String confirmationlabel){
    this.confirmationlabel = confirmationlabel;
}


public void setUseupload(Boolean useupload){
    this.useupload = useupload;
}


@Override
public boolean differsFrom(Element element){
    if (basicDiffersFrom(element))
        return true;
    if (!(element instanceof Confirmation))
        return true;
    Confirmation download = (Confirmation) element;
    if (confirmationtext != null && !confirmationtext.equals(download.confirmationtext))
        return true;
    if (usetext != download.usetext)
        return true;
    for (File file : files) {
        boolean found = false;
        for (File otherFile : download.files) {
            if (otherFile.getName().equals(file.getName()) && (otherFile.getComment() == null || otherFile.getComment().equals(file.getComment()))) {
                found = true;
                break;
            }
        }
        if (!found)
            return true;
    }
    for (File file : download.files) {
        boolean found = false;
        for (File otherFile : files) {
            if (otherFile.getName().equals(file.getName()) && (otherFile.getComment() == null || otherFile.getComment().equals(file.getComment()))) {
                found = true;
                break;
            }
        }
        if (!found)
            return true;
    }
    return false;
}


public void setFiles(List<File> files){
    this.files = files;
}


}