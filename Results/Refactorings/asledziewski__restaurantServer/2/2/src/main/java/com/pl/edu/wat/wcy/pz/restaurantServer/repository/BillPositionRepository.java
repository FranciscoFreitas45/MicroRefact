package com.pl.edu.wat.wcy.pz.restaurantServer.repository;

import com.pl.edu.wat.wcy.pz.restaurantServer.entity.BillPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
@Transactional
@Repository
public interface BillPositionRepository extends JpaRepository<BillPosition, Long> {


}