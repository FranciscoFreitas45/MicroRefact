package repositories;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Brotherhood;
import domain.Member;
import domain.Parade;
import domain.Request;
import domain.Status;
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{


@Query("select r from Brotherhood b join b.parades p join p.requests r where p = ?1 and r.status = ?2")
public List<Request> getRequestsByParadeAndStatus(Parade parade,Status status)
;

@Query("select r from Request r where r.member = ?1 and r.status = ?2")
public Collection<Request> getRequestsByMemberAndStatus(Member member,Status status)
;

@Query("select r from Brotherhood b join b.parades p join p.requests r where b = ?1 and p = ?2 and r.status = 'APPROVED'")
public Collection<Request> getRequestApprovedByBrotherhoodAndParade(Brotherhood brotherhood,Parade parade)
;

@Query("select r from Brotherhood b join b.parades p join p.requests r where b = ?1")
public Collection<Request> getRequestsByBrotherhood(Brotherhood brotherhood)
;

@Query("select r from Brotherhood b join b.parades p join p.requests r where b = ?1 and r = ?2")
public Request getRequestByBrotherhoodAndRequestId(Brotherhood brotherhood,Request request)
;

@Query("select r from Brotherhood b join b.parades p join p.requests r where b = ?1 and r.status = ?2")
public Collection<Request> getRequestsByBrotherhoodAndStatus(Brotherhood brotherhood,Status status)
;

@Query("select r from Request r where r.member = ?1")
public Collection<Request> getRequestsByMember(Member member)
;

}