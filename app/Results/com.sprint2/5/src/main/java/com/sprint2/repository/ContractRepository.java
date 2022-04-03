package com.sprint2.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint2.model.Contract;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{


}