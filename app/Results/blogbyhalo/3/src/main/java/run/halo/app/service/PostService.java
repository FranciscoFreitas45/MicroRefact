package run.halo.app.service;
 import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.PostMeta;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.params.PostQuery;
import run.halo.app.model.vo.ArchiveMonthVO;
import run.halo.app.model.vo.ArchiveYearVO;
import run.halo.app.model.vo.PostDetailVO;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.model.vo.PostMarkdownVO;
import run.halo.app.service.base.BasePostService;
public interface PostService extends BasePostService<Post>{


@NonNull
public Page<Post> pageBy(String keyword,Pageable pageable)
;

@NonNull
public List<PostMarkdownVO> listPostMarkdowns()
;

public List<ArchiveYearVO> convertToYearArchives(List<Post> posts)
;

@NotNull
public Sort getPostDefaultSort()
;

public List<ArchiveMonthVO> convertToMonthArchives(List<Post> posts)
;

@NonNull
public List<ArchiveYearVO> listYearArchives()
;

public List<PostListVO> convertToListVo(List<Post> posts,boolean queryEncryptCategory)
;

@NonNull
public PostDetailVO createBy(Post post,Set<Integer> tagIds,Set<Integer> categoryIds,boolean autoSave)
;

@NonNull
public List<Post> removeByIds(Collection<Integer> ids)
;

@NonNull
public PostDetailVO updateBy(Post postToUpdate,Set<Integer> tagIds,Set<Integer> categoryIds,Set<PostMeta> metas,boolean autoSave)
;

@NonNull
public String exportMarkdown(Post post)
;

@NonNull
public Post getBy(Integer year,Integer month,Integer day,String slug,PostStatus status)
;

@NonNull
public List<ArchiveMonthVO> listMonthArchives()
;

@NonNull
public PostDetailVO importMarkdown(String markdown,String filename)
;

public void publishVisitEvent(Integer postId)
;

@NonNull
public PostDetailVO convertToDetailVo(Post post,boolean queryEncryptCategory)
;

}