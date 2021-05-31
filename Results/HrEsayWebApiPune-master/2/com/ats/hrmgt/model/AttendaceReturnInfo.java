import java.util.List;
public class AttendaceReturnInfo {

 private  boolean isError;

 private  String msg;

 private  List<LeaveCancelEmployee> list;


public boolean isError(){
    return isError;
}


public String getMsg(){
    return msg;
}


public void setError(boolean isError){
    this.isError = isError;
}


public List<LeaveCancelEmployee> getList(){
    return list;
}


@Override
public String toString(){
    return "AttendaceReturnInfo [isError=" + isError + ", msg=" + msg + ", list=" + list + "]";
}


public void setList(List<LeaveCancelEmployee> list){
    this.list = list;
}


public void setMsg(String msg){
    this.msg = msg;
}


}