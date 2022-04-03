package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.OwnerDTO;
import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Owner;
import org.springframework.validation.BindingResult;
import java.util.List;
public interface OwnerService {


public List<Owner> getAllOwners()
;

public Owner addOwner(Owner owner)
;

public List<Owner> getAllCompanies()
;

public List<OwnerDTO> fromOwnerToOwnerDto(List<Owner> owners)
;

public List<Owner> getAllPersons()
;

public void updateOwner(Owner owner)
;

public ValidationErrorDTO getValidationDTO(BindingResult result)
;

public Owner getOwnerById(long id)
;

public List<Owner> findOwners(SearchDTO searchDTO)
;

public void deleteOwnerById(Long id)
;

}