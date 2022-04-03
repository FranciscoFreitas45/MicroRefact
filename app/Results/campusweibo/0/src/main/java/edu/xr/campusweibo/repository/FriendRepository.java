package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FriendRepository extends JpaRepository<Friend, Long>{


public Friend findByUidAndFuid(Long uid,Long fuid)
;

public List<Friend> findAllByUid(Long id)
;

public List<Friend> findAllByFuid(Long id)
;

}