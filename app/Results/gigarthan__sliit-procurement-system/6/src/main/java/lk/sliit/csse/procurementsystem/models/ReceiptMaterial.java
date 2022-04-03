package lk.sliit.csse.procurementsystem.models;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ReceiptMaterial {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

 private  long receiptId;

 private  String itemName;

 private  int qty;


public long getReceiptId(){
    return receiptId;
}


public String getItemName(){
    return itemName;
}


public void setQty(int qty){
    this.qty = qty;
}


public int getQty(){
    return qty;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setReceiptId(long receiptId){
    this.receiptId = receiptId;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


}