package sn.repositories;
 import org.hibernate.jpa.TypedParameterValue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import sn.model.Person;
import java.util.List;
import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{


@Query(value = "SELECT person.* FROM person " + "LEFT JOIN friendship ON person.id = friendship.src_person_id " + "WHERE dst_person_id = :id " + "AND status = 'FRIEND' " + "AND (first_name LIKE %:name% OR last_name LIKE %:name%) " + "UNION " + "SELECT person.* FROM person " + "LEFT JOIN friendship ON person.id = friendship.dst_person_id " + "WHERE src_person_id = :id " + "AND status = 'FRIEND' " + "AND (first_name LIKE %:name% OR last_name LIKE %:name%) " + "LIMIT :itemPerPage OFFSET :offset", nativeQuery = true)
public List<Person> findFriends(long id,int offset,int itemPerPage,String name)
;

public Optional<Person> findByFirstName(String firstName)
;

@Query("SELECT COUNT(p) FROM Person p")
public int getTotalCountUsers()
;

@Query(value = "SELECT person.* FROM person " + "LEFT JOIN friendship ON person.id = friendship.src_person_id " + "WHERE dst_person_id = :id " + "AND status = 'REQUEST' " + "AND (first_name LIKE %:name% OR last_name LIKE %:name%) " + "LIMIT :itemPerPage OFFSET :offset", nativeQuery = true)
public List<Person> findRequests(long id,int offset,int itemPerPage,String name)
;

@Query(value = "SELECT person.* FROM person " + "LEFT JOIN friendship ON person.id = friendship.src_person_id " + "WHERE person.city = :city " + "AND person.id != :id " + "AND status != 'FRIEND' " + "UNION " + "SELECT person.* FROM person " + "LEFT JOIN friendship ON person.id = friendship.dst_person_id " + "WHERE person.city = :city " + "AND person.id != :id " + "AND status != 'FRIEND' " + "UNION " + "SELECT person.* FROM person " + "WHERE person.city = :city " + "AND person.id != :id " + "LIMIT :itemPerPage OFFSET :offset", nativeQuery = true)
public List<Person> findRecommendedFriends(long id,String city,Integer offset,int itemPerPage)
;

public Optional<Person> findByPhone(String phone)
;

public Optional<Person> findByLastName(String lastName)
;

public Optional<Person> findByEmail(String email)
;

public void deleteByEmail(String email)
;

}