package DTO;
 import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import lombok.Builder;
public class DocumentSaveStatus {

 private  DocumentSaveStatus STATUS_Unknown;

 private  DocumentSaveStatus STATUS_Saved;

 private  DocumentSaveStatus STATUS_Deleted;

 private  DocumentSaveStatus STATUS_NotSavedJustCreated;

 private  DocumentSaveStatus STATUS_SavedJustLoaded;

 private  boolean saved;

 private  boolean hasChangesToBeSaved;

 private  boolean error;

 private  String reason;

 private  boolean deleted;

 private  Integer _hashcode;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getReason(){
    return reason;
}


public boolean isSavedOrDeleted(){
    return isSaved() || isDeleted();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSavedOrDeleted"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}