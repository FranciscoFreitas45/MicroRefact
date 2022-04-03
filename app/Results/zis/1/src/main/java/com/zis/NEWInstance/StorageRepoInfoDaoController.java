package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StorageRepoInfoDaoController {

 private StorageRepoInfoDao storagerepoinfodao;


@GetMapping
("/findByOwnerIdOrderByGmtCreateAsc")
public List<StorageRepoInfo> findByOwnerIdOrderByGmtCreateAsc(@RequestParam(name = "ownerId") Integer ownerId){
  return storagerepoinfodao.findByOwnerIdOrderByGmtCreateAsc(ownerId);
}


}