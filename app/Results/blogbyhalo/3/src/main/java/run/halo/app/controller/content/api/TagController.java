package run.halo.app.controller.content.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.Tag;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.PostService;
import run.halo.app.service.PostTagService;
import run.halo.app.service.TagService;
@RestController("ApiContentTagController")
@RequestMapping("/api/content/tags")
public class TagController {

 private  TagService tagService;

 private  PostTagService postTagService;

 private  PostService postService;

public TagController(TagService tagService, PostTagService postTagService, PostService postService) {
    this.tagService = tagService;
    this.postTagService = postTagService;
    this.postService = postService;
}
@GetMapping
@ApiOperation("Lists tags")
public List<? extends TagDTO> listTags(Sort sort,Boolean more){
    if (more) {
        return postTagService.listTagWithCountDtos(sort);
    }
    return tagService.convertTo(tagService.listAll(sort));
}


@GetMapping("{slug}/posts")
@ApiOperation("Lists posts by tag slug")
public Page<PostListVO> listPostsBy(String slug,Pageable pageable){
    // Get tag by slug
    Tag tag = tagService.getBySlugOfNonNull(slug);
    // Get posts, convert and return
    Page<Post> postPage = postTagService.pagePostsBy(tag.getId(), PostStatus.PUBLISHED, pageable);
    return postService.convertToListVo(postPage);
}


}