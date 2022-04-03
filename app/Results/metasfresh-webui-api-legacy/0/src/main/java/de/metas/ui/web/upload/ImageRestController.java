package de.metas.ui.web.upload;
 import java.io.IOException;
import org.compiere.model.I_AD_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import de.metas.ui.web.cache.ETagResponseEntityBuilder;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
@RestController
@RequestMapping(value = ImageRestController.ENDPOINT)
public class ImageRestController {

 public  String ENDPOINT;

@Autowired
 private  UserSession userSession;

@Autowired
 private  WebuiImageService imageService;


public JSONOptions newJSONOptions(){
    return JSONOptions.of(userSession);
}


public void assertUserHasAccess(WebuiImage image){
    final boolean hasAccess = userSession.getUserRolePermissions().canView(image.getAdClientId(), image.getAdOrgId(), I_AD_Image.Table_ID, image.getAdImageId());
    if (!hasAccess) {
        throw new EntityNotFoundException("No access to image: " + image.getAdImageId());
    }
}


@PostMapping
public WebuiImageId uploadImage(MultipartFile file){
    userSession.assertLoggedIn();
    return imageService.uploadImage(file);
}


@GetMapping("/{imageId}")
@ResponseBody
public ResponseEntity<byte[]> getImage(int imageIdInt,int maxWidth,int maxHeight,WebRequest request){
    userSession.assertLoggedIn();
    final WebuiImageId imageId = WebuiImageId.ofRepoIdOrNull(imageIdInt);
    return ETagResponseEntityBuilder.ofETagAware(request, getWebuiImage(imageId, maxWidth, maxHeight)).includeLanguageInETag().cacheMaxAge(userSession.getHttpCacheMaxAge()).jsonOptions(() -> newJSONOptions()).toResponseEntity((responseBuilder, webuiImage) -> webuiImage.toResponseEntity(responseBuilder));
}


public WebuiImage getWebuiImage(WebuiImageId imageId,int maxWidth,int maxHeight){
    final WebuiImage image = imageService.getWebuiImage(imageId, maxWidth, maxHeight);
    assertUserHasAccess(image);
    return image;
}


}