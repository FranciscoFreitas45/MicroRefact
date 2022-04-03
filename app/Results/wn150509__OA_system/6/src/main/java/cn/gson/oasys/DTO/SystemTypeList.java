package cn.gson.oasys.DTO;
 import javax.persistence;
import org.hibernate.validator.constraints.NotEmpty;
public class SystemTypeList {

 private  Long typeId;

 private  String typeName;

 private  Integer typeSortValue;

 private  String typeModel;

 private  String typeColor;


public Integer getTypeSortValue(){
    return typeSortValue;
}


public String getTypeColor(){
    return typeColor;
}


@Override
public String toString(){
    return "SystemTypeList [typeId=" + typeId + ", typeName=" + typeName + ", typeSortValue=" + typeSortValue + ", typeModel=" + typeModel + ", typeColor=" + typeColor + "]";
}


public String getTypeModel(){
    return typeModel;
}


public String getTypeName(){
    return typeName;
}


public Long getTypeId(){
    return typeId;
}


public void setTypeSortValue(Integer typeSortValue){
    this.typeSortValue = typeSortValue;
}


}