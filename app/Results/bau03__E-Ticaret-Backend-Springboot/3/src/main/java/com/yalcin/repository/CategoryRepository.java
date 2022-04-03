package com.yalcin.repository;
 import com.yalcin.entity.Category;
import com.yalcin.entity.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CategoryRepository extends JpaRepository<Category, Integer>{


public Optional<Category> findByCategorys(Categorys category)
;

}