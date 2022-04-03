package com.github.haseoo.courier.controllers.users;
 import com.github.haseoo.courier.commandsdata.users.clients.ClientCompanyAddCommandData;
import com.github.haseoo.courier.commandsdata.users.clients.ClientCompanyEditCommandData;
import com.github.haseoo.courier.security.UserDetailsServiceImpl;
import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyAddData;
import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyEditData;
import com.github.haseoo.courier.services.ports.ClientCompanyService;
import com.github.haseoo.courier.views.users.clients.ClientCompanyView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/api/client/company")
@RequiredArgsConstructor
public class ClientCompanyController {

 private  ClientCompanyService clientCompanyService;

 private  UserDetailsServiceImpl userDetalisService;


@PostMapping("/register")
public ResponseEntity<ClientCompanyView> add(ClientCompanyAddCommandData commandData){
    return new ResponseEntity<>(ClientCompanyView.of(clientCompanyService.add(ClientCompanyAddData.of(commandData))), CREATED);
}


@GetMapping("{id}")
public ResponseEntity<ClientCompanyView> getById(Long id){
    return new ResponseEntity<>(ClientCompanyView.of(clientCompanyService.getById(id)), OK);
}


@PreAuthorize("hasAnyRole({'ADMIN', 'CLIENT'})")
@PutMapping("{id}")
public ResponseEntity<ClientCompanyView> edit(Long id,ClientCompanyEditCommandData commandData){
    userDetalisService.verifyEditResource(id);
    return new ResponseEntity<>(ClientCompanyView.of(clientCompanyService.edit(id, ClientCompanyEditData.of(commandData))), OK);
}


}