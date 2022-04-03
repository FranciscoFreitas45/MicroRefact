package run.halo.app.DTO;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Tag;
import run.halo.app.utils.SlugUtils;
public class TagParam implements InputConverter<Tag>{

 private  String name;

 private  String slug;

 private  String thumbnail;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Override
public Tag convertTo(){
    slug = StringUtils.isBlank(slug) ? SlugUtils.slug(name) : SlugUtils.slug(slug);
    if (null == thumbnail) {
        thumbnail = "";
    }
    return InputConverter.super.convertTo();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertTo"))

;
Tag aux = restTemplate.getForObject(builder.toUriString(),Tag.class);
return aux;
}


@Override
public void update(Tag tag){
    slug = StringUtils.isBlank(slug) ? SlugUtils.slug(name) : SlugUtils.slug(slug);
    if (null == thumbnail) {
        thumbnail = "";
    }
    InputConverter.super.update(tag);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))

.queryParam("tag",tag)
;
restTemplate.put(builder.toUriString(),null);
}


}