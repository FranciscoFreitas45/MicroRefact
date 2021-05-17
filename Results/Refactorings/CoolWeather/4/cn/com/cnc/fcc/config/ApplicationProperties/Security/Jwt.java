import org.springframework.boot.context.properties.ConfigurationProperties;
public class Jwt {

 private  int tokenCountDay;


public void setTokenCountDay(int tokenCountDay){
    this.tokenCountDay = tokenCountDay;
}


public int getTokenCountDay(){
    return tokenCountDay;
}


}