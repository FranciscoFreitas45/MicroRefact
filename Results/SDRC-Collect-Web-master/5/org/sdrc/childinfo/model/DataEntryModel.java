import java.util.List;
public class DataEntryModel {

 private  ValueObject timePeriod;

 private  ValueObject sector;

 private  ValueObject program;

 private  List<IUSModel> iusModel;

 private  List<DataModel> dataModel;

 private  String dataProvided;

 private  ValueObject userDetails;


public void setUserDetails(ValueObject userDetails){
    this.userDetails = userDetails;
}


public List<IUSModel> getIusModel(){
    return iusModel;
}


public void setDataModel(List<DataModel> dataModel){
    this.dataModel = dataModel;
}


public void setTimePeriod(ValueObject timePeriod){
    this.timePeriod = timePeriod;
}


public List<DataModel> getDataModel(){
    return dataModel;
}


public ValueObject getSector(){
    return sector;
}


public void setSector(ValueObject sector){
    this.sector = sector;
}


public void setProgram(ValueObject program){
    this.program = program;
}


public ValueObject getUserDetails(){
    return userDetails;
}


public void setIusModel(List<IUSModel> iusModel){
    this.iusModel = iusModel;
}


public String getDataProvided(){
    return dataProvided;
}


public ValueObject getProgram(){
    return program;
}


public ValueObject getTimePeriod(){
    return timePeriod;
}


public void setDataProvided(String dataProvided){
    this.dataProvided = dataProvided;
}


}