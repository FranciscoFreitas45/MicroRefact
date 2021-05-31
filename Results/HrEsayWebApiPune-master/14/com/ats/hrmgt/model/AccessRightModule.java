import javax.persistence;
import java.util.List;
@Entity
@Table(name = "m_module")
public class AccessRightModule {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "module_id")
 private  int moduleId;

@Column(name = "module_name")
 private String moduleName;

@Column(name = "module_desc")
 private String moduleDesc;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "order_by")
 private  int orderBy;

@Column(name = "icon_div")
 private String iconDiv;

@Transient
 private List<AccessRightSubModule> accessRightSubModuleList;


public void setModuleId(int moduleId){
    this.moduleId = moduleId;
}


public String getModuleDesc(){
    return moduleDesc;
}


public void setOrderBy(int orderBy){
    this.orderBy = orderBy;
}


public List<AccessRightSubModule> getAccessRightSubModuleList(){
    return accessRightSubModuleList;
}


public int getModuleId(){
    return moduleId;
}


public String getModuleName(){
    return moduleName;
}


public int getDelStatus(){
    return delStatus;
}


public int getOrderBy(){
    return orderBy;
}


public String getIconDiv(){
    return iconDiv;
}


public void setAccessRightSubModuleList(List<AccessRightSubModule> accessRightSubModuleList){
    this.accessRightSubModuleList = accessRightSubModuleList;
}


@Override
public String toString(){
    return "AccessRightModule [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleDesc=" + moduleDesc + ", delStatus=" + delStatus + ", orderBy=" + orderBy + ", iconDiv=" + iconDiv + ", accessRightSubModuleList=" + accessRightSubModuleList + "]";
}


public void setModuleName(String moduleName){
    this.moduleName = moduleName;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIconDiv(String iconDiv){
    this.iconDiv = iconDiv;
}


public void setModuleDesc(String moduleDesc){
    this.moduleDesc = moduleDesc;
}


}