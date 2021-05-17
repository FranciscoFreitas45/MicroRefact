import com.ats.hrmgt.model;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class HrEasyApiController {

@Autowired
 private DesignationRepo desigRepo;

@Autowired
 private ContractorRepo contractRepo;

@Autowired
 private EmployeeMasterRepository empRepo;

@Autowired
 private DepartmentRepo deptRepo;

@Autowired
 private BankRepo bankRepo;

@Autowired
 private TblEmpBankInfoRepo tblEmpBankInfoRepo;

@Autowired
 private GetWeekShiftChangeRepo getWeekShiftChangeRepo;

@Autowired
 private SlabMasterRepository salSlabRepo;


@RequestMapping(value = { "/getEmpByDesignationId" }, method = RequestMethod.POST)
public int getEmpByDesignationId(int desigId,int companyId){
    int resp = 0;
    try {
        resp = empRepo.getEmpInfoByDesigId(desigId, companyId);
    } catch (Exception e) {
        System.err.println("Excep in getEmpByDesignationId : " + e.getMessage());
        e.printStackTrace();
    }
    return resp;
}


@RequestMapping(value = { "/getAllContractors" }, method = RequestMethod.POST)
public List<Contractor> getAllContractors(int companyId){
    List<Contractor> list = new ArrayList<Contractor>();
    try {
        list = contractRepo.findByCompanyIdAndDelStatusOrderByContractorIdDesc(companyId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAllContractors : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getSalSlabById" }, method = RequestMethod.POST)
public SlabMaster getSalSlabById(int slabId){
    SlabMaster salSlab = new SlabMaster();
    try {
        salSlab = salSlabRepo.findBySlabId(slabId);
    } catch (Exception e) {
        System.err.println("Excep in getSalSlabById : " + e.getMessage());
        e.printStackTrace();
    }
    return salSlab;
}


@RequestMapping(value = { "/getAllDepartments" }, method = RequestMethod.POST)
public List<Department> getAllDepartments(int companyId){
    List<Department> list = new ArrayList<Department>();
    try {
        list = deptRepo.findByCompanyIdAndDelStatusOrderByDepartIdDesc(companyId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAllDepartments : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveSalSlab" }, method = RequestMethod.POST)
public SlabMaster saveSalSlab(SlabMaster slab){
    SlabMaster salSlab = new SlabMaster();
    try {
        salSlab = salSlabRepo.save(slab);
    } catch (Exception e) {
        System.err.println("Excep in saveSalSlab : " + e.getMessage());
        e.printStackTrace();
    }
    return salSlab;
}


@RequestMapping(value = { "/deleteContractor" }, method = RequestMethod.POST)
public Info deleteContractor(int contractorId){
    Info info = new Info();
    try {
        int res = contractRepo.deleteContractorById(contractorId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteContractor : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getDeptByDeptIds" }, method = RequestMethod.POST)
public List<Department> getDeptByDeptIds(List<Integer> deptIdString){
    List<Department> list = new ArrayList<Department>();
    try {
        list = deptRepo.findByDepartIdIn(deptIdString);
    } catch (Exception e) {
        System.err.println("Excep in getDeptByDeptIds : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllDesignations" }, method = RequestMethod.POST)
public List<Designation> getAllDesignations(int companyId){
    List<Designation> list = new ArrayList<Designation>();
    try {
        list = desigRepo.findByCompanyIdAndDelStatusOrderByDesigIdDesc(companyId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAllDesignations : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getDepartmentById" }, method = RequestMethod.POST)
public Department getDepartmentById(int deptId){
    Department depart = new Department();
    try {
        depart = deptRepo.findByDepartIdAndDelStatus(deptId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getDepartmentById : " + e.getMessage());
        e.printStackTrace();
    }
    return depart;
}


@RequestMapping(value = { "/getEmpByDeptId" }, method = RequestMethod.POST)
public int getEmpByDeptId(int deptId,int companyId){
    int resp = 0;
    try {
        resp = empRepo.getEmpInfoByDepartment(deptId, companyId);
    } catch (Exception e) {
        System.err.println("Excep in /getEmpByDeptId : " + e.getMessage());
        e.printStackTrace();
    }
    return resp;
}


@RequestMapping(value = { "/deleteSalSlab" }, method = RequestMethod.POST)
public Info deleteSalSlab(int slabId){
    Info info = new Info();
    try {
        int res = salSlabRepo.deleteSlabSlabById(slabId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Bank Deleted Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed To Delete Bank");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Bank");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/deleteDesignationById" }, method = RequestMethod.POST)
public Info deleteDesignationById(int desigId){
    Info info = new Info();
    try {
        int res = desigRepo.deleteDesignation(desigId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteDesignationById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getContractorById" }, method = RequestMethod.POST)
public Contractor getContractorById(int contractorId){
    Contractor contract = new Contractor();
    try {
        contract = contractRepo.findByContractorIdAndDelStatus(contractorId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getContractorById : " + e.getMessage());
        e.printStackTrace();
    }
    return contract;
}


@RequestMapping(value = { "/deleteBank" }, method = RequestMethod.POST)
public Info deleteBank(int bankId){
    Info info = new Info();
    try {
        List<TblEmpBankInfo> empList = tblEmpBankInfoRepo.findByBankIdAndDelStatus(bankId, 1);
        if (empList.size() <= 0) {
            int res = bankRepo.deleteBankById(bankId);
            if (res > 0) {
                info.setError(false);
                info.setMsg("Bank Deleted Successfully");
            } else {
                info.setError(true);
                info.setMsg("Failed To Delete Bank");
            }
        } else {
            info.setError(true);
            info.setMsg("Bank Can't be Deleted as it is Asigned to Employee");
        }
    } catch (Exception e) {
        info.setError(true);
        info.setMsg("Failed To Delete Bank");
        System.err.println("Excep in deleteBank : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAllBanks" }, method = RequestMethod.POST)
public List<Bank> getAllBanks(int companyId){
    List<Bank> list = new ArrayList<Bank>();
    try {
        list = bankRepo.findByCompanyIdAndDelStatusOrderByBankIdDesc(companyId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAllBanks : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getBankById" }, method = RequestMethod.POST)
public Bank getBankById(int bankId){
    Bank bank = new Bank();
    try {
        bank = bankRepo.findByBankIdAndDelStatus(bankId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getBankById : " + e.getMessage());
        e.printStackTrace();
    }
    return bank;
}


@RequestMapping(value = { "/getEmpByContractorId" }, method = RequestMethod.POST)
public int getEmpByContractorId(int contractorId,int companyId){
    int resp = 0;
    try {
        resp = empRepo.getEmpInfoByContractId(contractorId, companyId);
    } catch (Exception e) {
        System.err.println("Excep in getEmpByContractorId : " + e.getMessage());
        e.printStackTrace();
    }
    return resp;
}


@RequestMapping(value = { "/getDesignationById" }, method = RequestMethod.POST)
public Designation getDesignationById(int desigId){
    Designation designation = new Designation();
    try {
        designation = desigRepo.findByDesigIdAndDelStatus(desigId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getDesignationById : " + e.getMessage());
        e.printStackTrace();
    }
    return designation;
}


@RequestMapping(value = { "/saveDepartment" }, method = RequestMethod.POST)
public Department saveDepartment(Department dept){
    Department depart = new Department();
    try {
        depart = deptRepo.save(dept);
    } catch (Exception e) {
        System.err.println("Excep in saveDepartment : " + e.getMessage());
        e.printStackTrace();
    }
    return depart;
}


@RequestMapping(value = { "/saveDesignation" }, method = RequestMethod.POST)
public Designation saveDesignation(Designation desig){
    Designation designation = new Designation();
    try {
        designation = desigRepo.save(desig);
    } catch (Exception e) {
        System.err.println("Excep in saveDesignation : " + e.getMessage());
        e.printStackTrace();
    }
    return designation;
}


@RequestMapping(value = { "/getAllWeekOffShifted" }, method = RequestMethod.POST)
public List<GetWeekShiftChange> getAllWeekOffShifted(String month,String year,int empId){
    List<GetWeekShiftChange> list = new ArrayList<GetWeekShiftChange>();
    try {
        if (empId != -1) {
            list = getWeekShiftChangeRepo.getAllWeekShifted(month, year, empId);
        } else {
            list = getWeekShiftChangeRepo.getAllWeekShiftedAllEmp(month, year);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllBanks : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveBank" }, method = RequestMethod.POST)
public Bank saveBank(Bank bank){
    Bank savBank = new Bank();
    try {
        savBank = bankRepo.save(bank);
    } catch (Exception e) {
        System.err.println("Excep in saveBank : " + e.getMessage());
        e.printStackTrace();
    }
    return savBank;
}


@RequestMapping(value = { "/saveContractor" }, method = RequestMethod.POST)
public Contractor saveContractor(Contractor contract){
    Contractor contrctr = new Contractor();
    try {
        contrctr = contractRepo.save(contract);
    } catch (Exception e) {
        System.err.println("Excep in saveDesignation : " + e.getMessage());
        e.printStackTrace();
    }
    return contrctr;
}


@RequestMapping(value = { "/deleteDepartment" }, method = RequestMethod.POST)
public Info deleteDepartment(int deptId){
    Info info = new Info();
    try {
        int res = deptRepo.deleteDepartment(deptId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteDepartment : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAllSalSlab" }, method = RequestMethod.POST)
public List<SlabMaster> getAllSalSlab(){
    List<SlabMaster> list = new ArrayList<SlabMaster>();
    try {
        list = salSlabRepo.getAllOrderBySlabIdDesc();
    } catch (Exception e) {
        System.err.println("Excep in getAllSalSlab : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllDesignationsListBySortNo" }, method = RequestMethod.POST)
public List<Designation> getAllDesignationsListByShName(int companyId){
    List<Designation> list = new ArrayList<Designation>();
    try {
        list = desigRepo.findByCompanyIdAndDelStatusOrderByExInt1Asc(companyId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAllDesignations : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


}