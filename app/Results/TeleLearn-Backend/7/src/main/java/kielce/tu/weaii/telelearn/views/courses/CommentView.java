package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.models.courses.Comment;
import kielce.tu.weaii.telelearn.views.UserView;
import lombok.Value;
import java.time.LocalDateTime;
@Value
public class CommentView {

 private Long id;

 private UserView author;

 private LocalDateTime publicationTime;

 private String content;


public CommentView from(Comment model){
    return new CommentView(model.getId(), UserView.from(model.getAuthor(), false), model.getPublicationTime(), model.getContent());
}


}