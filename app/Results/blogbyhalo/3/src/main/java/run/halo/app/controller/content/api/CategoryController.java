package run.halo.app.controller.content.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import com.google.common.collect.Sets;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.exception.ForbiddenException;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.AuthenticationService;
import run.halo.app.service.CategoryService;
import run.halo.app.service.PostCategoryService;
import run.halo.app.service.PostService;
@RestController("ApiContentCategoryController")
@RequestMapping("/api/content/categories")
public class CategoryController {

 private  CategoryService categoryService;

 private  PostCategoryService postCategoryService;

 private  PostService postService;

 private  AuthenticationService authenticationService;

public CategoryController(CategoryService categoryService, PostCategoryService postCategoryService, PostService postService, AuthenticationService authenticationService) {
    this.categoryService = categoryService;
    this.postCategoryService = postCategoryService;
    this.postService = postService;
    this.authenticationService = authenticationService;
}
@GetMapping("{slug}/posts")
@ApiOperation("Lists posts by category slug")
public Page<PostListVO> listPostsBy(String slug,String password,Pageable pageable){
    // Get category by slug
    Category category = categoryService.getBySlugOfNonNull(slug, true);
    if (!authenticationService.categoryAuthentication(category.getId(), password)) {
        throw new ForbiddenException("您没有该分类的访问权限");
    }
    Page<Post> postPage = postCategoryService.pagePostBy(category.getId(), Sets.immutableEnumSet(PostStatus.PUBLISHED, PostStatus.INTIMATE), pageable);
    return postService.convertToListVo(postPage);
}


@GetMapping
@ApiOperation("Lists categories")
public List<? extends CategoryDTO> listCategories(Sort sort,Boolean more){
    if (more) {
        return postCategoryService.listCategoryWithPostCountDto(sort, false);
    }
    return categoryService.convertTo(categoryService.listAll(sort));
}


}