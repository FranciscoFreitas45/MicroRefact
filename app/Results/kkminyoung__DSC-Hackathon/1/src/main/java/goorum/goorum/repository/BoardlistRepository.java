package goorum.goorum.repository;
 import goorum.goorum.domain.Boardlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface BoardlistRepository extends JpaRepository<Boardlist, Long>{


public List<Boardlist> findTop5ByOrderByLikesDesc()
;

@Query(value = "SELECT * FROM boardlist b WHERE b.category != '공지' ORDER BY b.likes DESC LIMIT 5", nativeQuery = true)
public List<Boardlist> findTopLikes()
;

@Query(value = "SELECT * FROM boardlist b where b.title LIKE %:keyword% OR b.content LIKE %:keyword%", nativeQuery = true)
public Page<Boardlist> findAllSearch(String keyword,Pageable pageRequest)
;

public List<Boardlist> findAllByCategory(String category)
;

public Page<Boardlist> findAllByWriterId(long writerId,Pageable pageRequest)
;

}