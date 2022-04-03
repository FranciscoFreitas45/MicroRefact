package com.sda.inTeams.Interface;
import java.util.*;
import com.sda.inTeams.model.*;
import com.sda.inTeams.DTO.*;

public interface UserRepository {

   public Optional<User> findById(Object Object);
}