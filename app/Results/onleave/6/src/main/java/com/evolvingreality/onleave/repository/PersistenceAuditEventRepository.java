package com.evolvingreality.onleave.repository;
 import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.PersistentAuditEvent;
import java.util.List;
public interface PersistenceAuditEventRepository extends JpaRepository<PersistentAuditEvent, String>{


public List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(String principal,LocalDateTime after)
;

public List<PersistentAuditEvent> findAllByAuditEventDateBetween(LocalDateTime fromDate,LocalDateTime toDate)
;

public List<PersistentAuditEvent> findByPrincipal(String principal)
;

}