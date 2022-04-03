package Interface;

import DTO.MyUser;

public interface LoginService {

   public MyUser findBySchoolcodeAndPassword(String schoolcode, String password);
   public MyUser getUserById(Long id);
}