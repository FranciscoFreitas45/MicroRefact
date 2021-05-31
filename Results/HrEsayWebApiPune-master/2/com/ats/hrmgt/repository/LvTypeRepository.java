import com.ats.hrmgt.model.LvType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface LvTypeRepository extends JpaRepository<LvType, Integer> {


@Query(value = "select * from tbl_lvm_lvtype where to_show_drowpdown=1", nativeQuery = true)
public List<LvType> getLvTypeList()


}