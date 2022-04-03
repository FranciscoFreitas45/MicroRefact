package cn.gson.oasys.DTO;
 import javax.persistence;
import org.hibernate.validator.constraints.NotEmpty;
public class SystemStatusList {

 private  Long statusId;

 private  String statusName;

 private  Integer statusSortValue;

 private  String statusModel;

 private  String statusColor;

 private  String statusPrecent;

public SystemStatusList() {
}
public Integer getStatusSortValue(){
    return statusSortValue;
}


public Long getStatusId(){
    return statusId;
}


public String getStatusColor(){
    return statusColor;
}


public String getStatusPrecent(){
    return statusPrecent;
}


public String getStatusName(){
    return statusName;
}


public String getStatusModel(){
    return statusModel;
}


@Override
public String toString(){
    return "SystemStatusList [statusId=" + statusId + ", statusName=" + statusName + ", statusSortValue=" + statusSortValue + ", statusModel=" + statusModel + ", statusColor=" + statusColor + ", statusPrecent=" + statusPrecent + "]";
}


}