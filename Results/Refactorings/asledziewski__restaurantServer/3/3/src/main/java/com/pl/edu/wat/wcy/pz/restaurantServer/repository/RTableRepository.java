package com.pl.edu.wat.wcy.pz.restaurantServer.repository;

import com.pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface RTableRepository extends JpaRepository<RTable, Long> {


public List<RTable> findAllBySize(int size);


public boolean existsBySize(int size);


public boolean existsByNumber(int number);

    @Modifying
    @Query(value = "update rtable r set r.status = ?2 where d.rtable_id = ?1", nativeQuery = true)
public void setStatus( long id,String status);


}