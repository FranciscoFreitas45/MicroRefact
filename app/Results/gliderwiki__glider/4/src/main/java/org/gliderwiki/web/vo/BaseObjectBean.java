package org.gliderwiki.web.vo;
 import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
public class BaseObjectBean implements Serializable{

 private  long serialVersionUID;

 private  Integer rtnResult;

 private  String rtnStatus;

 private  String rtnMsg;

 private  String rtnUrl;


public void setRtnResult(Integer rtnResult){
    this.rtnResult = rtnResult;
}


public int hashCode(){
    return HashCodeBuilder.reflectionHashCode(this);
}


public boolean equals(Object o){
    return EqualsBuilder.reflectionEquals(this, o);
}


public String getRtnStatus(){
    return rtnStatus;
}


public String toString(){
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}


public Integer getRtnResult(){
    return rtnResult;
}


public String getRtnUrl(){
    return rtnUrl;
}


public void setRtnMsg(String rtnMsg){
    this.rtnMsg = rtnMsg;
}


public String getRtnMsg(){
    return rtnMsg;
}


public void setRtnStatus(String rtnStatus){
    this.rtnStatus = rtnStatus;
}


public void setRtnUrl(String rtnUrl){
    this.rtnUrl = rtnUrl;
}


}