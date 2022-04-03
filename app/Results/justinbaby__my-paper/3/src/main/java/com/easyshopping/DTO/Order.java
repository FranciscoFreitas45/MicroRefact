package com.easyshopping.DTO;
 import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Order implements Serializable{

 private  long serialVersionUID;

 private  Direction DEFAULT_DIRECTION;

 private  String property;

 private  Direction direction;

/**
 * 初始化一个新创建的Order对象
 */
public Order() {
}/**
 * @param property
 *            属性
 * @param direction
 *            方向
 */
public Order(String property, Direction direction) {
    this.property = property;
    this.direction = direction;
}
public String getProperty(){
    return property;
}


public Direction getDirection(){
    return direction;
}


}