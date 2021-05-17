import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class UtTimeperiod implements Serializable{

 private  long serialVersionUID;

 private  int timePeriod_NId;

 private  Date endDate;

 private  String periodicity;

 private  Date startDate;

 private  String timePeriod;

 private RestTemplate restTemplate = new RestTemplate();


public Date getStartDate(){
    return this.startDate;
}


public String getTimePeriod(){
    return this.timePeriod;
}


public Date getEndDate(){
    return this.endDate;
}


public String getPeriodicity(){
    return this.periodicity;
}


public int getTimePeriod_NId(){
    return this.timePeriod_NId;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setStartDate"));

.queryParam("startDate",startDate);
restTemplate.put(builder.toUriString(),null);


public void setEndDate(Date endDate){
    this.endDate = endDate;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setEndDate"));

.queryParam("endDate",endDate);
restTemplate.put(builder.toUriString(),null);


public void setPeriodicity(String periodicity){
    this.periodicity = periodicity;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setPeriodicity"));

.queryParam("periodicity",periodicity);
restTemplate.put(builder.toUriString(),null);


public void setTimePeriod(String timePeriod){
    this.timePeriod = timePeriod;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setTimePeriod"));

.queryParam("timePeriod",timePeriod);
restTemplate.put(builder.toUriString(),null);


}