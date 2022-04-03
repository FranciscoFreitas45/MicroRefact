package DTO;
 import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Actor;
import domain.Box;
import domain.Configuration;
import domain.Message;
import repositories.ConfigurationRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.AdminService;
public class ConfigurationService {

 private  ConfigurationRepository configurationRepository;

 private  ActorService actorService;

 private  AdminService adminService;

 private  Validator validator;


public List<String> getSpamWords(){
    return this.configurationRepository.spamWords();
}


public Configuration getConfiguration(){
    return this.configurationRepository.findAll().get(0);
}


}