package run.halo.app.service;
 import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.model.dto.TagWithPostCountDTO;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.PostTag;
import run.halo.app.model.entity.Tag;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.service.base.CrudService;
public interface PostTagService extends CrudService<PostTag, Integer>{


public Page<Post> pagePostsBy(Integer tagId,PostStatus status,Pageable pageable)
;

@NonNull
public Map<Integer,List<Tag>> listTagListMapBy(Collection<Integer> postIds)
;

@NonNull
public List<Post> listPostsBy(String slug,PostStatus status)
;

@NonNull
public List<PostTag> listByPostId(Integer postId)
;

@NonNull
public List<PostTag> listByTagId(Integer tagId)
;

@NonNull
@Transactional
public List<PostTag> removeByTagId(Integer tagId)
;

@NonNull
public Set<Integer> listTagIdsByPostId(Integer postId)
;

@NonNull
@Transactional
public List<PostTag> removeByPostId(Integer postId)
;

@NonNull
public List<PostTag> mergeOrCreateByIfAbsent(Integer postId,Set<Integer> tagIds)
;

@NonNull
public List<Tag> listTagsBy(Integer postId)
;

@NonNull
public List<TagWithPostCountDTO> listTagWithCountDtos(Sort sort)
;

}