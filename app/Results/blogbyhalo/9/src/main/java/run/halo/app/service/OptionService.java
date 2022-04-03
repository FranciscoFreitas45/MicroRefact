package run.halo.app.service;
 import com.qiniu.common.Zone;
import com.qiniu.storage.Region;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.exception.MissingPropertyException;
import run.halo.app.model.dto.OptionDTO;
import run.halo.app.model.dto.OptionSimpleDTO;
import run.halo.app.model.entity.Option;
import run.halo.app.model.enums.PostPermalinkType;
import run.halo.app.model.enums.SheetPermalinkType;
import run.halo.app.model.enums.ValueEnum;
import run.halo.app.model.params.OptionParam;
import run.halo.app.model.params.OptionQuery;
import run.halo.app.model.properties.PropertyEnum;
import run.halo.app.service.base.CrudService;
public interface OptionService extends CrudService<Option, Integer>{

 private int DEFAULT_POST_PAGE_SIZE;

 private int DEFAULT_ARCHIVES_PAGE_SIZE;

 private int DEFAULT_COMMENT_PAGE_SIZE;

 private int DEFAULT_RSS_PAGE_SIZE;

 private String OPTIONS_KEY;


@NonNull
public Map<String,Object> listOptions(Collection<String> keys)
;

public Page<OptionSimpleDTO> pageDtosBy(Pageable pageable,OptionQuery optionQuery)
;

@Transactional
public void saveProperty(PropertyEnum property,String value)
;

@NonNull
@Deprecated
public Zone getQnYunZone()
;

public String getSeoDescription()
;

@NonNull
public Optional<T> getEnumByProperty(PropertyEnum property,Class<T> valueType)
;

public void save(OptionParam optionParam)
;

public void update(Integer optionId,OptionParam optionParam)
;

public int getCommentPageSize()
;

public int getPostPageSize()
;

public String getSheetPrefix()
;

public int getRssPageSize()
;

public int getArchivesPageSize()
;

@Transactional
public void saveProperties(Map<? extends PropertyEnum,String> properties)
;

@NonNull
public Option removePermanently(Integer id)
;

public PostPermalinkType getPostPermalinkType()
;

public SheetPermalinkType getSheetPermalinkType()
;

public T getByPropertyOrDefault(PropertyEnum property,Class<T> propertyType)
;

public Optional<T> getByProperty(PropertyEnum property,Class<T> propertyType)
;

@NonNull
public List<OptionDTO> listDtos()
;

public String getPhotosPrefix()
;

public String getJournalsPrefix()
;

public Boolean isEnabledAbsolutePath()
;

public long getBirthday()
;

public String getArchivesPrefix()
;

@Nullable
public Object getByPropertyOfNullable(PropertyEnum property)
;

@NonNull
public String getBlogTitle()
;

@NonNull
public Optional<T> getByKey(String key,Class<T> valueType)
;

@NonNull
public Object getByKeyOfNonNull(String key)
;

public String getLinksPrefix()
;

@NonNull
public OptionSimpleDTO convertToDto(Option option)
;

@Nullable
public E getValueEnumByPropertyOrDefault(PropertyEnum property,Class<V> valueType,Class<E> enumType,E defaultValue)
;

@NonNull
public Object getByPropertyOfNonNull(PropertyEnum property)
;

@NonNull
public String getBlogBaseUrl()
;

public String getPathSuffix()
;

public String getSeoKeywords()
;

@NonNull
public Optional<E> getValueEnumByProperty(PropertyEnum property,Class<V> valueType,Class<E> enumType)
;

@NonNull
public Region getQiniuRegion()
;

public String getTagsPrefix()
;

public T getByKeyOrDefault(String key,Class<T> valueType,T defaultValue)
;

@NonNull
public Locale getLocale()
;

@Nullable
public Object getByKeyOfNullable(String key)
;

@Nullable
public T getEnumByPropertyOrDefault(PropertyEnum property,Class<T> valueType,T defaultValue)
;

public String getCategoriesPrefix()
;

}