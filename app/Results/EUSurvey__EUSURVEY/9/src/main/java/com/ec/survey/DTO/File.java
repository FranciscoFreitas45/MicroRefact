package com.ec.survey.DTO;
 import com.ec.survey.tools.ConversionTools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import java.util.Date;
public class File {

 private  long serialVersionUID;

 private  Integer id;

 private  String name;

 private  String uid;

 private  Integer width;

 private  String comment;

 private  Integer position;

 private  Date deletionDate;

 private  Integer answerId;

 private  String longdesc;

 private  String questionUid;

 private  String description;


public void setName(String name){
    this.name = name;
}


@Column(name = "FILE_NAME", nullable = false)
public String getName(){
    return name;
}


@Transient
public String getQuestionUid(){
    return questionUid;
}


@Id
@Column(name = "FILE_ID", nullable = false)
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "FILE_WIDTH", nullable = false)
public Integer getWidth(){
    return width;
}


public void setDescription(String description){
    this.description = description;
}


public void setUid(String uid){
    this.uid = uid;
}


@Column(name = "FILE_DESC")
public String getDescription(){
    return description;
}


public void setPosition(Integer position){
    this.position = position;
}


@Column(name = "FILE_DEL")
public Date getDeletionDate(){
    return deletionDate;
}


@Column(name = "FILE_UID", nullable = false)
public String getUid(){
    return uid;
}


public void setId(Integer id){
    this.id = id;
}


@Lob
@Column(name = "FILE_COMMENT", length = 40000)
public String getComment(){
    return comment;
}


@Transient
public File copy(String fileDir){
    File copyFile = new File();
    copyFile.name = name;
    copyFile.uid = uid;
    copyFile.comment = comment;
    copyFile.position = position;
    copyFile.longdesc = longdesc;
    copyFile.description = description;
    return copyFile;
}


@Transient
public String getNameForExport(){
    return getName().replace(";", "").replace("|", "");
}


@Transient
public String getCleanComment(){
    return ConversionTools.removeHTML(comment, false);
}


public void setWidth(Integer width){
    this.width = width;
}


public void setAnswerId(Integer answerId){
    this.answerId = answerId;
}


@Column(name = "FILE_POS")
public Integer getPosition(){
    return position;
}


public void setComment(String comment){
    this.comment = comment;
}


public void setDeletionDate(Date deletionDate){
    this.deletionDate = deletionDate;
}


public void setLongdesc(String longdesc){
    this.longdesc = longdesc;
}


@Transient
public Integer getAnswerId(){
    return answerId;
}


public void setQuestionUid(String questionUid){
    this.questionUid = questionUid;
}


@Column(name = "FILE_LONGDESC")
public String getLongdesc(){
    return longdesc;
}


}