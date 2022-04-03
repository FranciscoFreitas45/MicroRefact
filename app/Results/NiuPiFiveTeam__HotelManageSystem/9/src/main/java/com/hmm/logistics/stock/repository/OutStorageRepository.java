package com.hmm.logistics.stock.repository;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.stock.entity.OutStorage;
@Repository
public interface OutStorageRepository extends PagingAndSortingRepository<OutStorage, Long>, JpaSpecificationExecutor<OutStorage>{


public void setRoomCleanRecord(Long id,RoomCleanRecord roomCleanRecord);

public void setRoomNo(Long id,String roomNo);

}