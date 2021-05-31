import javax.persistence;
@Entity
@Table(name = "dm_cal_year")
public class CalenderYear {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "cal_yr_id")
 private  int calYrId;

@Column(name = "cal_yr_from_date")
 private  String calYrFromDate;

@Column(name = "cal_yr_to_date")
 private  String calYrToDate;

@Column(name = "is_current")
 private  int isCurrent;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_int3")
 private  int exInt3;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Column(name = "ex_var3")
 private  String exVar3;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getExInt1(){
    return exInt1;
}


public String getCalYrToDate(){
    return calYrToDate;
}


public String getExVar1(){
    return exVar1;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setCalYrFromDate(String calYrFromDate){
    this.calYrFromDate = calYrFromDate;
}


public void setCalYrToDate(String calYrToDate){
    this.calYrToDate = calYrToDate;
}


public String getCalYrFromDate(){
    return calYrFromDate;
}


public int getIsCurrent(){
    return isCurrent;
}


public void setIsCurrent(int isCurrent){
    this.isCurrent = isCurrent;
}


public int getCalYrId(){
    return calYrId;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


@Override
public String toString(){
    return "CalculateYear [calYrId=" + calYrId + ", calYrFromDate=" + calYrFromDate + ", calYrToDate=" + calYrToDate + ", isCurrent=" + isCurrent + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


}