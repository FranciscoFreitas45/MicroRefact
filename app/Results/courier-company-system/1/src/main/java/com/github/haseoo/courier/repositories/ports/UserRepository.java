package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.UserModel;
import java.util.List;
import java.util.Optional;
public interface UserRepository {


public UserModel saveAndFlush(UserModel userModel)
;

public Optional<UserModel> getById(Long id)
;

public List<UserModel> getList()
;

public Optional<UserModel> getByUsername(String username)
;

}