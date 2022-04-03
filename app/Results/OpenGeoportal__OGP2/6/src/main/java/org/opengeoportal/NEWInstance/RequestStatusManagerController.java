package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RequestStatusManagerController {

 private RequestStatusManager requeststatusmanager;


@PutMapping
("/addDownloadRequest")
public void addDownloadRequest(@RequestParam(name = "downloadRequest") DownloadRequest downloadRequest){
requeststatusmanager.addDownloadRequest(downloadRequest);
}


@GetMapping
("/getDownloadRequest")
public DownloadRequest getDownloadRequest(@RequestParam(name = "requestId") UUID requestId){
  return requeststatusmanager.getDownloadRequest(requestId);
}


@PutMapping
("/addImageRequest")
public void addImageRequest(@RequestParam(name = "requestId") UUID requestId,@RequestParam(name = "sessionId") String sessionId,@RequestParam(name = "imageRequest") ImageRequest imageRequest){
requeststatusmanager.addImageRequest(requestId,sessionId,imageRequest);
}


@GetMapping
("/getImageRequest")
public ImageRequest getImageRequest(@RequestParam(name = "fromString") UUID fromString){
  return requeststatusmanager.getImageRequest(fromString);
}


}