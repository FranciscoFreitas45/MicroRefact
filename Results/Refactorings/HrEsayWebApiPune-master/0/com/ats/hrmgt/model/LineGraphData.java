import java.util.List;
public class LineGraphData {

 private  List<MonthWithOT> list;

 private  List<TotalOT> deptList;


public List<TotalOT> getDeptList(){
    return deptList;
}


public List<MonthWithOT> getList(){
    return list;
}


public void setDeptList(List<TotalOT> deptList){
    this.deptList = deptList;
}


@Override
public String toString(){
    return "LineGraphData [list=" + list + ", deptList=" + deptList + "]";
}


public void setList(List<MonthWithOT> list){
    this.list = list;
}


}