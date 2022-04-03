package run.halo.app.model.vo;
 import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.model.dto.BaseMetaDTO;
import run.halo.app.model.dto.post.BasePostDetailDTO;
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SheetDetailVO extends BasePostDetailDTO{

 private  Set<Long> metaIds;

 private  List<BaseMetaDTO> metas;


}