package com.sda.inTeams.configuration.entitiesGenerator;
 import com.sda.inTeams.configuration.StringUtilities;
import com.sda.inTeams.model.Team.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
public class TeamGenerator {


public List<Team> generateTeams(int size){
    List<Team> users = new ArrayList<>();
    IntStream.range(0, size).forEach(ind -> users.add(generateSingleTeam()));
    return users;
}


public Team generateSingleTeam(){
    return Team.builder().name("Team " + StringUtilities.getRandomWord() + " " + StringUtilities.getRandomNumberAsString(3)).build();
}


}