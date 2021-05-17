import java.util.List;
public class QmsOrganizationInfoLeftDTO {

 private  QmsOrganizationInfoDTO data;

 private  List<QmsOrganizationInfoLeftDTO> children;


public List<QmsOrganizationInfoLeftDTO> getChildren(){
    return children;
}


public void setData(QmsOrganizationInfoDTO data){
    this.data = data;
}


public void setChildren(List<QmsOrganizationInfoLeftDTO> children){
    this.children = children;
}


public QmsOrganizationInfoDTO getData(){
    return data;
}


}