package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AbstractStringCacheStoreController {

 private AbstractStringCacheStore abstractstringcachestore;


@GetMapping
("/getAny")
public Optional<T> getAny(@RequestParam(name = "key") String key,@RequestParam(name = "type") Class<T> type){
  return abstractstringcachestore.getAny(key,type);
}


@PutMapping
("/putAny")
public void putAny(@RequestParam(name = "key") String key,@RequestParam(name = "value") T value,@RequestParam(name = "timeout") long timeout,@RequestParam(name = "timeUnit") TimeUnit timeUnit){
abstractstringcachestore.putAny(key,value,timeout,timeUnit);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return abstractstringcachestore.delete(Object);
}


@GetMapping
("/putIfAbsent")
public Object putIfAbsent(@RequestParam(name = "Object") Object Object){
  return abstractstringcachestore.putIfAbsent(Object);
}


}