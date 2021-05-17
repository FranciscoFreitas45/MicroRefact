import cn.com.cnc.fcc.domain.PersistentAuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;
public interface PersistenceAuditEventRepository extends JpaRepository<PersistentAuditEvent, Long> {


public List<PersistentAuditEvent> findByAuditEventDateAfter(Instant after)


public List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(String principal,Instant after)


public Page<PersistentAuditEvent> findAllByAuditEventDateBetween(Instant fromDate,Instant toDate,Pageable pageable)


public List<PersistentAuditEvent> findByPrincipal(String principal)


public List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(String principal,Instant after,String type)


}