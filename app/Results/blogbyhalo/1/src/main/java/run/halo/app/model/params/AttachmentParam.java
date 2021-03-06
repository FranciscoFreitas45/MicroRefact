package run.halo.app.model.params;
 import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.Attachment;
@Data
public class AttachmentParam implements InputConverter<Attachment>{

@NotBlank(message = "附件名称不能为空")
@Size(max = 255, message = "附件名称的字符长度不能超过 {max}")
 private  String name;


}