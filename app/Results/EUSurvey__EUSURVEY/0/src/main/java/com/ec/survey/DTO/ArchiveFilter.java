package com.ec.survey.DTO;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
public class ArchiveFilter {

 private  String uniqueId;

 private  String title;

 private  String alias;

 private  String owner;

 private  Date createdFrom;

 private  Date createdTo;

 private  Date archivedFrom;

 private  Date archivedTo;

 private  Boolean finished;

 private  String sortKey;

 private  String sortOrder;

 private  int userId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreatedFrom(){
    return createdFrom;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getArchivedFrom(){
    return archivedFrom;
}


public String getShortname(){
    return alias;
}


public String getSortKey(){
    return sortKey;
}


public String getOwner(){
    return owner;
}


public String getSortOrder(){
    return sortOrder;
}


public String getUniqueId(){
    return uniqueId;
}


public String getTitle(){
    return title;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getArchivedTo(){
    return archivedTo;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreatedTo(){
    return createdTo;
}


public Boolean getFinished(){
    return finished;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTitle(String title){
    this.title = title;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTitle"))

.queryParam("title",title)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatedFrom(Date createdFrom){
    this.createdFrom = createdFrom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatedFrom"))

.queryParam("createdFrom",createdFrom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatedTo(Date createdTo){
    this.createdTo = createdTo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatedTo"))

.queryParam("createdTo",createdTo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setArchivedFrom(Date archivedFrom){
    this.archivedFrom = archivedFrom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setArchivedFrom"))

.queryParam("archivedFrom",archivedFrom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setArchivedTo(Date archivedTo){
    this.archivedTo = archivedTo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setArchivedTo"))

.queryParam("archivedTo",archivedTo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSortKey"))

.queryParam("sortKey",sortKey)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSortOrder"))

.queryParam("sortOrder",sortOrder)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUniqueId"))

.queryParam("uniqueId",uniqueId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShortname(String alias){
    this.alias = alias;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShortname"))

.queryParam("alias",alias)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOwner(String owner){
    this.owner = owner;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOwner"))

.queryParam("owner",owner)
;
restTemplate.put(builder.toUriString(),null);
}


}