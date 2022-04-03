package run.halo.app.controller.content.model;
 import org.springframework.data.domain.Sort.Direction.DESC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.Tag;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.service.PostTagService;
import run.halo.app.service.TagService;
import run.halo.app.service.ThemeService;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.ThemeService;
@Component
public class TagModel {

 private  TagService tagService;

 private  PostService postService;

 private  PostTagService postTagService;

 private  OptionService optionService;

 private  ThemeService themeService;

public TagModel(TagService tagService, PostService postService, PostTagService postTagService, OptionService optionService, ThemeService themeService) {
    this.tagService = tagService;
    this.postService = postService;
    this.postTagService = postTagService;
    this.optionService = optionService;
    this.themeService = themeService;
}
public String list(Model model){
    model.addAttribute("is_tags", true);
    model.addAttribute("meta_keywords", optionService.getSeoKeywords());
    model.addAttribute("meta_description", optionService.getSeoDescription());
    return themeService.render("tags");
}


public String listPost(Model model,String slug,Integer page){
    // Get tag by slug
    final Tag tag = tagService.getBySlugOfNonNull(slug);
    TagDTO tagDTO = tagService.convertTo(tag);
    final Pageable pageable = PageRequest.of(page - 1, optionService.getArchivesPageSize(), Sort.by(DESC, "createTime"));
    Page<Post> postPage = postTagService.pagePostsBy(tag.getId(), PostStatus.PUBLISHED, pageable);
    Page<PostListVO> posts = postService.convertToListVo(postPage);
    model.addAttribute("is_tag", true);
    model.addAttribute("posts", posts);
    model.addAttribute("tag", tagDTO);
    model.addAttribute("meta_keywords", optionService.getSeoKeywords());
    model.addAttribute("meta_description", optionService.getSeoDescription());
    return themeService.render("tag");
}


}