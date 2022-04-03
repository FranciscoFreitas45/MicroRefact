package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;
 import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
@NoArgsConstructor
@Setter
public class GroupThreeCategoryDTO implements Serializable{

 private  String categoryName;

 private  String categoryID;

 private  String parentID;


public String getDesignation(){
    return this.categoryName;
}


public String getID(){
    return categoryID;
}


public String getParentID(){
    return parentID;
}


}