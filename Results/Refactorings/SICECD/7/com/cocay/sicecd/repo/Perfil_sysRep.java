import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Perfil_sys;
@Repository
public interface Perfil_sysRep extends PagingAndSortingRepository<Perfil_sys, Integer> {


public List<Perfil_sys> findByNombre(String name)


}