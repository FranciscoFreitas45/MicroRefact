package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.post.BasePostDetailDTO;
import run.halo.app.model.dto.post.BasePostMinimalDTO;
import run.halo.app.model.dto.post.BasePostSimpleDTO;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.params.PostContentParam;
import run.halo.app.model.params.PostParam;
import run.halo.app.model.params.PostQuery;
import run.halo.app.model.vo.PostDetailVO;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.Interface.OptionService;
@RestController
@RequestMapping("/api/admin/posts")
public class PostController {

 private  PostService postService;

 private  OptionService optionService;

public PostController(PostService postService, OptionService optionService) {
    this.postService = postService;
    this.optionService = optionService;
}
@GetMapping
@ApiOperation("Lists posts")
public Page<? extends BasePostSimpleDTO> pageBy(Pageable pageable,PostQuery postQuery,Boolean more){
    Page<Post> postPage = postService.pageBy(postQuery, pageable);
    if (more) {
        return postService.convertToListVo(postPage, true);
    }
    return postService.convertToSimple(postPage);
}


@GetMapping(value = { "preview/{postId:\\d+}", "{postId:\\d+}/preview" })
@ApiOperation("Gets a post preview link")
public String preview(Integer postId){
    Post post = postService.getById(postId);
    post.setSlug(URLEncoder.encode(post.getSlug(), StandardCharsets.UTF_8.name()));
    BasePostMinimalDTO postMinimalDTO = postService.convertToMinimal(post);
    StringBuilder previewUrl = new StringBuilder();
    if (!optionService.isEnabledAbsolutePath()) {
        previewUrl.append(optionService.getBlogBaseUrl());
    }
    previewUrl.append(postMinimalDTO.getFullPath());
    // build preview post url and return
    return previewUrl.toString();
}


@PutMapping("{postId:\\d+}/status/{status}")
@ApiOperation("Updates post status")
public BasePostMinimalDTO updateStatusBy(Integer postId,PostStatus status){
    Post post = postService.updateStatus(status, postId);
    return new BasePostMinimalDTO().convertFrom(post);
}


@GetMapping("status/{status}")
@ApiOperation("Gets a page of post by post status")
public Page<? extends BasePostSimpleDTO> pageByStatus(PostStatus status,Boolean more,Pageable pageable){
    Page<Post> posts = postService.pageBy(status, pageable);
    if (more) {
        return postService.convertToListVo(posts, true);
    }
    return postService.convertToSimple(posts);
}


@DeleteMapping
@ApiOperation("Deletes posts permanently in batch by id array")
public List<Post> deletePermanentlyInBatch(List<Integer> ids){
    return postService.removeByIds(ids);
}


@GetMapping("latest")
@ApiOperation("Pages latest post")
public List<BasePostMinimalDTO> pageLatest(int top){
    return postService.convertToMinimal(postService.pageLatest(top).getContent());
}


@PostMapping
@ApiOperation("Creates a post")
public PostDetailVO createBy(PostParam postParam,Boolean autoSave){
    // Convert to
    Post post = postParam.convertTo();
    return postService.createBy(post, postParam.getTagIds(), postParam.getCategoryIds(), postParam.getPostMetas(), autoSave);
}


@PutMapping("{postId:\\d+}")
@ApiOperation("Updates a post")
public PostDetailVO updateBy(PostParam postParam,Integer postId,Boolean autoSave){
    // Get the post info
    Post postToUpdate = postService.getById(postId);
    postParam.update(postToUpdate);
    return postService.updateBy(postToUpdate, postParam.getTagIds(), postParam.getCategoryIds(), postParam.getPostMetas(), autoSave);
}


@GetMapping("{postId:\\d+}")
@ApiOperation("Gets a post")
public PostDetailVO getBy(Integer postId){
    Post post = postService.getById(postId);
    return postService.convertToDetailVo(post, true);
}


@PutMapping("status/{status}")
@ApiOperation("Updates post status in batch")
public List<Post> updateStatusInBatch(PostStatus status,List<Integer> ids){
    return postService.updateStatusByIds(ids, status);
}


@PutMapping("{postId:\\d+}/status/draft/content")
@ApiOperation("Updates draft")
public BasePostDetailDTO updateDraftBy(Integer postId,PostContentParam contentParam){
    // Update draft content
    Post post = postService.updateDraftContent(contentParam.getContent(), postId);
    return new BasePostDetailDTO().convertFrom(post);
}


@DeleteMapping("{postId:\\d+}")
@ApiOperation("Deletes a photo permanently")
public void deletePermanently(Integer postId){
    postService.removeById(postId);
}


@PutMapping("{postId:\\d+}/likes")
@ApiOperation("Likes a post")
public void likes(Integer postId){
    postService.increaseLike(postId);
}


}