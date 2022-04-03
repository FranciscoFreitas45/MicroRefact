package com.ukefu.webim.service.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Organization;
public interface OrganizationRepository extends JpaRepository<Organization, String>{


public Organization findById(String id)
;

}