package run.halo.app.service;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.Category;
import run.halo.app.model.vo.CategoryVO;
import run.halo.app.service.base.CrudService;
@Transactional(readOnly = true)
public interface CategoryService extends CrudService<Category, Integer>{


@NonNull
public List<CategoryVO> listAsTree(Sort sort)
;

public List<Category> listByParentId(Integer id)
;

@NonNull
public Boolean categoryHasEncrypt(Integer categoryId)
;

@NonNull
public List<Category> filterEncryptCategory(List<Category> categories)
;

@NonNull
public Category getBySlugOfNonNull(String slug,boolean queryEncryptCategory)
;

@Nullable
public Category getByName(String name)
;

public void refreshPostStatus(List<Integer> affectedPostIdList)
;

@NonNull
public List<CategoryDTO> convertTo(List<Category> categories)
;

public Category getBySlug(String slug)
;

@NonNull
public List<Category> listAll(Sort sort,boolean queryEncryptCategory)
;

@NonNull
public List<Category> listAllByIds(Collection<Integer> ids,boolean queryEncryptCategory)
;

@Transactional
public void removeCategoryAndPostCategoryBy(Integer categoryId)
;

}