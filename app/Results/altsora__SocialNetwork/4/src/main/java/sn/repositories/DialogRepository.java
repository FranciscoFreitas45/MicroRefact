package sn.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.model.Dialog;
@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long>{


public Dialog findByInviteCode(String inviteCode)
;

}