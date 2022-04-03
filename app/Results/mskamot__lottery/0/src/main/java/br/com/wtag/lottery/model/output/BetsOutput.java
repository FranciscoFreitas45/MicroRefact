package br.com.wtag.lottery.model.output;
 import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetsOutput {

 private  String email;

@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
 private  LocalDateTime registered;

 private  List<RandomNumbersOutput> randomNumbers;


}