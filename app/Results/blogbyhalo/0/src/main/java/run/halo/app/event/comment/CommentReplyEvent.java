package run.halo.app.event.comment;
 import org.springframework.lang.NonNull;
public class CommentReplyEvent extends AbstractCommentBaseEvent{

/**
 * Create a new ApplicationEvent.
 *
 * @param source the object on which the event initially occurred (never {@code null})
 * @param commentId comment id
 */
public CommentReplyEvent(Object source, @NonNull Long commentId) {
    super(source, commentId);
}
}