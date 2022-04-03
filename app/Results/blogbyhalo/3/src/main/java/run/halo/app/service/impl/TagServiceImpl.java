package run.halo.app.service.impl;
 import run.halo.app.model.support.HaloConst.URL_SEPARATOR;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.exception.AlreadyExistsException;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.Tag;
import run.halo.app.repository.TagRepository;
import run.halo.app.service.OptionService;
import run.halo.app.service.TagService;
import run.halo.app.service.base.AbstractCrudService;
import run.halo.app.Interface.OptionService;
@Slf4j
@Service
public class TagServiceImpl extends AbstractCrudService<Tag, Integer>implements TagService{

 private  TagRepository tagRepository;

 private  OptionService optionService;

public TagServiceImpl(TagRepository tagRepository, OptionService optionService) {
    super(tagRepository);
    this.tagRepository = tagRepository;
    this.optionService = optionService;
}
@Override
public Tag getBySlugOfNonNull(String slug){
    return tagRepository.getBySlug(slug).orElseThrow(() -> new NotFoundException("查询不到该标签的信息").setErrorData(slug));
}


@Override
public Tag getByName(String name){
    return tagRepository.getByName(name).orElse(null);
}


@Override
@Transactional
public Tag create(Tag tag){
    // Check if the tag is exist
    long count = tagRepository.countByNameOrSlug(tag.getName(), tag.getSlug());
    log.debug("Tag count: [{}]", count);
    if (count > 0) {
        // If the tag has exist already
        throw new AlreadyExistsException("该标签已存在").setErrorData(tag);
    }
    // Get tag name
    return super.create(tag);
}


@Override
public List<TagDTO> convertTo(List<Tag> tags){
    if (CollectionUtils.isEmpty(tags)) {
        return Collections.emptyList();
    }
    return tags.stream().map(this::convertTo).collect(Collectors.toList());
}


@Override
public Tag getBySlug(String slug){
    return tagRepository.getBySlug(slug).orElse(null);
}


}