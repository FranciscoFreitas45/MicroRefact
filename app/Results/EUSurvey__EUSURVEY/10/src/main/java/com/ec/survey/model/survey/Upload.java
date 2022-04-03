package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import com.ec.survey.tools.Tools;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("UPLOAD")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
public void setMaxFileSize(Integer maxFileSize){
    this.maxFileSize = (maxFileSize != null && maxFileSize > 0) ? maxFileSize : 1;
}


public Upload copy(String fileDir){
    Upload copy = new Upload();
    baseCopy(copy);
    copy.setExtensions(extensions);
    copy.setMaxFileSize(maxFileSize);
    return copy;
}


@Column(name = "MAXFILESIZE")
public Integer getMaxFileSize(){
    return maxFileSize > 0 ? maxFileSize : 1;
}


@Column(name = "EXTENSIONS")
public String getExtensions(){
    return extensions;
}


public void setExtensions(String extensions){
    this.extensions = extensions;
}


@Override
public boolean differsFrom(Element element){
    Upload upload = (Upload) element;
    if (!Tools.isEqual(extensions, upload.getExtensions())) {
        return true;
    }
    if (!Tools.isEqual(maxFileSize, upload.getMaxFileSize())) {
        return true;
    }
    return basicDiffersFrom(element);
}


}