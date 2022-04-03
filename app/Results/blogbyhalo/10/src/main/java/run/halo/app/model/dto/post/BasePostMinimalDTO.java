package run.halo.app.model.dto.post;
 import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.BasePost;
import run.halo.app.model.enums.PostEditorType;
import run.halo.app.model.enums.PostStatus;
@Data
@ToString
@EqualsAndHashCode
public class BasePostMinimalDTO implements OutputConverter<BasePostMinimalDTO, BasePost>{

 private  Integer id;

 private  String title;

 private  PostStatus status;

 private  String slug;

 private  PostEditorType editorType;

 private  Date updateTime;

 private  Date createTime;

 private  Date editTime;

 private  String metaKeywords;

 private  String metaDescription;

 private  String fullPath;


}