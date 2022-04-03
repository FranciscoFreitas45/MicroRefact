package com.evolvingreality.onleave.web.rest;
 import com.codahale.metrics.annotation.Timed;
import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;
import com.evolvingreality.onleave.service.ManagerService;
import com.evolvingreality.onleave.service.SecurityGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import java.util.List;
@RestController
@RequestMapping("/api/v1.0")
public class SecurityGroupResource {

 private  Logger log;

 private  SecurityGroupService securityGroupService;

@Autowired
public SecurityGroupResource(final SecurityGroupService securityGroupService) {
    this.securityGroupService = securityGroupService;
}
@RequestMapping(value = "/security/group/selectoption", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public List<SelectOption> getAll(){
    log.debug("REST request to get all SecurityGroups");
    return securityGroupService.getSecurityGroupSelectOptions();
}


}