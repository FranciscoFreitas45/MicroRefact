package br.com.wtag.lottery.DTO;
 import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.wtag.lottery.model.entity.Bets;
import lombok.Data;
import lombok.NoArgsConstructor;
public class BetsInput {

 private  Long id;

 private  String email;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public Bets toBets(){
    return new Bets(id, email, LocalDateTime.now(), null);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toBets"))

;
Bets aux = restTemplate.getForObject(builder.toUriString(),Bets.class);
return aux;
}


}