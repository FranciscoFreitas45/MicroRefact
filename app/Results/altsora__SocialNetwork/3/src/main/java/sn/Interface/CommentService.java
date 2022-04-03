package sn.Interface;
public interface CommentService {

   public List<CommentResponse> getCommentsByPostId(long postId);
}