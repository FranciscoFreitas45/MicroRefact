package com.cg.sprint.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.sprint.entity.Payments;
public interface PaymentDao extends JpaRepository<Payments, Integer>{


@Query("select p from Payments p where account_no=?1 and booking_id=?2")
public Payments refund(int acc_no,int book_id)
;

}