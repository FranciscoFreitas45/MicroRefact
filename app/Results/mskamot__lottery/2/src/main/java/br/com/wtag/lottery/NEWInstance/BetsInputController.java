package br.com.wtag.lottery.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BetsInputController {

 private BetsInput betsinput;

 private BetsInput betsinput;


@GetMapping
("/toBets")
public Bets toBets(){
  return betsinput.toBets();
}


}