package cn.gson.oasys.model.entity.system;
 import javax.persistence;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "aoa_status_list")
public class SystemStatusList {

@Id
@Column(name = "status_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long statusId;

@Column(name = "status_name")
@NotEmpty(message = "状态名称不能为空")
 private  String statusName;

@Column(name = "sort_value")
 private  Integer statusSortValue;

@Column(name = "status_model")
 private  String statusModel;

@Column(name = "status_color")
 private  String statusColor;

@Column(name = "sort_precent")
 private  String statusPrecent;

public SystemStatusList() {
}
public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public Integer getStatusSortValue(){
    return statusSortValue;
}


public void setStatusModel(String statusModel){
    this.statusModel = statusModel;
}


public Long getStatusId(){
    return statusId;
}


public String getStatusColor(){
    return statusColor;
}


public void setStatusPrecent(String statusPrecent){
    this.statusPrecent = statusPrecent;
}


public String getStatusPrecent(){
    return statusPrecent;
}


public String getStatusName(){
    return statusName;
}


public void setStatusName(String statusName){
    this.statusName = statusName;
}


public String getStatusModel(){
    return statusModel;
}


public void setStatusSortValue(Integer statusSortValue){
    this.statusSortValue = statusSortValue;
}


@Override
public String toString(){
    return "SystemStatusList [statusId=" + statusId + ", statusName=" + statusName + ", statusSortValue=" + statusSortValue + ", statusModel=" + statusModel + ", statusColor=" + statusColor + ", statusPrecent=" + statusPrecent + "]";
}


public void setStatusColor(String statusColor){
    this.statusColor = statusColor;
}


}