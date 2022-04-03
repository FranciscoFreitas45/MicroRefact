package org.jugbd.mnet.DTO;
 import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
public class Either {

 protected  L leftValue;

 protected  R rightValue;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


@Override
public R getRight(){
    throw new NoSuchElementException("Tried to getRight from Left");
}


@Override
public L getLeft(){
    return leftValue;
}


public Either<L,R> left(L left){
    return new Left<>(left);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/left"))

.queryParam("left",left)
;
Either<L,R> aux = restTemplate.getForObject(builder.toUriString(),Either<L,R>.class);
return aux;
}


public Either<L,R> right(R right){
    return new Right<>(right);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/right"))

.queryParam("right",right)
;
Either<L,R> aux = restTemplate.getForObject(builder.toUriString(),Either<L,R>.class);
return aux;
}


}