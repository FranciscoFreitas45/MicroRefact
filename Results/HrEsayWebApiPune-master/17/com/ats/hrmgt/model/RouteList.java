import javax.persistence;
@Entity
@Table(name = "m_route")
public class RouteList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "route_id")
 private  int routeId;

@Column(name = "type_id ")
 private  int typeId;

@Column(name = "route_name")
 private  String routeName;

@Column(name = "fr_name")
 private  String frName;

@Column(name = "fr_ids")
 private  String frIds;

@Column(name = "start_time")
 private  String startTime;

@Column(name = "km")
 private  int km;

@Column(name = "incentive ")
 private  float incentive;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "extra_int1 ")
 private  int extraInt1;

@Column(name = "extra_int2")
 private  int extraInt2;

@Column(name = "extra_var2")
 private  String extraVar2;

@Column(name = "extra_var1")
 private  String extraVar1;


public int getKm(){
    return km;
}


public int getId(){
    return id;
}


public int getTypeId(){
    return typeId;
}


public void setRouteName(String routeName){
    this.routeName = routeName;
}


public float getIncentive(){
    return incentive;
}


public int getExtraInt2(){
    return extraInt2;
}


public void setIncentive(float incentive){
    this.incentive = incentive;
}


public int getExtraInt1(){
    return extraInt1;
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


public String getStartTime(){
    return startTime;
}


public void setStartTime(String startTime){
    this.startTime = startTime;
}


public void setKm(int km){
    this.km = km;
}


public int getRouteId(){
    return routeId;
}


public void setTypeId(int typeId){
    this.typeId = typeId;
}


public String getExtraVar2(){
    return extraVar2;
}


public void setExtraVar1(String extraVar1){
    this.extraVar1 = extraVar1;
}


public void setExtraVar2(String extraVar2){
    this.extraVar2 = extraVar2;
}


public String getExtraVar1(){
    return extraVar1;
}


public void setExtraInt2(int extraInt2){
    this.extraInt2 = extraInt2;
}


public void setExtraInt1(int extraInt1){
    this.extraInt1 = extraInt1;
}


public void setFrIds(String frIds){
    this.frIds = frIds;
}


public int getDelStatus(){
    return delStatus;
}


public String getFrIds(){
    return frIds;
}


@Override
public String toString(){
    return "RouteList [id=" + id + ", routeId=" + routeId + ", typeId=" + typeId + ", routeName=" + routeName + ", frName=" + frName + ", frIds=" + frIds + ", startTime=" + startTime + ", km=" + km + ", incentive=" + incentive + ", delStatus=" + delStatus + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2 + ", extraVar2=" + extraVar2 + ", extraVar1=" + extraVar1 + "]";
}


public String getFrName(){
    return frName;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setFrName(String frName){
    this.frName = frName;
}


}