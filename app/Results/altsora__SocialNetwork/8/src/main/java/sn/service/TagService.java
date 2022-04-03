package sn.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.api.response.MessageResponse;
import sn.api.response.tagresponse.TagResponseDTO;
import sn.model.Tag;
import sn.repositories.TagRepository;
import sn.Interface.TagRepository;
@Service
public class TagService {

 private  TagRepository tagRepository;

@Autowired
public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
}
public TagResponseDTO createTag(String tag){
    Tag newTag = new Tag(tag);
    tagRepository.save(newTag);
    return new TagResponseDTO(newTag.getId(), newTag.getTag());
}


public MessageResponse deleteTagById(long id){
    MessageResponse response = new MessageResponse();
    if (tagRepository.findById(id).isPresent()) {
        tagRepository.deleteById(id);
        response.setMessage("ok");
    } else {
        response.setMessage("No Such Tag");
    }
    return response;
}


}