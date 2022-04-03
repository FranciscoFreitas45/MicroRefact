package DTO;
 import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
public class DocumentValidStatus {

 private  boolean VALID_Yes;

 private  boolean VALID_No;

 private  Boolean INITIALVALUE_Yes;

 private  Boolean INITIALVALUE_No;

 private  Boolean INITIALVALUE_Unknown;

 private  String REASON_Null;

 private  String FIELDNAME_Null;

 private  DocumentValidStatus STATE_InitialInvalid;

 private  DocumentValidStatus STATE_Valid;

 private  DocumentValidStatus STATE_InvalidIncludedDocument;

 private  boolean valid;

 private  Boolean initialValue;

 private  String reason;

 private  String fieldName;

 private  Integer _hashcode;

 private  String _toString;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getReason(){
    return reason;
}


public boolean isValid(){
    return valid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isValid"))

boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}