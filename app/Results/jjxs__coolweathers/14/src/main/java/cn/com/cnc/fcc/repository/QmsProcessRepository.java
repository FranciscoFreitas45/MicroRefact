package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsProcess;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsProcessRepository extends JpaRepository<QmsProcess, Long>, JpaSpecificationExecutor<QmsProcess>{


public QmsProcess findByProcessCdAndFlagStatus(String processCd,String string)
;

@Query(value = "select r from QmsProcess r where 1 = 1 and processCd like %:processCd% and processName like %:processName% ")
public Page<QmsProcess> getRoleInfoForCodeName(Pageable pageable,String processCd,String processName)
;

public List<QmsProcess> findByProcessCd(String s)
;

public QmsProcess findByFlagStatusAndId(String string,Long valueOf)
;

public List<QmsProcess> findByIdAndFlagStatus(Long id,String string)
;

public List<QmsProcess> findByFlagStatusAndProcessCd(String string,String processCd)
;

}