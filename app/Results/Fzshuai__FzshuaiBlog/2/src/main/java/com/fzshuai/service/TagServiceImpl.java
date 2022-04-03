package com.fzshuai.service;
 import com.fzshuai.NotFoundException;
import com.fzshuai.dao.TagRepository;
import com.fzshuai.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import com.fzshuai.Interface.TagRepository;
@Service
public class TagServiceImpl implements TagService{

@Autowired
 private  TagRepository tagRepository;


@Transactional
@Override
public Tag updateTag(Long id,Tag tag){
    Tag t = tagRepository.findById(id).orElse(null);
    if (t == null) {
        throw new NotFoundException("不存在该标签");
    }
    BeanUtils.copyProperties(tag, t);
    return tagRepository.save(t);
}


@Transactional
@Override
public void deleteTag(Long id){
    tagRepository.deleteById(id);
}


@Override
public List<Tag> listTag(String ids){
    // 1,2,3
    return tagRepository.findAllById(convertToList(ids));
}


@Override
public Tag getTagByName(String name){
    return tagRepository.findByName(name);
}


@Transactional
@Override
public Tag getTag(Long id){
    return tagRepository.findById(id).orElse(null);
}


@Override
public List<Tag> listTagTop(Integer size){
    Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
    Pageable pageable = PageRequest.of(0, size, sort);
    return tagRepository.findTop(pageable);
}


@Transactional
@Override
public Tag saveTag(Tag tag){
    return tagRepository.save(tag);
}


public List<Long> convertToList(String ids){
    List<Long> list = new ArrayList<>();
    if (!"".equals(ids) && ids != null) {
        String[] idarray = ids.split(",");
        for (int i = 0; i < idarray.length; i++) {
            list.add(new Long(idarray[i]));
        }
    }
    return list;
}


}