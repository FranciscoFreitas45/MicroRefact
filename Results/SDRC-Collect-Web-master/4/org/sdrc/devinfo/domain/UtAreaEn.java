import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_area_en")
@NamedQuery(name = "UtAreaEn.findAll", query = "SELECT u FROM UtAreaEn u")
public class UtAreaEn implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int area_NId;

@Lob
 private  String area_Block;

 private  String area_GId;

 private  byte area_Global;

 private  String area_ID;

 private  int area_Level;

 private  String area_Map;

 private  String area_Name;

 private  int area_Parent_NId;

 private  String areaShortName;

 private  byte data_Exist;


public int getArea_NId(){
    return this.area_NId;
}


public String getArea_Block(){
    return this.area_Block;
}


public String getArea_GId(){
    return this.area_GId;
}


public void setArea_Block(String area_Block){
    this.area_Block = area_Block;
}


public void setArea_ID(String area_ID){
    this.area_ID = area_ID;
}


public void setArea_Map(String area_Map){
    this.area_Map = area_Map;
}


public void setArea_Name(String area_Name){
    this.area_Name = area_Name;
}


public void setAreaShortName(String areaShortName){
    this.areaShortName = areaShortName;
}


public void setData_Exist(byte data_Exist){
    this.data_Exist = data_Exist;
}


public String getArea_Map(){
    return this.area_Map;
}


public void setArea_Global(byte area_Global){
    this.area_Global = area_Global;
}


public int getArea_Level(){
    return this.area_Level;
}


public void setArea_Level(int area_Level){
    this.area_Level = area_Level;
}


public void setArea_GId(String area_GId){
    this.area_GId = area_GId;
}


public String getArea_ID(){
    return this.area_ID;
}


public int getArea_Parent_NId(){
    return this.area_Parent_NId;
}


public String getAreaShortName(){
    return this.areaShortName;
}


public String getArea_Name(){
    return this.area_Name;
}


public void setArea_Parent_NId(int area_Parent_NId){
    this.area_Parent_NId = area_Parent_NId;
}


public byte getData_Exist(){
    return this.data_Exist;
}


public byte getArea_Global(){
    return this.area_Global;
}


public void setArea_NId(int area_NId){
    this.area_NId = area_NId;
}


}