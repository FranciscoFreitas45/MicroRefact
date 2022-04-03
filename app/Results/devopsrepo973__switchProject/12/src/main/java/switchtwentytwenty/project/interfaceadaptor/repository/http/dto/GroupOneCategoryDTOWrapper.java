package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;
 import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Setter
public class GroupOneCategoryDTOWrapper {

 private  List<GroupOneCategoryDTO> categoryDTOs;


public List<GroupOneCategoryDTO> getCategoryDTOs(){
    return new ArrayList<>(categoryDTOs);
}


}