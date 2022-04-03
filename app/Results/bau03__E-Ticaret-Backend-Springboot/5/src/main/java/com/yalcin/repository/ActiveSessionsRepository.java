package com.yalcin.repository;
 import com.yalcin.entity.ActiveSessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ActiveSessionsRepository extends JpaRepository<ActiveSessions, String>{


}