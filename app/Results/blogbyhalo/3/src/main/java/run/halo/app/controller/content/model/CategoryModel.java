package run.halo.app.controller.content.model;
 import org.springframework.data.domain.Sort.Direction.DESC;
import run.halo.app.model.support.HaloConst.POST_PASSWORD_TEMPLATE;
import run.halo.app.model.support.HaloConst.SUFFIX_FTL;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.EncryptTypeEnum;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.AuthenticationService;
import run.halo.app.service.CategoryService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostCategoryService;
import run.halo.app.service.PostService;
import run.halo.app.service.ThemeService;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.OptionService;
@Component
public class CategoryModel {

 private  CategoryService categoryService;

 private  ThemeService themeService;

 private  PostCategoryService postCategoryService;

 private  PostService postService;

 private  OptionService optionService;

 private  AuthenticationService authenticationService;

public CategoryModel(CategoryService categoryService, ThemeService themeService, PostCategoryService postCategoryService, PostService postService, OptionService optionService, AuthenticationService authenticationService) {
    this.categoryService = categoryService;
    this.themeService = themeService;
    this.postCategoryService = postCategoryService;
    this.postService = postService;
    this.optionService = optionService;
    this.authenticationService = authenticationService;
}
public String list(Model model){
    model.addAttribute("is_categories", true);
    model.addAttribute("meta_keywords", optionService.getSeoKeywords());
    model.addAttribute("meta_description", optionService.getSeoDescription());
    return themeService.render("categories");
}


public String listPost(Model model,String slug,Integer page){
    // Get category by slug
    final Category category = categoryService.getBySlugOfNonNull(slug, true);
    if (!authenticationService.categoryAuthentication(category.getId(), null)) {
        model.addAttribute("slug", category.getSlug());
        model.addAttribute("type", EncryptTypeEnum.CATEGORY.getName());
        if (themeService.templateExists(POST_PASSWORD_TEMPLATE + SUFFIX_FTL)) {
            return themeService.render(POST_PASSWORD_TEMPLATE);
        }
        return "common/template/" + POST_PASSWORD_TEMPLATE;
    }
    CategoryDTO categoryDTO = categoryService.convertTo(category);
    final Pageable pageable = PageRequest.of(page - 1, optionService.getArchivesPageSize(), Sort.by(DESC, "topPriority", "createTime"));
    Page<Post> postPage = postCategoryService.pagePostBy(category.getId(), Sets.immutableEnumSet(PostStatus.PUBLISHED, PostStatus.INTIMATE), pageable);
    Page<PostListVO> posts = postService.convertToListVo(postPage);
    // Generate meta description.
    if (StringUtils.isNotEmpty(category.getDescription())) {
        model.addAttribute("meta_description", category.getDescription());
    } else {
        model.addAttribute("meta_description", optionService.getSeoDescription());
    }
    model.addAttribute("is_category", true);
    model.addAttribute("posts", posts);
    model.addAttribute("category", categoryDTO);
    model.addAttribute("meta_keywords", optionService.getSeoKeywords());
    return themeService.render("category");
}


}