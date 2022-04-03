package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.Category;
import pl.szymanski.sharelibrary.repositories.jpa.CategoryJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.CategoryRepository;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

 private  CategoryJPARepository categoryJPARepository;


@Override
public List<Category> getAll(){
    return categoryJPARepository.findAll();
}


@Override
public Optional<Category> findByName(String name){
    return categoryJPARepository.findFirstByNameIgnoreCase(name);
}


}