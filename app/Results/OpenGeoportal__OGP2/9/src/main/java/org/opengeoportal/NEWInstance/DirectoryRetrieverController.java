package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DirectoryRetrieverController {

 private DirectoryRetriever directoryretriever;


@GetMapping
("/getDownloadDirectory")
public File getDownloadDirectory(){
  return directoryretriever.getDownloadDirectory();
}


}