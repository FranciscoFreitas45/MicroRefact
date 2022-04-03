package br.com.fatecmogidascruzes.news.data;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.fatecmogidascruzes.data.DAOImpl;
import br.com.fatecmogidascruzes.news.News;
public interface NewsDAO extends JpaRepository<News, Long>, DAOImpl<News, Long>{


public List<News> findByEnabledTrueAndHideFalseAndHighlightTrueOrderByReferenceDateDesc()
;

public Page<News> getByEnabledTrueAndHideFalseAndHighlightFalse(Pageable pageable)
;

@Query("SELECT ne FROM News ne WHERE TRUE = ne.enabled AND (UPPER(ne.name) LIKE CONCAT('%',:filter,'%') OR UPPER(ne.shortDescription) LIKE CONCAT('%',:filter,'%') OR UPPER(ne.longDescription) LIKE CONCAT('%',:filter,'%'))")
public Page<News> getEnabledByFilter(String filter,Pageable pageable)
;

}