package lk.sliit.csse.procurementsystem.models;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
@Entity
@Table
@Data
public class ItemList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int id;

 private  long reqOrderNo;

 private  int itemId;

 private  int qty;


public int getRelavantItemId(String itemId){
    return (int) (Math.random() * 100);
}


}