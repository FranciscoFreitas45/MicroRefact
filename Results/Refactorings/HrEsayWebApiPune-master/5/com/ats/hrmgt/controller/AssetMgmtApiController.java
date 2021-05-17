import com.ats.hrmgt.model;
import com.ats.hrmgt.model.assets;
import com.ats.hrmgt.repo.asset;
import com.ats.hrmgt.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@RestController
public class AssetMgmtApiController {

@Autowired
 private AssetCategoryRepo assetCatRepo;

@Autowired
 private AssetVendorRepo assetVendorRepo;

@Autowired
 private AssetAmcRepo assetAmcRepo;

@Autowired
 private AssetsRepo assetsRepo;

@Autowired
 private AssetsDetailsListRepo assetsListRepo;

@Autowired
 private AssetTransRepo assetTransRepo;

@Autowired
 private AssetEmployeeRepo assetEmpRepo;

@Autowired
 private AssignedAssetsListRepo assignedAssetListRepo;

@Autowired
 private AssetServicingRepo assetServiceRepo;

@Autowired
 private AssetServiceDetailsRepo assetServiceListRepo;

@Autowired
 private AssetEmpHistoryInfoRepo assetEmpHistoryRepo;

@Autowired
 private AssetNotificatnRepo assetNotityRepo;

@Autowired
 private AMCExpirationDetailRepo amcExpireNotifyRepo;

@Autowired
 private CatWiseAssetDistributnRepo catWiseAssetDistributnRepo;

@Autowired
 private CatWiseAssetCountRepo cateWiseAssetCntRepo;

@Autowired
 private ServicingDashDetailsRepo servicingDetailRepo;

@Autowired
 private AssetLogRepo aLogRepo;

 private Date date;

 private SimpleDateFormat sf;

 private Calendar curCal;

 private String curDtTime;

@Autowired
 private AssetsDashRepo assetDashRepo;

@Autowired
 private AssetAMCExpiryReportRepo assetAmcReportRepo;

@Autowired
 private CatWiseTotalAssetsReportRepo catWiseAssetRepo;

@Autowired
 private AssetCateWiseSummaryReportRepo cateWiseAssetReportRepo;

@Autowired
 private EmpWiseAssetsReportRepo empWiseAssetRepo;

@Autowired
 private AssetReturnPendingReportRepo assetPendingReturnRepo;

@Autowired
 private ScrappedAssetsReportRepo scrapAssetRepo;

@Autowired
 private VendorWiseTtlAssetsReportRepo vendorAssetReport;

@Autowired
 private LocationWiseTtlAssetsRepo locTtlAssetsRepo;

@Autowired
 private AssetLogReportRepo assetLogReport;


@RequestMapping(value = { "/getAssetEmployee" }, method = RequestMethod.POST)
public AssetEmployee getAssetEmployee(int locId,int empId){
    AssetEmployee emp = new AssetEmployee();
    try {
        emp = assetEmpRepo.getAssetsEmpByLocationAndEmpId(locId, empId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetEmployee : " + e.getMessage());
        e.printStackTrace();
    }
    return emp;
}


@RequestMapping(value = { "/getAllUnassignAssets" }, method = RequestMethod.GET)
public List<AssetsDetailsList> getAllUnassignAssets(){
    List<AssetsDetailsList> list = new ArrayList<AssetsDetailsList>();
    try {
        list = assetsListRepo.getAllUnassignAssetList();
    } catch (Exception e) {
        System.err.println("Excep in getAllUnassignAssets : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetInfoById" }, method = RequestMethod.POST)
public AssetsDetailsList getAssetInfoById(int assetId){
    AssetsDetailsList assetCat = new AssetsDetailsList();
    try {
        assetCat = assetsListRepo.getAssetDetailById(assetId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetInfoById : " + e.getMessage());
        e.printStackTrace();
    }
    return assetCat;
}


@RequestMapping(value = { "/saveAssetAmc" }, method = RequestMethod.POST)
public AssetAmc getAssetAmcById(AssetAmc assetAmc){
    AssetAmc amc = new AssetAmc();
    try {
        amc = assetAmcRepo.save(assetAmc);
    } catch (Exception e) {
        System.err.println("Excep in saveAssetAmc : " + e.getMessage());
        e.printStackTrace();
    }
    return amc;
}


@RequestMapping(value = { "/getAllAssetCategory" }, method = RequestMethod.GET)
public List<AssetCategory> getAllAssetCategory(){
    List<AssetCategory> list = new ArrayList<AssetCategory>();
    try {
        list = assetCatRepo.findByDelStatusOrderByAssetCatIdDesc(1);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetCategory : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveAssetCat" }, method = RequestMethod.POST)
public AssetCategory saveAssetCat(AssetCategory asset){
    AssetCategory assetCat = new AssetCategory();
    try {
        if (asset.getAssetCatId() == 0) {
            AssetCategory res = assetCatRepo.findByCatNameAndDelStatus(asset.getCatName(), 1);
            if (res == null) {
                assetCat = assetCatRepo.save(asset);
            } else {
                assetCat.setAssetCatId(-1);
            }
        } else {
            // System.err.println("In Else");
            int res = assetCatRepo.findAssetCat(asset.getAssetCatId(), asset.getCatName());
            if (res == 0) {
                assetCat = assetCatRepo.save(asset);
            } else {
                assetCat.setAssetCatId(-1);
            }
        }
    } catch (Exception e) {
        System.err.println("Excep in saveAssetCat : " + e.getMessage());
        e.printStackTrace();
    }
    return assetCat;
}


@RequestMapping(value = { "/deleteAssetById" }, method = RequestMethod.POST)
public Info deleteAssetById(int assetId){
    Info info = new Info();
    try {
        int i = assetsRepo.deleteAsset(assetId);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset Deleted Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Delete Asset");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAssetTransactnById" }, method = RequestMethod.POST)
public AssetTrans getAssetTransactnById(int assetId){
    AssetTrans aset = new AssetTrans();
    try {
        aset = assetTransRepo.findByAssetTransId(assetId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetTransactionById : " + e.getMessage());
        e.printStackTrace();
    }
    return aset;
}


@RequestMapping(value = { "/getAssetsList" }, method = RequestMethod.GET)
public List<Assets> getAssetsList(){
    List<Assets> list = new ArrayList<Assets>();
    try {
        list = assetsRepo.findByDelStatusOrderByAssetIdDesc(1);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssets : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveAssetsService" }, method = RequestMethod.POST)
public AssetServicing saveAssetsService(AssetServicing assetService){
    AssetServicing service = new AssetServicing();
    try {
        service = assetServiceRepo.save(assetService);
    } catch (Exception e) {
        System.err.println("Excep in saveAssetsService : " + e.getMessage());
        e.printStackTrace();
    }
    return service;
}


@RequestMapping(value = { "/getAllCateWiseAssetDistributn" }, method = RequestMethod.GET)
public List<CatWiseAssetDistributn> getAllCateWiseAssetDistributn(){
    List<CatWiseAssetDistributn> list = new ArrayList<CatWiseAssetDistributn>();
    try {
        list = catWiseAssetDistributnRepo.getCatWiseAssetDistributn();
    } catch (Exception e) {
        System.err.println("Excep in getAllCateWiseAssetDistributn : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssetEmployees" }, method = RequestMethod.POST)
public List<AssetEmployee> getAllAssetEmployees(int locId){
    List<AssetEmployee> list = new ArrayList<AssetEmployee>();
    try {
        list = assetEmpRepo.getAllAssetsEmpByLocation(locId);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetEmployees : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveAssetVendor" }, method = RequestMethod.POST)
public AssetVendor saveAssetVendor(AssetVendor assetVendor){
    AssetVendor assetCat = new AssetVendor();
    try {
        if (assetVendor.getVendorId() == 0) {
            AssetVendor resComp = assetVendorRepo.findByCompNameAndDelStatus(assetVendor.getCompName(), 1);
            AssetVendor resEmail = assetVendorRepo.findByVendorEmailAndDelStatus(assetVendor.getVendorEmail(), 1);
            if (resComp == null && resEmail == null) {
                assetCat = assetVendorRepo.save(assetVendor);
            } else {
                assetCat.setVendorId(-1);
            }
        } else {
            System.err.println("In Else");
            int resCompName = assetVendorRepo.getVendorDataByCompName(assetVendor.getVendorId(), assetVendor.getCompName());
            int resEmail = assetVendorRepo.getVendorDataByEmail(assetVendor.getVendorId(), assetVendor.getVendorEmail());
            if (resCompName == 0 && resEmail == 0) {
                assetCat = assetVendorRepo.save(assetVendor);
            } else {
                assetCat.setVendorId(-1);
            }
        }
    } catch (Exception e) {
        System.err.println("Excep in saveAssetVendor : " + e.getMessage());
        e.printStackTrace();
    }
    return assetCat;
}


@RequestMapping(value = { "/getAllAssetsByLocation" }, method = RequestMethod.POST)
public List<AssetsDetailsList> getAllAssetsByLocation(int locId){
    List<AssetsDetailsList> list = new ArrayList<AssetsDetailsList>();
    try {
        if (locId > 0) {
            list = assetsListRepo.getAllAssetByLoc(locId);
        } else {
            list = assetsListRepo.getAllAssetList();
        }
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetsByLocation : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetTransactionById" }, method = RequestMethod.POST)
public AssetTrans getAssetTransactionById(int assetTransId){
    AssetTrans aset = new AssetTrans();
    try {
        aset = assetTransRepo.findByAssetTransIdAndDelStatus(assetTransId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAssetTransactionById : " + e.getMessage());
        e.printStackTrace();
    }
    return aset;
}


@RequestMapping(value = { "/getLocationWiseTotalAssetReport" }, method = RequestMethod.POST)
public List<LocationWiseTtlAssets> getLocationWiseTotalAssetReport(int locId){
    List<LocationWiseTtlAssets> list = new ArrayList<LocationWiseTtlAssets>();
    try {
        if (locId != 0) {
            list = locTtlAssetsRepo.getLocationWiseTtlAssetsReportByLocId(locId);
        } else {
            list = locTtlAssetsRepo.getLocationWiseTtlAssetsReport();
        }
    } catch (Exception e) {
        System.err.println("Excep in getLocationWiseTotalAssetReport : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getCateWiseTotalAssetReport" }, method = RequestMethod.POST)
public List<CatWiseTotalAssetsReport> getCateWiseTotalAssetReport(int locId){
    List<CatWiseTotalAssetsReport> list = new ArrayList<CatWiseTotalAssetsReport>();
    try {
        if (locId != 0) {
            list = catWiseAssetRepo.getCatWiseTotalAssetsByLocation(locId);
        } else {
            list = catWiseAssetRepo.getCatWiseTotalAssets();
        }
    } catch (Exception e) {
        System.err.println("Excep in getCateWiseTotalAssetReport : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssetsTransactions" }, method = RequestMethod.GET)
public List<AssetTrans> getAllAssetsTransactions(){
    List<AssetTrans> list = new ArrayList<AssetTrans>();
    try {
        list = assetTransRepo.findByDelStatusOrderByAssetTransIdDesc(1);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetsTransactions : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/updtRegService" }, method = RequestMethod.POST)
public Info updtRegService(int serviceId,int assetId){
    Info info = new Info();
    try {
        int i = assetServiceRepo.chngRegAssetService(serviceId, assetId);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset Service Deleted Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Delete Asset Service");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAllAssignedAssets" }, method = RequestMethod.POST)
public List<AssignedAssetsList> getAllAssignedAssets(int empId){
    List<AssignedAssetsList> list = new ArrayList<AssignedAssetsList>();
    try {
        list = assignedAssetListRepo.getAllAssignedAssetsToEmp(empId);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetCategory : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetReturnPending" }, method = RequestMethod.POST)
public List<EmpWiseAssetsReport> getAssetReturnPending(int locId){
    List<EmpWiseAssetsReport> list = new ArrayList<EmpWiseAssetsReport>();
    try {
        if (locId != 0) {
            list = assetPendingReturnRepo.getAssetReturnPendingReportByLocId(locId);
        } else {
            list = assetPendingReturnRepo.getAssetReturnPendingReport();
        }
    } catch (Exception e) {
        System.err.println("Excep in getAssetReturnPending : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetsDashDetails" }, method = RequestMethod.POST)
public List<AssetsDashDetails> getAssetsDashDetails(int locId,int vendorIds,String fromDate,String toDate){
    List<AssetsDashDetails> list = new ArrayList<AssetsDashDetails>();
    try {
        if (vendorIds != 0 && locId != 0) {
            list = assetDashRepo.getAssetsDashDetailsByVendorId(locId, vendorIds, fromDate, toDate);
        } else if (vendorIds == 0 && locId != 0) {
            list = assetDashRepo.getAllAssetsDashDetails(locId, fromDate, toDate);
        } else if (vendorIds != 0 && locId == 0) {
            list = assetDashRepo.getAssetsDashDetailsByVendorId(vendorIds, fromDate, toDate);
        } else {
            list = assetDashRepo.getAllAssetsDashDetailsBetweenDates(fromDate, toDate);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAssetsDashDetails : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/updtAssetAMCStatus" }, method = RequestMethod.POST)
public Info updtAssetAMCStatus(int assetAmcId,int status){
    Info info = new Info();
    try {
        int i = assetAmcRepo.updtAmcStatus(assetAmcId, status);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset AMC Renewed Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Renewed Asset AMC");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetAmcById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAssignedImg" }, method = RequestMethod.POST)
public String updtAssetToScrap(int assetId,int empId){
    String imgStr = null;
    try {
        imgStr = assetTransRepo.getImgByEmpId(assetId, empId);
    } catch (Exception e) {
    // TODO: handle exception
    }
    return imgStr;
}


@RequestMapping(value = { "/updateAssetStatusLost" }, method = RequestMethod.POST)
public Info updateAssetStatusLost(int transactnId,int assetId,String lostAssetRemark,int userUpdateId,String updateTime,int status,int assetStatus){
    Info info = new Info();
    try {
        int i = assetTransRepo.updateStatusAssetsLost(transactnId, status, userUpdateId, updateTime, assetStatus, lostAssetRemark);
        if (i > 0) {
            int updtAsset = assetsRepo.changeAssetStatusToLost(assetId, assetStatus, userUpdateId, updateTime);
            info.setError(false);
            info.setMsg("Asset Lost Updated Succesfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Updated Asset Lost");
        }
    } catch (Exception e) {
        System.err.println("Excep in updateAssetStatusLost : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAssetServiceList" }, method = RequestMethod.POST)
public List<AssetServiceDetails> getAssetServiceList(int assetId){
    List<AssetServiceDetails> list = new ArrayList<AssetServiceDetails>();
    try {
        list = assetServiceListRepo.getAssetServiceList(assetId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetServiceList : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetCateSummaryReport" }, method = RequestMethod.GET)
public List<AssetCateWiseSummaryReport> getAssetCateSummaryReport(){
    List<AssetCateWiseSummaryReport> list = new ArrayList<AssetCateWiseSummaryReport>();
    try {
        list = cateWiseAssetReportRepo.getCateWiseAssetSummary();
    } catch (Exception e) {
        System.err.println("Excep in getAssetCateSummaryReport : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveAssetsTrans" }, method = RequestMethod.POST)
public List<AssetTrans> saveAssetsTrans(List<AssetTrans> assetTransList){
    List<AssetTrans> assetTrans = new ArrayList<AssetTrans>();
    AssetLog log = new AssetLog();
    int logId = 0;
    System.out.println("Save AssetTransList--------" + assetTransList);
    try {
        assetTrans = assetTransRepo.saveAll(assetTransList);
        System.err.println(assetTransList);
        for (int i = 0; i < assetTrans.size(); i++) {
            System.err.println(assetTrans.get(i).getAssetTransId());
            int assetId = assetTrans.get(i).getAssetId();
            int updtAsset = assetsRepo.changeAssetStatus(assetId, 1);
        }
    } catch (Exception e) {
        System.err.println("Excep in saveAssetsTrans : " + e.getMessage());
        e.printStackTrace();
    }
    return assetTrans;
}


@RequestMapping(value = { "/deleteAssetVendorById" }, method = RequestMethod.POST)
public Info deleteAssetVendorById(int assetVendorId){
    Info info = new Info();
    try {
        int i = assetVendorRepo.deleteAssetVendor(assetVendorId);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset Vendor Deleted Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Delete Asset Vendor");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetVendorById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getEmpWiseAssetsReport" }, method = RequestMethod.POST)
public List<EmpWiseAssetsReport> getEmpWiseAssetsReport(int locId){
    List<EmpWiseAssetsReport> list = new ArrayList<EmpWiseAssetsReport>();
    try {
        if (locId != 0) {
            list = empWiseAssetRepo.getEmpWiseAssetsReportByLocId(locId);
        } else {
            list = empWiseAssetRepo.getEmpWiseAssetsReport();
        }
    } catch (Exception e) {
        System.err.println("Excep in getEmpWiseAssetsReport : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssetVendor" }, method = RequestMethod.GET)
public List<AssetVendor> getAllAssetVendor(){
    List<AssetVendor> list = new ArrayList<AssetVendor>();
    try {
        list = assetVendorRepo.findByDelStatusOrderByVendorIdDesc(1);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetVendor : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssets" }, method = RequestMethod.GET)
public List<AssetsDetailsList> getAllAssets(){
    List<AssetsDetailsList> list = new ArrayList<AssetsDetailsList>();
    try {
        list = assetsListRepo.getAllAssetList();
    } catch (Exception e) {
        System.err.println("Excep in getAllAssets : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getServicingDetails" }, method = RequestMethod.GET)
public List<ServicingDashDetails> getServicingDetails(){
    List<ServicingDashDetails> list = new ArrayList<ServicingDashDetails>();
    try {
        list = servicingDetailRepo.getServicingDashDetailsList();
    } catch (Exception e) {
        System.err.println("Excep in getServicingDetails : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssetAmc" }, method = RequestMethod.GET)
public List<AssetAmc> getAllAssetAmc(){
    List<AssetAmc> list = new ArrayList<AssetAmc>();
    try {
        list = assetAmcRepo.findByDelStatusOrderByAmcIdDesc(1);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetAmc : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssetsAMCForNotifiction" }, method = RequestMethod.GET)
public List<AMCExpirationDetail> getAllAssetsAMCForNotifiction(){
    List<AMCExpirationDetail> list = new ArrayList<AMCExpirationDetail>();
    try {
        list = amcExpireNotifyRepo.getAMCExpirationDetailForNotificatn();
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetsAMCForNotifiction : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssetAMCDetails" }, method = RequestMethod.GET)
public List<AssetAmc> getAllAssetAMCDetails(){
    List<AssetAmc> list = new ArrayList<AssetAmc>();
    try {
        list = assetAmcRepo.getAllAssetAMC();
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetAMCDetails : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/saveRenewAssetAmc" }, method = RequestMethod.POST)
public AssetAmc renewAssetAmc(AssetAmc assetAmc){
    AssetAmc amc = new AssetAmc();
    Info info = new Info();
    try {
        amc = assetAmcRepo.save(assetAmc);
    } catch (Exception e) {
        System.err.println("Excep in saveAssetAmc : " + e.getMessage());
        e.printStackTrace();
    }
    return amc;
}


@RequestMapping(value = { "/returnAssetByIds" }, method = RequestMethod.POST)
public Info returnAssetByIds(int assetTransId,int assetTransStatus,String returnDate,int assetId,String returnRemark,String assetReturnImg,String updateDateTime,int updateUserId){
    Info info = new Info();
    AssetLog log = new AssetLog();
    try {
        int i = assetTransRepo.updateAssetsStatus(assetTransId, assetTransStatus, returnDate, returnRemark, assetReturnImg, updateDateTime, updateUserId);
        if (i > 0) {
            int updtAsset = assetsRepo.changeAssetStatus(assetId, 0);
            info.setError(false);
            info.setMsg("Asset Deleted Successfully");
            // Log
            log.setAssetLogId(0);
            log.setAssetId(assetId);
            log.setAssetLogDate(date);
            log.setAssetLogDesc("Return assets from employee");
            log.setAssetTransId(assetTransId);
            log.setDelStatus(1);
            log.setMakerUserId(updateUserId);
            log.setUpdateDateTime(curDtTime);
            aLogRepo.save(log);
        } else {
            info.setError(true);
            info.setMsg("Failed to Delete Asset");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/deleteAssetAmcById" }, method = RequestMethod.POST)
public Info deleteAssetAmcById(int assetAmcId){
    Info info = new Info();
    try {
        int i = assetAmcRepo.deleteAssetAmc(assetAmcId);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset Amc Deleted Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Delete Asset Amc");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetAmcById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getCateWiseAssetCnt" }, method = RequestMethod.GET)
public List<CatWiseAssetCount> getCateWiseAssetCnt(){
    List<CatWiseAssetCount> list = new ArrayList<CatWiseAssetCount>();
    try {
        list = cateWiseAssetCntRepo.getCatWiseAssetCount();
    } catch (Exception e) {
        System.err.println("Excep in getCateWiseAssetCnt : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getVendorWiseTotalAssetsReport" }, method = RequestMethod.GET)
public List<VendorWiseTtlAssetsReport> getVendorWiseTotalAssetsReport(){
    List<VendorWiseTtlAssetsReport> list = new ArrayList<VendorWiseTtlAssetsReport>();
    try {
        list = vendorAssetReport.getVendorWiseReport();
    } catch (Exception e) {
        System.err.println("Excep in getVendorWiseTotalAssetsReport : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAllAssetsForNotifiction" }, method = RequestMethod.GET)
public List<AssetNotificatn> getAllAssetsForNotifiction(){
    List<AssetNotificatn> list = new ArrayList<AssetNotificatn>();
    try {
        list = assetNotityRepo.getAssetDetailForNotification();
    } catch (Exception e) {
        System.err.println("Excep in getAllAssetsForNotifiction : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetCatById" }, method = RequestMethod.POST)
public AssetCategory getAssetCatById(int assetCatId){
    AssetCategory assetCat = new AssetCategory();
    try {
        assetCat = assetCatRepo.findByAssetCatId(assetCatId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetCatById : " + e.getMessage());
        e.printStackTrace();
    }
    return assetCat;
}


@RequestMapping(value = { "/saveAssets" }, method = RequestMethod.POST)
public Assets saveAssets(Assets assets){
    Assets aset = new Assets();
    try {
        aset = assetsRepo.save(assets);
    } catch (Exception e) {
        System.err.println("Excep in getAllAssets : " + e.getMessage());
        e.printStackTrace();
    }
    return aset;
}


@RequestMapping(value = { "/getAssetById" }, method = RequestMethod.POST)
public Assets getAssetById(int assetId){
    Assets aset = new Assets();
    try {
        aset = assetsRepo.findByAssetId(assetId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetById : " + e.getMessage());
        e.printStackTrace();
    }
    return aset;
}


@RequestMapping(value = { "/getAssetsAMCExpiryDetails" }, method = RequestMethod.POST)
public List<AssetAMCExpiryReport> getAssetsAMCExpiryDetails(int locId){
    List<AssetAMCExpiryReport> list = new ArrayList<AssetAMCExpiryReport>();
    try {
        if (locId != 0) {
            list = assetAmcReportRepo.getAllAssetAMCExpiryDetails(locId);
        }
    } catch (Exception e) {
        System.err.println("Excep in getAssetsAMCExpiryDetails : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetServicingById" }, method = RequestMethod.POST)
public AssetServicing getAssetServicingById(int serviceId){
    AssetServicing service = new AssetServicing();
    try {
        service = assetServiceRepo.findByTServicingId(serviceId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetTransactionById : " + e.getMessage());
        e.printStackTrace();
    }
    return service;
}


@RequestMapping(value = { "/terminateAssetAMC" }, method = RequestMethod.POST)
public Info terminateAssetAMC(int assetAMCId,int status,int userUpdateId,String updateTime){
    Info info = new Info();
    try {
        int i = assetAmcRepo.terminateAMC(assetAMCId, status, userUpdateId, updateTime);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset AMC Terminated Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Terminated Asset AMC");
        }
    } catch (Exception e) {
        System.err.println("Excep in terminateAssetAMC : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/deleteAssetCat" }, method = RequestMethod.POST)
public Info deleteAssetCat(int assetCatId){
    Info info = new Info();
    try {
        int i = assetCatRepo.deleteAssetCatId(assetCatId);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset Category Deleted Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Delete Asset Category");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetCat : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAssetVendorById" }, method = RequestMethod.POST)
public AssetVendor getAssetVendorById(int assetVendorId){
    AssetVendor assetVendor = new AssetVendor();
    try {
        assetVendor = assetVendorRepo.findByVendorId(assetVendorId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetVendorById : " + e.getMessage());
        e.printStackTrace();
    }
    return assetVendor;
}


@RequestMapping(value = { "/deleteAssetServiceById" }, method = RequestMethod.POST)
public Info deleteAssetServiceById(int serviceId){
    Info info = new Info();
    try {
        int i = assetServiceRepo.deleteAssetServiceInfo(serviceId);
        if (i > 0) {
            info.setError(false);
            info.setMsg("Asset Service Deleted Successfully");
        } else {
            info.setError(true);
            info.setMsg("Failed to Delete Asset Service");
        }
    } catch (Exception e) {
        System.err.println("Excep in deleteAssetById : " + e.getMessage());
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getAssetLogReport" }, method = RequestMethod.POST)
public List<AssetLogReport> assetLogReport(int assetId){
    List<AssetLogReport> list = new ArrayList<AssetLogReport>();
    try {
        if (assetId != 0) {
            list = assetLogReport.getAssetLogReportByAssetId(assetId);
        } else {
            list = assetLogReport.getAssetLogReport();
        }
    } catch (Exception e) {
        System.err.println("Excep in getAssetLogReport : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetsListByLocId" }, method = RequestMethod.POST)
public List<Assets> getAssetsListByLocId(int locId){
    List<Assets> list = new ArrayList<Assets>();
    try {
        list = assetsRepo.findByLocIdAndDelStatus(locId, 1);
    } catch (Exception e) {
        System.err.println("Excep in getAssetsListByLocId : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getScrappedAssetsReport" }, method = RequestMethod.POST)
public List<ScrappedAssetsReport> getScrappedAssetsReport(int locId){
    List<ScrappedAssetsReport> list = new ArrayList<ScrappedAssetsReport>();
    try {
        if (locId != 0) {
            list = scrapAssetRepo.getScrappedAllAssetsReportByLocId(locId);
        } else {
            list = scrapAssetRepo.getScrappedAllAssetsReport();
        }
    } catch (Exception e) {
        System.err.println("Excep in getScrappedAssetsReport : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getAssetEmpHistoryList" }, method = RequestMethod.POST)
public List<AssetEmpHistoryInfo> getAssetEmpHistoryList(int assetId){
    List<AssetEmpHistoryInfo> list = new ArrayList<AssetEmpHistoryInfo>();
    try {
        list = assetEmpHistoryRepo.getAssetAssignedToEmp(assetId);
    } catch (Exception e) {
        System.err.println("Excep in getAssetEmpHistoryList : " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}


}