package kr.ac.sejong.api.repository;
 import kr.ac.sejong.api.domain.Upload;
import kr.ac.sejong.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UploadRepository extends JpaRepository<Upload, Long>{


public Upload findByUpId(long i)
;

public long countByUser(User user)
;

public long count()
;

public List<Upload> findByUser(User user)
;

}