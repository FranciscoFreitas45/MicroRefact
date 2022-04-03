package org.jeecgframework.web.cgform.entity.generate;
 import java.util.List;
import org.jeecgframework.codegenerate.pojo.onetomany.SubTableEntity;
public class GenerateSubListEntity {

 private  String projectPath;

 private  String packageStyle;

 private  List<SubTableEntity> subTabParamIn;


public String getProjectPath(){
    String pt = projectPath;
    if (pt != null) {
        pt = pt.replace("\\", "/");
        if (!pt.endsWith("/")) {
            pt = pt + "/";
        }
    }
    return pt;
}


public List<SubTableEntity> getSubTabParamIn(){
    return subTabParamIn;
}


public void setProjectPath(String projectPath){
    this.projectPath = projectPath;
}


public String getPackageStyle(){
    return packageStyle;
}


public void setSubTabParamIn(List<SubTableEntity> subTabParamIn){
    this.subTabParamIn = subTabParamIn;
}


public void setPackageStyle(String packageStyle){
    this.packageStyle = packageStyle;
}


}