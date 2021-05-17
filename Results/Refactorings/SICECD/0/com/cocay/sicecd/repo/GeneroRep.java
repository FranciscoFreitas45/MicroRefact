import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Genero;
@Repository
public interface GeneroRep extends PagingAndSortingRepository<Genero, Integer> {


@Query("SELECT c FROM Genero c where c.genero = :genero")
public Genero findByNombreGenero(String genero)


}