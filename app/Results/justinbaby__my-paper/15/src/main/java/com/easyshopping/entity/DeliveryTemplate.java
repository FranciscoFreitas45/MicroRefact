package com.easyshopping.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_delivery_template")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_delivery_template_sequence")
public class DeliveryTemplate extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  String content;

 private  Integer width;

 private  Integer height;

 private  Integer offsetX;

 private  Integer offsetY;

 private  String background;

 private  Boolean isDefault;

 private  String memo;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


@NotEmpty
@Lob
@Column(nullable = false)
public String getContent(){
    return content;
}


@NotNull
@Min(1)
@Column(nullable = false)
public Integer getWidth(){
    return width;
}


public void setWidth(Integer width){
    this.width = width;
}


public void setHeight(Integer height){
    this.height = height;
}


@NotNull
@Column(nullable = false)
public Integer getOffsetX(){
    return offsetX;
}


public void setBackground(String background){
    this.background = background;
}


public void setOffsetX(Integer offsetX){
    this.offsetX = offsetX;
}


public void setMemo(String memo){
    this.memo = memo;
}


@NotNull
@Min(1)
@Column(nullable = false)
public Integer getHeight(){
    return height;
}


@NotNull
@Column(nullable = false)
public Integer getOffsetY(){
    return offsetY;
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


@NotNull
@Column(nullable = false)
public Boolean getIsDefault(){
    return isDefault;
}


public void setIsDefault(Boolean isDefault){
    this.isDefault = isDefault;
}


public void setOffsetY(Integer offsetY){
    this.offsetY = offsetY;
}


@Length(max = 200)
public String getBackground(){
    return background;
}


}