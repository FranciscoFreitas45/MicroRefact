package com.yalcin.repository;
 import com.yalcin.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer>{


public List<Adress> findAllByUser_Id(Integer userId)
;

public Adress findAllById(Integer id)
;

public void setAdress(Integer id,Adress adress);

public Adress getAdress(Integer id);

public void setAdress(Integer id,Set<Adress> adress);

public Set<Adress> getAdress(Integer id);

}