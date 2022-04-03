package lk.sliit.csse.procurementsystem.repositories;
 import lk.sliit.csse.procurementsystem.models.ProcurementStaff;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface ProcurementStaffRepository extends EmployeeBaseRepository<ProcurementStaff>{


}