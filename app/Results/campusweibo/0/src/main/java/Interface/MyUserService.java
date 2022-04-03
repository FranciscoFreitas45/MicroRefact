package Interface;

import DTO.MyUser;

public interface MyUserService {

   public MyUser getUserById(Long id);
   public MyUser getUserBySchoolcode(String schoolcode);
}