package switchtwentytwenty.project.interfaceadaptor.repository.http.repository;
 import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupThreeCategoryMapper;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupThreeCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupThreeCategoryDTOWrapper;
import switchtwentytwenty.project.interfaceadaptor.repository.http.irepository.IExternalCategoryRepository;
import java.util.Collections;
import java.util.List;
@Repository
public class GroupThreeExternalCategoryRepository implements IExternalCategoryRepository{

 private RestTemplate restTemplate;


public List<StandardCategoryDTO> getStandardCategories(){
    GroupThreeCategoryDTOWrapper response;
    try {
        response = restTemplate.getForObject(Constants.URL_CATEGORIES_GROUP_III, GroupThreeCategoryDTOWrapper.class);
    } catch (Exception exception) {
        return Collections.emptyList();
    }
    if (response == null) {
        return Collections.emptyList();
    }
    List<GroupThreeCategoryDTO> dtoList = response.getOutputCategoryDTOList();
    return GroupThreeCategoryMapper.toStandardDTO(dtoList);
}


}