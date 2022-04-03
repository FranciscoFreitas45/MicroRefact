package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AlbumController {

 private AlbumDAO albumdao;


@PutMapping
("/addImage/{id}")
public void addImage(@PathVariable(name = "id") Long id,@RequestParam(name = "image") File image){
 albumdao.addImage(id,image);
}


@PutMapping
("/removeImage/{id}")
public void removeImage(@PathVariable(name = "id") Long id,@RequestParam(name = "imageHash") UUID imageHash){
 albumdao.removeImage(id,imageHash);
}


}