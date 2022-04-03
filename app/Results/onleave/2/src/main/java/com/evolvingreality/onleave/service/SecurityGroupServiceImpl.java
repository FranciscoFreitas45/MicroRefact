package com.evolvingreality.onleave.service;
 import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.SecurityGroup;
import com.evolvingreality.onleave.repository.SecurityGroupRepository;
@Service
@Transactional(readOnly = true)
public class SecurityGroupServiceImpl extends EntityServiceImpl<SecurityGroup>implements SecurityGroupService{

 private  SecurityGroupRepository securityGroupRepository;

@Autowired
public SecurityGroupServiceImpl(final SecurityGroupRepository securityGroupRepository) {
    super(securityGroupRepository);
    this.securityGroupRepository = securityGroupRepository;
}
@Override
public List<SelectOption> getSecurityGroupSelectOptions(){
    log.debug("Entering");
    return securityGroupRepository.findAll().stream().map(securityGroup -> new SelectOption(String.valueOf(securityGroup.getId()), securityGroup.getGroupName())).collect(Collectors.toList());
}


}