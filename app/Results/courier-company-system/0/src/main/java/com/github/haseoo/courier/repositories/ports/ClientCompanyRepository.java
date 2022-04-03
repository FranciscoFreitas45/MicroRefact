package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.ClientCompanyModel;
import java.util.Optional;
public interface ClientCompanyRepository {


public ClientCompanyModel saveAndFlush(ClientCompanyModel clientCompanyModel)
;

public Optional<ClientCompanyModel> getByNip(String nip)
;

public Optional<ClientCompanyModel> getById(Long id)
;

}