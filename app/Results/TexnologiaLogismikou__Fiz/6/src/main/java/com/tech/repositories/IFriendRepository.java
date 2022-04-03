package com.tech.repositories;
 import com.tech.models.entities.Friend;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IFriendRepository extends JpaRepository<Friend, Long>{


public List<Friend> findByDate(Date date)
;

public List<Friend> findByUserid(Long userid)
;

public Friend findByUseridAndFriendid(Long userid,Long friendid)
;

}