import org.apache.commons.io.FileUtils;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.edu.wat.wcy.pz.restaurantServer.entity;
import pl.edu.wat.wcy.pz.restaurantServer.repository;
import java.io.File;
import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@SpringBootApplication
@EnableDiscoveryClient
public class RestaurantServerApplication {


@Bean
public ApplicationRunner init(RoleRepository roleRepository,UserRepository userRepository,RTableRepository tableRepository,DishRepository dishRepository,BillRepository billRepository){
    return args -> {
        Role role = new Role(1L, "ADMIN");
        Role role2 = new Role(2L, "WORKER");
        Role role3 = new Role(3L, "USER");
        roleRepository.save(role);
        roleRepository.save(role2);
        roleRepository.save(role3);
        Set<Role> roles = new HashSet<>();
        roles.add(role3);
        roles.add(role2);
        User worker = new User("w@w.pl", "Worker", "Worker", "12345678", roles);
        userRepository.save(worker);
        roles.add(role);
        User admin = new User("a@a.pl", "Admin", "Admin", "$2a$10$ELwxAkpJa2daHpaIKkY/XOeq8FxyyRQhIfjOvv3FU.I4NLBOciMP.", roles);
        userRepository.save(admin);
        byte[] fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/sausage.png"));
        Dish dish1 = new Dish("Sausage", "Kiełbasa", 14.99, Base64.getEncoder().encodeToString(fileContent));
        fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/orangejuice.png"));
        Dish dish2 = new Dish("Orange juice", "Sok pomarańczowy", 6.00, Base64.getEncoder().encodeToString(fileContent));
        fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/beef.png"));
        Dish dish3 = new Dish("Beef", "Wołowina", 24.99, Base64.getEncoder().encodeToString(fileContent));
        fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/porkchops.png"));
        Dish dish4 = new Dish("Pork chops", "Kotlety schabowe", 19.99, Base64.getEncoder().encodeToString(fileContent));
        fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/water.png"));
        Dish dish5 = new Dish("Water", "Woda", 2.50, Base64.getEncoder().encodeToString(fileContent));
        fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/fish.png"));
        Dish dish6 = new Dish("Fish and chips", "Ryba z frytkami", 18.99, Base64.getEncoder().encodeToString(fileContent));
        fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/chicken.png"));
        Dish dish7 = new Dish("Grilled chicken", "Grilowany kurczak", 15.00, Base64.getEncoder().encodeToString(fileContent));
        fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/images/spaghetti.png"));
        Dish dish8 = new Dish("Spaghetti bolognese", "Spaghetti bolońskie", 13.50, Base64.getEncoder().encodeToString(fileContent));
        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);
        dishRepository.save(dish4);
        dishRepository.save(dish5);
        dishRepository.save(dish6);
        dishRepository.save(dish7);
        dishRepository.save(dish8);
        RTable rTable1 = new RTable(1, 3, "FREE");
        RTable rTable2 = new RTable(2, 3, "FREE");
        RTable rTable3 = new RTable(3, 4, "FREE");
        RTable rTable4 = new RTable(4, 4, "FREE");
        RTable rTable5 = new RTable(5, 2, "FREE");
        RTable rTable6 = new RTable(6, 2, "FREE");
        tableRepository.save(rTable1);
        tableRepository.save(rTable2);
        tableRepository.save(rTable3);
        tableRepository.save(rTable4);
        tableRepository.save(rTable5);
        tableRepository.save(rTable6);
        Bill rTable11 = new Bill((long) 1, "IN_PROGRESS", new Date(), (long) 1, 0);
        Bill rTable22 = new Bill((long) 2, "IN_PROGRESS", new Date(), (long) 1, 0);
        Bill rTable33 = new Bill((long) 3, "IN_PROGRESS", new Date(), (long) 2, 0);
        Bill rTable44 = new Bill((long) 4, "IN_PROGRESS", new Date(), (long) 3, 0);
        Bill rTable55 = new Bill((long) 5, "IN_PROGRESS", new Date(), (long) 3, 0);
        Bill rTable66 = new Bill((long) 6, "IN_PROGRESS", new Date(), (long) 4, 0);
        billRepository.save(rTable11);
        billRepository.save(rTable22);
        billRepository.save(rTable33);
        billRepository.save(rTable44);
        billRepository.save(rTable55);
        billRepository.save(rTable66);
    };
}


@Bean
@LoadBalanced
public RestTemplate restTemplate(){
    return new RestTemplate();
}


public void main(String[] args){
    SpringApplication.run(RestaurantServerApplication.class, args);
}


}