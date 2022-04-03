package cn.gson.oasys.model.entity.system;
 import javax.persistence;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "aoa_type_list")
public class SystemTypeList {

@Id
@Column(name = "type_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long typeId;

@Column(name = "type_name")
@NotEmpty(message = "类型名称不能为空")
 private  String typeName;

@Column(name = "sort_value")
 private  Integer typeSortValue;

@Column(name = "type_model")
 private  String typeModel;

@Column(name = "type_color")
 private  String typeColor;


public Integer getTypeSortValue(){
    return typeSortValue;
}


public String getTypeColor(){
    return typeColor;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


@Override
public String toString(){
    return "SystemTypeList [typeId=" + typeId + ", typeName=" + typeName + ", typeSortValue=" + typeSortValue + ", typeModel=" + typeModel + ", typeColor=" + typeColor + "]";
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public String getTypeModel(){
    return typeModel;
}


public void setTypeModel(String typeModel){
    this.typeModel = typeModel;
}


public String getTypeName(){
    return typeName;
}


public Long getTypeId(){
    return typeId;
}


public void setTypeColor(String typeColor){
    this.typeColor = typeColor;
}


public void setTypeSortValue(Integer typeSortValue){
    this.typeSortValue = typeSortValue;
}


}