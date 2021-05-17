import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "tbl_advance_details")
public class AdvanceDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int advDetailId;

 private  int advId;

 private  int skipLoginId;

 private  Date skipLoginTime;

 private  String skipRemarks;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public void setAdvDetailId(int advDetailId){
    this.advDetailId = advDetailId;
}


// 
@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy hh:mm:ss a")
public Date getSkipLoginTime(){
    return skipLoginTime;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setSkipLoginTime(Date skipLoginTime){
    this.skipLoginTime = skipLoginTime;
}


public int getAdvDetailId(){
    return advDetailId;
}


public int getDelStatus(){
    return delStatus;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public int getSkipLoginId(){
    return skipLoginId;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setSkipRemarks(String skipRemarks){
    this.skipRemarks = skipRemarks;
}


@Override
public String toString(){
    return "AdvanceDetails [advDetailId=" + advDetailId + ", advId=" + advId + ", skipLoginId=" + skipLoginId + ", skipLoginTime=" + skipLoginTime + ", skipRemarks=" + skipRemarks + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public int getAdvId(){
    return advId;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setSkipLoginId(int skipLoginId){
    this.skipLoginId = skipLoginId;
}


public void setAdvId(int advId){
    this.advId = advId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public String getSkipRemarks(){
    return skipRemarks;
}


}