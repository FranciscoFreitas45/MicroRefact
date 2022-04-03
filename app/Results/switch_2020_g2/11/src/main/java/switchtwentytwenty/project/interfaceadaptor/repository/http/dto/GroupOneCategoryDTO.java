package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;
 import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
@NoArgsConstructor
@Setter
public class GroupOneCategoryDTO implements Serializable{

 private  String id;

 private  String name;

 private  String parentId;


public String getDesignation(){
    return this.name;
}


public String getID(){
    return id;
}


public String getParentID(){
    return parentId;
}


}