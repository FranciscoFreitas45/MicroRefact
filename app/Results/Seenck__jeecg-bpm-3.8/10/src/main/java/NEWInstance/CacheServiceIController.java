package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CacheServiceIController {

 private CacheServiceI cacheservicei;


@GetMapping
("/get")
public Object get(@RequestParam(name = "cacheName") String cacheName,@RequestParam(name = "key") Object key){
  return cacheservicei.get(cacheName,key);
}


@PutMapping
("/put")
public void put(@RequestParam(name = "cacheName") String cacheName,@RequestParam(name = "key") Object key,@RequestParam(name = "value") Object value){
cacheservicei.put(cacheName,key,value);
}


@GetMapping
("/remove")
public boolean remove(@RequestParam(name = "cacheName") String cacheName,@RequestParam(name = "key") Object key){
  return cacheservicei.remove(cacheName,key);
}


@PutMapping
("/clean")
public void clean(@RequestParam(name = "cacheName") String cacheName){
cacheservicei.clean(cacheName);
}


}