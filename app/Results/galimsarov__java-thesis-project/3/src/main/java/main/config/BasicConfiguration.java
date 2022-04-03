package main.config;
 import main.model.helper.Account;
import main.repository.UserRepository;
import main.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter{

@Autowired
 private  UserRepository userRepository;


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}


@Override
public void configure(AuthenticationManagerBuilder auth){
    UserServiceImpl service = new UserServiceImpl(userRepository);
    List<Account> accounts = service.getAccounts();
    for (Account account : accounts) {
        String encodedPassword = passwordEncoder().encode(account.getPassword());
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser(account.getName()).password(encodedPassword).roles("ADMIN");
    }
}


}