import javax.persistence;
@Entity
@Table(name = "m_module_sub")
public class AccessRightSubModule {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "sub_module_id")
 private  int subModuleId;

@Column(name = "module_id")
 private  int moduleId;

@Column(name = "sub_module_name")
 private String subModulName;

@Column(name = "sub_module_mapping")
 private String subModuleMapping;

@Column(name = "sub_module_desc")
 private String subModuleDesc;

@Column(name = "type")
 private  int type;

@Column(name = "view")
 private  int view;

@Column(name = "add_approve_config")
 private  int addApproveConfig;

@Column(name = "edit_reject")
 private  int editReject;

@Column(name = "delete_reject_approve")
 private  int deleteRejectApprove;

@Column(name = "is_delete")
 private  int isDelete;

@Column(name = "order_by")
 private  int orderBy;


public void setSubModuleDesc(String subModuleDesc){
    this.subModuleDesc = subModuleDesc;
}


public int getIsDelete(){
    return isDelete;
}


public void setOrderBy(int orderBy){
    this.orderBy = orderBy;
}


public int getModuleId(){
    return moduleId;
}


public void setIsDelete(int isDelete){
    this.isDelete = isDelete;
}


public void setSubModuleId(int subModuleId){
    this.subModuleId = subModuleId;
}


public int getOrderBy(){
    return orderBy;
}


public int getDeleteRejectApprove(){
    return deleteRejectApprove;
}


public String getSubModulName(){
    return subModulName;
}


public int getView(){
    return view;
}


public void setSubModuleMapping(String subModuleMapping){
    this.subModuleMapping = subModuleMapping;
}


public int getAddApproveConfig(){
    return addApproveConfig;
}


public void setDeleteRejectApprove(int deleteRejectApprove){
    this.deleteRejectApprove = deleteRejectApprove;
}


public String getSubModuleDesc(){
    return subModuleDesc;
}


public String getSubModuleMapping(){
    return subModuleMapping;
}


public void setModuleId(int moduleId){
    this.moduleId = moduleId;
}


public void setType(int type){
    this.type = type;
}


public int getEditReject(){
    return editReject;
}


public void setAddApproveConfig(int addApproveConfig){
    this.addApproveConfig = addApproveConfig;
}


public int getType(){
    return type;
}


public void setSubModulName(String subModulName){
    this.subModulName = subModulName;
}


public void setView(int view){
    this.view = view;
}


public int getSubModuleId(){
    return subModuleId;
}


@Override
public String toString(){
    return "AccessRightSubModule [subModuleId=" + subModuleId + ", moduleId=" + moduleId + ", subModulName=" + subModulName + ", subModuleMapping=" + subModuleMapping + ", subModuleDesc=" + subModuleDesc + ", type=" + type + ", view=" + view + ", addApproveConfig=" + addApproveConfig + ", editReject=" + editReject + ", deleteRejectApprove=" + deleteRejectApprove + ", isDelete=" + isDelete + ", orderBy=" + orderBy + "]";
}


public void setEditReject(int editReject){
    this.editReject = editReject;
}


}