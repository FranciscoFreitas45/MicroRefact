package pl.szymanski.sharelibrary.services.ports;
 import pl.szymanski.sharelibrary.entity.Category;
import java.util.List;
public interface CategoryService {


public List<Category> getAll()
;

public Category findByName(String name)
;

}