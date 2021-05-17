import cn.com.cnc.fcc.config.audit.AuditEventConverter;
import cn.com.cnc.fcc.repository.PersistenceAuditEventRepository;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.Optional;
@Service
@Transactional
public class AuditEventService {

 private  PersistenceAuditEventRepository persistenceAuditEventRepository;

 private  AuditEventConverter auditEventConverter;


public Optional<AuditEvent> find(Long id){
    return Optional.ofNullable(persistenceAuditEventRepository.findById(id)).filter(Optional::isPresent).map(Optional::get).map(auditEventConverter::convertToAuditEvent);
}


public Page<AuditEvent> findByDates(Instant fromDate,Instant toDate,Pageable pageable){
    return persistenceAuditEventRepository.findAllByAuditEventDateBetween(fromDate, toDate, pageable).map(auditEventConverter::convertToAuditEvent);
}


public Page<AuditEvent> findAll(Pageable pageable){
    return persistenceAuditEventRepository.findAll(pageable).map(auditEventConverter::convertToAuditEvent);
}


}