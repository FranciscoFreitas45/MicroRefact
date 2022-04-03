package com.zis.DTO;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shop.bean.Company;
public interface CompanyDao extends PagingAndSortingRepository<Company, Integer>{

 final  String NORMAL;

 final  String DELETE;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Query(value = "FROM Company WHERE companyId <> 0 AND companyId = :companyId AND status = '" + NORMAL + "'")
public Company findByCompanyId(Integer companyId)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCompanyId"))

.queryParam("companyId",companyId)
;
Company aux = restTemplate.getForObject(builder.toUriString(),Company.class);
return aux;
}
;

@Query(value = "FROM Company WHERE companyName = :companyName AND companyId <> 0 AND status = '" + NORMAL + "'")
public Company findByCompanyName(String companyName)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCompanyName"))

.queryParam("companyName",companyName)
;
Company aux = restTemplate.getForObject(builder.toUriString(),Company.class);
return aux;
}
;

@Query(value = "SELECT cp FROM Company cp WHERE cp.companyId <> 0 AND cp.status = '" + NORMAL + "'")
public Page<Company> findAllCompany(Pageable page)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllCompany"))

.queryParam("page",page)
;
Page<Company> aux = restTemplate.getForObject(builder.toUriString(),Page<Company>.class);
return aux;
}
;

@Query(value = "SELECT cp FROM Company cp WHERE " + "cp.contacts = :contacts AND cp.companyId <> 0 AND cp.status = '" + NORMAL + "'")
public Page<Company> findByContacts(String contacts,Pageable page)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByContacts"))

.queryParam("contacts",contacts)
.queryParam("page",page)
;
Page<Company> aux = restTemplate.getForObject(builder.toUriString(),Page<Company>.class);
return aux;
}
;

@Query(value = "SELECT cp FROM Company cp WHERE " + "cp.companyName LIKE %:companyName% AND cp.companyId <> 0 AND cp.status = '" + NORMAL + "'")
public Page<Company> findByLikeCompanyName(String companyName,Pageable page)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLikeCompanyName"))

.queryParam("companyName",companyName)
.queryParam("page",page)
;
Page<Company> aux = restTemplate.getForObject(builder.toUriString(),Page<Company>.class);
return aux;
}
;

}