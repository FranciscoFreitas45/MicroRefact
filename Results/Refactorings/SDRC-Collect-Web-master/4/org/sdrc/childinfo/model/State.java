import java.util.List;
public class State {

 private  int stateId;

 private  String stateCode;

 private  String stateName;

 private  List<District> samikshyaDistricts;


public void setStateId(int stateId){
    this.stateId = stateId;
}


public String getStateName(){
    return stateName;
}


public void setStateName(String stateName){
    this.stateName = stateName;
}


public void setSamikshyaDistricts(List<District> samikshyaDistricts){
    this.samikshyaDistricts = samikshyaDistricts;
}


public String getStateCode(){
    return stateCode;
}


public void setStateCode(String stateCode){
    this.stateCode = stateCode;
}


public int getStateId(){
    return stateId;
}


public List<District> getSamikshyaDistricts(){
    return samikshyaDistricts;
}


}