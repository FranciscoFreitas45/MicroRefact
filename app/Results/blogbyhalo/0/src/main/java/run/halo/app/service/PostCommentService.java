package run.halo.app.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.entity.PostComment;
import run.halo.app.model.vo.PostCommentWithPostVO;
import run.halo.app.service.base.BaseCommentService;
public interface PostCommentService extends BaseCommentService<PostComment>{


public void validateCommentBlackListStatus()
;

@NonNull
public List<PostCommentWithPostVO> convertToWithPostVo(List<PostComment> postComments)
;

}