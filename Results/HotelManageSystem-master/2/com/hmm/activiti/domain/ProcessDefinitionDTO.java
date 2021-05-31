public class ProcessDefinitionDTO {

 private  String id;

 private  String category;

 private  String name;

 private  String key;

 private  String description;

 private  int version;

 private  String resourceName;

 private  String deploymentId;

 private  String diagramResourceName;

 private  String tenantId;

 private  boolean startFormKey;

 private  boolean graphicalNotation;

 private  boolean suspended;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setDeploymentId(String deploymentId){
    this.deploymentId = deploymentId;
}


public void setCategory(String category){
    this.category = category;
}


public void setTenantId(String tenantId){
    this.tenantId = tenantId;
}


public void setId(String id){
    this.id = id;
}


public void setGraphicalNotation(boolean graphicalNotation){
    this.graphicalNotation = graphicalNotation;
}


public void setKey(String key){
    this.key = key;
}


public int getVersion(){
    return version;
}


public String getKey(){
    return key;
}


public boolean isSuspended(){
    return suspended;
}


public void setVersion(int version){
    this.version = version;
}


public void setDiagramResourceName(String diagramResourceName){
    this.diagramResourceName = diagramResourceName;
}


public String getCategory(){
    return category;
}


public String getResourceName(){
    return resourceName;
}


public String getTenantId(){
    return tenantId;
}


public void setStartFormKey(boolean startFormKey){
    this.startFormKey = startFormKey;
}


public void setSuspended(boolean suspended){
    this.suspended = suspended;
}


public void setResourceName(String resourceName){
    this.resourceName = resourceName;
}


public boolean isStartFormKey(){
    return startFormKey;
}


public boolean isGraphicalNotation(){
    return graphicalNotation;
}


public String getDiagramResourceName(){
    return diagramResourceName;
}


public String getDeploymentId(){
    return deploymentId;
}


}