package ink.champ.repository;
 import ink.champ.models.Champ;
import ink.champ.models.Team;
import ink.champ.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ChampRepository extends JpaRepository<Champ, Long>{


@Query("SELECT c FROM champs c INNER JOIN c.roles cr WHERE cr.user = ?1 AND cr.role > 0 AND lower(c.name) LIKE lower(concat('%', ?2, '%')) ORDER BY cr.role DESC, c.id DESC")
public List<Champ> findChampsByUserAll(User user,String search)
;

public List<Champ> findChampsByNameContainingIgnoreCase(String search,Sort sort)
;

@Query("SELECT c FROM champs c INNER JOIN c.roles cr WHERE (SELECT COUNT(ct.id) FROM c.teams ct WHERE ct.team = ?1) = 0 AND cr.user = ?2 AND cr.role >= 3 ORDER BY c.id DESC")
public List<Champ> findChampsByUserRoleAndTeamNotIn(Team team,User user)
;

@Query("SELECT c FROM champs c INNER JOIN c.roles cr WHERE cr.user = ?1 AND cr.role = ?2 AND lower(c.name) LIKE lower(concat('%', ?3, '%')) ORDER BY c.id DESC")
public List<Champ> findChampsByUserRole(User user,int role,String search)
;

public List<Champ> findChampsByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort)
;

}