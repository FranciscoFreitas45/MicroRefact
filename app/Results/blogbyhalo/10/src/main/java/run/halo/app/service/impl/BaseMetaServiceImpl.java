package run.halo.app.service.impl;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.model.dto.BaseMetaDTO;
import run.halo.app.model.entity.BaseMeta;
import run.halo.app.model.params.BaseMetaParam;
import run.halo.app.repository.base.BaseMetaRepository;
import run.halo.app.service.base.AbstractCrudService;
import run.halo.app.service.base.BaseMetaService;
import run.halo.app.utils.ServiceUtils;
@Slf4j
public class BaseMetaServiceImpl extends AbstractCrudService<META, Long>implements BaseMetaService<META>{

 private  BaseMetaRepository<META> baseMetaRepository;

public BaseMetaServiceImpl(BaseMetaRepository<META> baseMetaRepository) {
    super(baseMetaRepository);
    this.baseMetaRepository = baseMetaRepository;
}
@Override
@NonNull
public META createBy(BaseMetaParam<META> metaParam){
    Assert.notNull(metaParam, "Meta param must not be null");
    return create(metaParam.convertTo());
}


@Override
public Map<String,Object> convertToMap(List<META> metas){
    return ServiceUtils.convertToMap(metas, META::getKey, META::getValue);
}


@Override
public List<META> removeByPostId(Integer postId){
    Assert.notNull(postId, "Post id must not be null of removeByPostId");
    return baseMetaRepository.deleteByPostId(postId);
}


@Override
@NonNull
public META create(META meta){
    Assert.notNull(meta, "Domain must not be null");
    // Check post id
    if (!ServiceUtils.isEmptyId(meta.getPostId())) {
        validateTarget(meta.getPostId());
    }
    // Create meta
    return super.create(meta);
}


@Override
@NonNull
public List<BaseMetaDTO> convertTo(List<META> postMetaList){
    if (CollectionUtils.isEmpty(postMetaList)) {
        return Collections.emptyList();
    }
    return postMetaList.stream().map(this::convertTo).collect(Collectors.toList());
}


@Override
@NonNull
public List<META> listBy(Integer postId){
    Assert.notNull(postId, "Post id must not be null");
    return baseMetaRepository.findAllByPostId(postId);
}


@Override
@Transactional
public List<META> createOrUpdateByPostId(Integer postId,Set<META> metas){
    Assert.notNull(postId, "Post id must not be null");
    // firstly remove post metas by post id
    removeByPostId(postId);
    if (CollectionUtils.isEmpty(metas)) {
        return Collections.emptyList();
    }
    // Save post metas
    metas.forEach(postMeta -> {
        if (StringUtils.isNotEmpty(postMeta.getValue()) && StringUtils.isNotEmpty(postMeta.getKey())) {
            postMeta.setPostId(postId);
            baseMetaRepository.save(postMeta);
        }
    });
    return new ArrayList<>(metas);
}


@Override
public Map<Integer,List<META>> listPostMetaAsMap(Set<Integer> postIds){
    Assert.notNull(postIds, "Post ids must not be null");
    if (CollectionUtils.isEmpty(postIds)) {
        return Collections.emptyMap();
    }
    // Find all metas
    List<META> metas = baseMetaRepository.findAllByPostIdIn(postIds);
    // Convert to meta map
    Map<Long, META> postMetaMap = ServiceUtils.convertToMap(metas, META::getId);
    // Create category list map
    Map<Integer, List<META>> postMetaListMap = new HashMap<>();
    // Foreach and collect
    metas.forEach(meta -> postMetaListMap.computeIfAbsent(meta.getPostId(), postId -> new LinkedList<>()).add(postMetaMap.get(meta.getId())));
    return postMetaListMap;
}


}