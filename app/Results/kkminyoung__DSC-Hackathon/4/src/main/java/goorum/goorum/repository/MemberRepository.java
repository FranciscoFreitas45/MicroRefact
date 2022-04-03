package goorum.goorum.repository;
 import goorum.goorum.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface MemberRepository extends JpaRepository<Member, Long>{


public Optional<Member> findById(String id)
;

public Optional<Member> findByIdAndPwd(String id,String pwd)
;

}