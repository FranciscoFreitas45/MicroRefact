import cn.offway.athena.domain.VRanking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface VRankingService {


public List<VRanking> save(List<VRanking> entities)


public VRanking findOne(Long id)


public void delete(Long id)


public Page<VRanking> findAll(Pageable pageable,String brandId)


}