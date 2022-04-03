package sn.api.response.tagresponse;
 import lombok.Getter;
import sn.api.response.AbstractResponse;
@Getter
public class TagResponseDTO extends AbstractResponse{

 private  long id;

 private  String tag;

public TagResponseDTO(String tag) {
    this.tag = tag;
}public TagResponseDTO(long id, String tag) {
    this.id = id;
    this.tag = tag;
}
}