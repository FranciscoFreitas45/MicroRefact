package sn.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sn.api.response.AbstractResponse;
import sn.api.response.ServiceResponse;
import sn.service.StorageService;
import javax.mail.Multipart;
@RestController
@RequestMapping("/storage")
public class StorageController {

@Autowired
 private  StorageService storageService;


@PostMapping
public ResponseEntity<ServiceResponse<AbstractResponse>> uploadFile(MultipartFile file,String type){
    return storageService.uploadFile(file, type);
}


}