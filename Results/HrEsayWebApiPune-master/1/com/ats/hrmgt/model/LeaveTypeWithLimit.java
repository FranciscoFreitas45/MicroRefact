import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LeaveTypeWithLimit {

@Id
 private  int lvTypeId;

 private  int lvsId;

 private  String lvTitleShort;

 private  int maxNoDays;

 private  int minNoDays;


public void setMaxNoDays(int maxNoDays){
    this.maxNoDays = maxNoDays;
}


public int getMaxNoDays(){
    return maxNoDays;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public int getMinNoDays(){
    return minNoDays;
}


public void setLvsId(int lvsId){
    this.lvsId = lvsId;
}


public void setLvTitleShort(String lvTitleShort){
    this.lvTitleShort = lvTitleShort;
}


public int getLvTypeId(){
    return lvTypeId;
}


public int getLvsId(){
    return lvsId;
}


public String getLvTitleShort(){
    return lvTitleShort;
}


@Override
public String toString(){
    return "LeaveTypeWithLimit [lvTypeId=" + lvTypeId + ", lvsId=" + lvsId + ", lvTitleShort=" + lvTitleShort + ", maxNoDays=" + maxNoDays + ", minNoDays=" + minNoDays + "]";
}


public void setMinNoDays(int minNoDays){
    this.minNoDays = minNoDays;
}


}