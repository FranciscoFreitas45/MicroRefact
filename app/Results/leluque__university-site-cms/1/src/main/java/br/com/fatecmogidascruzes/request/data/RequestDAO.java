package br.com.fatecmogidascruzes.request.data;
 import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.fatecmogidascruzes.data.DAOImpl;
import br.com.fatecmogidascruzes.request.Request;
public interface RequestDAO extends DAOImpl<Request, Long>, JpaRepository<Request, Long>{


public Optional<Request> getByEnabledTrueAndProtocol(Long protocol)
;

@Query("SELECT re FROM Request re WHERE TRUE = re.enabled AND (UPPER(re.protocol) LIKE CONCAT('%',:filter,'%') OR UPPER(re.name) LIKE CONCAT('%',:filter,'%') OR UPPER(re.comments) LIKE CONCAT('%',:filter,'%'))")
public Page<Request> getEnabledByFilter(String filter,Pageable pageable)
;

}