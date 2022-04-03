package com.qidian.hcm.module.authorization.repository;
 import com.qidian.hcm.module.authorization.entity.Menu;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MenuRepository extends JpaRepository<Menu, Long>{


public Menu findByCode(MenuCode code)
;

}