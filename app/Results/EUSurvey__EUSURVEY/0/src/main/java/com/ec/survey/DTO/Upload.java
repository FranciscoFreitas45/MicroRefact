package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import com.ec.survey.tools.Tools;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
public class Upload extends Question{

 private  long serialVersionUID;

 private  String extensions;

 private  int maxFileSize;

public Upload() {
}public Upload(String text, String shortname, String uid) {
    setTitle(text);
    setUniqueId(uid);
    setShortname(shortname);
}
@Column(name = "MAXFILESIZE")
public Integer getMaxFileSize(){
    return maxFileSize > 0 ? maxFileSize : 1;
}


@Column(name = "EXTENSIONS")
public String getExtensions(){
    return extensions;
}


}