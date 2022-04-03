package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import domain.Path;
import domain.Segment;
import repositories.PathRepository;
public class PathService {

 private  PathRepository pathRepository;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public void delete(Path path){
    this.pathRepository.delete(path);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))

.queryParam("path",path)
;
restTemplate.put(builder.toUriString(),null);
}


public Path save(Path path){
    return this.pathRepository.save(path);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))

.queryParam("path",path)
;
Path aux = restTemplate.getForObject(builder.toUriString(),Path.class);
return aux;
}


public Path create(){
    Path path = new Path();
    List<Segment> segments = new ArrayList<>();
    path.setSegments(segments);
    return path;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))

;
Path aux = restTemplate.getForObject(builder.toUriString(),Path.class);
return aux;
}


}