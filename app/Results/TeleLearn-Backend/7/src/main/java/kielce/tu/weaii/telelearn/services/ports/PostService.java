package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.courses.Comment;
import kielce.tu.weaii.telelearn.models.courses.Post;
import kielce.tu.weaii.telelearn.requests.courses.PostCommentRequest;
import kielce.tu.weaii.telelearn.requests.courses.PostRequest;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
public interface PostService {


public Post updatePost(Long id,PostRequest postRequest,List<MultipartFile> newAttachments)
;

public Post getById(Long id)
;

public void deleteComment(Long id)
;

public List<Comment> getComments(Long postId)
;

public Post addPost(PostRequest request,List<MultipartFile> attachments)
;

public List<Comment> addComment(Long postId,PostCommentRequest postCommentRequest)
;

public void removePost(Long id)
;

public List<Post> getCoursePosts(Long courseId)
;

}