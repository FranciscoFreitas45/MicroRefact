package sn.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.model.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{


}