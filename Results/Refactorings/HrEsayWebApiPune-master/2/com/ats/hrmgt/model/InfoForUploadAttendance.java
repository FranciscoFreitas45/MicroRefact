import javax.persistence;
@Entity
public class InfoForUploadAttendance {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "total_emp")
 private  int totalEmp;

@Column(name = "date_diff")
 private  int dateDiff;

@Column(name = "updated_by_step1")
 private  int updatedByStep1;

@Column(name = "updated_by_file")
 private  int updatedByFile;


public void setUpdatedByFile(int updatedByFile){
    this.updatedByFile = updatedByFile;
}


public void setUpdatedByStep1(int updatedByStep1){
    this.updatedByStep1 = updatedByStep1;
}


public void setDateDiff(int dateDiff){
    this.dateDiff = dateDiff;
}


public int getDateDiff(){
    return dateDiff;
}


public int getUpdatedByStep1(){
    return updatedByStep1;
}


@Override
public String toString(){
    return "InfoForUploadAttendance [totalEmp=" + totalEmp + ", dateDiff=" + dateDiff + ", updatedByStep1=" + updatedByStep1 + ", updatedByFile=" + updatedByFile + "]";
}


public int getTotalEmp(){
    return totalEmp;
}


public int getUpdatedByFile(){
    return updatedByFile;
}


public void setTotalEmp(int totalEmp){
    this.totalEmp = totalEmp;
}


}