package restock.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import restock.entities.Permisos;
public interface PermisosRepository extends JpaRepository<Permisos, Integer>{


public List<Permisos> findByRolId(Integer rolId)
;

}