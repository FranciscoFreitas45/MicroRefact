package goorum.goorum.repository;
 import goorum.goorum.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface BoardRepository extends JpaRepository<Board, Long>{


@Query(value = "SELECT * FROM (SELECT * FROM board WHERE board_id < :boardId ORDER BY board_id desc LIMIT 1) A UNION SELECT * FROM (SELECT * FROM board WHERE board_id > :boardId ORDER BY board_id asc LIMIT 1) B", nativeQuery = true)
public List<Board> findPrevAndNextBoardIdByBoardId(long boardId)
;

}