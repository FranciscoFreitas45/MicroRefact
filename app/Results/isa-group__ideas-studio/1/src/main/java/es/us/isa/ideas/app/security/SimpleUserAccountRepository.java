package es.us.isa.ideas.app.security;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SimpleUserAccountRepository {

 private String locationPrefix;

 private List<UserAccount> accounts;

public SimpleUserAccountRepository() {
    this.locationPrefix = "";
    accounts = new ArrayList<UserAccount>();
    initializeUserAccounts();
}
public UserAccount findByUsername(String username){
    UserAccount result = null;
    for (UserAccount user : accounts) {
        if (user.getUsername().equals(username)) {
            result = user;
            break;
        }
    }
    return result;
}


public void initializeUserAccounts(){
    ApplicationContext ctx;
    ctx = new ClassPathXmlApplicationContext(locationPrefix + "user-accounts.xml");
    for (Entry<String, UserAccount> entry : ctx.getBeansOfType(UserAccount.class).entrySet()) {
        accounts.add(entry.getValue());
    }
    ((ClassPathXmlApplicationContext) ctx).close();
}


}