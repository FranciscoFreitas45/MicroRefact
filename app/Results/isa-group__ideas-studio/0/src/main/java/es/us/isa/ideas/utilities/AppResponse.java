package es.us.isa.ideas.utilities;
 import java.io.Serializable;
public class AppResponse implements Serializable{

 private  long serialVersionUID;

 private  String val;

 protected  Status status;

 protected  String message;

 protected  String htmlMessage;

 protected  String data;

 protected  String context;

 protected  String fileUri;

 protected  AppAnnotations[] annotations;

 protected  Serializable customStruct;

public AppResponse() {
    super();
}
public void setData(String data){
    this.data = data;
}


public void setFileUri(String fileUri){
    this.fileUri = fileUri;
}


public String getMessage(){
    return message;
}


public void setHtmlMessage(String htmlMessage){
    this.htmlMessage = htmlMessage;
}


public void setAnnotations(AppAnnotations[] annotations){
    this.annotations = annotations;
}


public Status getStatus(){
    return status;
}


public void setMessage(String message){
    this.message = message;
}


public String getFileUri(){
    return fileUri;
}


public String getHtmlMessage(){
    return htmlMessage;
}


public AppAnnotations[] getAnnotations(){
    return annotations;
}


public void setStatus(Status status){
    this.status = status;
}


public void setCustomStruct(Serializable customStruct){
    this.customStruct = customStruct;
}


public Serializable getCustomStruct(){
    return customStruct;
}


public void setContext(String context){
    this.context = context;
}


@Override
public String toString(){
    return this.val;
}


public String getContext(){
    return context;
}


public String getData(){
    return data;
}


}