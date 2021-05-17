import javax.persistence;
@Entity
public class PlanHistoryRouteWise {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "route_id")
 private  int routeId;

@Column(name = "route_name")
 private  String routeName;

@Column(name = "count")
 private  int count;

@Column(name = "incentive")
 private  float incentive;

@Column(name = "km")
 private  int km;


public void setKm(int km){
    this.km = km;
}


public int getKm(){
    return km;
}


public int getId(){
    return id;
}


public int getRouteId(){
    return routeId;
}


public void setRouteName(String routeName){
    this.routeName = routeName;
}


public float getIncentive(){
    return incentive;
}


public void setIncentive(float incentive){
    this.incentive = incentive;
}


public String getRouteName(){
    return routeName;
}


public void setRouteId(int routeId){
    this.routeId = routeId;
}


public void setId(int id){
    this.id = id;
}


@Override
public String toString(){
    return "PlanHistoryRouteWise [id=" + id + ", routeId=" + routeId + ", routeName=" + routeName + ", count=" + count + ", incentive=" + incentive + ", km=" + km + "]";
}


public int getCount(){
    return count;
}


public void setCount(int count){
    this.count = count;
}


}