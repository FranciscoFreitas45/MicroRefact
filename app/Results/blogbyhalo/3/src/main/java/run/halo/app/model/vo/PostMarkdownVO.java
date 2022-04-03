package run.halo.app.model.vo;
 import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class PostMarkdownVO {

 private  String title;

 private  String slug;

 private  String originalContent;

 private  String frontMatter;


}