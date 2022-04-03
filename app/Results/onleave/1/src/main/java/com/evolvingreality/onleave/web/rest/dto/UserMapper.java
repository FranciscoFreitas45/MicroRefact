package com.evolvingreality.onleave.web.rest.dto;
 import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.evolvingreality.onleave.model.User;
@Mapper
public interface UserMapper {


public UserDTO toDto(User user)
;

public User fromDto(UserDTO user)
;

}