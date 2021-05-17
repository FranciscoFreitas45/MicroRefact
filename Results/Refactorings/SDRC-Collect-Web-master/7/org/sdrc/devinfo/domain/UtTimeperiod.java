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
@Entity
@Table(name = "ut_timeperiod")
@NamedQuery(name = "UtTimeperiod.findAll", query = "SELECT u FROM UtTimeperiod u")
public class UtTimeperiod implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int timePeriod_NId;

@Temporal(TemporalType.TIMESTAMP)
 private  Date endDate;

 private  String periodicity;

@Temporal(TemporalType.TIMESTAMP)
 private  Date startDate;

 private  String timePeriod;


public Date getStartDate(){
    return this.startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setStartDate"));

.queryParam("startDate",startDate);
restTemplate.put(builder.toUriString(),null);


public void setTimePeriod_NId(int timePeriod_NId){
    this.timePeriod_NId = timePeriod_NId;
}


public String getTimePeriod(){
    return this.timePeriod;
}


public void setPeriodicity(String periodicity){
    this.periodicity = periodicity;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setPeriodicity"));

.queryParam("periodicity",periodicity);
restTemplate.put(builder.toUriString(),null);


public Date getEndDate(){
    return this.endDate;
}


public void setTimePeriod(String timePeriod){
    this.timePeriod = timePeriod;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setTimePeriod"));

.queryParam("timePeriod",timePeriod);
restTemplate.put(builder.toUriString(),null);


public String getPeriodicity(){
    return this.periodicity;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ timePeriod_NId).concat("/setEndDate"));

.queryParam("endDate",endDate);
restTemplate.put(builder.toUriString(),null);


public int getTimePeriod_NId(){
    return this.timePeriod_NId;
}


}