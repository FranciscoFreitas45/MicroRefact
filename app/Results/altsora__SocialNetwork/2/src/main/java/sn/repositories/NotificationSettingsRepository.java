package sn.repositories;
 import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.model.Notification;
import sn.model.NotificationSettings;
import sn.model.NotificationType;
import sn.model.Person;
import sn.model.enums.NotificationTypeCode;
import java.util.List;
import java.util.Optional;
@Repository
public interface NotificationSettingsRepository extends CrudRepository<NotificationSettings, Long>{


public List<NotificationSettings> findByOwner(Person person)
;

@Query(value = "SELECT ns FROM NotificationSettings ns " + "WHERE ns.owner=:owner and ns.type.code=:typeCode")
public Optional<NotificationSettings> findByOwnerAndType(Person person,NotificationTypeCode typeCode)
;

}