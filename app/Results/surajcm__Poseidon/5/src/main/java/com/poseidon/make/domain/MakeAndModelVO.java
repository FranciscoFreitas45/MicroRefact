package com.poseidon.make.domain;
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


public void setMakeName(String makeName){
    this.makeName = makeName;
}


public void setModelId(Long modelId){
    this.modelId = modelId;
}


public void setStartswith(Boolean startswith){
    this.startswith = startswith;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
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


public void setModelName(String modelName){
    this.modelName = modelName;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setId(Long id){
    this.id = id;
}


public Boolean getStartswith(){
    return startswith;
}


public Boolean getIncludes(){
    return includes;
}


@Override
public String toString(){
    return new StringJoiner(", ", MakeAndModelVO.class.getSimpleName() + "[", "]").add("id=" + id).add("makeId=" + makeId).add("modelId=" + modelId).add("makeName='" + makeName + "'").add("modelName='" + modelName + "'").add("description='" + description + "'").add("startswith=" + startswith).add("includes=" + includes).add("createdBy='" + createdBy + "'").add("modifiedBy='" + modifiedBy + "'").toString();
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
}


public void setMakeId(Long makeId){
    this.makeId = makeId;
}


public void setIncludes(Boolean includes){
    this.includes = includes;
}


public String getCreatedBy(){
    return createdBy;
}


public Long getMakeId(){
    return makeId;
}


}