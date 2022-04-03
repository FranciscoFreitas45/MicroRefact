package lk.sliit.csse.procurementsystem.models;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class SupplyMaterial {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long itemId;

 private  int supId;

 private  String name;

 private  String category;

 private  String description;

 private  String price;


public void setSupId(int supId){
    this.supId = supId;
}


public void setName(String name){
    this.name = name;
}


public long getItemId(){
    return itemId;
}


public String getName(){
    return name;
}


public void setCategory(String category){
    this.category = category;
}


public String getCategory(){
    return category;
}


public int getSupId(){
    return supId;
}


public void setItemId(long itemId){
    this.itemId = itemId;
}


public void setDescription(String description){
    this.description = description;
}


public void setPrice(String price){
    this.price = price;
}


public String getDescription(){
    return description;
}


public String getPrice(){
    return price;
}


}