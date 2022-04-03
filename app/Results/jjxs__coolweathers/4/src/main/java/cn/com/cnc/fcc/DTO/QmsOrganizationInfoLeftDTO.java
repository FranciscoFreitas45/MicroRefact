package cn.com.cnc.fcc.DTO;
 import java.util.List;
public class QmsOrganizationInfoLeftDTO {

 private  QmsOrganizationInfoDTO data;

 private  List<QmsOrganizationInfoLeftDTO> children;


public List<QmsOrganizationInfoLeftDTO> getChildren(){
    return children;
}


public void setChildren(List<QmsOrganizationInfoLeftDTO> children){
    this.children = children;
}


public QmsOrganizationInfoDTO getData(){
    return data;
}


}