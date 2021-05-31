import javax.persistence;
@Entity
@Table(name = "tbl_pay_deduction")
public class PayDeduction {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ded_type_id")
 private  int dedTypeId;

@Column(name = "type_name")
 private  String typeName;

@Column(name = "ded_rate")
 private  double dedRate;

@Column(name = "is_used")
 private  int isUsed;

@Column(name = "enter_maker_datetime")
 private  String enterMakerDatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Column(name = "del_status")
 private  int delStatus;


public String getExVar2(){
    return exVar2;
}


public int getDedTypeId(){
    return dedTypeId;
}


public void setDedTypeId(int dedTypeId){
    this.dedTypeId = dedTypeId;
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


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setDedRate(double dedRate){
    this.dedRate = dedRate;
}


public void setEnterMakerDatetime(String enterMakerDatetime){
    this.enterMakerDatetime = enterMakerDatetime;
}


public double getDedRate(){
    return dedRate;
}


public int getDelStatus(){
    return delStatus;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


@Override
public String toString(){
    return "PayDeduction [dedTypeId=" + dedTypeId + ", typeName=" + typeName + ", dedRate=" + dedRate + ", isUsed=" + isUsed + ", enterMakerDatetime=" + enterMakerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", delStatus=" + delStatus + "]";
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