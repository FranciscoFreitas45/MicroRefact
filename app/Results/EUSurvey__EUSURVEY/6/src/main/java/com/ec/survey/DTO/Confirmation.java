package com.ec.survey.DTO;
 import com.ec.survey.model.survey.base.File;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
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


@Lob
@Column(name = "CONFTEXT", length = 40000)
public String getConfirmationtext(){
    return confirmationtext;
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


}