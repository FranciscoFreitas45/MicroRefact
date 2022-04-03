package org.gliderwiki.web.domain;
 import java.util.Date;
import java.text.SimpleDateFormat;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("attachments")
public class Attachment extends BaseObjectBean{

 private  long serialVersionUID;

@Column(name = "id", primaryKey = true, autoIncrement = true)
 private  Long id;

@Column(name = "category")
 private  AttachmentCategory category;

@Column(name = "user_id")
 private  String user_id;

@Column(name = "board_no")
 private  Integer board_no;

@Column(name = "extension")
 private  String extension;

@Column(name = "file_name")
 private  String file_name;

@Column(name = "created_date")
 private  Date created_date;

public Attachment() {
}
public String getExtension(){
    return extension;
}


public void setExtension(String extension){
    this.extension = extension;
}


public void setFile_name(String file_name){
    this.file_name = file_name;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public AttachmentCategory getCategory(){
    return category;
}


public void setBoard_no(Integer board_no){
    this.board_no = board_no;
}


public Long getId(){
    return id;
}


public Date getCreated_date(){
    return created_date;
}


public String getCreatedDateYearMonth(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
    return sdf.format(getCreated_date());
}


public Integer getBoard_no(){
    return board_no;
}


public String getFile_name(){
    return file_name;
}


public String getUser_id(){
    return user_id;
}


public void setUser_id(String user_id){
    this.user_id = user_id;
}


public void setCategory(AttachmentCategory category){
    this.category = category;
}


public String getFilePath(){
    return String.format("/%s/%s/%s.%s", getCategory().name().toLowerCase(), getCreatedDateYearMonth(), getFile_name(), getExtension());
}


public void setId(Long id){
    this.id = id;
}


public void setCreated_date(Date created_date){
    this.created_date = created_date;
}


}