package com.lingxiang2014.entity;
 import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
@MappedSuperclass
public class OrderEntity extends BaseEntityimplements Comparable<OrderEntity>{

 private  long serialVersionUID;

 public  String ORDER_PROPERTY_NAME;

 private  Integer order;


public void setOrder(Integer order){
    this.order = order;
}


@JsonProperty
@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@Min(0)
@Column(name = "orders")
public Integer getOrder(){
    return order;
}


public int compareTo(OrderEntity orderEntity){
    return new CompareToBuilder().append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
}


}