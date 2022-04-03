package com.github.haseoo.courier.services.adapters;
 import com.github.haseoo.courier.exceptions.serviceexceptions.userexceptions.clients.ClientNotFound;
import com.github.haseoo.courier.exceptions.serviceexceptions.userexceptions.clients.ClientWithNipExists;
import com.github.haseoo.courier.models.ClientCompanyModel;
import com.github.haseoo.courier.repositories.ports.ClientCompanyRepository;
import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyAddData;
import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyData;
import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyEditData;
import com.github.haseoo.courier.services.ports.ClientCompanyService;
import com.github.haseoo.courier.services.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.haseoo.courier.enums.ClientType.COMPANY;
import com.github.haseoo.courier.utilities.Utils.copyNonNullProperties;
import com.github.haseoo.courier.utilities.Utils.validateEmailAddress;
@Service
@RequiredArgsConstructor
public class ClientCompanyServiceImpl implements ClientCompanyService{

 private  ClientCompanyRepository clientCompanyRepository;

 private  UserService userService;

 private  ModelMapper modelMapper;

 private  PasswordEncoder passwordEncoder;


@Transactional
@Override
public ClientCompanyData add(ClientCompanyAddData addData){
    validateAddData(addData);
    ClientCompanyModel modelToAdd = modelMapper.map(addData, ClientCompanyModel.class);
    modelToAdd.setPassword(passwordEncoder.encode(String.valueOf(addData.getPassword())).toCharArray());
    return ClientCompanyData.of(clientCompanyRepository.saveAndFlush(modelToAdd));
}


@Override
public ClientCompanyData getById(Long id){
    return ClientCompanyData.of(clientCompanyRepository.getById(id).orElseThrow(() -> new ClientNotFound(id, COMPANY)));
}


@Transactional
@Override
public ClientCompanyData edit(Long id,ClientCompanyEditData editData){
    ClientCompanyModel clientCompanyModel = clientCompanyRepository.getById(id).orElseThrow(() -> new ClientNotFound(id, COMPANY));
    validateEditData(editData);
    ClientCompanyModel modelToEdit = prepareEditModel(editData);
    copyNonNullProperties(modelToEdit, clientCompanyModel);
    return ClientCompanyData.of(clientCompanyRepository.saveAndFlush(clientCompanyModel));
}


public void validateAddData(ClientCompanyAddData addData){
    userService.checkUsername(addData.getUserName());
    clientCompanyRepository.getByNip(addData.getNip()).ifPresent(model -> {
        throw new ClientWithNipExists();
    });
    validateEmailAddress(addData.getEmailAddress());
    validateEmailAddress(addData.getRepresentativeEmailAddress());
// validate nip
}


public ClientCompanyModel prepareEditModel(ClientCompanyEditData editData){
    ClientCompanyModel modelToEdit = modelMapper.map(editData, ClientCompanyModel.class);
    if (editData.getPassword() != null) {
        modelToEdit.setPassword(passwordEncoder.encode(String.valueOf(editData.getPassword())).toCharArray());
    }
    return modelToEdit;
}


public void validateEditData(ClientCompanyEditData editData){
    if (editData.getEmailAddress() != null) {
        validateEmailAddress(editData.getEmailAddress());
    }
    if (editData.getRepresentativeEmailAddress() != null) {
        validateEmailAddress(editData.getRepresentativeEmailAddress());
    }
}


}