package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserFilterController {

 private UserFilter userfilter;

 private UserFilter userfilter;


@PutMapping
("/setEmail")
public void setEmail(@RequestParam(name = "email") String email){
userfilter.setEmail(email);
}


@PutMapping
("/setComment")
public void setComment(@RequestParam(name = "comment") String comment){
userfilter.setComment(comment);
}


@PutMapping
("/setECaccess")
public void setECaccess(@RequestParam(name = "eCaccess") Boolean eCaccess){
userfilter.setECaccess(eCaccess);
}


@PutMapping
("/setNoECaccess")
public void setNoECaccess(@RequestParam(name = "noECaccess") Boolean noECaccess){
userfilter.setNoECaccess(noECaccess);
}


@PutMapping
("/setECASaccess")
public void setECASaccess(@RequestParam(name = "eCASaccess") Boolean eCASaccess){
userfilter.setECASaccess(eCASaccess);
}


@PutMapping
("/setNoECASaccess")
public void setNoECASaccess(@RequestParam(name = "noECASaccess") Boolean noECASaccess){
userfilter.setNoECASaccess(noECASaccess);
}


@PutMapping
("/setECAS")
public void setECAS(@RequestParam(name = "eCAS") Boolean eCAS){
userfilter.setECAS(eCAS);
}


@PutMapping
("/setSystem")
public void setSystem(@RequestParam(name = "system") Boolean system){
userfilter.setSystem(system);
}


@PutMapping
("/setLanguages")
public void setLanguages(@RequestParam(name = "languages") String[] languages){
userfilter.setLanguages(languages);
}


@PutMapping
("/setBanned")
public void setBanned(@RequestParam(name = "banned") Boolean banned){
userfilter.setBanned(banned);
}


@PutMapping
("/setUnbanned")
public void setUnbanned(@RequestParam(name = "unbanned") Boolean unbanned){
userfilter.setUnbanned(unbanned);
}


@PutMapping
("/setRoles")
public void setRoles(@RequestParam(name = "roles") String[] roles){
userfilter.setRoles(roles);
}


@PutMapping
("/setSortKey")
public void setSortKey(@RequestParam(name = "sortKey") String sortKey){
userfilter.setSortKey(sortKey);
}


@PutMapping
("/setSortOrder")
public void setSortOrder(@RequestParam(name = "sortOrder") String sortOrder){
userfilter.setSortOrder(sortOrder);
}


}