package com.example.smartkitchenbackend.repositories.food;
 import com.example.smartkitchenbackend.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FoodJPARepository extends JpaRepository<Food, Long>{


}