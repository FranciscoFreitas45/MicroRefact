import org.sdrc.childinfo.domain.CounterCount;
import org.sdrc.childinfo.repository.CounterCountRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
public interface SpringDataCounterCountRepository extends CounterCountRepository {


@Override
@Query("select counter from CounterCount counter")
public CounterCount findTotalCount()


}