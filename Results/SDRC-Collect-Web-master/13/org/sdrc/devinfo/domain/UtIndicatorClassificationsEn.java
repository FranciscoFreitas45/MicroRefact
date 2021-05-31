import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_indicator_classifications_en")
@NamedQuery(name = "UtIndicatorClassificationsEn.findAll", query = "SELECT u FROM UtIndicatorClassificationsEn u")
public class UtIndicatorClassificationsEn implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int IC_NId;

 private  String DIYear;

 private  String IC_GId;

 private  byte IC_Global;

@Lob
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


public void setSourceLink2(String sourceLink2){
    this.sourceLink2 = sourceLink2;
}


public void setSourceLink1(String sourceLink1){
    this.sourceLink1 = sourceLink1;
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


public void setIC_NId(int IC_NId){
    this.IC_NId = IC_NId;
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


public void setIC_Global(byte IC_Global){
    this.IC_Global = IC_Global;
}


public void setIC_Type(String IC_Type){
    this.IC_Type = IC_Type;
}


public int getIC_NId(){
    return this.IC_NId;
}


public void setDIYear(String DIYear){
    this.DIYear = DIYear;
}


public String getSourceLink1(){
    return this.sourceLink1;
}


public String getSourceLink2(){
    return this.sourceLink2;
}


public void setIC_Parent_NId(int IC_Parent_NId){
    this.IC_Parent_NId = IC_Parent_NId;
}


public void setNature(String nature){
    this.nature = nature;
}


public void setTitle(String title){
    this.title = title;
}


public void setIC_GId(String IC_GId){
    this.IC_GId = IC_GId;
}


public String getIsbn(){
    return this.isbn;
}


public void setIC_Info(String IC_Info){
    this.IC_Info = IC_Info;
}


public void setIC_Name(String IC_Name){
    this.IC_Name = IC_Name;
}


public void setIC_Order(int IC_Order){
    this.IC_Order = IC_Order;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getIC_Name(){
    return this.IC_Name;
}


public String getPublisher(){
    return this.publisher;
}


public void setIC_Short_Name(String IC_Short_Name){
    this.IC_Short_Name = IC_Short_Name;
}


public int getIC_Parent_NId(){
    return this.IC_Parent_NId;
}


public String getIC_Type(){
    return this.IC_Type;
}


public void setPublisher(String publisher){
    this.publisher = publisher;
}


public int getIC_Order(){
    return this.IC_Order;
}


}