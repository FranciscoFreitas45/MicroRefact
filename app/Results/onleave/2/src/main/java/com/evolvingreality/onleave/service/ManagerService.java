package com.evolvingreality.onleave.service;
 import java.util.List;
import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.User;
import org.springframework.data.domain.Page;
public interface ManagerService {


public List<SelectOption> getManagerSelectOptions()
;

public Page<User> getDirectReports(User user)
;

public Page<User> getManagers()
;

}