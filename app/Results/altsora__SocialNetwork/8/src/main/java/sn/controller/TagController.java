package sn.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sn.api.response.MessageResponse;
import sn.api.response.SimpleServiceResponse;
import sn.api.response.ServiceResponseDataList;
import sn.api.response.tagresponse.TagResponseDTO;
import sn.service.TagService;
@RestController
@RequestMapping("/tags")
public class TagController {

 private  TagService tagService;

@Autowired
public TagController(TagService tagService) {
    this.tagService = tagService;
}
@DeleteMapping
public SimpleServiceResponse<MessageResponse> deleteTag(long id){
    return new SimpleServiceResponse<>(tagService.deleteTagById(id));
}


@PostMapping
public SimpleServiceResponse<TagResponseDTO> createTag(String tag){
    return new SimpleServiceResponse<>(tagService.createTag(tag));
}


@GetMapping
public ServiceResponseDataList<TagResponseDTO> getTags(String tag,int offset,int initPerPage){
    TagResponseDTO tagResponseDTO = new TagResponseDTO(tag);
    return new ServiceResponseDataList<>(0, offset, initPerPage, List.of(tagResponseDTO));
}


}