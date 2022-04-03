package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsMaterielEntry;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsMaterielEntryRepository extends JpaRepository<QmsMaterielEntry, Long>{


public Optional<QmsMaterielEntry> findByIdAndFlagStatus(Long id,String flag)
;

public List<QmsMaterielEntry> findByMaterielIdAndFlagStatus(Integer materielId,String flagStatus)
;

}