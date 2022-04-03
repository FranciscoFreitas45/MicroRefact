package es.us.isa.ideas.app.repositories;
 import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import es.us.isa.ideas.app.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>{


@Query("SELECT t FROM Tag t " + "JOIN t.workspaces w " + "JOIN w.owner o" + " JOIN o.userAccount u" + " WHERE w.name = ? AND u.username = ?")
public Collection<Tag> findByWorkspaceName(String workspaceName,String username)
;

@Query("SELECT t FROM Tag t " + "JOIN t.workspaces w " + "WHERE w.name != NULL")
public Collection<Tag> findTagsInUse()
;

@Query("SELECT t FROM Tag AS t WHERE t.id = ?")
public Tag findById(Integer id)
;

@Query("SELECT t FROM Tag AS t WHERE t.name = ?")
public Tag findByName(String name)
;

}