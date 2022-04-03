package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.KbsExpert;
import com.ukefu.webim.web.model.User;
public interface KbsExpertRepository extends JpaRepository<KbsExpert, String>{


public List<KbsExpert> findByOrgiAndKbstype(String orgi,String kbstype)
;

public List<KbsExpert> findByOrgiAndUser(String orgi,User user)
;

}