package goorum.goorum.repository;
 import goorum.goorum.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface ReplyRepository extends JpaRepository<Reply, Long>{


public List<Reply> findAllByBoard(long board_id)
;

public Optional<Reply> findByReplyIdAndParent(long replyId,long parent)
;

public Optional<Reply> findByReplyIdAndParentAndWriter(long replyId,long parent,long memberId)
;

public List<Reply> findAllByParent(long parent)
;

public void deleteByReplyIdAndParentAndWriter(long replyId,long parent,long memberId)
;

}