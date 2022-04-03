package kr.ac.sejong.api.repository;
 import kr.ac.sejong.api.domain.ResultSection;
import kr.ac.sejong.api.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ResultSectionRepository extends JpaRepository<ResultSection, Long>{


public List<ResultSection> findByUpload(Upload upload)
;

}