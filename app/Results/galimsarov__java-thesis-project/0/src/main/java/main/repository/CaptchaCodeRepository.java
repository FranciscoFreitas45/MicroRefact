package main.repository;
 import main.model.CaptchaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface CaptchaCodeRepository extends JpaRepository<CaptchaCode, Integer>{


@Query(value = "select secret_code from captcha_codes where code = " + ":query", nativeQuery = true)
public String findSecretByCode(String code)
;

@Modifying
@Transactional
@Query(value = "delete from captcha_codes where time < " + "subdate(current_time(), interval :query hour)", nativeQuery = true)
public void deleteOldCaptchas(int time)
;

}