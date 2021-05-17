import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Log_evento_sys;
@Repository
public interface Log_evento_sysRep extends PagingAndSortingRepository<Log_evento_sys, String> {


@Query(value = "SELECT * FROM Log_evento_sys", nativeQuery = true)
public List<Log_evento_sys> findEv()


public List<Log_evento_sys> findByNombre(String name)


}