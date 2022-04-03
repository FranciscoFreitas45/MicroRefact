package org.vaadin.paul.spring.repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vaadin.paul.spring.entities.Centro;
import org.vaadin.paul.spring.entities.Cita;
import org.vaadin.paul.spring.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


public User findBydni(String dni)
;

@Query(value = "SELECT * FROM user WHERE username = ?1 AND NOT id = ?2 ", nativeQuery = true)
public User findByUsernameEdit(String username,int id)
;

@Query(value = "Select * From User Where rol = 2", nativeQuery = true)
public List<User> findByRoles(int rol)
;

public User findByid(int i)
;

@Query(value = "SELECT * FROM user WHERE dni = ?1 AND NOT id = ?2 ", nativeQuery = true)
public User findByDniEdit(String dni,int id)
;

public List<User> findAll()
;

public User findByusername(String username)
;

public User getPaciente(int id);

public void setPaciente(int id,User user);

public User getUser(int id);

}