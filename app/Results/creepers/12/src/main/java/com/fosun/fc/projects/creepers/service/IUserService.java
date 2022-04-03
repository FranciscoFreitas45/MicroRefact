package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import com.fosun.fc.projects.creepers.dto.UserDTO;
public interface IUserService extends BaseService{


public void saveUserDTO(UserDTO user)
;

public List<UserDTO> findById(String userId)
;

public List<UserDTO> findDTOByNameAndSex(String userName,String sex)
;

public List<UserDTO> findByName(String name)
;

public void updateUserDTO(UserDTO user)
;

}