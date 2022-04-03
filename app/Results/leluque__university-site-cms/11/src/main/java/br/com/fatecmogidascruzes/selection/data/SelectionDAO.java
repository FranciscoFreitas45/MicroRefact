package br.com.fatecmogidascruzes.selection.data;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.fatecmogidascruzes.data.DAOImpl;
import br.com.fatecmogidascruzes.selection.Selection;
public interface SelectionDAO extends JpaRepository<Selection, Long>, DAOImpl<Selection, Long>{


@Query("SELECT se FROM Selection se WHERE TRUE = se.enabled AND (UPPER(se.number) LIKE CONCAT('%',:filter,'%') OR UPPER(se.description) LIKE CONCAT('%',:filter,'%'))")
public Page<Selection> getEnabledByFilter(String filemr,Pageable pageable)
;

}