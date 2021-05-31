import javax.persistence;
@Entity
public class PayableDayAndPresentDays {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "uuid")
 private  String uuid;

@Column(name = "payable_days")
 private  double payableDays;

@Column(name = "present_days")
 private  double presentDays;

@Column(name = "pl_calc_base")
 private  int plCalcBase;

@Transient
 private  boolean isError;


public double getPresentDays(){
    return presentDays;
}


public void setPayableDays(double payableDays){
    this.payableDays = payableDays;
}


public void setPresentDays(double presentDays){
    this.presentDays = presentDays;
}


public int getPlCalcBase(){
    return plCalcBase;
}


public double getPayableDays(){
    return payableDays;
}


public boolean isError(){
    return isError;
}


public void setError(boolean isError){
    this.isError = isError;
}


public String getUuid(){
    return uuid;
}


public void setPlCalcBase(int plCalcBase){
    this.plCalcBase = plCalcBase;
}


@Override
public String toString(){
    return "PayableDayAndPresentDate [uuid=" + uuid + ", payableDays=" + payableDays + ", presentDays=" + presentDays + ", plCalcBase=" + plCalcBase + ", isError=" + isError + "]";
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


}