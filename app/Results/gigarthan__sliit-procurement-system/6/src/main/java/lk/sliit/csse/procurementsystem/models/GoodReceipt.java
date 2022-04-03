package lk.sliit.csse.procurementsystem.models;
 import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class GoodReceipt {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long receiptId;

 private  long orderRefer;

 private  String deliverDate;

 private  String receiptStatus;


public long getOrderRefer(){
    return orderRefer;
}


public String getReceiptStatus(){
    return receiptStatus;
}


public long getReceiptId(){
    return receiptId;
}


public String getDeliverDate(){
    return deliverDate;
}


public void setReceiptStatus(String receiptStatus){
    this.receiptStatus = receiptStatus;
}


public void setDeliverDate(String deliverDate){
    this.deliverDate = deliverDate;
}


public void setOrderRefer(long orderRefer){
    this.orderRefer = orderRefer;
}


public void setReceiptId(long receiptId){
    this.receiptId = receiptId;
}


}