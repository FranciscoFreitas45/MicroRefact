package com.sda.inTeams.configuration.entitiesGenerator;
 import com.sda.inTeams.configuration.BasicConfiguration;
import com.sda.inTeams.configuration.StringUtilities;
import com.sda.inTeams.model.User.AccountRole;
import com.sda.inTeams.model.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
public class UserGenerator {

 private  Random random;


public List<User> generateUsers(int size){
    List<User> users = new ArrayList<>();
    IntStream.range(0, size).forEach(ind -> users.add(generateSingleUser()));
    return users;
}


public User generateSingleUser(){
    return User.builder().firstName("Name" + StringUtilities.getRandomNumberAsString(4)).lastName("Last" + StringUtilities.getRandomNumberAsString(4)).username("user" + StringUtilities.getRandomNumberAsString(4)).password(StringUtilities.getRandomWord()).build();
}


public User pickRandomUserFromList(List<User> users){
    return users.get(random.nextInt(users.size()));
}


}