package goorum.goorum.repository;
 import goorum.goorum.domain.Replylist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ReplylistRepository extends JpaRepository<Replylist, Long>{


public List<Replylist> findAllByBoardId(long board_id)
;

public Page<Replylist> findAllByMemberId(long memberId,Pageable pageRequest)
;

}