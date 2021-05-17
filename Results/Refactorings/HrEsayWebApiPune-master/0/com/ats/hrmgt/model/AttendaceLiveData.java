import java.util.List;
public class AttendaceLiveData {

 private  CountData countData;

 private  List<PresentAttendaceList> presentList;

 private  List<PresentAttendaceList> lateList;


public void setPresentList(List<PresentAttendaceList> presentList){
    this.presentList = presentList;
}


public List<PresentAttendaceList> getLateList(){
    return lateList;
}


public List<PresentAttendaceList> getPresentList(){
    return presentList;
}


public void setLateList(List<PresentAttendaceList> lateList){
    this.lateList = lateList;
}


@Override
public String toString(){
    return "AttendaceLiveData [countData=" + countData + ", presentList=" + presentList + ", lateList=" + lateList + "]";
}


public CountData getCountData(){
    return countData;
}


public void setCountData(CountData countData){
    this.countData = countData;
}


}