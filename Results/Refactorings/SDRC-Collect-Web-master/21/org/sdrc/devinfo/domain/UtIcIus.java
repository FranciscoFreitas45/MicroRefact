import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_ic_ius")
@NamedQuery(name = "UtIcIus.findAll", query = "SELECT u FROM UtIcIus u")
public class UtIcIus implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int IC_IUSNId;

 private  String IC_IUS_Label;

 private  Double IC_IUS_Order;

 private  int IC_NId;

 private  int IUSNId;

 private  byte recommendedSource;


public void setIC_IUS_Label(String IC_IUS_Label){
    this.IC_IUS_Label = IC_IUS_Label;
}


public int getIUSNId(){
    return this.IUSNId;
}


public void setIC_IUSNId(int IC_IUSNId){
    this.IC_IUSNId = IC_IUSNId;
}


public void setIC_NId(int IC_NId){
    this.IC_NId = IC_NId;
}


public int getIC_IUSNId(){
    return this.IC_IUSNId;
}


public Double getIC_IUS_Order(){
    return this.IC_IUS_Order;
}


public byte getRecommendedSource(){
    return this.recommendedSource;
}


public void setIC_IUS_Order(Double IC_IUS_Order){
    this.IC_IUS_Order = IC_IUS_Order;
}


public void setIUSNId(int IUSNId){
    this.IUSNId = IUSNId;
}


public void setRecommendedSource(byte recommendedSource){
    this.recommendedSource = recommendedSource;
}


public int getIC_NId(){
    return this.IC_NId;
}


public String getIC_IUS_Label(){
    return this.IC_IUS_Label;
}


}