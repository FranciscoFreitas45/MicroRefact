package io.delivery.entity;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence;
@Entity
@Table(name = "basket")
public class BasketUnit {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "unit_id")
 private  Long id;

@Version
 private  long version;

@Column(name = "item_id")
 private  Long itemId;

 private  int quantity;

@ManyToOne
@JsonBackReference
@JoinColumn(name = "order_id")
 private  Order order;

public BasketUnit() {
}public BasketUnit(Long itemId) {
    this.itemId = itemId;
}
public void setOrder(Order order){
    this.order = order;
}


public Long getItemId(){
    return itemId;
}


public int getQuantity(){
    return quantity;
}


public void setQuantity(int quantity){
    this.quantity = quantity;
}


public Order getOrder(){
    return order;
}


public Long getId(){
    return id;
}


}