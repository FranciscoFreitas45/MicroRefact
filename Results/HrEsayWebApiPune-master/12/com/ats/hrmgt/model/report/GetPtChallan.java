import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetPtChallan {

@Id
 private  int slabId;

 private  int salTermId;

 private  String minVal;

 private  String maxVal;

 private  double amount;

 private  int gender;

 private  int locId;

 private  int count;

 private  String genderName;


public String getGenderName(){
    return genderName;
}


public void setMaxVal(String maxVal){
    this.maxVal = maxVal;
}


public int getLocId(){
    return locId;
}


public void setLocId(int locId){
    this.locId = locId;
}


public void setGenderName(String genderName){
    this.genderName = genderName;
}


public int getGender(){
    return gender;
}


public void setSalTermId(int salTermId){
    this.salTermId = salTermId;
}


public void setGender(int gender){
    this.gender = gender;
}


public int getSalTermId(){
    return salTermId;
}


public String getMaxVal(){
    return maxVal;
}


public void setMinVal(String minVal){
    this.minVal = minVal;
}


public String getMinVal(){
    return minVal;
}


public int getSlabId(){
    return slabId;
}


@Override
public String toString(){
    return "GetPtChallan [slabId=" + slabId + ", salTermId=" + salTermId + ", minVal=" + minVal + ", maxVal=" + maxVal + ", amount=" + amount + ", gender=" + gender + ", locId=" + locId + ", count=" + count + ", genderName=" + genderName + "]";
}


public void setAmount(double amount){
    this.amount = amount;
}


public int getCount(){
    return count;
}


public void setSlabId(int slabId){
    this.slabId = slabId;
}


public void setCount(int count){
    this.count = count;
}


public double getAmount(){
    return amount;
}


}