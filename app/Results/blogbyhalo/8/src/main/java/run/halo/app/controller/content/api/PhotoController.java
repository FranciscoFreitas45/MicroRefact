package run.halo.app.controller.content.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.PhotoDTO;
import run.halo.app.model.params.PhotoQuery;
import run.halo.app.service.PhotoService;
@RestController("ApiContentPhotoController")
@RequestMapping("/api/content/photos")
public class PhotoController {

 private  PhotoService photoService;

public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
}
@GetMapping
public Page<PhotoDTO> pageBy(Pageable pageable,PhotoQuery photoQuery){
    return photoService.pageDtosBy(pageable, photoQuery);
}


@GetMapping(value = "latest")
public List<PhotoDTO> listPhotos(Sort sort){
    return photoService.listDtos(sort);
}


}