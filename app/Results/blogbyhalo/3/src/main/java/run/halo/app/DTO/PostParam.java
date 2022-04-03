package run.halo.app.DTO;
 import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.PostMeta;
import run.halo.app.model.enums.PostEditorType;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.utils.SlugUtils;
public class PostParam implements InputConverter<Post>{

 private  String title;

 private  PostStatus status;

 private  String slug;

 private  PostEditorType editorType;

 private  String originalContent;

 private  String summary;

 private  String thumbnail;

 private  Boolean disallowComment;

 private  String password;

 private  String template;

 private  Integer topPriority;

 private  Date createTime;

 private  String metaKeywords;

 private  String metaDescription;

 private  Set<Integer> tagIds;

 private  Set<Integer> categoryIds;

 private  Set<PostMetaParam> metas;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Set<PostMeta> getPostMetas(){
    Set<PostMeta> postMetaSet = new HashSet<>();
    if (CollectionUtils.isEmpty(metas)) {
        return postMetaSet;
    }
    for (PostMetaParam postMetaParam : metas) {
        PostMeta postMeta = postMetaParam.convertTo();
        postMetaSet.add(postMeta);
    }
    return postMetaSet;
}


@Override
public Post convertTo(){
    slug = StringUtils.isBlank(slug) ? SlugUtils.slug(title) : SlugUtils.slug(slug);
    if (null == thumbnail) {
        thumbnail = "";
    }
    if (null == editorType) {
        editorType = PostEditorType.MARKDOWN;
    }
    return InputConverter.super.convertTo();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertTo"))

;
Post aux = restTemplate.getForObject(builder.toUriString(),Post.class);
return aux;
}


@Override
public void update(Post post){
    slug = StringUtils.isBlank(slug) ? SlugUtils.slug(title) : SlugUtils.slug(slug);
    if (null == thumbnail) {
        thumbnail = "";
    }
    if (null == editorType) {
        editorType = PostEditorType.MARKDOWN;
    }
    InputConverter.super.update(post);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))

.queryParam("post",post)
;
restTemplate.put(builder.toUriString(),null);
}


}