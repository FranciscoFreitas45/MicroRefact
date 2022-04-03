package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.PhotoDTO;
import run.halo.app.model.entity.Photo;
import run.halo.app.model.params.PhotoParam;
import run.halo.app.model.params.PhotoQuery;
import run.halo.app.service.PhotoService;
@RestController
@RequestMapping("/api/admin/photos")
public class PhotoController {

 private  PhotoService photoService;

public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
}
@GetMapping
@ApiOperation("Lists photos")
public Page<PhotoDTO> pageBy(Pageable pageable,PhotoQuery photoQuery){
    return photoService.pageDtosBy(pageable, photoQuery);
}


@GetMapping("teams")
@ApiOperation("Lists all of photo teams")
public List<String> listTeams(){
    return photoService.listAllTeams();
}


@PostMapping
@ApiOperation("Creates a photo")
public PhotoDTO createBy(PhotoParam photoParam){
    return new PhotoDTO().convertFrom(photoService.createBy(photoParam));
}


@PutMapping("{photoId:\\d+}")
@ApiOperation("Updates a photo")
public PhotoDTO updateBy(Integer photoId,PhotoParam photoParam){
    // Get the photo
    Photo photo = photoService.getById(photoId);
    // Update changed properties of the photo
    photoParam.update(photo);
    // Update menu in database
    return new PhotoDTO().convertFrom(photoService.update(photo));
}


@GetMapping("{photoId:\\d+}")
@ApiOperation("Gets photo detail by id")
public PhotoDTO getBy(Integer photoId){
    return new PhotoDTO().convertFrom(photoService.getById(photoId));
}


@GetMapping(value = "latest")
@ApiOperation("Lists latest photos")
public List<PhotoDTO> listPhotos(Sort sort){
    return photoService.listDtos(sort);
}


@DeleteMapping("{photoId:\\d+}")
@ApiOperation("Deletes photo by id")
public void deletePermanently(Integer photoId){
    photoService.removeById(photoId);
}


}