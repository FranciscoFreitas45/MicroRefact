package com.github.haseoo.courier.repositories.jpa;
 import com.github.haseoo.courier.models.EstimatedDeliveryTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EstimatedDeliveryTimeJPARepository extends JpaRepository<EstimatedDeliveryTimeModel, Long>{


public Optional<EstimatedDeliveryTimeModel> findById(Long id)
;

public EstimatedDeliveryTimeModel getEstimatedDeliveryTimeModelById(Long id)
;

}