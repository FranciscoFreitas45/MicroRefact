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
import run.halo.app.model.dto.CategoryWithPostCountDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.PostCategory;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.service.base.CrudService;
public interface PostCategoryService extends CrudService<PostCategory, Integer>{


@NonNull
@Transactional
public List<PostCategory> removeByCategoryId(Integer categoryId)
;

@NonNull
public List<PostCategory> listByCategoryIdList(List<Integer> categoryIdList)
;

@NonNull
public List<Category> listCategoriesBy(Integer postId,boolean queryEncryptCategory)
;

@NonNull
public List<PostCategory> listByPostId(Integer postId)
;

@NonNull
public Page<Post> pagePostBy(Integer categoryId,Set<PostStatus> status,Pageable pageable)
;

@NonNull
@Transactional
public List<PostCategory> removeByPostId(Integer postId)
;

@NonNull
public List<Post> listPostBy(String slug,Set<PostStatus> status)
;

@NonNull
public List<CategoryWithPostCountDTO> listCategoryWithPostCountDto(Sort sort,boolean queryEncryptCategory)
;

@NonNull
public List<PostCategory> listByCategoryId(Integer categoryId)
;

@NonNull
public List<PostCategory> mergeOrCreateByIfAbsent(Integer postId,Set<Integer> categoryIds)
;

@NonNull
public Map<Integer,List<Category>> listCategoryListMap(Collection<Integer> postIds,boolean queryEncryptCategory)
;

@NonNull
public Set<Integer> listCategoryIdsByPostId(Integer postId)
;

}