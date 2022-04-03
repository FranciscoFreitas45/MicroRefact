package kr.ac.sejong.api.repository;
 import kr.ac.sejong.api.domain.UploadImg;
import kr.ac.sejong.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UploadImgRepository extends JpaRepository<UploadImg, Long>{


public UploadImg findByUpImgId(long id)
;

public List<UploadImg> findAll()
;

public List<UploadImg> findByImgUpUser(User user)
;

public UploadImg findTopByOrderByUpImgIdDesc()
;

}