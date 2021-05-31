import com.ats.hrmgt.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface BankRepo extends JpaRepository<Bank, Integer> {


public Bank findByBankIdAndDelStatus(int bankId,int del)


@Transactional
@Modifying
@Query(value = "UPDATE m_bank SET del_status=0 WHERE bank_id=:bankId", nativeQuery = true)
public int deleteBankById(int bankId)


public List<Bank> findByCompanyIdAndDelStatusOrderByBankIdDesc(int companyId,int del)


}