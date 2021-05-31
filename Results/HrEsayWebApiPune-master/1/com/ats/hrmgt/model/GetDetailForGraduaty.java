import javax.persistence;
@Entity
public class GetDetailForGraduaty {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "uuid")
 private  String uuid;

 private  double basic;

 private  double allowanceValue;

 private  String cmpJoiningDate;

 private  String service;


public void setService(String service){
    this.service = service;
}


public void setBasic(double basic){
    this.basic = basic;
}


public String getCmpJoiningDate(){
    return cmpJoiningDate;
}


public void setAllowanceValue(double allowanceValue){
    this.allowanceValue = allowanceValue;
}


public String getUuid(){
    return uuid;
}


public double getBasic(){
    return basic;
}


public void setCmpJoiningDate(String cmpJoiningDate){
    this.cmpJoiningDate = cmpJoiningDate;
}


@Override
public String toString(){
    return "GetDetailForGraduaty [uuid=" + uuid + ", basic=" + basic + ", allowanceValue=" + allowanceValue + ", cmpJoiningDate=" + cmpJoiningDate + ", service=" + service + "]";
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public double getAllowanceValue(){
    return allowanceValue;
}


public String getService(){
    return service;
}


}