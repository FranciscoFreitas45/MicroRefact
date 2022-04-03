package com.sda.inTeams.Interface;
import java.util.*;
import com.sda.inTeams.model.*;
import com.sda.inTeams.DTO.*;

public interface TeamService {

   public Team getByIdOrThrow(long teamId);
}