package com.project.stockexchangeappbackend.rest;
 import com.project.stockexchangeappbackend.dto.CreateTagDTO;
import com.project.stockexchangeappbackend.dto.ErrorResponse;
import com.project.stockexchangeappbackend.dto.TagDTO;
import com.project.stockexchangeappbackend.repository.specification.TagSpecification;
import com.project.stockexchangeappbackend.service.TagService;
import io.swagger.annotations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
@RestController
@RequestMapping("/api/tag")
@Validated
@CrossOrigin("*")
@AllArgsConstructor
@Api(value = "Tags", description = "REST API for tags' management", tags = "Tags")
public class TagController {

 private  TagService tagService;

 private  ModelMapper modelMapper;


@DeleteMapping("/{name}")
@PreAuthorize("hasRole('ADMIN')")
@ApiOperation(value = "Delete tag by name and every object tagged this name", notes = "Required role ADMIN.")
@ApiResponses({ @ApiResponse(code = 200, message = "Tag was successfully retrieved."), @ApiResponse(code = 400, message = "Tag cannot be deleted.", response = ErrorResponse.class), @ApiResponse(code = 401, message = "Unauthorized."), @ApiResponse(code = 403, message = "Access Denied."), @ApiResponse(code = 404, message = "Tag not found.", response = ErrorResponse.class) })
public void deleteTag(String tag){
    tagService.removeTag(tag);
}


@PostMapping
@PreAuthorize("hasRole('ADMIN')")
@ApiOperation(value = "Create new tag", notes = "Required role ADMIN.")
@ApiResponses({ @ApiResponse(code = 200, message = "Tag was successfully retrieved."), @ApiResponse(code = 401, message = "Unauthorized."), @ApiResponse(code = 403, message = "Access Denied."), @ApiResponse(code = 409, message = "Tag already exist.", response = ErrorResponse.class) })
public void createTag(CreateTagDTO tag){
    tagService.createTag(tag);
}


@GetMapping
@ApiOperation(value = "Page and filter tags")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered tags.") })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)") })
public Page<TagDTO> getTags(Pageable pageable,TagSpecification specification){
    return tagService.getTags(pageable, specification).map(tag -> modelMapper.map(tag, TagDTO.class));
}


@GetMapping("/{name}")
@ApiOperation(value = "Retrieve tag by name", response = TagDTO.class)
@ApiResponses({ @ApiResponse(code = 200, message = "Tag was successfully retrieved."), @ApiResponse(code = 404, message = "Tag not found.", response = ErrorResponse.class) })
public TagDTO getTag(String tag){
    return modelMapper.map(tagService.findTag(tag), TagDTO.class);
}


}