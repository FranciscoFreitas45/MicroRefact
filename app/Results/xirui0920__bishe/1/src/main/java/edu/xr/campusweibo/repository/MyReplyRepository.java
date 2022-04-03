package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.domain.MyReply;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MyReplyRepository extends JpaRepository<MyReply, Long>{


public List<MyReply> findAllByWid(Long id)
;

}