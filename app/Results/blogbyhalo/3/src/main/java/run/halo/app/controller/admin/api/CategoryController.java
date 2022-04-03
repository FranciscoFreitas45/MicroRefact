package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.params.CategoryParam;
import run.halo.app.model.vo.CategoryVO;
import run.halo.app.service.CategoryService;
import run.halo.app.service.PostCategoryService;
import run.halo.app.DTO.CategoryParam;
@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

 private  CategoryService categoryService;

 private  PostCategoryService postCategoryService;

public CategoryController(CategoryService categoryService, PostCategoryService postCategoryService) {
    this.categoryService = categoryService;
    this.postCategoryService = postCategoryService;
}
@GetMapping("tree_view")
@ApiOperation("List all categories as tree")
public List<CategoryVO> listAsTree(Sort sort){
    return categoryService.listAsTree(sort);
}


@PostMapping
@ApiOperation("Creates category")
public CategoryDTO createBy(CategoryParam categoryParam){
    // Convert to category
    Category category = categoryParam.convertTo();
    // Save it
    return categoryService.convertTo(categoryService.create(category));
}


@PutMapping("{categoryId:\\d+}")
@ApiOperation("Updates category")
public CategoryDTO updateBy(Integer categoryId,CategoryParam categoryParam){
    Category categoryToUpdate = categoryService.getById(categoryId);
    categoryParam.update(categoryToUpdate);
    return categoryService.convertTo(categoryService.update(categoryToUpdate));
}


@GetMapping("{categoryId:\\d+}")
@ApiOperation("Gets category detail")
public CategoryDTO getBy(Integer categoryId){
    return categoryService.convertTo(categoryService.getById(categoryId));
}


@GetMapping
@ApiOperation("Lists all categories")
public List<? extends CategoryDTO> listAll(Sort sort,boolean more){
    if (more) {
        return postCategoryService.listCategoryWithPostCountDto(sort, true);
    }
    return categoryService.convertTo(categoryService.listAll(sort, true));
}


@DeleteMapping("{categoryId:\\d+}")
@ApiOperation("Deletes category")
public void deletePermanently(Integer categoryId){
    categoryService.removeCategoryAndPostCategoryBy(categoryId);
}


}