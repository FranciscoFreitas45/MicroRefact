import javax.persistence;
@Entity
@Table(name = "m_route_type")
public class RouteType {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "type_id")
 private  int typeId;

@Column(name = "type_name")
 private  String typeName;

@Column(name = "del_status")
 private  int delStatus;


public int getDelStatus(){
    return delStatus;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


@Override
public String toString(){
    return "RouteType [typeId=" + typeId + ", typeName=" + typeName + ", delStatus=" + delStatus + "]";
}


public void setTypeId(int typeId){
    this.typeId = typeId;
}


public String getTypeName(){
    return typeName;
}


public int getTypeId(){
    return typeId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}