package main.repository;
 import main.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface TagRepository extends JpaRepository<Tag, Integer>{


@Query(value = "select name from tags where name like concat(:query,'%')", nativeQuery = true)
public List<String> findByName(String query)
;

@Query(value = "SELECT name FROM tags", nativeQuery = true)
public List<String> findNamesOfTags()
;

@Query(value = "SELECT * FROM tags where name=:query", nativeQuery = true)
public Tag findTagByName(String name)
;

}