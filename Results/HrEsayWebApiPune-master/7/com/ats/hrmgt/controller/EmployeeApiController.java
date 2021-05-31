import com.ats.hrmgt.claim.repository.EmployeeRelatedTblsRepo;
import com.ats.hrmgt.claim.repository.ViewEmployeeRepo;
import com.ats.hrmgt.model;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.List;
@RestController
public class EmployeeApiController {

@Autowired
 private EmployeeMasterRepository empRepo;

@Autowired
 private TblEmpInfoRepo empInfoRepo;

@Autowired
 private TblEmpBankInfoRepo bankInfoRepo;

@Autowired
 private TblEmpNomineesRepo nomineeRepo;

@Autowired
 private EmpSalaryInfoRepo empSalRepo;

@Autowired
 private UserRepo userRepo;

@Autowired
 private AllowancesRepo allowanceRepo;

@Autowired
 private EmpSalAllowanceRepo empSalAllowanceRepo;

@Autowired
 private EmpDoctypeRepo empDocRepo;

@Autowired
 private EmployeeDocsRepository employeeDocsRepository;

@Autowired
 private EmpDriverRepo empDriverRepo;

@Autowired
 private GetEmployeeDetailsRepo getEmployeeDetailsRepo;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private EmployeeRelatedTblsRepo empRelatedRepo;

@Autowired
 private ViewEmployeeRepo empProfileRepo;


@RequestMapping(value = { "/getAllEmpDocTypes" }, method = RequestMethod.POST)
public List<EmpDoctype> getAllEmpDocTypes(int companyId){
    List<EmpDoctype> empDocTypes = new ArrayList<EmpDoctype>();
    try {
        empDocTypes = empDocRepo.findByDelStatusAndCompanyId(0, companyId);
    } catch (Exception e) {
        System.err.println("Excep in getAllEmpDocTypes : " + e.getMessage());
        e.printStackTrace();
    }
    return empDocTypes;
}


@RequestMapping(value = { "/getDriverByEmpId" }, method = RequestMethod.POST)
public EmpDriver getDriverByEmpId(int empId){
    EmpDriver emp = new EmpDriver();
    try {
        emp = empDriverRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getEmployeeById : " + e.getMessage());
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/getAllAllowances" }, method = RequestMethod.GET)
public List<Allowances> getAllAllowances(){
    List<Allowances> list = new ArrayList<Allowances>();
    try {
        list = allowanceRepo.findBydelStatusAndIsActive(0, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAllAllowances : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveEmployeeIdBank" }, method = RequestMethod.POST)
public TblEmpBankInfo saveEmployee(TblEmpBankInfo empBank){
    TblEmpBankInfo empSave = new TblEmpBankInfo();
    System.out.println("empBank--------" + empBank);
    try {
        empSave = bankInfoRepo.save(empBank);
    } catch (Exception e) {
        System.err.println("Excep in saveEmployeeIdBank : " + e.getMessage());
        e.printStackTrace();
    }
    return empSave;
}


@RequestMapping(value = { "/saveDriver" }, method = RequestMethod.POST)
public EmpDriver saveDriver(EmpDriver emp){
    EmpDriver empSave = new EmpDriver();
    try {
        empSave = empDriverRepo.save(emp);
    } catch (Exception e) {
        System.err.println("Excep in saveDriver : " + e.getMessage());
        e.printStackTrace();
    }
    return empSave;
}


@RequestMapping(value = { "/getSalaryDetailRate" }, method = RequestMethod.POST)
public SalaryRateData getSalaryDetailRate(List<Integer> locId){
    SalaryRateData salaryRateData = new SalaryRateData();
    try {
        List<GetEmployeeDetails> list = getEmployeeDetailsRepo.getSalaryDetailRate(locId);
        List<EmpSalAllowance> alloList = empSalAllowanceRepo.getEmployeeSalAllowancesInfoAll(locId);
        List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
        salaryRateData.setAllowancelist(allowancelist);
        salaryRateData.setAlloList(alloList);
        salaryRateData.setList(list);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return salaryRateData;
}


@RequestMapping(value = { "/deleteEmpSalAllowanceInfo" }, method = RequestMethod.POST)
public Info deleteEmpSalAllowanceInfo(int empId){
    Info info = new Info();
    try {
        int res = empSalAllowanceRepo.deleteEmpAllowances(empId);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteEmpSalAllowanceInfo : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getEmpInfoByEmpCode" }, method = RequestMethod.POST)
public EmployeeMaster getEmpInfoByEmpCode(String empCode){
    EmployeeMaster emp = new EmployeeMaster();
    try {
        emp = empRepo.findByEmpCodeAndDelStatus(empCode.trim(), 1);
        System.err.println("---" + emp.toString());
    } catch (Exception e) {
        System.err.println("Excep in getEmpInfoByEmpCode : " + e.getMessage());
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/saveEmployeeList" }, method = RequestMethod.POST)
public List<EmployeeMaster> saveEmployeeList(List<EmployeeMaster> empList){
    List<EmployeeMaster> empSaveResp = new ArrayList<EmployeeMaster>();
    try {
        empSaveResp = empRepo.saveAll(empList);
    } catch (Exception e) {
        System.err.println("Excep in saveEmployeeList : " + e.getMessage());
        e.printStackTrace();
    }
    return empSaveResp;
}


@RequestMapping(value = { "/getEmpRelatedInfo" }, method = RequestMethod.POST)
public EmployeeRelatedTbls getEmpRelatedInfo(String empCode){
    EmployeeRelatedTbls resp1 = new EmployeeRelatedTbls();
    try {
        resp1 = empRelatedRepo.getAllEmpRelatedInfo(empCode);
        if (resp1.getEmpId() != 0) {
            try {
                if (resp1.getAllowanceId() == null) {
                    resp1.setAllowanceId("0");
                }
            } catch (Exception e) {
                resp1.setAllowanceId("0");
            }
            try {
                if (resp1.getEmpSalAllowanceId() == null) {
                    resp1.setEmpSalAllowanceId("0");
                }
            } catch (Exception e) {
                resp1.setEmpSalAllowanceId("0");
            }
            try {
                if (resp1.getDocId() == null) {
                    resp1.setDocId("0");
                }
            } catch (Exception e) {
                resp1.setDocId("0");
            }
        }
    } catch (Exception e) {
        resp1 = null;
        e.printStackTrace();
    }
    return resp1;
}


@RequestMapping(value = { "/getEmployeeNominee" }, method = RequestMethod.POST)
public TblEmpNominees getEmployeeNominee(int empId){
    TblEmpNominees nominee = new TblEmpNominees();
    System.out.println("empNominee--------" + nominee);
    try {
        nominee = nomineeRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getEmployeeNominee : " + e.getMessage());
        e.printStackTrace();
    }
    return nominee;
}


@RequestMapping(value = { "/saveEmpDocList" }, method = RequestMethod.POST)
@ResponseBody
public List<EmployeDoc> saveEmpDocList(List<EmployeDoc> employeeDepartment){
    List<EmployeDoc> save = new ArrayList<>();
    try {
        save = employeeDocsRepository.saveAll(employeeDepartment);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return save;
}


@RequestMapping(value = { "/getEmployeeDocs" }, method = RequestMethod.POST)
@ResponseBody
public List<EmployeDoc> getEmployeeDocs(int empId){
    List<EmployeDoc> docs = new ArrayList<>();
    try {
        docs = employeeDocsRepository.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return docs;
}


@RequestMapping(value = { "/getEmployeeSalAllowancesInfo" }, method = RequestMethod.POST)
public List<EmpSalAllowance> getEmployeeSalAllowancesInfo(int empId){
    List<EmpSalAllowance> empAllowance = new ArrayList<EmpSalAllowance>();
    try {
        empAllowance = empSalAllowanceRepo.getEmployeeSalAllowancesInfo(empId);
    // System.out.println(empAllowance.size());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return empAllowance;
}


@RequestMapping(value = { "/getEmployeeAllInfo" }, method = RequestMethod.POST)
@ResponseBody
public ViewEmployee getEmployeeAllInfo(int empId){
    ViewEmployee emp = new ViewEmployee();
    try {
        emp = empProfileRepo.getEmployeeDetails(empId);
        System.out.println(emp);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/getMaxEmp" }, method = RequestMethod.GET)
public int getMaxEmp(){
    int n = 0;
    EmployeeMaster emp = new EmployeeMaster();
    try {
        emp = empRepo.getEmpMax();
        n = emp.getEmpId();
    } catch (Exception e) {
        System.err.println("Excep in getEmpInfoByEmpCode : " + e.getMessage());
        e.printStackTrace();
    }
    return n;
}


@RequestMapping(value = { "/saveEmployeeIdSalary" }, method = RequestMethod.POST)
public EmpSalaryInfo saveEmployeeIdNominee(EmpSalaryInfo empSal){
    EmpSalaryInfo empSave = new EmpSalaryInfo();
    System.out.println("empSal--------" + empSal);
    try {
        empSave = empSalRepo.save(empSal);
    } catch (Exception e) {
        System.err.println("Excep in saveEmployeeIdSalary : " + e.getMessage());
        e.printStackTrace();
    }
    return empSave;
}


@RequestMapping(value = { "/getAllDriverEmployee" }, method = RequestMethod.GET)
public List<GetEmployeeDetails> getAllDriverEmployee(){
    List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
    Setting setting = settingRepo.findByKey("designation_id");
    try {
        list = getEmployeeDetailsRepo.getDriverEmpDetailList(Integer.parseInt(setting.getValue()));
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployee : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllEmployee" }, method = RequestMethod.POST)
public List<EmployeeMaster> getAllEmployee(int companyId){
    List<EmployeeMaster> list = new ArrayList<EmployeeMaster>();
    try {
        list = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
    } catch (Exception e) {
        System.err.println("Excep in getAllEmployee : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getEmployeeById" }, method = RequestMethod.POST)
public EmployeeMaster getEmployeeById(int empId){
    EmployeeMaster emp = new EmployeeMaster();
    try {
        emp = empRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getEmployeeById : " + e.getMessage());
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/getEmployeeBankInfo" }, method = RequestMethod.POST)
public TblEmpBankInfo getEmployeeBankInfo(int empId){
    TblEmpBankInfo empBank = new TblEmpBankInfo();
    try {
        empBank = bankInfoRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getEmployeeBankInfo : " + e.getMessage());
        e.printStackTrace();
    }
    return empBank;
}


@RequestMapping(value = { "/saveEmpSalAllowanceInfo" }, method = RequestMethod.POST)
public List<EmpSalAllowance> saveEmpSalAllowanceInfo(List<EmpSalAllowance> allowncList){
    List<EmpSalAllowance> empAllowance = new ArrayList<EmpSalAllowance>();
    System.out.println("allowance--------" + allowncList);
    try {
        empAllowance = empSalAllowanceRepo.saveAll(allowncList);
    } catch (Exception e) {
        System.err.println("Excep in saveEmployeeIdUser : " + e.getMessage());
        e.printStackTrace();
    }
    return empAllowance;
}


@RequestMapping(value = { "/saveEmpSalAllowanceIds" }, method = RequestMethod.POST)
public EmpSalAllowance saveEmpSalAllowanceIds(EmpSalAllowance allowance){
    EmpSalAllowance empAllowanceIds = new EmpSalAllowance();
    System.out.println("allowance--------" + allowance);
    try {
        empAllowanceIds = empSalAllowanceRepo.save(allowance);
    } catch (Exception e) {
        System.err.println("Excep in saveEmployeeIdUser : " + e.getMessage());
        e.printStackTrace();
    }
    return empAllowanceIds;
}


@RequestMapping(value = { "/getEmployeeSalAllowances" }, method = RequestMethod.POST)
public List<EmpSalAllowance> getEmployeeSalAllowances(int empId){
    List<EmpSalAllowance> empAllowance = new ArrayList<EmpSalAllowance>();
    try {
        empAllowance = empSalAllowanceRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getEmployeeSalAllowances : " + e.getMessage());
        e.printStackTrace();
    }
    return empAllowance;
}


@RequestMapping(value = { "/getEmployeeSalInfo" }, method = RequestMethod.POST)
public EmpSalaryInfo getEmployeeSalInfo(int empId){
    EmpSalaryInfo empSal = new EmpSalaryInfo();
    try {
        empSal = empSalRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        System.err.println("Excep in saveEmployeeIdSalary : " + e.getMessage());
        e.printStackTrace();
    }
    return empSal;
}


@RequestMapping(value = { "/deleteEmployeeStatus" }, method = RequestMethod.POST)
public Info deleteEmployeeStatus(int empId,int isActive){
    Info info = new Info();
    try {
        int res = empRepo.deleteEmployeeStatus(empId, isActive);
        if (res > 0) {
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/updateEmpCode" }, method = RequestMethod.POST)
@ResponseBody
public List<DailyDaily> updateEmpCode(List<DailyDaily> dailyList){
    List<DailyDaily> dailyListRes = new ArrayList<>();
    try {
        for (int x = 0; x < dailyList.size(); x++) {
            int result = empRepo.submitupdateempcode(dailyList.get(x).getOtHr(), dailyList.get(x).getId());
        }
    } catch (Exception e) {
        dailyListRes = new ArrayList<>();
        e.printStackTrace();
    }
    return dailyListRes;
}


@RequestMapping(value = { "/deleteEmployee" }, method = RequestMethod.POST)
public Info deleteEmployee(int empId){
    Info info = new Info();
    try {
        int res = empRepo.deleteEmployee(empId);
        if (res > 0) {
            int a = empInfoRepo.deleteEmployeeInfo(empId);
            int b = bankInfoRepo.deleteEmpBankInfo(empId);
            int c = nomineeRepo.deleteEmpNominee(empId);
            int d = empSalRepo.deleteEmpSalInfo(empId);
            int e = empSalAllowanceRepo.deleteEmpAllowances(empId);
            int f = employeeDocsRepository.deleteEmpDoc(empId);
            info.setError(false);
            info.setMsg("Sucess");
        } else {
            info.setError(true);
            info.setMsg("Fail");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteEmployee : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getEmployeePersonalInfo" }, method = RequestMethod.POST)
public TblEmpInfo getEmployeePersonalInfo(int empId){
    TblEmpInfo emp = new TblEmpInfo();
    try {
        emp = empInfoRepo.findByEmpIdAndDelStatus(empId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getEmployeePersonalInfo : " + e.getMessage());
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/saveEmployeeIdUser" }, method = RequestMethod.POST)
public User saveEmployeeIdUser(User user){
    User empSave = new User();
    System.out.println("user--------" + user);
    try {
        empSave = userRepo.save(user);
    } catch (Exception e) {
        System.err.println("Excep in saveEmployeeIdUser : " + e.getMessage());
        e.printStackTrace();
    }
    return empSave;
}


}