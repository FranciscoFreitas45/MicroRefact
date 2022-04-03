package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import edu.nju.careerbridge.youth.model.CompanyRemark;
import edu.nju.careerbridge.youth.model.SingleCompanyRemark;
import java.util.List;
public interface SingleCompanyRemarkRepository extends JpaRepository<SingleCompanyRemark, Integer>{


public List<SingleCompanyRemark> findByCompany(String company)
;

}