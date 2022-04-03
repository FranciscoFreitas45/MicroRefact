package com.ec.survey.model;
 import com.ec.survey.tools.ConversionTools;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
public class FileResult {

 private  long serialVersionUID;

 private  String surveyUid;

 private  String surveyShortname;

 private  String filePath;

 private  String fileName;

 private  String fileUid;

 private  String fileType;

 private  String fileExtension;

 private  Date created;

 private  String filterApplied;

 private  String fileSize;

 private  String error;

 private  boolean archive;


public String getFilterApplied(){
    return filterApplied;
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
}


public void setFileExtension(String fileExtension){
    this.fileExtension = fileExtension;
}


public boolean isRecreatePossible(){
    if (surveyShortname == null || surveyShortname.length() == 0) {
        return false;
    }
    if (fileType == null || fileType.length() == 0) {
        return false;
    }
    return "results;statistics;charts;activities;survey;contribution".contains(fileType);
}


public String getFileType(){
    return fileType;
}


public String getSurveyShortname(){
    return surveyShortname;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public String getFileSize(){
    return fileSize;
}


public String getFileName(){
    return fileName;
}


public String getSurveyUid(){
    return surveyUid;
}


public boolean isArchive(){
    return archive;
}


public void setFileType(String fileType){
    this.fileType = fileType;
}


public void setFilterApplied(String filterApplied){
    this.filterApplied = filterApplied;
}


public void setSurveyShortname(String surveyShortname){
    this.surveyShortname = surveyShortname;
}


public void setFileSize(String fileSize){
    this.fileSize = fileSize;
}


public void setArchive(boolean archive){
    this.archive = archive;
}


public String getFileUid(){
    return fileUid;
}


@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreated(){
    return created;
}


public void setCreated(Date created){
    this.created = created;
}


public String getError(){
    return error;
}


public void setError(String error){
    this.error = error;
}


public String getFilePath(){
    return filePath;
}


public String getFileExtension(){
    return fileExtension;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public void setFileUid(String fileUid){
    this.fileUid = fileUid;
}


}