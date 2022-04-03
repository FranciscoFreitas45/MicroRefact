package com.csquard.mregister.repository;
 import org.springframework.stereotype.Repository;
import com.csquard.mregister.model.Asm;
import com.csquard.mregister.model.Tdr;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface AsmRepository extends JpaRepository<Asm, Long>{


@Query(nativeQuery = true, value = "SELECT * FROM asms JOIN users ON  asms.user_id=users.id ")
public List<Asm> findAllAsmsHere()
;

public Page<Tdr> findBySalesRegionId(long asmId,Pageable pageable)
;

public Boolean existsBySalesRegionId(Long salesRegionId)
;

}