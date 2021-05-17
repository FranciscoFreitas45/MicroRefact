import com.ats.hrmgt.model.claim.GetEmployeeClaimStrudt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetEmployeeClaimStrudtRepo extends JpaRepository<GetEmployeeClaimStrudt, Integer> {


@Query(value = " SELECT\n" + "    claim_structure_details.clm_struct_details_id,\n" + "    claim_structure_details.clm_type_id,\n" + "    claim_structure_details.clm_amt,\n" + "    claim_structure_details.clm_struct_head_id,\n" + "    claim_structure_header.claim_struct_name,\n" + "    claim_type.claim_type_title,\n" + "    claim_type.claim_type_title_short\n" + "FROM\n" + "    claim_type,\n" + "    claim_structure_details,\n" + "    claim_structure_header,\n" + "    claim_structure_allotment\n" + "WHERE\n" + "    claim_structure_header.clm_struct_head_id = claim_structure_details.clm_struct_head_id AND claim_type.claim_type_id = claim_structure_details.clm_type_id AND claim_structure_allotment.del_status = 1 AND claim_structure_allotment.clms_id = claim_structure_header.clm_struct_head_id AND claim_structure_allotment.emp_id = :empId", nativeQuery = true)
public List<GetEmployeeClaimStrudt> getClaimApplyStructList(int empId)


@Query(value = " SELECT\n" + "    claim_structure_details.clm_struct_details_id,\n" + "    claim_structure_details.clm_type_id,\n" + "    claim_structure_details.clm_amt,\n" + "    claim_structure_details.clm_struct_head_id,\n" + "    claim_structure_header.claim_struct_name,\n" + "    claim_type.claim_type_title,\n" + "    claim_type.claim_type_title_short\n" + "FROM\n" + "    claim_type,\n" + "    claim_structure_details,\n" + "    claim_structure_header,\n" + "    claim_structure_allotment\n" + "WHERE\n" + "    claim_structure_header.clm_struct_head_id = claim_structure_details.clm_struct_head_id AND claim_type.claim_type_id = claim_structure_details.clm_type_id AND claim_structure_allotment.del_status = 1 AND claim_structure_allotment.clms_id = claim_structure_header.clm_struct_head_id AND claim_structure_allotment.emp_id = :empId AND claim_structure_details.clm_type_id=:typeId", nativeQuery = true)
public GetEmployeeClaimStrudt getClaimApplyStructListUnique(int empId,int typeId)


}