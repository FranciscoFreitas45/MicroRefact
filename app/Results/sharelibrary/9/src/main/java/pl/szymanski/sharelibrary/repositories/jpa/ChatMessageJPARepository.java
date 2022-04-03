package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymanski.sharelibrary.entity.ChatMessage;
public interface ChatMessageJPARepository extends JpaRepository<ChatMessage, Long>{


}