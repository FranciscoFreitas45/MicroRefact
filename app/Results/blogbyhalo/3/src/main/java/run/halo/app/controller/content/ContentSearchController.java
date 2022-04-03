package run.halo.app.controller.content;
 import org.springframework.data.domain.Sort.Direction.DESC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;
import run.halo.app.model.entity.Post;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.service.ThemeService;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.ThemeService;
@Controller
@RequestMapping(value = "/search")
public class ContentSearchController {

 private  PostService postService;

 private  OptionService optionService;

 private  ThemeService themeService;

public ContentSearchController(PostService postService, OptionService optionService, ThemeService themeService) {
    this.postService = postService;
    this.optionService = optionService;
    this.themeService = themeService;
}
@GetMapping(value = "page/{page}")
public String search(Model model,String keyword,Integer page,Sort sort){
    final Pageable pageable = PageRequest.of(page - 1, optionService.getPostPageSize(), sort);
    final Page<Post> postPage = postService.pageBy(keyword, pageable);
    final Page<PostListVO> posts = postService.convertToListVo(postPage);
    model.addAttribute("is_search", true);
    model.addAttribute("keyword", keyword);
    model.addAttribute("posts", posts);
    model.addAttribute("meta_keywords", optionService.getSeoKeywords());
    model.addAttribute("meta_description", optionService.getSeoDescription());
    return themeService.render("search");
}


}