package com.zis.trade.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.trade.entity.Order;
public interface OrderDao extends JpaSpecificationExecutor<Order>, PagingAndSortingRepository<Order, Integer>{


public List<Order> findByOrderGroupNumberIn(List<String> orderGroupNumbers)
;

public List<Order> findByShopIdAndOrderGroupNumber(Integer shopId,String orderGroupNumber)
;

public Page<Order> findByCompanyIdOrderByUpdateTimeDesc(Integer companyId,Pageable page)
;

public List<Order> findByShopIdAndReceiverNameAndReceiverPhoneAndReceiverAddr(Integer shopId,String receiverName,String receiverPhone,String receiverAddr)
;

public Page<Order> findByCompanyIdAndStorageStatusInAndPayStatusNotInOrderByUpdateTimeDesc(Integer companyId,List<String> storageStatusList,List<String> payStatus,Pageable page)
;

public Order findByOrderGroupNumberAndPayStatusNotIn(String orderGroupNumber,List<String> payStatus)
;

public List<Order> findByCompanyIdAndOrderGroupNumberInOrderByUpdateTimeDesc(Integer companyId,List<String> orderGroupNumbers)
;

@Modifying
@Query("update Order set storageStatus='pickup_finish' where storageStatus='pickup' and id in (:ids)")
public int updateStorageStatusToFinishByIds(List<Integer> orderIds)
;

public Page<Order> findByCompanyIdAndExpressStatusAndStorageStatusInAndPayStatusNotInOrderByUpdateTimeDesc(Integer companyId,String expressStatus,List<String> storageStatusList,List<String> payStatus,Pageable page)
;

public Page<Order> findByCompanyIdAndStorageStatusAndPayStatusNotInOrderByUpdateTimeDesc(Integer companyId,String storageStatus,List<String> payStatus,Pageable page)
;

public List<Order> findByRepoIdAndExpressNumber(Integer repoId,String expressNumber)
;

public List<Order> findByRepoIdAndExpressNumberAndExpressStatus(Integer repoId,String expressNumber,String expressStatus)
;

public Page<Order> findByCompanyIdAndExpressStatusAndStorageStatusNotInAndPayStatusNotInOrderByUpdateTimeDesc(Integer companyId,String expressStatus,List<String> storageStatusList,List<String> payStatus,Pageable page)
;

public Page<Order> findByCompanyIdAndExpressStatus(Integer companyId,String expressStatus,Pageable page)
;

public Page<Order> findByCompanyIdAndPayStatusOrderByUpdateTimeDesc(Integer companyId,String payStatus,Pageable page)
;

public Order findByIdAndCompanyId(Integer id,Integer companyId)
;

}