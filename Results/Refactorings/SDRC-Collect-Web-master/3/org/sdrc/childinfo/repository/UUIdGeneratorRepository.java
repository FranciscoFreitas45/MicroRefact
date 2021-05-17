import java.util.List;
import org.sdrc.childinfo.domain.UUIdGenerator;
import org.springframework.transaction.annotation.Transactional;
public interface UUIdGeneratorRepository {


@Transactional
public void save(UUIdGenerator uuidGenerator)


public List<UUIdGenerator> findAll()


}