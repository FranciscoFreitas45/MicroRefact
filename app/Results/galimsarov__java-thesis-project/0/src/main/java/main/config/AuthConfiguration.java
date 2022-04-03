package main.config;
 import org.springframework.context.annotation.Configuration;
import java.util.Map;
@Configuration
public class AuthConfiguration {

 private  Map<String,Integer> authorizations;

public AuthConfiguration(Map<String, Integer> authorizations) {
    this.authorizations = authorizations;
}
public void deleteAuth(String key){
    authorizations.remove(key);
}


public void addAuth(String key,int value){
    authorizations.put(key, value);
}


public Map<String,Integer> getAuths(){
    return authorizations;
}


}