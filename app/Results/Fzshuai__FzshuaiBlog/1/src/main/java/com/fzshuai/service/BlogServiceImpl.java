package com.fzshuai.service;
 import com.fzshuai.NotFoundException;
import com.fzshuai.dao.BlogRepository;
import com.fzshuai.po.Blog;
import com.fzshuai.po.Type;
import com.fzshuai.util.MyBeanUtils;
import com.fzshuai.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fzshuai.Interface.BlogRepository;
@Service
public class BlogServiceImpl implements BlogService{

@Autowired
 private  BlogRepository blogRepository;


@Override
public List<Blog> listRecommendBlogTop(Integer size){
    Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
    Pageable pageable = PageRequest.of(0, size, sort);
    return blogRepository.findTop(pageable);
}


@Transactional
@Override
public Blog saveBlog(Blog blog){
    if (blog.getId() == null) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
    } else {
        blog.setUpdateTime(new Date());
    }
    return blogRepository.save(blog);
}


@Override
public Blog getBlog(Long id){
    return blogRepository.findById(id).orElse(null);
}


@Transactional
@Override
public void deleteBlog(Long id){
    blogRepository.deleteById(id);
}


@Override
public Page<Blog> listBlog(Pageable pageable){
    return blogRepository.findAll(pageable);
}


@Override
public Predicate toPredicate(Root<Blog> root,CriteriaQuery<?> cq,CriteriaBuilder cb){
    List<Predicate> predicates = new ArrayList<>();
    if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
        predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
    }
    if (blog.getTypeId() != null) {
        predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
    }
    if (blog.isRecommend()) {
        predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
    }
    cq.where(predicates.toArray(new Predicate[predicates.size()]));
    return null;
}


@Transactional
@Override
public Blog updateBlog(Long id,Blog blog){
    Blog b = blogRepository.findById(id).orElse(null);
    if (b == null) {
        throw new NotFoundException("该博客不存在");
    }
    BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
    b.setUpdateTime(new Date());
    return blogRepository.save(b);
}


}