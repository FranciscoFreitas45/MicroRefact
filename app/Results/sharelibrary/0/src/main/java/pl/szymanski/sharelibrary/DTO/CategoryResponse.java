package pl.szymanski.sharelibrary.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Category;
public class CategoryResponse {

 private  int id;

 private  String name;


public CategoryResponse of(Category category){
    return new CategoryResponse(category.getId(), category.getName());
}


}