package com.evolvingreality.onleave.service;
 import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;
@Service
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService{

 private  Logger log;

 private  UserRepository userRespository;

@Autowired
public ManagerServiceImpl(final UserRepository userRespository) {
    this.userRespository = userRespository;
}
@Override
public List<SelectOption> getManagerSelectOptions(){
    log.debug("Entering");
    return userRespository.findAllByGroupMembers_SecurityGroupGroupNameIn(Collections.singleton("MANAGER")).stream().map(user -> new SelectOption(String.valueOf(user.getId()), user.getFirstName() + " " + user.getLastName())).collect(Collectors.toList());
}


@Override
public Page<User> getDirectReports(User user){
    // TODO Auto-generated method stub
    return null;
}


@Override
public Page<User> getManagers(){
    // TODO Auto-generated method stub
    return null;
}


}