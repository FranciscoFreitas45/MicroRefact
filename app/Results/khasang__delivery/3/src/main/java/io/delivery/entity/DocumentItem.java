package io.delivery.entity;
 import javax.persistence;
import java.math.BigDecimal;
@Entity
@Table(name = "document_items")
public class DocumentItem {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

 private  String name;

 private  BigDecimal price;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setPrice(BigDecimal price){
    this.price = price;
}


public BigDecimal getPrice(){
    return price;
}


}