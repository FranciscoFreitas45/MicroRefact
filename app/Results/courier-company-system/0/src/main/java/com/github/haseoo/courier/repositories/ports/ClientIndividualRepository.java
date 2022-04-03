package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.ClientIndividualModel;
import java.util.Optional;
public interface ClientIndividualRepository {


public ClientIndividualModel saveAndFlush(ClientIndividualModel clientCompanyModel)
;

public Optional<ClientIndividualModel> getById(Long id)
;

public ClientIndividualModel getByEmailAddress(String email)
;

public Optional<ClientIndividualModel> getByPesel(String pesel)
;

}