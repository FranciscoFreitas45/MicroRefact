package edu.xr.campusweibo.service;
 import edu.xr.campusweibo.config.audit.AuditEventConverter;
import edu.xr.campusweibo.repository.PersistenceAuditEventRepository;
import java.time.LocalDateTime;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
@Transactional
public class AuditEventService {

 private  PersistenceAuditEventRepository persistenceAuditEventRepository;

 private  AuditEventConverter auditEventConverter;

public AuditEventService(PersistenceAuditEventRepository persistenceAuditEventRepository, AuditEventConverter auditEventConverter) {
    this.persistenceAuditEventRepository = persistenceAuditEventRepository;
    this.auditEventConverter = auditEventConverter;
}
public Optional<AuditEvent> find(Long id){
    return Optional.ofNullable(persistenceAuditEventRepository.findOne(id)).map(auditEventConverter::convertToAuditEvent);
}


public Page<AuditEvent> findByDates(LocalDateTime fromDate,LocalDateTime toDate,Pageable pageable){
    return persistenceAuditEventRepository.findAllByAuditEventDateBetween(fromDate, toDate, pageable).map(auditEventConverter::convertToAuditEvent);
}


public Page<AuditEvent> findAll(Pageable pageable){
    return persistenceAuditEventRepository.findAll(pageable).map(auditEventConverter::convertToAuditEvent);
}


}