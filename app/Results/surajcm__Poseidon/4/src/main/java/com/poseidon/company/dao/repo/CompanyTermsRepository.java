package com.poseidon.company.dao.repo;
 import com.poseidon.company.dao.entities.CompanyTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CompanyTermsRepository extends JpaRepository<CompanyTerms, Long>{


public Optional<CompanyTerms> findFirstByOrderByIdAsc()
;

}