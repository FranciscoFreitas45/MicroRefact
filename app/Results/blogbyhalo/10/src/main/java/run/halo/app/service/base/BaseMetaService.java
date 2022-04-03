package run.halo.app.service.base;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.lang.NonNull;
import run.halo.app.model.dto.BaseMetaDTO;
import run.halo.app.model.entity.BaseMeta;
import run.halo.app.model.params.BaseMetaParam;
public interface BaseMetaService extends CrudService<M, Long>{


@NonNull
public M createBy(BaseMetaParam<M> metaParam)
;

public Map<String,Object> convertToMap(List<M> metas)
;

public List<M> removeByPostId(Integer postId)
;

@NonNull
@Override
public M create(M meta)
;

public void validateTarget(Integer targetId)
;

@NonNull
public List<BaseMetaDTO> convertTo(List<M> postMetaList)
;

@NonNull
public List<M> listBy(Integer postId)
;

public List<M> createOrUpdateByPostId(Integer postId,Set<M> metas)
;

public Map<Integer,List<M>> listPostMetaAsMap(Set<Integer> postIds)
;

}