import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.domain.PhBanner;
import cn.offway.athena.domain.PhBannerHistory;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.repository.PhBannerHistoryRepository;
import cn.offway.athena.service.PhBannerHistoryService;
import cn.offway.athena.service.PhBannerService;
import cn.offway.athena.service.PhBrandadminService;
import cn.offway.athena.service.PhRoleadminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping
public class BannerController {

@Autowired
 private  PhBannerService bannerService;

@Autowired
 private  QiniuProperties qiniuProperties;

@Autowired
 private  PhBannerHistoryService bannerHistoryService;

@Autowired
 private  PhBannerHistoryRepository bannerHistoryRepository;

@Autowired
 private  PhRoleadminService roleadminService;

@Autowired
 private  PhBrandadminService brandadminService;


@RequestMapping("/banner_list")
@ResponseBody
public Map<String,Object> getAll(HttpServletRequest request,String id,String remark){
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "status"), new Sort.Order("sort"));
    Page<PhBanner> pages = bannerService.findAll(id, remark, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort));
    int initEcho = sEcho + 1;
    Map<String, Object> map = new HashMap<>();
    map.put("sEcho", initEcho);
    // 数据总条数
    map.put("iTotalRecords", pages.getTotalElements());
    // 显示的条数
    map.put("iTotalDisplayRecords", pages.getTotalElements());
    // 数据集合
    map.put("aData", pages.getContent());
    return map;
}


@RequestMapping("/banner_listHistorySub")
@ResponseBody
public List<PhBannerHistory> listHistorySub(String id){
    return bannerHistoryService.findList(id);
}


@ResponseBody
@RequestMapping("/banner_top")
public boolean top(Long id,Long sort){
    PhBanner banner = bannerService.findOne(id);
    if (banner != null) {
        bannerService.resort(sort);
        banner.setSort(sort);
        bannerService.save(banner);
    }
    return true;
}


@RequestMapping("/banner_listHistoryRank")
@ResponseBody
public Object listHistoryRank(PhAdmin admin,String from,String id,String name){
    if ("1".equals(from)) {
        return bannerHistoryRepository.listRank();
    } else {
        List<PhBannerHistory> res;
        List<Long> roles = roleadminService.findRoleIdByAdminId(admin.getId());
        if (roles.contains(BigInteger.valueOf(1L))) {
            if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(name)) {
                res = bannerHistoryRepository.listRank(Long.valueOf(id), "%" + name + "%");
            } else if (StringUtils.isNotBlank(id)) {
                res = bannerHistoryRepository.listRank(Long.valueOf(id));
            } else if (StringUtils.isNotBlank(name)) {
                res = bannerHistoryRepository.listRank("%" + name + "%");
            } else {
                res = bannerHistoryRepository.listRank();
            }
        } else {
            List<Long> brandIds = brandadminService.findBrandIdByAdminId(admin.getId());
            if (brandIds != null && brandIds.size() > 0) {
                // String inStr = StringUtils.join(brandIds, ",");
                if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(name)) {
                    res = bannerHistoryRepository.listRank(Long.valueOf(id), "%" + name + "%", brandIds);
                } else if (StringUtils.isNotBlank(id)) {
                    res = bannerHistoryRepository.listRank(Long.valueOf(id), brandIds);
                } else if (StringUtils.isNotBlank(name)) {
                    res = bannerHistoryRepository.listRank("%" + name + "%", brandIds);
                } else {
                    res = bannerHistoryRepository.listRank(brandIds);
                }
            } else {
                return null;
            }
        }
        int initEcho = 1;
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", initEcho);
        // 数据总条数
        map.put("iTotalRecords", res.size());
        // 显示的条数
        map.put("iTotalDisplayRecords", res.size());
        // 数据集合
        map.put("aData", res);
        return map;
    }
}


@RequestMapping("/banner_get")
@ResponseBody
public PhBanner get(Long id){
    return bannerService.findOne(id);
}


@Transactional
@RequestMapping("/banner_save")
@ResponseBody
public boolean save(PhBanner banner){
    if (banner.getId() == null) {
        banner.setStatus("0");
        banner.setSort(null);
        if (null == banner.getRedirectId()) {
            banner.setRedirectId("");
        }
        banner.setCreateTime(new Date());
    } else {
        PhBanner bannerSaved = bannerService.findOne(banner.getId());
        banner.setStatus(bannerSaved.getStatus());
        banner.setSort(bannerSaved.getSort());
        banner.setCreateTime(bannerSaved.getCreateTime());
        boolean timeRangeChanged = Math.abs(banner.getBeginTime().compareTo(bannerSaved.getBeginTime())) + Math.abs(banner.getEndTime().compareTo(bannerSaved.getEndTime())) != 0;
        /* 状态[0-未上架,1-已上架] **/
        if (timeRangeChanged && "1".equals(banner.getStatus())) {
            saveHistory(banner);
        }
    }
    bannerService.save(banner);
    return true;
}


@RequestMapping("/banner.html")
public String index(ModelMap map){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    return "banner_index";
}


@RequestMapping("/banner_rank.html")
public String rank(ModelMap map){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    return "banner_rank";
}


@RequestMapping("/banner_up")
@ResponseBody
public boolean up(Long id){
    PhBanner banner = bannerService.findOne(id);
    long latest = bannerService.getMaxSort();
    banner.setStatus("1");
    banner.setSort(latest + 1);
    bannerService.save(banner);
    saveHistory(banner);
    return true;
}


public void saveHistory(PhBanner banner){
    PhBannerHistory history = new PhBannerHistory();
    history.setBanner(banner.getRemark());
    history.setBannerId(banner.getRedirectId());
    history.setBannerImg(banner.getBanner());
    history.setBeginTime(banner.getBeginTime());
    history.setEndTime(banner.getEndTime());
    history.setCreateTime(new Date());
    bannerHistoryService.save(history);
}


@RequestMapping("/banner_del")
@ResponseBody
public boolean delete(Long[] ids){
    for (long id : ids) {
        bannerService.delete(id);
    }
    return true;
}


@RequestMapping("/banner_down")
@ResponseBody
public boolean down(Long id){
    PhBanner banner = bannerService.findOne(id);
    banner.setStatus("0");
    banner.setSort(null);
    bannerService.save(banner);
    return true;
}


}