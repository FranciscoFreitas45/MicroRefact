package org.jugbd.mnet.domain;
 import javax.persistence;
import javax.validation.constraints.NotNull;
@Entity
public class Attachment extends PersistentObject{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@NotNull
 private  String fileName;

@NotNull
 private  String mimeType;

@Lob
// 3 MB
@Column(length = 3145728, nullable = false)
 private  byte[] data;

@Column(length = 3000)
 private  String comment;

 private  boolean deleted;


public void setFileName(String fileName){
    this.fileName = fileName;
}


public String getMimeType(){
    return mimeType;
}


public boolean isDeleted(){
    return deleted;
}


public void setData(byte[] data){
    this.data = data;
}


public void setMimeType(String mimeType){
    this.mimeType = mimeType;
}


public void setId(Long id){
    this.id = id;
}


public void setComment(String comment){
    this.comment = comment;
}


public Long getId(){
    return id;
}


public String getComment(){
    return comment;
}


public String getFileName(){
    return fileName;
}


public byte[] getData(){
    return data;
}


public Attachment setDeleted(boolean deleted){
    this.deleted = deleted;
    return this;
}


}