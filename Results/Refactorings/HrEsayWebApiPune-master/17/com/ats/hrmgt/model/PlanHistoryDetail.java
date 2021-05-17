import javax.persistence;
import java.util.List;
@Entity
public class PlanHistoryDetail {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "offdays")
 private  int offdays;

@Column(name = "ffdays ")
 private  int ffdays;

@Column(name = "km")
 private  int km;

@Column(name = "incentive")
 private  int incentive;

@Column(name = "emp_name")
 private  String empName;

@Transient
 private List<PlanHistoryTypeWise> planwisehistoryList;

@Transient
 private List<PlanHistoryRouteWise> routewisePlanHistory;


public void setKm(int km){
    this.km = km;
}


public int getKm(){
    return km;
}


public void setRoutewisePlanHistory(List<PlanHistoryRouteWise> routewisePlanHistory){
    this.routewisePlanHistory = routewisePlanHistory;
}


public List<PlanHistoryRouteWise> getRoutewisePlanHistory(){
    return routewisePlanHistory;
}


public void setOffdays(int offdays){
    this.offdays = offdays;
}


public String getId(){
    return id;
}


public int getOffdays(){
    return offdays;
}


public String getEmpName(){
    return empName;
}


public int getIncentive(){
    return incentive;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setIncentive(int incentive){
    this.incentive = incentive;
}


public void setPlanwisehistoryList(List<PlanHistoryTypeWise> planwisehistoryList){
    this.planwisehistoryList = planwisehistoryList;
}


public void setId(String id){
    this.id = id;
}


@Override
public String toString(){
    return "PlanHistoryDetail [id=" + id + ", offdays=" + offdays + ", ffdays=" + ffdays + ", km=" + km + ", incentive=" + incentive + ", empName=" + empName + ", planwisehistoryList=" + planwisehistoryList + ", routewisePlanHistory=" + routewisePlanHistory + "]";
}


public int getFfdays(){
    return ffdays;
}


public void setFfdays(int ffdays){
    this.ffdays = ffdays;
}


public List<PlanHistoryTypeWise> getPlanwisehistoryList(){
    return planwisehistoryList;
}


}