import javax.persistence;
@Entity
@Table(name = "tbl_slabs")
public class SlabMaster {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "slab_id")
 private  int slabId;

@Column(name = "sal_term_id")
 private  int salTermId;

@Column(name = "min_val")
 private  double minVal;

@Column(name = "max_val")
 private  double maxVal;

@Column(name = "amount")
 private  int amount;

@Column(name = "gender")
 private  int gender;

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


public void setMaxVal(double maxVal){
    this.maxVal = maxVal;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setGender(int gender){
    this.gender = gender;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public int getSalTermId(){
    return salTermId;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public int getSlabId(){
    return slabId;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setAmount(int amount){
    this.amount = amount;
}


public void setSlabId(int slabId){
    this.slabId = slabId;
}


public int getAmount(){
    return amount;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getGender(){
    return gender;
}


public void setSalTermId(int salTermId){
    this.salTermId = salTermId;
}


public double getMaxVal(){
    return maxVal;
}


public void setMinVal(double minVal){
    this.minVal = minVal;
}


public double getMinVal(){
    return minVal;
}


@Override
public String toString(){
    return "SlabMaster [slabId=" + slabId + ", salTermId=" + salTermId + ", minVal=" + minVal + ", maxVal=" + maxVal + ", amount=" + amount + ", gender=" + gender + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
}


}