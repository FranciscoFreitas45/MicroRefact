import javax.persistence;
@Entity
public class LiveThumbData {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "count_thumb")
 private  int countThumb;

@Column(name = "in_id")
 private  int inId;

@Column(name = "in_punch_time")
 private  String inPunchTime;

@Column(name = "in_date")
 private  String inDate;

@Column(name = "in_time")
 private  String inTime;

@Column(name = "out_id")
 private  int outId;

@Column(name = "out_punch_time")
 private  String outPunch_time;

@Column(name = "out_date")
 private  String outDate;

@Column(name = "out_time")
 private  String outTime;


public int getOutId(){
    return outId;
}


public void setOutDate(String outDate){
    this.outDate = outDate;
}


public void setInId(int inId){
    this.inId = inId;
}


public String getId(){
    return id;
}


public String getOutDate(){
    return outDate;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setOutPunch_time(String outPunch_time){
    this.outPunch_time = outPunch_time;
}


public int getCountThumb(){
    return countThumb;
}


public String getInTime(){
    return inTime;
}


public String getEmpCode(){
    return empCode;
}


public void setInDate(String inDate){
    this.inDate = inDate;
}


public int getInId(){
    return inId;
}


public void setInPunchTime(String inPunchTime){
    this.inPunchTime = inPunchTime;
}


public String getOutTime(){
    return outTime;
}


public String getOutPunch_time(){
    return outPunch_time;
}


public void setCountThumb(int countThumb){
    this.countThumb = countThumb;
}


public void setId(String id){
    this.id = id;
}


public void setOutId(int outId){
    this.outId = outId;
}


public void setOutTime(String outTime){
    this.outTime = outTime;
}


@Override
public String toString(){
    return "LiveThumbData [id=" + id + ", empCode=" + empCode + ", countThumb=" + countThumb + ", inId=" + inId + ", inPunchTime=" + inPunchTime + ", inDate=" + inDate + ", inTime=" + inTime + ", outId=" + outId + ", outPunch_time=" + outPunch_time + ", outDate=" + outDate + ", outTime=" + outTime + "]";
}


public String getInDate(){
    return inDate;
}


public void setInTime(String inTime){
    this.inTime = inTime;
}


public String getInPunchTime(){
    return inPunchTime;
}


}