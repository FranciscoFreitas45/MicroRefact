import javax.persistence;
@Entity
@Table(name = "tbl_pay_bonus")
public class PayBonus {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int payTypeId;

 private  String typeName;

 private  double payRate;

 private  int isUsed;

 private  int delStatus;

 private  String enterMakerDatetime;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

@Transient
 private  boolean error;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public String getEnterMakerDatetime(){
    return enterMakerDatetime;
}


public String getExVar1(){
    return exVar1;
}


public void setPayRate(double payRate){
    this.payRate = payRate;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setEnterMakerDatetime(String enterMakerDatetime){
    this.enterMakerDatetime = enterMakerDatetime;
}


public double getPayRate(){
    return payRate;
}


public int getPayTypeId(){
    return payTypeId;
}


public void setPayTypeId(int payTypeId){
    this.payTypeId = payTypeId;
}


public boolean isError(){
    return error;
}


public int getDelStatus(){
    return delStatus;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setError(boolean error){
    this.error = error;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


@Override
public String toString(){
    return "PayBonus [payTypeId=" + payTypeId + ", typeName=" + typeName + ", payRate=" + payRate + ", isUsed=" + isUsed + ", delStatus=" + delStatus + ", enterMakerDatetime=" + enterMakerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public String getTypeName(){
    return typeName;
}


public int getIsUsed(){
    return isUsed;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsUsed(int isUsed){
    this.isUsed = isUsed;
}


}