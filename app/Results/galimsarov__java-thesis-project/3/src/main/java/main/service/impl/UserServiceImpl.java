package main.service.impl;
 import main.model.User;
import main.model.helper.Account;
import main.repository.UserRepository;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
public class UserServiceImpl implements UserService{

@Autowired
 private  UserRepository userRepository;

public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}
@Override
public List<Account> getAccounts(){
    List<User> admins = userRepository.findAll();
    List<Account> accounts = new ArrayList<>();
    for (User admin : admins) {
        String name = admin.getName();
        String password = admin.getPassword();
        Account account = new Account(name, password);
        accounts.add(account);
    }
    return accounts;
}


}