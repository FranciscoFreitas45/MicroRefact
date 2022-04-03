package sn.repositories;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.model.Notification;
import sn.model.Person;
import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{


public Page<Notification> findByToWhomAndIsReadedFalse(Person userReceiver,Pageable pageable)
;

public List<Notification> findAllByToWhomAndIsReadedFalse(Person userReceiver)
;

}