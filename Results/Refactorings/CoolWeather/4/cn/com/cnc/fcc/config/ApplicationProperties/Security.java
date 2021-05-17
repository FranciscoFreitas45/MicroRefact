import org.springframework.boot.context.properties.ConfigurationProperties;
public class Security {

 private  Jwt jwt;

 private  int tokenCountDay;


public void setTokenCountDay(int tokenCountDay){
    this.tokenCountDay = tokenCountDay;
}


public Jwt getJwt(){
    return jwt;
}


public int getTokenCountDay(){
    return tokenCountDay;
}


}