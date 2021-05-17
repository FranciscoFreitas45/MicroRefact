import cn.com.cnc.fcc.domain.QmsMaterielDetails;
import cn.com.cnc.fcc.domain.QmsSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsMaterielDetailsRepository extends JpaRepository<QmsMaterielDetails, Long> {


@Modifying
@Transactional
@Query("delete from QmsMaterielDetails t01 where t01.entryId = ?1")
public int deleteByEntryId(Integer engineerId)


public List<QmsMaterielDetails> findAllByEntryIdAndFlagStatus(Integer id,String flagStatus)


public Optional<QmsMaterielDetails> findByIdAndFlagStatus(Long id,String flagStatus)


}