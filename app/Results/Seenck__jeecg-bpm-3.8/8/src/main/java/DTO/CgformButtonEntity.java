package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class CgformButtonEntity {

 private  java.lang.String id;

 private  java.lang.String formId;

 private  java.lang.String buttonCode;

 private  java.lang.String buttonName;

 private  java.lang.String buttonStyle;

 private  java.lang.String optType;

 private  java.lang.String exp;

 private  java.lang.String buttonStatus;

 private  java.lang.Integer orderNum;

 private  java.lang.String buttonIcon;


@Column(name = "FORM_ID", nullable = true, length = 32)
public java.lang.String getFormId(){
    return this.formId;
}


@Column(name = "order_num", nullable = true, length = 4)
public java.lang.Integer getOrderNum(){
    return orderNum;
}


@Column(name = "BUTTON_STYLE", nullable = true, length = 20)
public java.lang.String getButtonStyle(){
    return this.buttonStyle;
}


public void setButtonStyle(java.lang.String buttonStyle){
    this.buttonStyle = buttonStyle;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "BUTTON_CODE", nullable = true, length = 50)
public java.lang.String getButtonCode(){
    return this.buttonCode;
}


@Column(name = "BUTTON_NAME", nullable = true, length = 50)
public java.lang.String getButtonName(){
    return this.buttonName;
}


@Column(name = "EXP", nullable = true, length = 255)
public java.lang.String getExp(){
    return this.exp;
}


public void setButtonIcon(java.lang.String buttonIcon){
    this.buttonIcon = buttonIcon;
}


public void setExp(java.lang.String exp){
    this.exp = exp;
}


@Column(name = "button_icon", nullable = true, length = 20)
public java.lang.String getButtonIcon(){
    return buttonIcon;
}


@Column(name = "OPT_TYPE", nullable = true, length = 20)
public java.lang.String getOptType(){
    return this.optType;
}


public void setOrderNum(java.lang.Integer orderNum){
    this.orderNum = orderNum;
}


@Column(name = "BUTTON_STATUS", nullable = true, length = 2)
public java.lang.String getButtonStatus(){
    return this.buttonStatus;
}


}