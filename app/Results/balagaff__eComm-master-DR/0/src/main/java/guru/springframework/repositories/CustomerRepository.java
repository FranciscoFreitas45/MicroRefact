package guru.springframework.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
@Repository("CustomerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long>{


public Customer findCustomerById(Integer id)
;

}