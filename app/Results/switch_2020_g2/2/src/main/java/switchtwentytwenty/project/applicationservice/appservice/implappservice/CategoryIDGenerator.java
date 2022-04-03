package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryIDGenerator;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CategoryIDGenerator implements ICategoryIDGenerator{

@Autowired
 private ICategoryRepository categoryRepository;


public CategoryID generate(){
    CategoryID categoryID;
    do {
        categoryID = new CategoryID(UUID.randomUUID().toString());
    } while (categoryRepository.existsByID(categoryID));
    return categoryID;
}


}