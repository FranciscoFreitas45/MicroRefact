public class ExtworkForm {

 private  boolean success;

 private  WorkRecord data;


public void setSuccess(boolean success){
    this.success = success;
}


public void setData(WorkRecord data){
    this.data = data;
}


public WorkRecord getData(){
    return data;
}


public boolean isSuccess(){
    return success;
}


}