import javax.transaction.Transactional;
import org.sdrc.childinfo.domain.CounterCount;
public interface CounterCountRepository {


public CounterCount findTotalCount()


@Transactional
public void save(CounterCount counterCount)


}