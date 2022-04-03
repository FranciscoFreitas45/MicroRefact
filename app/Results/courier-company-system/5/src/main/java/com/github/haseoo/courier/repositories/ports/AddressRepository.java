package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.AddressModel;
import com.github.haseoo.courier.querydata.AddressQueryData;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public interface AddressRepository {


public AddressModel saveAndFlush(AddressModel addressModel)
;

public Optional<AddressModel> getById(Long id)
;

public List<AddressModel> getList()
;

public Optional<AddressModel> addressExist(AddressQueryData addressQueryData)
;

}