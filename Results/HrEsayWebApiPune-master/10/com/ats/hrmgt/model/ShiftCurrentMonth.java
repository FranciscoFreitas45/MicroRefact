import javax.persistence;
@Entity
@Table(name = "t_shift_current_month")
public class ShiftCurrentMonth {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "loc_id")
 private  int locId;

@Column(name = "date")
 private  String date;

@Column(name = "last_updated_by ")
 private  int lastUpdatedBy;

@Column(name = "last_update_datetime")
 private  String lastUpdateDatetime;

@Column(name = "is_current")
 private  int isCurrent;

@Column(name = "extra_int1")
 private  int extraInt1;

@Column(name = "extra_int2")
 private  int extraInt2;

@Column(name = "extra_varchar1")
 private  String extraVarchar1;


public int getLocId(){
    return locId;
}


public String getExtraVarchar1(){
    return extraVarchar1;
}


public void setLocId(int locId){
    this.locId = locId;
}


public void setLastUpdatedBy(int lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public int getId(){
    return id;
}


public void setLastUpdateDatetime(String lastUpdateDatetime){
    this.lastUpdateDatetime = lastUpdateDatetime;
}


public int getExtraInt2(){
    return extraInt2;
}


public void setExtraInt2(int extraInt2){
    this.extraInt2 = extraInt2;
}


public int getIsCurrent(){
    return isCurrent;
}


public void setIsCurrent(int isCurrent){
    this.isCurrent = isCurrent;
}


public int getExtraInt1(){
    return extraInt1;
}


public void setExtraInt1(int extraInt1){
    this.extraInt1 = extraInt1;
}


public int getCompanyId(){
    return companyId;
}


public String getLastUpdateDatetime(){
    return lastUpdateDatetime;
}


public void setId(int id){
    this.id = id;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "ShiftCurrentMonth [id=" + id + ", companyId=" + companyId + ", locId=" + locId + ", date=" + date + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDatetime=" + lastUpdateDatetime + ", isCurrent=" + isCurrent + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2 + ", extraVarchar1=" + extraVarchar1 + "]";
}


public int getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setExtraVarchar1(String extraVarchar1){
    this.extraVarchar1 = extraVarchar1;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


}