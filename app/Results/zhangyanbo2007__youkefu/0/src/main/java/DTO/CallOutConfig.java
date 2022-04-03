package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class CallOutConfig {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  String dataid;

 private  Date createtime;

 private  Date updatetime;

 private  String username;

 private  boolean enablecallout;

 private  int countdown;

 private  boolean enabletagentthreads;

 private  int agentthreads;

 private  boolean enabletaithreads;

 private  int aithreads;

 private  boolean enablefthreads;

 private  int fthreads;

 private  boolean enableauto;

 private  boolean forecast;

 private  int forecastratio;

 private  int fmaxavgtime;

 private  int fminavgtime;

 private  int favgaftertime;

 private  String defaultvalue;

 private  String strategy;

 private  boolean previewautocallout;

 private  boolean appointment;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getStrategy(){
    return strategy;
}


public String getName(){
    return name;
}


public int getAgentthreads(){
    return agentthreads;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getDefaultvalue(){
    return defaultvalue;
}


public int getFthreads(){
    return fthreads;
}


public int getForecastratio(){
    return forecastratio;
}


public String getType(){
    return type;
}


public int getFminavgtime(){
    return fminavgtime;
}


public int getAithreads(){
    return aithreads;
}


public int getFmaxavgtime(){
    return fmaxavgtime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getCreater(){
    return creater;
}


public int getFavgaftertime(){
    return favgaftertime;
}


public String getDataid(){
    return dataid;
}


public String getOrgi(){
    return orgi;
}


public int getCountdown(){
    return countdown;
}


public boolean isForecast(){
    return forecast;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isForecast"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}