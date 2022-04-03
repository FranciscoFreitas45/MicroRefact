package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.entity.Owner;
import java.util.List;
public interface OwnerDAO extends GenericDAO<Owner, Long>{


public List<Owner> getAllCompanies()
;

public List<Owner> getAllPersons()
;

public List<Owner> findOwners(SearchDTO searchDTO)
;

public void deleteOwnerById(Long id)
;

}