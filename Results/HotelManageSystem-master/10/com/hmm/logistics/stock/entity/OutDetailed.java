import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "t_OutDetailed")
public class OutDetailed {

 private  Long id;

 private  String goodsName;

 private  String goodsNo;

 private  float amount;

 private  OutStorage outStorage;


public void setGoodsNo(String goodsNo){
    this.goodsNo = goodsNo;
}


public String getGoodsName(){
    return goodsName;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


@ManyToOne
public OutStorage getOutStorage(){
    return outStorage;
}


public String getGoodsNo(){
    return goodsNo;
}


public void setId(Long id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public void setOutStorage(OutStorage outStorage){
    this.outStorage = outStorage;
}


public void setAmount(float amount){
    this.amount = amount;
}


public float getAmount(){
    return amount;
}


}