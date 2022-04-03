package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.domain.PersistentAuditEvent;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PersistenceAuditEventRepository extends JpaRepository<PersistentAuditEvent, Long>{


public List<PersistentAuditEvent> findByAuditEventDateAfter(LocalDateTime after)
;

public List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(String principal,LocalDateTime after)
;

public Page<PersistentAuditEvent> findAllByAuditEventDateBetween(LocalDateTime fromDate,LocalDateTime toDate,Pageable pageable)
;

public List<PersistentAuditEvent> findByPrincipal(String principal)
;

public List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(String principle,LocalDateTime after,String type)
;

}