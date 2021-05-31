import javax.persistence;
public class SlabMaster {

 private  int slabId;

 private  int salTermId;

 private  double minVal;

 private  double maxVal;

 private  int amount;

 private  int gender;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

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


public String getExVar1(){
    return exVar1;
}


public int getSalTermId(){
    return salTermId;
}


public int getSlabId(){
    return slabId;
}


public int getAmount(){
    return amount;
}


public int getGender(){
    return gender;
}


public double getMaxVal(){
    return maxVal;
}


public double getMinVal(){
    return minVal;
}


}