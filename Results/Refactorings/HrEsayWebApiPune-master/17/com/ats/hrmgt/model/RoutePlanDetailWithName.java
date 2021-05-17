import javax.persistence;
@Entity
public class RoutePlanDetailWithName {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "plan_detail_id")
 private  int planDetailId;

@Column(name = "plan_head_id")
 private  int planHeadId;

@Column(name = "route_id")
 private  int routeId;

@Column(name = "driver_id")
 private  int driverId;

@Column(name = "isoffday_isff")
 private  int isoffdayIsff;

@Column(name = "type_id ")
 private  int typeId;

@Column(name = "route_name")
 private  String routeName;

@Column(name = "fr_name")
 private  String frName;

@Column(name = "fr_ids")
 private  String frIds;

@Column(name = "late_mark")
 private  int lateMark;

@Column(name = "late_min")
 private  int lateMin;

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

@Column(name = "first_name")
 private  String firstName;

@Column(name = "surname")
 private  String surname;


public int getPlanHeadId(){
    return planHeadId;
}


public int getKm(){
    return km;
}


public int getPlanDetailId(){
    return planDetailId;
}


public void setDriverId(int driverId){
    this.driverId = driverId;
}


public int getTypeId(){
    return typeId;
}


public void setRouteName(String routeName){
    this.routeName = routeName;
}


public int getLateMin(){
    return lateMin;
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


public void setSurname(String surname){
    this.surname = surname;
}


public int getIsoffdayIsff(){
    return isoffdayIsff;
}


public String getRouteName(){
    return routeName;
}


public void setRouteId(int routeId){
    this.routeId = routeId;
}


public void setPlanHeadId(int planHeadId){
    this.planHeadId = planHeadId;
}


public void setPlanDetailId(int planDetailId){
    this.planDetailId = planDetailId;
}


public void setKm(int km){
    this.km = km;
}


public void setLateMark(int lateMark){
    this.lateMark = lateMark;
}


public int getDriverId(){
    return driverId;
}


public void setIsoffdayIsff(int isoffdayIsff){
    this.isoffdayIsff = isoffdayIsff;
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


public int getLateMark(){
    return lateMark;
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


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public String getFrIds(){
    return frIds;
}


@Override
public String toString(){
    return "RoutePlanDetailWithName [planDetailId=" + planDetailId + ", planHeadId=" + planHeadId + ", routeId=" + routeId + ", driverId=" + driverId + ", isoffdayIsff=" + isoffdayIsff + ", typeId=" + typeId + ", routeName=" + routeName + ", frName=" + frName + ", frIds=" + frIds + ", lateMark=" + lateMark + ", lateMin=" + lateMin + ", km=" + km + ", incentive=" + incentive + ", delStatus=" + delStatus + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2 + ", extraVar2=" + extraVar2 + ", extraVar1=" + extraVar1 + ", firstName=" + firstName + ", surname=" + surname + "]";
}


public String getFrName(){
    return frName;
}


public void setLateMin(int lateMin){
    this.lateMin = lateMin;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public String getFirstName(){
    return firstName;
}


public void setFrName(String frName){
    this.frName = frName;
}


public String getSurname(){
    return surname;
}


}