package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.SalaryItem;
import com.qidian.hcm.module.salary.enums.SalaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
public interface SalaryItemRepository extends JpaRepository<SalaryItem, Long>{


public List<SalaryItem> findAllByCodeIn(Collection<String> codes)
;

public List<SalaryItem> findAllByInflow(Boolean inflow)
;

public Optional<SalaryItem> findByCode(String code)
;

public Optional<SalaryItem> findByNameAndIdNot(String name,Long id)
;

public List<SalaryItem> findByFormulaLike(String name)
;

public List<SalaryItem> findByInOption(Boolean inOption)
;

public List<SalaryItem> findByInListOrderByIdAsc(Boolean inList)
;

public List<SalaryItem> findByType(SalaryType salaryType)
;

}