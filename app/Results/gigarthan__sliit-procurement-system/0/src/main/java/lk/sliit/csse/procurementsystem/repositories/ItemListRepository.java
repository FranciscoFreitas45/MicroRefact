package lk.sliit.csse.procurementsystem.repositories;
 import lk.sliit.csse.procurementsystem.models.ItemList;
import lk.sliit.csse.procurementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface ItemListRepository extends JpaRepository<T, Long>{


}