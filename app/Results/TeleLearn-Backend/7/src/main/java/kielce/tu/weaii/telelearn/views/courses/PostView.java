package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.models.courses.Post;
import kielce.tu.weaii.telelearn.models.courses.PostVisibility;
import kielce.tu.weaii.telelearn.views.UserView;
import lombok.Value;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors.toList;
@Value
public class PostView {

 private Long id;

 private Long courseId;

 private String content;

 private UserView author;

 private LocalDateTime publicationTime;

 private PostVisibility postVisibility;

 private boolean commentingAllowed;

 private List<AttachmentView> attachments;

 private int commentCount;


public PostView from(Post model){
    return new PostView(model.getId(), model.getCourse().getId(), model.getContent(), UserView.from(model.getAuthor(), false), model.getPublicationTime(), model.getPostVisibility(), model.isCommentingAllowed(), model.getAttachments().stream().map(AttachmentView::form).collect(toList()), model.getComments().size());
}


}