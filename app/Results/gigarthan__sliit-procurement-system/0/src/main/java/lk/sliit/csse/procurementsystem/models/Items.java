package lk.sliit.csse.procurementsystem.models;
 import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence;
@Entity(name = "Items")
@Table(name = "items")
public class Items {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int itemId;

 private  String itemName;

 private  String categoryId;

 private  Float price;

public Items() {
}public Items(String itemName, int itemQty) {
}
public int getItemId(){
    return itemId;
}


public String getItemName(){
    return itemName;
}


public String getCategoryId(){
    return categoryId;
}


public void setCategoryId(String categoryId){
    this.categoryId = categoryId;
}


public void setItemId(int itemId){
    this.itemId = itemId;
}


public void setPrice(Float price){
    this.price = price;
}


public Float getPrice(){
    return price;
}


public void setItemName(String itemName){
    this.itemName = itemName;
}


}