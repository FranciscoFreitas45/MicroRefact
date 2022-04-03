package goorum.goorum.service;
 import goorum.goorum.domain.Category;
import goorum.goorum.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{

@Autowired
 private  CategoryRepository categoryRepository;


@Override
public List<Category> getList(){
    return categoryRepository.findAll();
}


@Override
public boolean addCategory(String categoryName){
    Category category = Category.builder().categoryName(categoryName).build();
    log.info(category.getCategoryId() + "");
    category = categoryRepository.save(category);
    log.info(category.getCategoryId() + "");
    return true;
}


}