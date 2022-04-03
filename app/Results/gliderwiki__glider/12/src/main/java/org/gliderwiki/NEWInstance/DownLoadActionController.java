package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DownLoadActionController {

 private DownLoadAction downloadaction;

 private DownLoadAction downloadaction;


@PutMapping
("/getFileDownload")
public void getFileDownload(@RequestParam(name = "requestedFile") String requestedFile,@RequestParam(name = "realFileName") String realFileName,@RequestParam(name = "response") HttpServletResponse response,@RequestParam(name = "type") String type){
downloadaction.getFileDownload(requestedFile,realFileName,response,type);
}


}