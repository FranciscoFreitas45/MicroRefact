package switchtwentytwenty.project.DTO;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.InternalDate;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class RegistrationDate implements InternalDate,ValueObject{

 private  SimpleDateFormat dateFormat;

 private  Date date;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

// Constructor Methods
/**
 * Sole Constructor
 */
public RegistrationDate() {
    this.date = new Date(System.currentTimeMillis());
}
public Date getDate(){
    return new Date(this.date.getTime());
}


@Override
public boolean equals(Object other){
    if (this == other)
        return true;
    if (other == null || getClass() != other.getClass())
        return false;
    RegistrationDate that = (RegistrationDate) other;
    return Objects.equals(date, that.date);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("other",other)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setDate(Date dateToInput){
    this.date = new Date(dateToInput.getTime());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDate"))

.queryParam("dateToInput",dateToInput)
;
restTemplate.put(builder.toUriString(),null);
}


}