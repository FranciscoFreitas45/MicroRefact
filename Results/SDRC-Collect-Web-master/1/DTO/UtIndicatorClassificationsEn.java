import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
public class UtIndicatorClassificationsEn implements Serializable{

 private  long serialVersionUID;

 private  int IC_NId;

 private  String DIYear;

 private  String IC_GId;

 private  byte IC_Global;

 private  String IC_Info;

 private  String IC_Name;

 private  int IC_Order;

 private  int IC_Parent_NId;

 private  String IC_Short_Name;

 private  String IC_Type;

 private  String isbn;

 private  String nature;

 private  String publisher;

 private  String sourceLink1;

 private  String sourceLink2;

 private  String title;


public String getNature(){
    return this.nature;
}


public String getIC_Short_Name(){
    return this.IC_Short_Name;
}


public String getIC_Info(){
    return this.IC_Info;
}


public String getDIYear(){
    return this.DIYear;
}


public String getIC_GId(){
    return this.IC_GId;
}


public String getTitle(){
    return this.title;
}


public byte getIC_Global(){
    return this.IC_Global;
}


public int getIC_NId(){
    return this.IC_NId;
}


public String getSourceLink1(){
    return this.sourceLink1;
}


public String getSourceLink2(){
    return this.sourceLink2;
}


public String getIsbn(){
    return this.isbn;
}


public String getIC_Name(){
    return this.IC_Name;
}


public String getPublisher(){
    return this.publisher;
}


public int getIC_Parent_NId(){
    return this.IC_Parent_NId;
}


public String getIC_Type(){
    return this.IC_Type;
}


public int getIC_Order(){
    return this.IC_Order;
}


}