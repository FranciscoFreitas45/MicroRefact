package br.com.wtag.lottery.model.input;
 import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.wtag.lottery.model.entity.Bets;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class BetsInput {

 private  Long id;

@NotNull
@NotEmpty
@Size(max = 255)
@Email
 private  String email;


public Bets toBets(){
    return new Bets(id, email, LocalDateTime.now(), null);
}


}