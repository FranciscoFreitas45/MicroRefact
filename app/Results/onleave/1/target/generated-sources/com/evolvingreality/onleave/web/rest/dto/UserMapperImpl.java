package com.evolvingreality.onleave.web.rest.dto;

import com.evolvingreality.onleave.model.User;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    comments = "version: 1.0.0.CR2, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"

)

@Component

public class UserMapperImpl implements UserMapper {

    @Override

    public UserDTO toDto(User user) {

        if ( user == null ) {

            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setPassword( user.getPassword() );

        userDTO.setLangKey( user.getLangKey() );

        userDTO.setLastName( user.getLastName() );

        userDTO.setEmail( user.getEmail() );

        userDTO.setFirstName( user.getFirstName() );

        return userDTO;
    }

    @Override

    public User fromDto(UserDTO user) {

        if ( user == null ) {

            return null;
        }

        User user_ = new User();

        user_.setPassword( user.getPassword() );

        user_.setLastName( user.getLastName() );

        user_.setLangKey( user.getLangKey() );

        user_.setEmail( user.getEmail() );

        user_.setFirstName( user.getFirstName() );

        return user_;
    }
}

