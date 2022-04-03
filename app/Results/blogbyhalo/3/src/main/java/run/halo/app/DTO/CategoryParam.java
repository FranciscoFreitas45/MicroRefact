package run.halo.app.DTO;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Category;
import run.halo.app.utils.SlugUtils;
public class CategoryParam implements InputConverter<Category>{

 private  String name;

 private  String slug;

 private  String description;

 private  String thumbnail;

 private  String password;

 private  Integer parentId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Override
public Category convertTo(){
    slug = StringUtils.isBlank(slug) ? SlugUtils.slug(name) : SlugUtils.slug(slug);
    if (null == thumbnail) {
        thumbnail = "";
    }
    return InputConverter.super.convertTo();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertTo"))

;
Category aux = restTemplate.getForObject(builder.toUriString(),Category.class);
return aux;
}


@Override
public void update(Category category){
    slug = StringUtils.isBlank(slug) ? SlugUtils.slug(name) : SlugUtils.slug(slug);
    if (null == thumbnail) {
        thumbnail = "";
    }
    InputConverter.super.update(category);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))

.queryParam("category",category)
;
restTemplate.put(builder.toUriString(),null);
}


}