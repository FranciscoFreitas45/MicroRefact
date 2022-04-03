package ink.champ.repository;
 import ink.champ.models.Player;
import ink.champ.models.Team;
import ink.champ.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface PlayerRepository extends JpaRepository<Player, Long>{


@Query("SELECT p FROM players p INNER JOIN p.roles pr WHERE pr.user = ?1 AND pr.role > 0 AND lower(p.name) LIKE lower(concat('%', ?2, '%')) ORDER BY pr.role DESC, p.id DESC")
public List<Player> findPlayersByUserAll(User user,String search)
;

public List<Player> findPlayersByPrivatIsFalseAndNameContainingIgnoreCase(String search,Sort sort)
;

@Query("SELECT p FROM players p INNER JOIN p.roles pr WHERE pr.user = ?1 AND pr.role = ?2 AND lower(p.name) LIKE lower(concat('%', ?3, '%')) ORDER BY p.id DESC")
public List<Player> findPlayersByUserRole(User user,int role,String search)
;

@Query("SELECT p FROM players p INNER JOIN p.roles pr WHERE (SELECT COUNT(pt.id) FROM p.teams pt WHERE pt.team = ?1) = 0 AND pr.user = ?2 AND pr.role >= 3 ORDER BY p.id DESC")
public List<Player> findPlayersByUserRoleAndNotInTeam(Team team,User user)
;

public List<Player> findPlayersByNameContainingIgnoreCase(String search,Sort sort)
;

}