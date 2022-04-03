package es.us.isa.ideas.app.repositories;
 import es.us.isa.ideas.app.entities.Workspace;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Integer>{


@Query("SELECT w FROM Workspace w " + " JOIN w.owner o" + " JOIN o.userAccount u" + " WHERE u.username = ?")
public Collection<Workspace> findByOwner(String userAccount)
;

@Query("SELECT w FROM Workspace w WHERE w.origin = ?")
public Collection<Workspace> findByOrigin(String id)
;

@Query("SELECT w FROM Workspace w " + "JOIN w.workspaceTags t WHERE t.id= ?")
public Collection<Workspace> findByTag(String tagId)
;

@Query("SELECT w FROM Workspace w WHERE w.id = ?")
public Workspace findById(Integer id)
;

@Query("SELECT w FROM Workspace w" + " JOIN w.owner o" + " JOIN o.userAccount u" + " WHERE w.name = ? AND u.username = ?")
public Workspace findByName(String name,String userAccount)
;

@Query("SELECT w FROM Workspace w " + "JOIN w.workspaceTags t WHERE t.name=?")
public Collection<Workspace> findByTagName(String tagName)
;

}