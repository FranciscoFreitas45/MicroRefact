package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;
 import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupThreeCategoryMapper {


public List<StandardCategoryDTO> toStandardDTO(List<GroupThreeCategoryDTO> list){
    List<StandardCategoryDTO> result = new ArrayList<>();
    for (GroupThreeCategoryDTO dto : list) {
        result.add(toStandardDTO(dto));
    }
    return result;
}


}