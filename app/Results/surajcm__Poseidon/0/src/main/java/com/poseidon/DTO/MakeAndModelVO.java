package com.poseidon.DTO;
 import java.util.StringJoiner;
public class MakeAndModelVO {

 private  Long id;

 private  Long makeId;

 private  Long modelId;

 private  String makeName;

 private  String modelName;

 private  String description;

 private  Boolean startswith;

 private  Boolean includes;

 private  String createdBy;

 private  String modifiedBy;


public Long getModelId(){
    return modelId;
}


public Long getId(){
    return id;
}


public String getModelName(){
    return modelName;
}


public String getDescription(){
    return description;
}


public String getModifiedBy(){
    return modifiedBy;
}


public String getMakeName(){
    return makeName;
}


public Boolean getStartswith(){
    return startswith;
}


public Boolean getIncludes(){
    return includes;
}


public String getCreatedBy(){
    return createdBy;
}


public Long getMakeId(){
    return makeId;
}


}