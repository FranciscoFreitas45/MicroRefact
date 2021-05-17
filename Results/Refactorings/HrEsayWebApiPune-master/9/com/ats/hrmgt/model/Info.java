public class Info {

 private  boolean isError;

 private  String msg;


public boolean isError(){
    return isError;
}


public String getMsg(){
    return msg;
}


public void setError(boolean isError){
    this.isError = isError;
}


@Override
public String toString(){
    return "Info [isError=" + isError + ", msg=" + msg + "]";
}


public void setMsg(String msg){
    this.msg = msg;
}


}