package com.zammc.repository;
 import com.zammc.domain.goods.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long>, JpaSpecificationExecutor<GoodsEntity>{


@Query(value = "select goods from GoodsEntity goods where goods.dataStatus = '0' and goods.goodsType = '1'")
public List<GoodsEntity> queryAllNotSingleGoods()
;

}