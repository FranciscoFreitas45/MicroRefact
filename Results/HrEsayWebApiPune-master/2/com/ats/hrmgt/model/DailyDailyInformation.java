import javax.persistence;
@Entity
public class DailyDailyInformation {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "uuid")
 private  String uuid;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "daycount")
 private  int daycount;

@Column(name = "lv_sumup_id")
 private  int lvSumupId;

@Column(name = "name_sd")
 private  String nameSd;

@Column(name = "working_min")
 private  int workingMin;

@Column(name = "ot_min")
 private  int otMin;

@Column(name = "late_mark")
 private  int lateMark;

@Column(name = "late_min")
 private  int lateMin;

@Column(name = "full_night")
 private  int fullNight;

@Column(name = "mark_compoff")
 private  int markCompoff;

@Column(name = "sal_basis")
 private  String salBasis;


public void setWorkingMin(int workingMin){
    this.workingMin = workingMin;
}


public int getDaycount(){
    return daycount;
}


public int getLateMin(){
    return lateMin;
}


public void setFullNight(int fullNight){
    this.fullNight = fullNight;
}


public void setDaycount(int daycount){
    this.daycount = daycount;
}


public void setSalBasis(String salBasis){
    this.salBasis = salBasis;
}


public void setOtMin(int otMin){
    this.otMin = otMin;
}


public int getFullNight(){
    return fullNight;
}


public void setMarkCompoff(int markCompoff){
    this.markCompoff = markCompoff;
}


public void setLvSumupId(int lvSumupId){
    this.lvSumupId = lvSumupId;
}


public int getOtMin(){
    return otMin;
}


public int getLvSumupId(){
    return lvSumupId;
}


public String getNameSd(){
    return nameSd;
}


public void setLateMark(int lateMark){
    this.lateMark = lateMark;
}


public int getWorkingMin(){
    return workingMin;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getSalBasis(){
    return salBasis;
}


public int getLateMark(){
    return lateMark;
}


public void setNameSd(String nameSd){
    this.nameSd = nameSd;
}


public String getUuid(){
    return uuid;
}


@Override
public String toString(){
    return "DailyDailyInformation [uuid=" + uuid + ", empId=" + empId + ", daycount=" + daycount + ", lvSumupId=" + lvSumupId + ", nameSd=" + nameSd + ", workingMin=" + workingMin + ", otMin=" + otMin + ", lateMark=" + lateMark + ", lateMin=" + lateMin + ", fullNight=" + fullNight + ", markCompoff=" + markCompoff + ", salBasis=" + salBasis + "]";
}


public int getMarkCompoff(){
    return markCompoff;
}


public void setLateMin(int lateMin){
    this.lateMin = lateMin;
}


}