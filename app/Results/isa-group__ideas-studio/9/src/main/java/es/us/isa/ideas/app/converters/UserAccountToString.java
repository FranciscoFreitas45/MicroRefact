package es.us.isa.ideas.app.converters;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.security.UserAccount;
@Component
@Transactional
public class UserAccountToString extends DomainEntityToString<UserAccount>{


}