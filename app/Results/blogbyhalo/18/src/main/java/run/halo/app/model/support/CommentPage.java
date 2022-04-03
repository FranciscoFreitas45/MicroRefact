package run.halo.app.model.support;
 import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CommentPage extends PageImpl<T>{

 private  long commentCount;

public CommentPage(List<T> content, Pageable pageable, long topTotal, long commentCount) {
    super(content, pageable, topTotal);
    this.commentCount = commentCount;
}public CommentPage(List<T> content, long commentCount) {
    super(content);
    this.commentCount = commentCount;
}
}