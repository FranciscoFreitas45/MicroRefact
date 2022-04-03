package com.evolvingreality.onleave.web.rest;
 import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.EntitlementService;
import com.evolvingreality.onleave.service.UserService;
import com.evolvingreality.onleave.web.propertyeditors.LocaleDateTimeEditor;
import com.evolvingreality.onleave.web.rest.util.UriUtil;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
@RestController
@RequestMapping("/api/v1.0")
public class EntitlementResource {

 private  EntitlementService entitlementService;

 private  UserService userService;

@Autowired
public EntitlementResource(final EntitlementService entitlementService, final UserService userService) {
    this.entitlementService = entitlementService;
    this.userService = userService;
}
@RequestMapping(value = "/entitlement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public Page<Entitlement> getAll(Pageable pageable){
    return entitlementService.getPage(pageable);
}


@RequestMapping(value = "/user/{userId}/entitlement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<?> post(Long userId,Entitlement entitlement){
    Optional<User> user = userService.get(userId);
    if (entitlementService.hasUserEntitlementForYear(user.get(), entitlement.getYear()))
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    entitlementService.save(entitlement);
    return ResponseEntity.created(UriUtil.buildGetUri(this, entitlement.getId())).build();
}


@RequestMapping(value = "/entitlement/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<Entitlement> get(Long id){
    Optional<Entitlement> entitlement = entitlementService.get(id);
    if (!entitlement.isPresent())
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<Entitlement>(entitlement.get(), HttpStatus.OK);
}


@RequestMapping(value = "/entitlement", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<Entitlement> update(Entitlement entitlement){
    entitlementService.save(entitlement);
    return ResponseEntity.ok().body(entitlement);
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
}


@RequestMapping(value = "/entitlement/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
@RolesAllowed(AuthoritiesConstants.ADMIN)
public ResponseEntity<?> delete(Long id){
    entitlementService.delete(id);
    return ResponseEntity.noContent().build();
}


}