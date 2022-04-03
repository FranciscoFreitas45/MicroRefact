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
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.entity.SheetMeta;
import run.halo.app.model.enums.PostEditorType;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.utils.SlugUtils;
public class SheetParam implements InputConverter<Sheet>{

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

 private  Set<SheetMetaParam> metas;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Set<SheetMeta> getSheetMetas(){
    Set<SheetMeta> sheetMetasSet = new HashSet<>();
    if (CollectionUtils.isEmpty(metas)) {
        return sheetMetasSet;
    }
    for (SheetMetaParam sheetMetaParam : metas) {
        SheetMeta sheetMeta = sheetMetaParam.convertTo();
        sheetMetasSet.add(sheetMeta);
    }
    return sheetMetasSet;
}


@Override
public Sheet convertTo(){
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
Sheet aux = restTemplate.getForObject(builder.toUriString(),Sheet.class);
return aux;
}


@Override
public void update(Sheet sheet){
    slug = StringUtils.isBlank(slug) ? SlugUtils.slug(title) : SlugUtils.slug(slug);
    if (null == thumbnail) {
        thumbnail = "";
    }
    if (null == editorType) {
        editorType = PostEditorType.MARKDOWN;
    }
    InputConverter.super.update(sheet);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))

.queryParam("sheet",sheet)
;
restTemplate.put(builder.toUriString(),null);
}


}