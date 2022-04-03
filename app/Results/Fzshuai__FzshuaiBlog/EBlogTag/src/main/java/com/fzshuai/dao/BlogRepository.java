package com.fzshuai.dao;
 import com.fzshuai.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public interface BlogRepository extends JpaSpecificationExecutor<Blog>, JpaRepository<Blog, Long>{


@Query("select b from Blog b where b.title like ?1 or b.content like ?1")
public Page<Blog> findByQuery(String query,Pageable pageable)
;

@Query("select function('date_format',b.createTime,'%Y') as year from Blog b group by function('date_format',b.createTime,'%Y') order by year desc ")
public List<String> findGroupYear()
;

@Query("select b from Blog b where b.recommend = true")
public List<Blog> findTop(Pageable pageable)
;

@Query("select b from Blog b where function('date_format',b.createTime,'%Y') = ?1")
public List<Blog> findByYear(String year)
;

@Transactional
@Modifying
@Query("update Blog b set b.views = b.views+1 where b.id = ?1")
public int updateViews(Long id)
;

public Blog getBlog(Long id);

public void setBlog(Long id,Blog blog);

public void setBlogs(Long id,List<Blog> blogs);

public List<Blog> getBlogs(Long id);

public void setBlogs(Long id,List<Blog> blogs);

public List<Blog> getBlogs(Long id);

public void setCreateTime(Long id,Date createTime);

public void setViews(Long id,Integer views);

public void setUser(Long id,User user);

public void setType(Long id,Type type);

}