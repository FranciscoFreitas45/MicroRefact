package com.zis.shop.controller;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.mvc.ext.Token;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.shiro.dto.ActiveUser;
import com.zis.shiro.dto.RegistUserDto;
import com.zis.shiro.dto.UpdateUserInfo;
import com.zis.shiro.service.RegistAndUpdateService;
import com.zis.shop.bean.Company;
import com.zis.DTO.ShopInfo;
import com.zis.shop.dto.CompanyAndStockDto;
import com.zis.shop.dto.SaveOrUpdateCompanyDto;
import com.zis.Interface.ShopService;
import com.zis.Interface.StorageRepoInfoDao;
import com.zis.Interface.ShopService;
import com.zis.DTO.StorageRepoInfo;


@Controller
@RequestMapping(value = "/shop")
public class CompanyController {

 private  Logger logger;

 private  String OPERATE_TYPE_UPDATE;

 private  String OPERATE_TYPE_ADD;

@Autowired
 private  ShopService shopService;

@Autowired
 private  RegistAndUpdateService registAndUpdateService;

@Autowired
 private  StorageRepoInfoDao storageRepoInfoDao;


public void trimAndDeleteEnterDto(SaveOrUpdateCompanyDto dto){
    dto.setCompanyName(DeleteEnter(dto.getCompanyName()));
    dto.setContacts(DeleteEnter(dto.getContacts()));
    dto.setMobile(DeleteEnter(dto.getMobile()));
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/showCompanys")
public String showCompanys(String companyName,String contacts,HttpServletRequest request,ModelMap map){
    Pageable page = WebHelper.buildPageRequest(request);
    Page<Company> companyList = this.shopService.queryCompany(companyName, contacts, page);
    if (!companyList.getContent().isEmpty()) {
        List<Company> list = companyList.getContent();
        List<CompanyAndStockDto> csList = this.registAndUpdateService.buildCompanyAndStockDto(list);
        map.put("companyList", csList);
        map.put("page", page.getPageNumber() + 1);
        setQueryConditionToPage(companyName, contacts, map);
        if (companyList.hasPrevious()) {
            map.put("prePage", page.previousOrFirst().getPageNumber());
        }
        if (companyList.hasNext()) {
            map.put("nextPage", page.next().getPageNumber());
        }
        return "shop/company/show/show-update-company-list";
    }
    map.put("notResult", "???????????????,??????????????????????????????????????????????????????");
    return "shop/company/show/show-update-company-list";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/saveOrUpdateCompany")
@Token(checking = true)
public String saveOrUpdateCompany(SaveOrUpdateCompanyDto dto,BindingResult br,ModelMap map){
    map.put("company", dto);
    if (br.hasErrors()) {
        return "shop/company/saveOrUpdate/save-or-update-company";
    }
    try {
        if (OPERATE_TYPE_ADD.equals(dto.getTypeStatus())) {
            trimAndDeleteEnterDto(dto);
            this.shopService.saveCompany(dto);
            map.put("actionMessage", "[" + dto.getCompanyName() + "]" + "????????????");
            return "shop/company/show/show-update-company-list";
        } else if (OPERATE_TYPE_UPDATE.equals(dto.getTypeStatus())) {
            trimAndDeleteEnterDto(dto);
            this.shopService.updateCompany(dto);
            map.put("actionMessage", "[" + dto.getCompanyName() + "]" + "????????????");
            return "shop/company/show/show-update-company-list";
        } else {
            throw new RuntimeException("????????????????????????: " + dto.getTypeStatus());
        }
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        logger.error(e.getMessage(), e);
        return "error";
    }
}


@RequestMapping(value = "/showCompanyInfo")
public String showCompanyInfo(ModelMap map){
    Integer companyId = getCompanyId();
    Company company = this.shopService.findCompanyOne(companyId);
    if (company == null) {
        map.put("actionError", "?????????????????????????????????????????????");
        logger.error("?????????????????????????????????????????????");
        return "error";
    }
    map.put("company", company);
    setCompanyInfoForJsp(map, companyId);
    return "shop/company/show/show-company-info";
}


@RequestMapping(value = "/gotoUpdateUserCompany")
@Token(generate = true)
public String gotoUpdateUserCompany(ModelMap map){
    Integer companyId = getCompanyId();
    Company company = this.shopService.findCompanyOne(companyId);
    if (company == null) {
        map.put("actionError", "?????????????????????????????????????????????");
        logger.error("?????????????????????????????????????????????");
        return "error";
    }
    map.put("company", company);
    return "shop/company/saveOrUpdate/update-user-company";
}


public void setCompanyInfoForJsp(ModelMap map,Integer companyId){
    // TODO ???????????????????????? ????????????????????????????????????
    List<ShopInfo> shopList = this.shopService.findCompanyShop(companyId);
    map.put("shopList", shopList);
    List<UpdateUserInfo> userList = this.registAndUpdateService.findAllUserByCompanyId(companyId);
    map.put("userList", userList);
    List<StorageRepoInfo> storList = this.storageRepoInfoDao.findByOwnerIdOrderByGmtCreateAsc(getCompanyId());
    if (!storList.isEmpty()) {
        map.put("stock", storList.get(0));
    }
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/gotoSaveCompany")
@Token(generate = true)
public String gotoSaveCompany(ModelMap map){
    SaveOrUpdateCompanyDto dto = new SaveOrUpdateCompanyDto();
    dto.setTypeStatus(OPERATE_TYPE_ADD);
    map.put("company", dto);
    return "shop/company/saveOrUpdate/save-or-update-company";
}


@RequestMapping(value = "/updateUserCompany")
@Token(generate = true)
public String updateUserCompany(SaveOrUpdateCompanyDto dto,BindingResult br,ModelMap map){
    try {
        map.put("company", dto);
        if (br.hasErrors()) {
            return "shop/company/saveOrUpdate/update-user-company";
        }
        dto.setCompanyId(getCompanyId());
        this.shopService.updateCompany(dto);
        map.put("actionMessage", dto.getCompanyName() + " ????????????");
        return "forward:/shop/showCompanyInfo";
    } catch (Exception e) {
        map.put("errorAction", e.getMessage());
        logger.error(e.getMessage(), e);
        return "error";
    }
}


public Integer getCompanyId(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    return au.getCompanyId();
}


public void setQueryConditionToPage(String companyName,String contacts,ModelMap map){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(companyName)) {
        condition.append("companyName=" + companyName.trim() + "&");
    }
    if (StringUtils.isNotBlank(contacts)) {
        condition.append("contacts=" + contacts.trim() + "&");
    }
    map.put("queryCondition", condition.toString());
}


public String DeleteEnter(String str){
    String trimStr = str.trim();
    StringBuilder sb = new StringBuilder();
    if (trimStr.contains("\n")) {
        String[] s = trimStr.split("\n");
        for (String s1 : s) {
            sb.append(s1);
        }
    } else {
        sb.append(trimStr);
    }
    String s2 = sb.toString();
    if (s2.contains("\r")) {
        String[] s = s2.split("\r");
        sb = new StringBuilder();
        for (String s1 : s) {
            sb.append(s1);
        }
    }
    return sb.toString();
}


public String registCompanyUser(RegistUserDto registUserDto,BindingResult br,ModelMap map,String typeName){
    if (br.hasErrors()) {
        return "shop/company/regist-company-user";
    }
    String password = registUserDto.getPassword();
    String passwordAgain = registUserDto.getPasswordAgain();
    if (!password.equals(passwordAgain)) {
        map.put("passwordError", "?????????????????????");
        return "shop/company/regist-company-user";
    }
    registUserDto.setCompanyId(getCompanyId());
    try {
        this.registAndUpdateService.saveOrUpdateCompanyUser(registUserDto);
    } catch (Exception e) {
        map.put("errorAction", e.getMessage());
        logger.error(e.getMessage(), e);
        return "shop/company/regist-company-user";
    }
    if (registUserDto.getId() == null) {
        map.put("actionMessage", "[" + registUserDto.getUserName() + "] ????????????");
    } else {
        map.put("actionMessage", "[" + registUserDto.getUserName() + "] ????????????");
    }
    return "shop/company/update-company";
}


@RequiresPermissions(value = { "shiro:shiro" })
@RequestMapping(value = "/gotoUpdateCompany")
@Token(generate = true)
public String gotoUpdateCompany(ModelMap map,Integer companyId){
    SaveOrUpdateCompanyDto dto = new SaveOrUpdateCompanyDto();
    Company company = this.shopService.findCompanyOne(companyId);
    if (company == null) {
        String ms = "??????ID??????????????????????????? ??????ID???" + companyId;
        map.put("actionError", ms);
        logger.error(ms + " ???JS???????????????");
        return "error";
    }
    dto.setCompanyId(company.getCompanyId());
    dto.setAddress(company.getAddress());
    dto.setCompanyName(company.getCompanyName());
    dto.setMobile(company.getMobile());
    dto.setContacts(company.getContacts());
    dto.setTypeStatus(OPERATE_TYPE_UPDATE);
    map.put("company", dto);
    return "shop/company/saveOrUpdate/save-or-update-company";
}


}