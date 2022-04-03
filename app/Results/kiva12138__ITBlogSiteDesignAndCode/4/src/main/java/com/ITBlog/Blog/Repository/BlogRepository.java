package com.ITBlog.Blog.Repository;
 import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ITBlog.Blog.Entity;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{


@Query("select blog from Blog blog where blog.title like %:title%")
public List<Blog> findBlogLikeTitle(String title)
;

@Modifying
@Query(value = "UPDATE Blog blog SET blog.commentsNum=:commentsNum WHERE blog.blogId=:blogId")
public int updateCommentsNum(int commentsNum,long blogId)
;

@Modifying
@Query(value = "UPDATE Blog blog SET blog.context=:context WHERE blog.blogId=:blogId")
public int updateContext(String context,long blogId)
;

@Modifying
@Query(value = "UPDATE Blog blog SET blog.time=:time WHERE blog.blogId=:blogId")
public int updateTime(Date time,long blogId)
;

@Modifying
@Query(value = "UPDATE Blog blog SET blog.readNum=:readNum WHERE blog.blogId=:blogId")
public int updateReadNum(int readNum,long blogId)
;

@Modifying
@Query(value = "UPDATE Blog blog SET blog.title=:title WHERE blog.blogId=:blogId")
public int updateTitle(String title,long blogId)
;

@Query(value = "select blog from Blog blog where blog.blogId=:blogId")
public Blog findBlogById(long blogId)
;

}