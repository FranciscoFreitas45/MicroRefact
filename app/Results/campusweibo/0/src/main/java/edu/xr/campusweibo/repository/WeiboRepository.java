package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.domain.Weibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface WeiboRepository extends JpaRepository<Weibo, Long>{


@Query(value = "Select * from w_weibo weibo WHERE weibo.uid=?1 ORDER BY weibo.create_date DESC ", nativeQuery = true)
public List<Weibo> findAllByUid(Long id)
;

public Weibo findByTextAndUid(String text,Long uid)
;

}