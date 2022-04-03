package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{


@Query("select m from Member m join m.userAccount u where u.username = ?1")
public Member getMemberByUsername(String username)
;

}