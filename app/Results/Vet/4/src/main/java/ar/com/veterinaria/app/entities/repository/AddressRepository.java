package ar.com.veterinaria.app.entities.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.veterinaria.app.entities.Address;
public interface AddressRepository extends JpaRepository<Address, Integer>{


public Address findAddressById(Integer id)
;

@SuppressWarnings("unchecked")
public Address save(Address address)
;

public List<Address> findAll()
;

public void delete(Address address)
;

public void setAddress(Integer id,Address address);

public Address getAddress(Integer id);

}