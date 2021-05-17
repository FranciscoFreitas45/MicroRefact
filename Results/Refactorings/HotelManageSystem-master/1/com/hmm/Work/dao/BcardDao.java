import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.Work.entity.Bcard;
@Repository
public interface BcardDao extends JpaSpecificationExecutor<Bcard> {


}