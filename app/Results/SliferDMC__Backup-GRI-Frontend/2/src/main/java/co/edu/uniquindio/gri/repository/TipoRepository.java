package co.edu.uniquindio.gri.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.gri.model.Tipo;
@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{


public void setTipo(long id,Tipo tipo);

public Tipo getTipo(long id);

}