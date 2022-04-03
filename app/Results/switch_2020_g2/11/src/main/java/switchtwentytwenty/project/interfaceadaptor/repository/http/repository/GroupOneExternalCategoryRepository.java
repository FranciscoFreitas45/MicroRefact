package switchtwentytwenty.project.interfaceadaptor.repository.http.repository;
 import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.irepository.IExternalCategoryRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupOneCategoryMapper;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.GroupOneCategoryDTOWrapper;
import java.util.Collections;
import java.util.List;
@Repository
public class GroupOneExternalCategoryRepository implements IExternalCategoryRepository{

 private RestTemplate restTemplate;


public List<StandardCategoryDTO> getStandardCategories(){
    ResponseEntity<GroupOneCategoryDTOWrapper> response;
    try {
        response = restTemplate.getForEntity(Constants.URL_CATEGORIES_GROUP_I, GroupOneCategoryDTOWrapper.class);
    } catch (Exception exception) {
        return Collections.emptyList();
    }
    if (response.getBody() == null) {
        return Collections.emptyList();
    }
    return GroupOneCategoryMapper.toStandardDTOList(response.getBody().getCategoryDTOs());
}


}