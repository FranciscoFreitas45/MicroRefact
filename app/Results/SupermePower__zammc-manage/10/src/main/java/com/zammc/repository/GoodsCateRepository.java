package com.zammc.repository;
 import com.zammc.domain.goods.GoodsCateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface GoodsCateRepository extends JpaSpecificationExecutor<GoodsCateEntity>, JpaRepository<GoodsCateEntity, Long>{


@Query(value = "select cate from GoodsCateEntity cate where cate.dataStatus = '0' and cate.status = '1'")
public List<GoodsCateEntity> queryCateList()
;

}