package com.ec.survey.model.delphi;
 import java.util.ArrayList;
import java.util.List;
import com.ec.survey.model.survey.base.File;
public class DelphiExplanation {

 private  String responseMessage;

 private  String text;

 private  List<String> fileList;

 private  boolean changedForMedian;

public DelphiExplanation() {
    responseMessage = "";
    text = "";
}public DelphiExplanation(String responseMessage, String text) {
    this.responseMessage = responseMessage;
    this.text = text;
}
public void setFileInfoFromFiles(List<File> files){
    this.fileList.clear();
    for (File file : files) {
        this.fileList.add(file.getName());
    }
}


public String getText(){
    return text;
}


public void setResponseMessage(String responseMessage){
    this.responseMessage = responseMessage;
}


public boolean isChangedForMedian(){
    return changedForMedian;
}


public void setFileList(List<String> fileList){
    this.fileList = fileList;
}


public String getResponseMessage(){
    return responseMessage;
}


public void setText(String text){
    this.text = text;
}


public List<String> getFileList(){
    return fileList;
}


public void setChangedForMedian(boolean changedForMedian){
    this.changedForMedian = changedForMedian;
}


}