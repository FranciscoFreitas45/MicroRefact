package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsNotice;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsNoticeRepository extends JpaRepository<QmsNotice, Long>{


}