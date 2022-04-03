package com.easyshopping;
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
public Order asc(String property){
    return new Order(property, Direction.asc);
}


public String getProperty(){
    return property;
}


public Direction getDirection(){
    return direction;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 37).append(getProperty()).append(getDirection()).toHashCode();
}


public void setProperty(String property){
    this.property = property;
}


@Override
public boolean equals(Object obj){
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    if (this == obj) {
        return true;
    }
    Order other = (Order) obj;
    return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getDirection(), other.getDirection()).isEquals();
}


public Direction fromString(String value){
    return Direction.valueOf(value.toLowerCase());
}


public void setDirection(Direction direction){
    this.direction = direction;
}


public Order desc(String property){
    return new Order(property, Direction.desc);
}


}