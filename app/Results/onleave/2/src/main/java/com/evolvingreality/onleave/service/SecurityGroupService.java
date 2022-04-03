package com.evolvingreality.onleave.service;
 import java.util.List;
import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.SecurityGroup;
public interface SecurityGroupService extends EntityService<SecurityGroup>{


public List<SelectOption> getSecurityGroupSelectOptions()
;

}