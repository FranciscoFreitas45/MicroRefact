package run.halo.app.model.params;
 import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import run.halo.app.model.support.CreateCheck;
@EqualsAndHashCode(callSuper = true)
@Data
public class InstallParam extends UserParam{

 private  String locale;

@NotBlank(message = "博客名称不能为空", groups = CreateCheck.class)
 private  String title;

 private  String url;


}