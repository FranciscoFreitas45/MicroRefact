package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.ClientModel;
import java.util.List;
import java.util.Optional;
public interface ClientRepository {


public Optional<ClientModel> getById(Long id)
;

public List<ClientModel> getList()
;

}