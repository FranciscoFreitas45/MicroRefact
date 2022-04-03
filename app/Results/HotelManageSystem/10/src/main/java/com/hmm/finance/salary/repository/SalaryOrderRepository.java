package com.hmm.finance.salary.repository;
 import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.finance.salary.domain.SalaryOrder;
@Repository
public interface SalaryOrderRepository extends PagingAndSortingRepository<SalaryOrder, Long>, JpaSpecificationExecutor<SalaryOrder>{


@Query("select sum(s.basicwage+s.overtimefee+s.allowance) from SalaryOrder s where DATE_FORMAT(date,'%Y-%m-%d') = ?1 group by date")
public Float findSalaryByDay(String date)
;

}