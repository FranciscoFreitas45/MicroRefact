import javax.persistence;
@Entity
public class GetDetailForBonus {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

 private  int empId;

 private  float presentdays;

 private  float weeklyoff;

 private  float holiday;

 private  float bonusPer;

 private  double basic;

 private  double allowanceValue;


public float getHoliday(){
    return holiday;
}


public void setAllowanceValue(double allowanceValue){
    this.allowanceValue = allowanceValue;
}


public void setHoliday(float holiday){
    this.holiday = holiday;
}


public float getPresentdays(){
    return presentdays;
}


public float getBonusPer(){
    return bonusPer;
}


public double getBasic(){
    return basic;
}


public int getId(){
    return id;
}


public int getEmpId(){
    return empId;
}


public double getAllowanceValue(){
    return allowanceValue;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setPresentdays(float presentdays){
    this.presentdays = presentdays;
}


public float getWeeklyoff(){
    return weeklyoff;
}


public void setWeeklyoff(float weeklyoff){
    this.weeklyoff = weeklyoff;
}


public void setBasic(double basic){
    this.basic = basic;
}


public void setBonusPer(float bonusPer){
    this.bonusPer = bonusPer;
}


public void setId(int id){
    this.id = id;
}


@Override
public String toString(){
    return "GetDetailForBonus [id=" + id + ", empId=" + empId + ", presentdays=" + presentdays + ", weeklyoff=" + weeklyoff + ", holiday=" + holiday + ", bonusPer=" + bonusPer + ", basic=" + basic + ", allowanceValue=" + allowanceValue + "]";
}


}