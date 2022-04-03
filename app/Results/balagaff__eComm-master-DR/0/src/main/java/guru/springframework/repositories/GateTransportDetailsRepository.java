package guru.springframework.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import guru.springframework.domain.GateTransportDetails;
public interface GateTransportDetailsRepository extends JpaRepository<GateTransportDetails, Integer>{


}