public class AdminLog {

 private  String formname;

 private  String level;

 private  String user;

 private  String block;

 private  String district;

 private  String status;

 private  String start;

 private  String end;


public String getBlock(){
    return block;
}


public String getStart(){
    return start;
}


public String getFormname(){
    return formname;
}


public String getUser(){
    return user;
}


public void setBlock(String block){
    this.block = block;
}


public void setFormname(String formname){
    this.formname = formname;
}


public String getStatus(){
    return status;
}


public String getEnd(){
    return end;
}


public void setStatus(String status){
    this.status = status;
}


public void setLevel(String level){
    this.level = level;
}


public String getLevel(){
    return level;
}


public void setStart(String start){
    this.start = start;
}


public String getDistrict(){
    return district;
}


public void setEnd(String end){
    this.end = end;
}


public void setDistrict(String district){
    this.district = district;
}


public void setUser(String user){
    this.user = user;
}


}