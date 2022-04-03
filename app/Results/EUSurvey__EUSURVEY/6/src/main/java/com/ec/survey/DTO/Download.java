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
public class Download extends Question{

 private  long serialVersionUID;

 private  List<File> files;

public Download() {
}public Download(String text, String shortname, String uid) {
    setTitle(text);
    setUniqueId(uid);
    setShortname(shortname);
}
@ManyToMany(targetEntity = File.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<File> getFiles(){
    return files;
}


}