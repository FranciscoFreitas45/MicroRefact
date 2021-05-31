import java.util.List;
public class MonthWithOT {

 private  String month;

 private  List<TotalOT> otlist;


public void setMonth(String month){
    this.month = month;
}


@Override
public String toString(){
    return "MonthWithOT [month=" + month + ", otlist=" + otlist + "]";
}


public String getMonth(){
    return month;
}


public void setOtlist(List<TotalOT> otlist){
    this.otlist = otlist;
}


public List<TotalOT> getOtlist(){
    return otlist;
}


}