package guru.springframework.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import guru.springframework.domain.BulkOrder;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
@Repository("BulkOrderRepository")
public interface BulkOrderRepository extends JpaRepository<BulkOrder, Long>{


}