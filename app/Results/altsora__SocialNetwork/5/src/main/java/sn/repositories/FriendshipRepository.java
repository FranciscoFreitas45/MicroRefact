package sn.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.model.Friendship;
@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long>{


@Query(value = "SELECT COUNT(*) FROM friendship" + " WHERE (src_person_id = ?1 OR dst_person_id = ?1)" + " AND status = 'FRIEND'", nativeQuery = true)
public int getFriendsCount(long id)
;

@Query(value = "SELECT COUNT(*) FROM friendship" + " WHERE dst_person_id = ?1" + " AND status = 'REQUEST'", nativeQuery = true)
public int getRequestsCount(long id)
;

@Query(value = "SELECT * FROM friendship WHERE " + "(src_person_id = :id AND dst_person_id =:friendId) " + "OR (src_person_id = :friendId AND dst_person_id =:id) " + "AND status = :status", nativeQuery = true)
public Friendship getFriendship(long id,long friendId,String status)
;

}