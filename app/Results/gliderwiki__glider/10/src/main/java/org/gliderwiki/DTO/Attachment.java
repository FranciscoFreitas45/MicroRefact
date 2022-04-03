package org.gliderwiki.DTO;
 import java.util.Date;
import java.text.SimpleDateFormat;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class Attachment extends BaseObjectBean{

 private  long serialVersionUID;

 private  Long id;

 private  AttachmentCategory category;

 private  String user_id;

 private  Integer board_no;

 private  String extension;

 private  String file_name;

 private  Date created_date;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";

public Attachment() {
}
public String getExtension(){
    return extension;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public AttachmentCategory getCategory(){
    return category;
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


public String getFilePath(){
    return String.format("/%s/%s/%s.%s", getCategory().name().toLowerCase(), getCreatedDateYearMonth(), getFile_name(), getExtension());
}


public void setCategory(AttachmentCategory category){
    this.category = category;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCategory"))

.queryParam("category",category)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUser_id(String user_id){
    this.user_id = user_id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser_id"))

.queryParam("user_id",user_id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBoard_no(Integer board_no){
    this.board_no = board_no;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBoard_no"))

.queryParam("board_no",board_no)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFile_name(String file_name){
    this.file_name = file_name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFile_name"))

.queryParam("file_name",file_name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExtension(String extension){
    this.extension = extension;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExtension"))

.queryParam("extension",extension)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreated_date(Date created_date){
    this.created_date = created_date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreated_date"))

.queryParam("created_date",created_date)
;
restTemplate.put(builder.toUriString(),null);
}


}