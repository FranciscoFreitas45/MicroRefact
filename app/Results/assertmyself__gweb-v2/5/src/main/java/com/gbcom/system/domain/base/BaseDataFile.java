package com.gbcom.system.domain.base;
 import java.io.Serializable;
public class BaseDataFile implements Serializable{

 public  String REF;

 public  String PROP_FILE_NAME;

 public  String PROP_FILE_DATE;

 public  String PROP_FILE_PATH;

 public  String PROP_FILE_SIZE;

 private  int hashCode;

 private  Long id;

 private  String fileName;

 private  String fileDate;

 private  String filePath;

 private  String fileSize;

// constructors
public BaseDataFile() {
    initialize();
}/**
 * Constructor for primary key
 */
public BaseDataFile(java.lang.Long id) {
    this.setId(id);
    initialize();
}/**
 * Constructor for required fields
 */
public BaseDataFile(java.lang.Long id, java.lang.String fileName) {
    this.setId(id);
    this.setFileName(fileName);
    initialize();
}
public void setFileSize(String fileSize){
    this.fileSize = fileSize;
}


public java.lang.Long getId(){
    return id;
}


public String getFileSize(){
    return fileSize;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public int hashCode(){
    if (Integer.MIN_VALUE == this.hashCode) {
        if (null == this.getId())
            return super.hashCode();
        else {
            String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
            this.hashCode = hashStr.hashCode();
        }
    }
    return this.hashCode;
}


public String getFilePath(){
    return filePath;
}


public boolean equals(Object obj){
    if (null == obj)
        return false;
    if (!(obj instanceof com.gbcom.system.domain.DataFile))
        return false;
    else {
        com.gbcom.system.domain.DataFile dataFile = (com.gbcom.system.domain.DataFile) obj;
        if (null == this.getId() || null == dataFile.getId())
            return false;
        else
            return (this.getId().equals(dataFile.getId()));
    }
}


public void setId(java.lang.Long id){
    this.id = id;
    this.hashCode = Integer.MIN_VALUE;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public String toString(){
    org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
    builder.append(id);
    builder.append(fileName);
    builder.append(fileDate);
    builder.append(filePath);
    builder.append(fileSize);
    return builder.toString();
}


public void initialize(){
}


public String getFileDate(){
    return fileDate;
}


public String getFileName(){
    return fileName;
}


public void setFileDate(String fileDate){
    this.fileDate = fileDate;
}


}