package com.ec.survey.model;
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


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreatedFrom(){
    return createdFrom;
}


public void setArchivedFrom(Date archivedFrom){
    this.archivedFrom = archivedFrom;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getArchivedFrom(){
    return archivedFrom;
}


public String getShortname(){
    return alias;
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreatedTo(Date createdTo){
    this.createdTo = createdTo;
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
}


public String getSortKey(){
    return sortKey;
}


public String getOwner(){
    return owner;
}


public void setShortname(String alias){
    this.alias = alias;
}


public void setOwner(String owner){
    this.owner = owner;
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
}


public String getSortOrder(){
    return sortOrder;
}


public String getUniqueId(){
    return uniqueId;
}


public void setCreatedFrom(Date createdFrom){
    this.createdFrom = createdFrom;
}


public String getTitle(){
    return title;
}


@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getArchivedTo(){
    return archivedTo;
}


public void setArchivedTo(Date archivedTo){
    this.archivedTo = archivedTo;
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


public void setFinished(Boolean finished){
    this.finished = finished;
}


public void setUserId(int userId){
    this.userId = userId;
}


}