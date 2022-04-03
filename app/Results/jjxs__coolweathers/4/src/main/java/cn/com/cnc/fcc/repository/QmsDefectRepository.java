package cn.com.cnc.fcc.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cn.com.cnc.fcc.domain.QmsDefect;
@SuppressWarnings("unused")
@Repository
public interface QmsDefectRepository extends JpaRepository<QmsDefect, Long>{


public List<QmsDefect> findByDefectCdAndFlagStatus(String defectCd,String string)
;

public List<QmsDefect> findByIdAndFlagStatus(Long id,String string)
;

}