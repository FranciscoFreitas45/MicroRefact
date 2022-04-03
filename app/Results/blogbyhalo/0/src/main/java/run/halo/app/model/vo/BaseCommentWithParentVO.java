package run.halo.app.model.vo;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import run.halo.app.model.dto.BaseCommentDTO;
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class BaseCommentWithParentVO extends BaseCommentDTOimplements Cloneable{

 private  BaseCommentWithParentVO parent;


@Override
public BaseCommentWithParentVO clone(){
    try {
        return (BaseCommentWithParentVO) super.clone();
    } catch (CloneNotSupportedException e) {
        log.error("Clone not support exception", e);
        return null;
    }
}


}