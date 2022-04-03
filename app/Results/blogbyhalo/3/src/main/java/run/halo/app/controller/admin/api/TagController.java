package run.halo.app.controller.admin.api;
 import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.Tag;
import run.halo.app.model.params.TagParam;
import run.halo.app.service.PostTagService;
import run.halo.app.service.TagService;
import run.halo.app.DTO.TagParam;
@Slf4j
@RestController
@RequestMapping("/api/admin/tags")
public class TagController {

 private  TagService tagService;

 private  PostTagService postTagService;

public TagController(TagService tagService, PostTagService postTagService) {
    this.tagService = tagService;
    this.postTagService = postTagService;
}
@GetMapping
@ApiOperation("Lists tags")
public List<? extends TagDTO> listTags(Sort sort,Boolean more){
    if (more) {
        return postTagService.listTagWithCountDtos(sort);
    }
    return tagService.convertTo(tagService.listAll(sort));
}


@PostMapping
@ApiOperation("Creates a tag")
public TagDTO createTag(TagParam tagParam){
    // Convert to tag
    Tag tag = tagParam.convertTo();
    log.debug("Tag to be created: [{}]", tag);
    // Create and convert
    return tagService.convertTo(tagService.create(tag));
}


@PutMapping("{tagId:\\d+}")
@ApiOperation("Updates a tag")
public TagDTO updateBy(Integer tagId,TagParam tagParam){
    // Get old tag
    Tag tag = tagService.getById(tagId);
    // Update tag
    tagParam.update(tag);
    // Update tag
    return tagService.convertTo(tagService.update(tag));
}


@GetMapping("{tagId:\\d+}")
@ApiOperation("Gets tag detail by id")
public TagDTO getBy(Integer tagId){
    return tagService.convertTo(tagService.getById(tagId));
}


@DeleteMapping("{tagId:\\d+}")
@ApiOperation("Deletes a tag")
public TagDTO deletePermanently(Integer tagId){
    // Remove the tag
    Tag deletedTag = tagService.removeById(tagId);
    // Remove the post tag relationship
    postTagService.removeByTagId(tagId);
    return tagService.convertTo(deletedTag);
}


}