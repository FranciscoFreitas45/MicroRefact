package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DefaultBookMetadataCaptureHandlerController {

 private DefaultBookMetadataCaptureHandler defaultbookmetadatacapturehandler;


@GetMapping
("/captureDetailPage")
public BookMetadata captureDetailPage(@RequestParam(name = "itemId") String itemId,@RequestParam(name = "bookMetadataSource") String bookMetadataSource){
  return defaultbookmetadatacapturehandler.captureDetailPage(itemId,bookMetadataSource);
}


@GetMapping
("/captureListPage")
public BookMetadata captureListPage(@RequestParam(name = "keyword") String keyword){
  return defaultbookmetadatacapturehandler.captureListPage(keyword);
}


}