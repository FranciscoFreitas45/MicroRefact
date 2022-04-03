package org.jugbd.mnet.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EitherController {

 private Either either;

 private Either either;


@GetMapping
("/right")
public Either<L,R> right(@RequestParam(name = "right") R right){
  return either.right(right);
}


}