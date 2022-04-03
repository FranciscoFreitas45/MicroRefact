package com.credit.dto;
 import java.util.ArrayList;
import java.util.List;
public class CheckRecordDTO {

 private  List<CheckRecordDetailDTO> organizationCheckList;

 private  List<CheckRecordDetailDTO> personalCheckList;


public List<CheckRecordDetailDTO> getPersonalCheckList(){
    return personalCheckList;
}


public void setPersonalCheckList(List<CheckRecordDetailDTO> personalCheckList){
    this.personalCheckList = personalCheckList;
}


public void setOrganizationCheckList(List<CheckRecordDetailDTO> organizationCheckList){
    this.organizationCheckList = organizationCheckList;
}


public List<CheckRecordDetailDTO> getOrganizationCheckList(){
    return organizationCheckList;
}


}