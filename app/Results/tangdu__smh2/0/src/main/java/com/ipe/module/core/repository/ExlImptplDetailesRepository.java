package com.ipe.module.core.repository;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.module.core.entity.ExlImptplDetailes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface ExlImptplDetailesRepository extends CustomerRepository<ExlImptplDetailes, String>{


}