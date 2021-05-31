import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
public class UtAreaEn implements Serializable{

 private  long serialVersionUID;

 private  int area_NId;

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


public String getArea_Map(){
    return this.area_Map;
}


public int getArea_Level(){
    return this.area_Level;
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


public byte getData_Exist(){
    return this.data_Exist;
}


public byte getArea_Global(){
    return this.area_Global;
}


}