import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PreDayAttnDash {

@Id
 private  String uniKey;

 private  int preEmp;

 private  int absentEmp;

 private  int woEmp;

 private  int lvEmp;

 private  String attnDate;


public int getAbsentEmp(){
    return absentEmp;
}


public void setAbsentEmp(int absentEmp){
    this.absentEmp = absentEmp;
}


public void setWoEmp(int woEmp){
    this.woEmp = woEmp;
}


public void setUniKey(String uniKey){
    this.uniKey = uniKey;
}


public int getWoEmp(){
    return woEmp;
}


public void setAttnDate(String attnDate){
    this.attnDate = attnDate;
}


public void setPreEmp(int preEmp){
    this.preEmp = preEmp;
}


public void setLvEmp(int lvEmp){
    this.lvEmp = lvEmp;
}


public int getLvEmp(){
    return lvEmp;
}


public String getAttnDate(){
    return attnDate;
}


public int getPreEmp(){
    return preEmp;
}


public String getUniKey(){
    return uniKey;
}


@Override
public String toString(){
    return "PreDayAttnDash [uniKey=" + uniKey + ", preEmp=" + preEmp + ", absentEmp=" + absentEmp + ", woEmp=" + woEmp + ", lvEmp=" + lvEmp + ", attnDate=" + attnDate + "]";
}


}