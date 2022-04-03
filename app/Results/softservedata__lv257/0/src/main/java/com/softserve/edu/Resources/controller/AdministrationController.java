package com.softserve.edu.Resources.controller;
 import com.softserve.edu.Resources.dto.PrivilegesDTO;
import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.Role;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation;
import java.util;
import com.softserve.edu.Resources.DTO.Role;
@RestController
@RequestMapping("/API")
public class AdministrationController {

@Autowired
 private PrivilegeService privilegeService;

@Autowired
 private RoleService roleService;


@RequestMapping(value = "/privilege/", method = RequestMethod.GET)
@Transactional
public ResponseEntity<List<Privilege>> listAllPrivileges(){
    List<Privilege> privileges = privilegeService.getAllPrivileges();
    if (privileges.isEmpty()) {
        // You many decide to return HttpStatus.NOT_FOUND
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(privileges, HttpStatus.OK);
}


@RequestMapping(value = "/privilege/new", params = { "rn" }, method = RequestMethod.GET)
@Transactional
public ResponseEntity<PrivilegesDTO> listRolePrivileges(Map<String,String> queryUser){
    String roleName = queryUser.get("rn");
    List<Privilege> privileges = privilegeService.getAllPrivileges();
    List<String> privilegesSet = roleService.getRolePrivileges(roleName);
    Map<Privilege, Boolean> privilegesMap = new HashMap<>();
    for (Privilege i : privileges) privilegesMap.put(i, false);
    for (Map.Entry<Privilege, Boolean> entry : privilegesMap.entrySet()) {
        for (String x : privilegesSet) {
            if (entry.getKey().getName().equals(x)) {
                entry.setValue(true);
            }
        }
        System.out.println("Privilege : " + entry.getKey() + " Enabled : " + entry.getValue());
    }
    PrivilegesDTO privilegesDTO = new PrivilegesDTO();
    privilegesDTO.convertToPrivilegeDTO(privilegesMap, roleName);
    if (privilegesMap.isEmpty()) {
        // You many decide to return HttpStatus.NOT_FOUND
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(privilegesDTO, HttpStatus.OK);
}


@RequestMapping(value = "/privilege/new", method = RequestMethod.POST)
@Transactional
public ResponseEntity<Role> assignPrivilegesToRole(PrivilegesDTO privilegesDTO){
    // getting role  by role name from DTO
    Role role = roleService.findByName(privilegesDTO.getRoleName());
    Set<Privilege> privileges = new HashSet<>(privilegesDTO.convertToPrivileges(privilegesDTO));
    for (Privilege privItem : privileges) {
        privilegeService.addPrivilege(privItem);
    }
    role.setPrivileges(privileges);
    roleService.updateRole(role);
    return new ResponseEntity<>(role, HttpStatus.OK);
}


}