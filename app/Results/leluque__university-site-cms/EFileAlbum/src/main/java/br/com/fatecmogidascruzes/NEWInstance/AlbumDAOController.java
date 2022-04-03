package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AlbumDAOController {

 private AlbumDAO albumdao;


@GetMapping
("/getEnabledByFilter")
public List<Album> getEnabledByFilter(@RequestParam(name = "filter") String filter){
  return albumdao.getEnabledByFilter(filter);
}


}