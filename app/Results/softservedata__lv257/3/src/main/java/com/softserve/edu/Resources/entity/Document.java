package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Constants;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence;
import java.util.UUID;
@Entity
@Table(name = "DOCUMENTS")
public class Document {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
 private  long id_document;

@Column(name = "code")
 private  String code;

@Column(name = "extension")
 private  String fileExtension;

@Column(name = "URL")
 private  String documentsURL;

@Transient
 private  MultipartFile file;

public Document() {
    this.code = "file" + UUID.randomUUID().toString().substring(26).toUpperCase();
}
public void setDocumentsURL(String documentsURL){
    this.documentsURL = documentsURL;
}


public void setFile(MultipartFile file){
    this.file = file;
}


@Override
public int hashCode(){
    int result = (int) (id_document ^ (id_document >>> 32));
    result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
    result = 31 * result + (getFileExtension() != null ? getFileExtension().hashCode() : 0);
    return result;
}


public void setCode(String code){
    this.code = code;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Document))
        return false;
    Document document = (Document) o;
    if (id_document != document.id_document)
        return false;
    if (getCode() != null ? !getCode().equals(document.getCode()) : document.getCode() != null)
        return false;
    return getFileExtension() != null ? getFileExtension().equals(document.getFileExtension()) : document.getFileExtension() == null;
}


public String getFileExtension(){
    return fileExtension;
}


public void setId(long id_document){
    this.id_document = id_document;
}


public long getId(){
    return id_document;
}


public void setFileExtension(String fileExtension){
    this.fileExtension = fileExtension;
}


public String getCode(){
    return code;
}


public MultipartFile getFile(){
    return file;
}


public String getDocumentsURL(){
    return documentsURL;
}


}