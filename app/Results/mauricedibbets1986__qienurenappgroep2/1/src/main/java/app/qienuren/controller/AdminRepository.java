package app.qienuren.controller;
 import app.qienuren.model.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminRepository extends JpaRepository<AdminAccount, Long>{


}