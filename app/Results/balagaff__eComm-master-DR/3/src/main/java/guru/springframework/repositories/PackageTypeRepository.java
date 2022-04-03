package guru.springframework.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import guru.springframework.domain.PackageType;
@Repository("PackageTypeRepository")
public interface PackageTypeRepository extends JpaRepository<PackageType, Integer>{


}