package sn.repositories;
 import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.model.NotificationType;
import sn.model.enums.NotificationTypeCode;
import java.util.List;
import java.util.Optional;
@Repository
public interface NotificationTypeRepository extends CrudRepository<NotificationType, Long>{


public Optional<NotificationType> findByCode(NotificationTypeCode code)
;

}