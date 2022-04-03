package com.hmm.finance.roomOrder.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.finance.roomOrder.domain.RoomOrder;
import com.hmm.finance.salary.domain.SalaryOrder;
@Repository
public interface RoomOrderRepository extends PagingAndSortingRepository<RoomOrder, Long>, JpaSpecificationExecutor<RoomOrder>{


@Query("from RoomOrder where roomNo=?1 and realIncome is null")
public List<RoomOrder> findByRoomNo(Long roomNo)
;

@Query("select sum(s.realIncome) from RoomOrder s where DATE_FORMAT(checkOutTime,'%Y-%m-%d') = ?1 group by checkOutTime ")
public Float findRoomOrderByDay(String dateString)
;

}