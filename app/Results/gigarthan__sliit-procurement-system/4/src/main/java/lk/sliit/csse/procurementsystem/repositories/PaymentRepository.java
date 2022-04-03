package lk.sliit.csse.procurementsystem.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import lk.sliit.csse.procurementsystem.models.Payment;
@Transactional
public interface PaymentRepository extends JpaRepository<T, Long>{


}