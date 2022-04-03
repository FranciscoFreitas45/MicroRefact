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


public void setFinished(Boolean finished){
    this.finished = finished;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFinished"))

.queryParam("finished",finished)
;
restTemplate.put(builder.toUriString(),null);
}


}