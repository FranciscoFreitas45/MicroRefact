package com.evolvingreality.onleave.service;
 import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;
public interface EntitlementService extends EntityService<Entitlement>{


public Boolean hasUserEntitlementForYear(User user,Integer year)
;

}