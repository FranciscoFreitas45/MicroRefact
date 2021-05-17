import java.util.List;
public class QmsOrganizationInfoLeftDTO {

 private  QmsOrganizationInfoDTO data;

 private  List<QmsOrganizationInfoLeftDTO> children;


public List<QmsOrganizationInfoLeftDTO> getChildren(){
    return children;
}


public QmsOrganizationInfoDTO getData(){
    return data;
}


}