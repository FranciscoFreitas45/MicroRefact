package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QuickDownloadController {

 private QuickDownload quickdownload;


@GetMapping
("/downloadZipFile")
public File downloadZipFile(@RequestParam(name = "layerId") String layerId,@RequestParam(name = "bounds") BoundingBox bounds){
  return quickdownload.downloadZipFile(layerId,bounds);
}


}