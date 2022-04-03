package com.easyshopping;
 import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Filter implements Serializable{

 private  long serialVersionUID;

 private  boolean DEFAULT_IGNORE_CASE;

 private  String property;

 private  Operator operator;

 private  Object value;

 private  Boolean ignoreCase;

/**
 * 初始化一个新创建的Filter对象
 */
public Filter() {
}/**
 * 初始化一个新创建的Filter对象
 *
 * @param property
 *            属性
 * @param operator
 *            运算符
 * @param value
 *            值
 */
public Filter(String property, Operator operator, Object value) {
    this.property = property;
    this.operator = operator;
    this.value = value;
}/**
 * 初始化一个新创建的Filter对象
 *
 * @param property
 *            属性
 * @param operator
 *            运算符
 * @param value
 *            值
 * @param ignoreCase
 *            忽略大小写
 */
public Filter(String property, Operator operator, Object value, boolean ignoreCase) {
    this.property = property;
    this.operator = operator;
    this.value = value;
    this.ignoreCase = ignoreCase;
}
public Filter isNotNull(String property){
    return new Filter(property, Operator.isNotNull, null);
}


public String getProperty(){
    return property;
}


public Filter like(String property,Object value){
    return new Filter(property, Operator.like, value);
}


public Filter in(String property,Object value){
    return new Filter(property, Operator.in, value);
}


public Filter lt(String property,Object value){
    return new Filter(property, Operator.lt, value);
}


public Filter eq(String property,Object value,boolean ignoreCase){
    return new Filter(property, Operator.eq, value, ignoreCase);
}


public Filter gt(String property,Object value){
    return new Filter(property, Operator.gt, value);
}


public Boolean getIgnoreCase(){
    return ignoreCase;
}


public Object getValue(){
    return value;
}


public Filter ignoreCase(){
    this.ignoreCase = true;
    return this;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
}


public Filter ne(String property,Object value,boolean ignoreCase){
    return new Filter(property, Operator.ne, value, ignoreCase);
}


public Filter isNull(String property){
    return new Filter(property, Operator.isNull, null);
}


public void setProperty(String property){
    this.property = property;
}


public void setValue(Object value){
    this.value = value;
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
    Filter other = (Filter) obj;
    return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator()).append(getValue(), other.getValue()).isEquals();
}


public Filter le(String property,Object value){
    return new Filter(property, Operator.le, value);
}


public void setOperator(Operator operator){
    this.operator = operator;
}


public Operator fromString(String value){
    return Operator.valueOf(value.toLowerCase());
}


public void setIgnoreCase(Boolean ignoreCase){
    this.ignoreCase = ignoreCase;
}


public Filter ge(String property,Object value){
    return new Filter(property, Operator.ge, value);
}


public Operator getOperator(){
    return operator;
}


}