package com.dtdhehe.ptulife.repository;
 import com.dtdhehe.ptulife.entity.PtuNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Map;
public interface PtuNewsRepository extends JpaRepository<PtuNews, String>{


@Query(value = "select t from PtuNews t where t.userId=?1 and t.newsTitle like %?2%")
public Page<PtuNews> findByUserId(String userId,String newsTitle,Pageable pageable)
;

@Query(value = "select new map(t.userId as userId,t.newsTitle as newsTitle,t.newsAuthor as newsAuthor," + "t.newsComment as newsComment,t.newsDate as newsDate,t.newsDesc as newsDesc," + "t.newsIcon as newsIcon,t.newsId as newsId,u.headImg as headImg) from PtuNews t left join PtuUser u on t.userId=u.userId")
public Page<Map<String,Object>> findAllWithHead(Pageable pageable)
;

public void setNewsDate(String id,String newsDate);

}