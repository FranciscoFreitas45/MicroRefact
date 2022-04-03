package com.hmm.Work.entity;
 public class ExtTotalworkForm {

 private  boolean success;

 private  WorkTatalRecord data;

public ExtTotalworkForm() {
}public ExtTotalworkForm(boolean success) {
    this.success = success;
}public ExtTotalworkForm(boolean success, WorkTatalRecord data) {
    this.success = success;
    this.setData(data);
}
public void setSuccess(boolean success){
    this.success = success;
}


public void setData(WorkTatalRecord data){
    this.data = data;
}


public WorkTatalRecord getData(){
    return data;
}


public boolean isSuccess(){
    return success;
}


}