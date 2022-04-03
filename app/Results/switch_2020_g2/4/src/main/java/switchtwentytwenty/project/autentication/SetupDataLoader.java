package switchtwentytwenty.project.autentication;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import switchtwentytwenty.project.applicationservice.irepository.ICategoryRepository;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import switchtwentytwenty.project.Interface.ICategoryRepository;
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

 private  String SYSTEM_MANAGER;

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  RoleRepository roleRepository;

@Autowired
 private  PasswordEncoder passwordEncoder;

@Autowired
 private  ICategoryRepository categoryRepository;


@Override
@Transactional
public void onApplicationEvent(ContextRefreshedEvent event){
    RoleJPA admin = new RoleJPA(ERole.ROLE_ADMIN);
    RoleJPA user = new RoleJPA(ERole.ROLE_USER);
    RoleJPA systemManager = new RoleJPA(ERole.ROLE_SYSTEM_MANAGER);
    if (!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()) {
        roleRepository.save(admin);
    }
    if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
        roleRepository.save(user);
    }
    if (!roleRepository.findByName(ERole.ROLE_SYSTEM_MANAGER).isPresent()) {
        roleRepository.save(systemManager);
    }
    Set<RoleJPA> roles = new HashSet<>();
    roles.add(systemManager);
    UserJPA userJPA = new UserJPA();
    userJPA.setUsername(SYSTEM_MANAGER);
    userJPA.setEmail("sm@gmail.com");
    userJPA.setFamilyID("system_manager");
    userJPA.setPassword(passwordEncoder.encode(SYSTEM_MANAGER));
    userJPA.setRoles(roles);
    if (!userRepository.existsByUsername(SYSTEM_MANAGER)) {
        userRepository.save(userJPA);
    }
    CategoryID categoryID = new CategoryID("bce97861-bd6d-4a10-b304-aed1d322b96f");
    Designation designation = new CategoryDesignation("Groceries");
    Category category = CategoryFactory.create(designation, categoryID, null);
    categoryRepository.save(category);
}


}