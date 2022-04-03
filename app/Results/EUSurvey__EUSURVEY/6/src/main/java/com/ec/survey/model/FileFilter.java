package com.ec.survey.model;
 import java.util;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
public class FileFilter {

 private  long serialVersionUID;

 private  String surveyUid;

 private  String surveyShortname;

 private  String filePath;

 private  String fileName;

 private  String fileUid;

 private  Set<String> fileTypes;

 private  Set<String> fileExtensions;

 private  Date createdFrom;

 private  Date createdTo;

 private  String filterApplied;

 private  String sortKey;

 private  String sortOrder;

 private  int userId;

 private  boolean systemExports;

 private  boolean surveyUploads;

 private  boolean temporaryFiles;

 private  boolean archivedSurveys;

 private  boolean unknownFiles;

 private  boolean surveyFiles;

 private  boolean onlyUnreferenced;

 private  boolean searchInFileSystem;

 private  int page;

 private  int itemsPerPage;


public boolean isUnknownFiles(){
    return unknownFiles;
}


public String getFilterApplied(){
    return filterApplied;
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
}


public int getPage(){
    return page;
}


public boolean isValidExtension(String extension){
    if (fileExtensions == null || fileExtensions.isEmpty())
        return true;
    return fileExtensions.contains(extension);
}


public void setTemporaryFiles(boolean temporaryFiles){
    this.temporaryFiles = temporaryFiles;
}


public void setOnlyUnreferenced(boolean onlyUnreferenced){
    this.onlyUnreferenced = onlyUnreferenced;
}


public void setArchivedSurveys(boolean archivedSurveys){
    this.archivedSurveys = archivedSurveys;
}


public String getSortKey(){
    return sortKey;
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
}


public String getSortOrder(){
    return sortOrder;
}


public void setSurveyUploads(boolean surveyUploads){
    this.surveyUploads = surveyUploads;
}


public boolean isTemporaryFiles(){
    return temporaryFiles;
}


public int getItemsPerPage(){
    return itemsPerPage;
}


public String getSurveyShortname(){
    return surveyShortname;
}


public void setSystemExports(boolean systemExports){
    this.systemExports = systemExports;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public boolean isSurveyFiles(){
    return surveyFiles;
}


public void setSurveyFiles(boolean surveyFiles){
    this.surveyFiles = surveyFiles;
}


public boolean isOnlyUnreferenced(){
    return onlyUnreferenced;
}


@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreatedTo(){
    return createdTo;
}


public void setItemsPerPage(int itemsPerPage){
    this.itemsPerPage = itemsPerPage;
}


public String getFileName(){
    return fileName;
}


public String getSurveyUid(){
    return surveyUid;
}


public void setPage(int page){
    this.page = page;
}


@DateTimeFormat(pattern = ConversionTools.DateFormat)
public Date getCreatedFrom(){
    return createdFrom;
}


public boolean isSystemExports(){
    return systemExports;
}


public void setSearchInFileSystem(boolean searchInFileSystem){
    this.searchInFileSystem = searchInFileSystem;
}


public void setFilterApplied(String filterApplied){
    this.filterApplied = filterApplied;
}


public boolean isArchivedSurveys(){
    return archivedSurveys;
}


public void setSurveyShortname(String surveyShortname){
    this.surveyShortname = surveyShortname;
}


public void setSortOrder(String sortOrder){
    if (sortOrder.equalsIgnoreCase("DESC") || sortOrder.equalsIgnoreCase("ASC")) {
        this.sortOrder = sortOrder;
    }
}


public void setFileTypes(Set<String> fileTypes){
    this.fileTypes = fileTypes;
}


public String getFileUid(){
    return fileUid;
}


public void setCreatedTo(Date createdTo){
    this.createdTo = createdTo;
}


public boolean isVisible(String type){
    if (fileTypes == null || fileTypes.isEmpty())
        return true;
    return fileTypes.contains(type);
}


public boolean isSurveyUploads(){
    return surveyUploads;
}


public void setCreatedFrom(Date createdFrom){
    this.createdFrom = createdFrom;
}


public String getFilePath(){
    return filePath;
}


public Set<String> getFileTypes(){
    return fileTypes;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public void setFileUid(String fileUid){
    this.fileUid = fileUid;
}


public void setFileExtensions(Set<String> fileExtensions){
    this.fileExtensions = fileExtensions;
}


public void setUnknownFiles(boolean unknownFiles){
    this.unknownFiles = unknownFiles;
}


public boolean isSearchInFileSystem(){
    return searchInFileSystem;
}


public Set<String> getFileExtensions(){
    return fileExtensions;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


}