package org.sdrc.childinfo.model;
 import org.springframework.web.multipart.MultipartFile;
public class Workspace extends BaseModel{

 private  MultipartFile file;

 private  int monitoringFormId;

 private  String formName;

 private  String areaName;

 private  String parentAreaName;

 private  String areaCode;

 private  String parentAreaCode;

 private  String lastUpdatedUser;

 private  String deadlineDate;

 private  String status;

 private  String remarks;

 private  boolean shouldShowUploadButton;

 private  boolean shouldShowDownloadButton;

 private  boolean shouldAggregateButton;

 private  boolean shouldShowBlankForm;

 private  String blankFormPath;

 private  String uploadedFormPath;


public void setParentAreaCode(String parentAreaCode){
    this.parentAreaCode = parentAreaCode;
}


public boolean isShouldAggregateButton(){
    return shouldAggregateButton;
}


public void setUploadedFormPath(String uploadedFormPath){
    this.uploadedFormPath = uploadedFormPath;
}


public String getParentAreaName(){
    return parentAreaName;
}


public String getStatus(){
    return status;
}


public MultipartFile getFile(){
    return file;
}


public void setBlankFormPath(String blankFormPath){
    this.blankFormPath = blankFormPath;
}


public void setLastUpdatedUser(String lastUpdatedUser){
    this.lastUpdatedUser = lastUpdatedUser;
}


public boolean isShouldShowDownloadButton(){
    return shouldShowDownloadButton;
}


public String getAreaCode(){
    return areaCode;
}


public void setDeadlineDate(String deadlineDate){
    this.deadlineDate = deadlineDate;
}


public void setShouldShowBlankForm(boolean shouldShowBlankForm){
    this.shouldShowBlankForm = shouldShowBlankForm;
}


public void setShouldAggregateButton(boolean shouldAggregateButton){
    this.shouldAggregateButton = shouldAggregateButton;
}


public String getBlankFormPath(){
    return blankFormPath;
}


public boolean isShouldShowUploadButton(){
    return shouldShowUploadButton;
}


public void setMonitoringFormId(int monitoringFormId){
    this.monitoringFormId = monitoringFormId;
}


public void setAreaCode(String areaCode){
    this.areaCode = areaCode;
}


public String getRemarks(){
    return remarks;
}


public String getAreaName(){
    return areaName;
}


public int getMonitoringFormId(){
    return monitoringFormId;
}


public String getDeadlineDate(){
    return deadlineDate;
}


public void setStatus(String status){
    this.status = status;
}


public void setParentAreaName(String parentAreaName){
    this.parentAreaName = parentAreaName;
}


public String getUploadedFormPath(){
    return uploadedFormPath;
}


public String getLastUpdatedUser(){
    return lastUpdatedUser;
}


public void setShouldShowUploadButton(boolean shouldShowUploadButton){
    this.shouldShowUploadButton = shouldShowUploadButton;
}


public void setFile(MultipartFile file){
    this.file = file;
}


public String getParentAreaCode(){
    return parentAreaCode;
}


public void setShouldShowDownloadButton(boolean shouldShowDownloadButton){
    this.shouldShowDownloadButton = shouldShowDownloadButton;
}


public String getFormName(){
    return formName;
}


public void setFormName(String formName){
    this.formName = formName;
}


public void setAreaName(String areaName){
    this.areaName = areaName;
}


public boolean isShouldShowBlankForm(){
    return shouldShowBlankForm;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


}