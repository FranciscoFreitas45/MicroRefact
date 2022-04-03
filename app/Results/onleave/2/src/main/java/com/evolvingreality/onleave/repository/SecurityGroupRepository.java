package com.evolvingreality.onleave.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.SecurityGroup;
public interface SecurityGroupRepository extends JpaRepository<SecurityGroup, Long>{


}