package run.halo.app.model.dto;
 import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.BaseMeta;
@Data
@ToString
@EqualsAndHashCode
public class BaseMetaDTO implements OutputConverter<BaseMetaDTO, BaseMeta>{

 private  Long id;

 private  Integer postId;

 private  String key;

 private  String value;

 private  Date createTime;


}