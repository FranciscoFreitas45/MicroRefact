package run.halo.app.service;
 import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.Tag;
import run.halo.app.service.base.CrudService;
public interface TagService extends CrudService<Tag, Integer>{


@NonNull
public Tag getBySlugOfNonNull(String slug)
;

@Nullable
public Tag getByName(String name)
;

@NonNull
public List<TagDTO> convertTo(List<Tag> tags)
;

@NonNull
public Tag getBySlug(String slug)
;

}