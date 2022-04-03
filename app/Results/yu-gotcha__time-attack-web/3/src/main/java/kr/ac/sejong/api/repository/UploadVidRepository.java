package kr.ac.sejong.api.repository;
 import kr.ac.sejong.api.domain.UploadVid;
import kr.ac.sejong.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UploadVidRepository extends JpaRepository<UploadVid, Long>{


public UploadVid findByUpVidId(long id)
;

public List<UploadVid> findByVidUpUser(User user)
;

public UploadVid findTopByOrderByUpVidIdDesc()
;

}