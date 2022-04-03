package com.sda.inTeams.Interface;
import java.util.*;
import com.sda.inTeams.model.*;
import com.sda.inTeams.DTO.*;
 import com.sda.inTeams.exception.InvalidOperation;


public interface UserService {
    public User getByIdOrThrow(long userId) throws InvalidOperation ;

}