public class IUSValueObject {

 private  Integer iusid;

 private  String indicator;

 private  String unit;

 private  String subgroup;

 private  String weight;

 private  boolean highisgood;


public String getSubgroup(){
    return subgroup;
}


public String getIndicator(){
    return indicator;
}


public String getWeight(){
    return weight;
}


public void setWeight(String weight){
    this.weight = weight;
}


public void setIusid(Integer iusid){
    this.iusid = iusid;
}


public void setSubgroup(String subgroup){
    this.subgroup = subgroup;
}


public Integer getIusid(){
    return iusid;
}


public void setHighisgood(boolean highisgood){
    this.highisgood = highisgood;
}


public boolean isHighisgood(){
    return highisgood;
}


public void setUnit(String unit){
    this.unit = unit;
}


@Override
public String toString(){
    return "IUSValueObject [iusid=" + iusid + "indicator=" + indicator + "subgroup=" + subgroup + ", subgroup=" + subgroup + ", weight=" + weight + ", highisgood=" + highisgood + "]";
}


public String getUnit(){
    return unit;
}


public void setIndicator(String indicator){
    this.indicator = indicator;
}


}