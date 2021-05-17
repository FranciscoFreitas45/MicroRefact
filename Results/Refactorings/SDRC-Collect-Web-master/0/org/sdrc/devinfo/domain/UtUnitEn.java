import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_unit_en")
@NamedQuery(name = "UtUnitEn.findAll", query = "SELECT u FROM UtUnitEn u")
public class UtUnitEn implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int unit_NId;

 private  String unit_GId;

 private  byte unit_Global;

 private  String unit_Name;


public String getUnit_GId(){
    return this.unit_GId;
}


public void setUnit_NId(int unit_NId){
    this.unit_NId = unit_NId;
}


public String getUnit_Name(){
    return this.unit_Name;
}


public int getUnit_NId(){
    return this.unit_NId;
}


public byte getUnit_Global(){
    return this.unit_Global;
}


public void setUnit_Global(byte unit_Global){
    this.unit_Global = unit_Global;
}


public void setUnit_GId(String unit_GId){
    this.unit_GId = unit_GId;
}


public void setUnit_Name(String unit_Name){
    this.unit_Name = unit_Name;
}


}