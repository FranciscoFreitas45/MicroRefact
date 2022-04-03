package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArchiveFilterController {

 private ArchiveFilter archivefilter;

 private ArchiveFilter archivefilter;


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") int userId){
archivefilter.setUserId(userId);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
archivefilter.setTitle(title);
}


@PutMapping
("/setCreatedFrom")
public void setCreatedFrom(@RequestParam(name = "createdFrom") Date createdFrom){
archivefilter.setCreatedFrom(createdFrom);
}


@PutMapping
("/setCreatedTo")
public void setCreatedTo(@RequestParam(name = "createdTo") Date createdTo){
archivefilter.setCreatedTo(createdTo);
}


@PutMapping
("/setArchivedFrom")
public void setArchivedFrom(@RequestParam(name = "archivedFrom") Date archivedFrom){
archivefilter.setArchivedFrom(archivedFrom);
}


@PutMapping
("/setArchivedTo")
public void setArchivedTo(@RequestParam(name = "archivedTo") Date archivedTo){
archivefilter.setArchivedTo(archivedTo);
}


@PutMapping
("/setSortKey")
public void setSortKey(@RequestParam(name = "sortKey") String sortKey){
archivefilter.setSortKey(sortKey);
}


@PutMapping
("/setSortOrder")
public void setSortOrder(@RequestParam(name = "sortOrder") String sortOrder){
archivefilter.setSortOrder(sortOrder);
}


@PutMapping
("/setUniqueId")
public void setUniqueId(@RequestParam(name = "uniqueId") String uniqueId){
archivefilter.setUniqueId(uniqueId);
}


@PutMapping
("/setShortname")
public void setShortname(@RequestParam(name = "alias") String alias){
archivefilter.setShortname(alias);
}


@PutMapping
("/setOwner")
public void setOwner(@RequestParam(name = "owner") String owner){
archivefilter.setOwner(owner);
}


}