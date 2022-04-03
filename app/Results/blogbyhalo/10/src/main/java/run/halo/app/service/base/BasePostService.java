package run.halo.app.service.base;
 import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.dto.post.BasePostDetailDTO;
import run.halo.app.model.dto.post.BasePostMinimalDTO;
import run.halo.app.model.dto.post.BasePostSimpleDTO;
import run.halo.app.model.entity.BasePost;
import run.halo.app.model.enums.PostStatus;
public interface BasePostService extends CrudService<POST, Integer>{


@NonNull
public Page<POST> pageBy(PostStatus status,Pageable pageable)
;

@NonNull
public List<POST> listLatest(int top)
;

public long countByStatus(PostStatus status)
;

@NonNull
public List<POST> listAllBy(PostStatus status)
;

@NonNull
public POST updateDraftContent(String content,Integer postId)
;

@NonNull
public Page<BasePostMinimalDTO> convertToMinimal(Page<POST> postPage)
;

public long countLike()
;

@NonNull
public Optional<POST> getPrevPost(POST post)
;

@NonNull
public Page<POST> pageLatest(int top)
;

@NonNull
public POST createOrUpdateBy(POST post)
;

@NonNull
public POST filterIfEncrypt(POST post)
;

@NonNull
public List<POST> listNextPosts(POST post,int size)
;

public void increaseVisit(Integer postId)
;

public long countVisit()
;

@NonNull
public BasePostDetailDTO convertToDetail(POST post)
;

@NonNull
public POST getBy(PostStatus status,Integer id)
;

@NonNull
public Optional<POST> getNextPost(POST post)
;

@NonNull
public POST updateStatus(PostStatus status,Integer postId)
;

@NonNull
public Page<BasePostSimpleDTO> convertToSimple(Page<POST> postPage)
;

@NonNull
public POST getBySlug(String slug)
;

public void increaseLike(Integer postId)
;

@NonNull
public List<POST> updateStatusByIds(List<Integer> ids,PostStatus status)
;

@NonNull
public List<POST> listPrevPosts(POST post,int size)
;

public String generateDescription(String content)
;

}