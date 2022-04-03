package sn.service;
 import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sn.api.requests.PostCommentCreateRequest;
import sn.api.response.CommentResponse;
import sn.api.response.IdResponse;
import sn.model.Comment;
import sn.model.Post;
import sn.repositories.CommentRepository;
import sn.utils.TimeUtil;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

@Autowired
 private  CommentRepository commentRepository;


public Comment findById(long commentId){
    return commentRepository.findById(commentId).orElse(null);
}


public List<CommentResponse> getCommentsByPostId(long postId){
    Sort sort = Sort.by(Sort.Direction.ASC, CommentRepository.COMMENT_TIME);
    List<Comment> comments = commentRepository.findAllCommentsByPostId(postId, sort);
    List<CommentResponse> commentResponses = new ArrayList<>();
    for (Comment comment : comments) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setPostId(comment.getPost().getId());
        commentResponse.setAuthorId(comment.getAuthor().getId());
        commentResponse.setCommentText(comment.getText());
        commentResponse.setTime(TimeUtil.getTimestampFromLocalDateTime(comment.getTime()));
        commentResponse.setBlocked(comment.isBlocked());
        Comment parent = comment.getParent();
        if (parent != null) {
            commentResponse.setParentId(parent.getParent().getId());
        }
        commentResponses.add(commentResponse);
    }
    return commentResponses;
}


}