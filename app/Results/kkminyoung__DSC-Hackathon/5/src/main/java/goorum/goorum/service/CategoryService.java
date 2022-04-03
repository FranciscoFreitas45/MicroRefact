package goorum.goorum.service;
 import goorum.goorum.domain.Category;
import java.util.List;
public interface CategoryService {


public List<Category> getList()
;

public boolean addCategory(String category)
;

}