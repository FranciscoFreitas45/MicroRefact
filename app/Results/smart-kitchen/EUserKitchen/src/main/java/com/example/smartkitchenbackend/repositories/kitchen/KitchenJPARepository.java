package com.example.smartkitchenbackend.repositories.kitchen;
 import com.example.smartkitchenbackend.entities.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface KitchenJPARepository extends JpaRepository<Kitchen, Long>{


public List<Kitchen> findByNameContaining(String name)
;

}