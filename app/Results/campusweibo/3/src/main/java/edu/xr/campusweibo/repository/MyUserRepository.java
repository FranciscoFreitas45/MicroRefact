package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MyUserRepository extends JpaRepository<MyUser, Long>{


public MyUser findById(Long id)
;

public MyUser findBySchoolcodeAndPassword(String schoolcode,String password)
;

public MyUser findBySchoolcode(String schoolcode)
;

}