package DTO;
 import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.ukefu.util.UCKeFuTime;
import com.ukefu.webim.web.model.Extention;
import Interface.Extention;
public class CallCenterAgent implements Comparable<CallCenterAgent>{

 private  long serialVersionUID;

 private  String userid;

 private  String username;

 private  String organ;

 private  String extno;

 private  boolean ready;

 private  String orgi;

 private  Date updatetime;

 private  String status;

 private  String workstatus;

 private  String siptrunk;

 private  String skill;

 private  String forecastvalue;

 private  String phonenum;

 private  String eventid;

 private  Extention extention;

 private  String nameid;

 private  String agent;

 private  long hanguptime;

 private  String dataid;

 private  String metaid;

 private  boolean forecast;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public CallCenterAgent(String userid, String extno, String orgi) {
    this.userid = userid;
    this.extno = extno;
    this.orgi = orgi;
}
public Extention getExtention(){
    return extention;
}


public String getPhonenum(){
    return phonenum;
}


public String getTime(){
    String formattime = null;
    if (updatetime != null) {
        long time = System.currentTimeMillis() - this.updatetime.getTime();
        formattime = new UCKeFuTime(0, 0, (int) time / 1000).toString();
    }
    return formattime;
}


public String getExtno(){
    return extno;
}


public String getNameid(){
    return nameid;
}


public String getMetaid(){
    return metaid;
}


public String getStatus(){
    return status;
}


public String getUsername(){
    return username;
}


public String getUserid(){
    return userid;
}


public String getForecastvalue(){
    return forecastvalue;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getWorkstatus(){
    return workstatus;
}


public String getAgent(){
    return agent;
}


public String getDataid(){
    return dataid;
}


public String getOrgan(){
    return organ;
}


public String getSiptrunk(){
    return siptrunk;
}


public String getOrgi(){
    return orgi;
}


public String getEventid(){
    return eventid;
}


public String getSkill(){
    return skill;
}


public long getHanguptime(){
    return hanguptime;
}


public void setSiptrunk(String siptrunk){
    this.siptrunk = siptrunk;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSiptrunk"))

.queryParam("siptrunk",siptrunk)
;
restTemplate.put(builder.toUriString(),null);
}


}