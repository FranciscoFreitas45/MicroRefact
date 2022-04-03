package com.qidian.hcm.module.salary.repository;
 import com.qidian.hcm.module.salary.entity.SalarySetting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SalarySettingRepository extends JpaRepository<SalarySetting, Long>{


public Optional<SalarySetting> findByKey(String key)
;

}