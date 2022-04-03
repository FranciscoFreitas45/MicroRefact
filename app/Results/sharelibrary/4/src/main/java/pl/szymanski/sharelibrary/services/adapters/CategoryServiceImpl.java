package pl.szymanski.sharelibrary.services.adapters;
 import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanski.sharelibrary.entity.Category;
import pl.szymanski.sharelibrary.exceptions.categories.CategoryNotExist;
import pl.szymanski.sharelibrary.repositories.ports.CategoryRepository;
import pl.szymanski.sharelibrary.services.ports.CategoryService;
import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

 private  CategoryRepository categoryRepository;


@Override
public List<Category> getAll(){
    return categoryRepository.getAll();
}


@Override
public Category findByName(String name){
    return categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotExist(name));
}


}