import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AgeDiversityDash {

@Id
 private  String uniKey;

 private  int ageRange1;

 private  int ageRange2;

 private  int ageRange3;

 private  int ageRange4;

 private  int ageRange5;

 private  int ageRange6;

 private  int ageRange7;

 private  int ageRange8;

 private  int ageRange9;

 private  int ageRange10;

 private  int ageRange11;


public int getAgeRange5(){
    return ageRange5;
}


public int getAgeRange4(){
    return ageRange4;
}


public int getAgeRange7(){
    return ageRange7;
}


public int getAgeRange6(){
    return ageRange6;
}


public int getAgeRange9(){
    return ageRange9;
}


public void setAgeRange10(int ageRange10){
    this.ageRange10 = ageRange10;
}


public int getAgeRange8(){
    return ageRange8;
}


public void setAgeRange5(int ageRange5){
    this.ageRange5 = ageRange5;
}


public void setAgeRange4(int ageRange4){
    this.ageRange4 = ageRange4;
}


public void setAgeRange7(int ageRange7){
    this.ageRange7 = ageRange7;
}


public void setUniKey(String uniKey){
    this.uniKey = uniKey;
}


public void setAgeRange6(int ageRange6){
    this.ageRange6 = ageRange6;
}


public int getAgeRange11(){
    return ageRange11;
}


public void setAgeRange1(int ageRange1){
    this.ageRange1 = ageRange1;
}


public int getAgeRange10(){
    return ageRange10;
}


public void setAgeRange3(int ageRange3){
    this.ageRange3 = ageRange3;
}


public void setAgeRange2(int ageRange2){
    this.ageRange2 = ageRange2;
}


public void setAgeRange9(int ageRange9){
    this.ageRange9 = ageRange9;
}


public void setAgeRange8(int ageRange8){
    this.ageRange8 = ageRange8;
}


public String getUniKey(){
    return uniKey;
}


@Override
public String toString(){
    return "AgeDiversityDash [uniKey=" + uniKey + ", ageRange1=" + ageRange1 + ", ageRange2=" + ageRange2 + ", ageRange3=" + ageRange3 + ", ageRange4=" + ageRange4 + ", ageRange5=" + ageRange5 + ", ageRange6=" + ageRange6 + ", ageRange7=" + ageRange7 + ", ageRange8=" + ageRange8 + ", ageRange9=" + ageRange9 + ", ageRange10=" + ageRange10 + ", ageRange11=" + ageRange11 + "]";
}


public void setAgeRange11(int ageRange11){
    this.ageRange11 = ageRange11;
}


public int getAgeRange1(){
    return ageRange1;
}


public int getAgeRange3(){
    return ageRange3;
}


public int getAgeRange2(){
    return ageRange2;
}


}