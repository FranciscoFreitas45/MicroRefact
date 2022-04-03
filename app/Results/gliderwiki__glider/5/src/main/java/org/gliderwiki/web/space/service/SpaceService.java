package org.gliderwiki.web.space.service;
 import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.exception.ResourceNotFoundException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.AuthorityType;
import org.gliderwiki.web.domain.ImageInfo;
import org.gliderwiki.web.domain.JoinStatus;
import org.gliderwiki.web.domain.JoinType;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeSpaceGroup;
import org.gliderwiki.web.domain.WeSpaceImage;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.domain.WeSpaceUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WebConstant;
import org.gliderwiki.web.space.dao.SpaceDao;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.SpaceInfoVo;
import org.gliderwiki.web.vo.TempUploadVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.common.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.DTO.WeSpace;
import org.gliderwiki.DTO.WeGroupInfo;
import org.gliderwiki.DTO.WeFavorite;
import org.gliderwiki.DTO.TempUploadVo;
import org.gliderwiki.DTO.WeUser;
import org.gliderwiki.DTO.WeProfile;
@Service("spaceService")
@RemoteProxy(name = "spaceService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class SpaceService {

 private Logger logger;

 private  String VIEW_AUTHORITY;

 private  String EDIT_AUTHORITY;

@Value("#{config['file.maxSize']}")
 private String uploadMaxSize;

@SuppressWarnings("rawtypes")
@Resource(name = "entityService")
 private EntityService entityService;

@Resource(name = "spaceDao")
 private SpaceDao spaceDao;

@Resource(name = "commonService")
 private  CommonService commonService;

@Resource(name = "apacheFileUtilService")
 private  FileService fileService;


@SuppressWarnings("unchecked")
public WeSpace getWeSpaceInfo(int spaceIdx){
    WeSpace weSpace = new WeSpace();
    weSpace.setWe_space_idx(spaceIdx);
    return (WeSpace) entityService.getRowEntity(weSpace);
}


@Transactional(readOnly = false)
public void saveSpaceGroup(int spaceIdx,int groupIdx,String authorityPrivacy){
    WeSpaceGroup weSpaceGroup = null;
    weSpaceGroup = new WeSpaceGroup(spaceIdx, groupIdx, authorityPrivacy);
    entityService.insertEntity(weSpaceGroup);
}


@Transactional(readOnly = false)
public boolean checkDuplicateJoinUser(int spaceIdx,int userIdx){
    WeSpaceJoin checkDuplication = new WeSpaceJoin();
    checkDuplication.setWe_space_idx(spaceIdx);
    checkDuplication.setWe_user_idx(userIdx);
    Object searchResult = entityService.getRowEntity(checkDuplication);
    return isEmptyObject(searchResult);
}


public List<Map<String,String>> getMySpaceList(int userIdx){
    return spaceDao.getMySpaceList(userIdx);
}


public Object nameDuplicateCheck(String spaceName){
    WeSpace weSpace = new WeSpace();
    weSpace.setWe_space_name(spaceName);
    return entityService.getRowEntity(weSpace);
}


public boolean isEmptyObject(Object obj){
    if (obj != null) {
        return false;
    }
    return true;
}


@Transactional(readOnly = false)
public int entryApprove(int spaceJoinIdx,WeSpaceUser weSpaceUser){
    return spaceDao.entryApprove(spaceJoinIdx, weSpaceUser);
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = false)
public void update(WeSpace weSpace,Integer weUserIdx,ImageInfo imageInfo){
    // 공간정보 업데이트
    WeSpace originalSpaceInfo = new WeSpace(weSpace.getWe_space_idx());
    WeSpace originSpace = (WeSpace) entityService.getRowEntity(originalSpaceInfo);
    WeSpace updateSpaceInfo = new WeSpace(weSpace.getWe_space_idx(), weSpace.getWe_space_name(), weSpace.getWe_space_desc(), weSpace.getWe_space_exposed(), weSpace.getWe_view_privacy(), weSpace.getWe_edit_privacy(), weUserIdx, originSpace.getWe_ins_date(), originSpace.getWe_admin_idx());
    entityService.updateEntity(updateSpaceInfo);
    // 기존에 있던 그룹 혹은 구성원 정보 삭제
    if (originSpace.isAllGroupViewPrivacy()) {
        deleteSpaceUserList(originSpace.getWe_space_idx(), VIEW_AUTHORITY, originSpace.getWe_view_privacy());
    }
    if (originSpace.isAllGroupEditPrivacy()) {
        deleteSpaceUserList(originSpace.getWe_space_idx(), EDIT_AUTHORITY, originSpace.getWe_edit_privacy());
    }
    // 생성, 수정권한에 대한 DB처리
    processSpaceAuthorityPolicy(weSpace);
    // 프로필관련 DB처리
    updateSpaceInfo.setWe_upload_imgName(weSpace.getWe_upload_imgName());
    processSpaceImage(updateSpaceInfo, imageInfo);
}


public List<Map<String,String>> getRecentSpaceList(Integer weUserIdx,Integer startRow,Integer endRow){
    return spaceDao.getRecentSpaceList(weUserIdx, startRow, endRow);
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = false)
public int groupDeleted(Integer spaceIdx,Integer groupIdx,String type){
    WeSpaceGroup weSpaceGroup = new WeSpaceGroup();
    weSpaceGroup.setWe_space_idx(spaceIdx);
    weSpaceGroup.setWe_group_idx(groupIdx);
    weSpaceGroup.setAuthorityType(type);
    return entityService.deleteEntity(weSpaceGroup);
}


@RemoteMethod
public List<Map<String,Object>> userSelectedList(int spaceIdx){
    logger.debug("##userSelectedList");
    return spaceDao.userSelectedList(spaceIdx);
}


@Transactional(readOnly = false)
public boolean checkDuplicateInvitedUser(int spaceIdx,int userIdx){
    WeSpaceJoin checkDuplication = new WeSpaceJoin();
    checkDuplication.setWe_space_idx(spaceIdx);
    checkDuplication.setWe_user_idx(userIdx);
    checkDuplication.setWe_join_type(JoinType.INVITE);
    WeSpaceJoin searchResult = (WeSpaceJoin) entityService.getRowEntity(checkDuplication);
    return isEmptyObject(searchResult);
}


@SuppressWarnings("unchecked")
public List<WeGroupInfo> getAllGroupList(){
    WeGroupInfo weGroupInfo = new WeGroupInfo();
    weGroupInfo.setWe_use_yn("Y");
    return entityService.getListEntity(weGroupInfo);
}


@Transactional(readOnly = false)
public int processSpaceImage(WeSpace weSpace,ImageInfo imageInfo){
    if (weSpace.isUploadImgName()) {
        copySpaceImageFileToReal(imageInfo, weSpace.getWe_upload_imgName());
        if (weSpace.doUpdate()) {
            WeSpaceImage weSpaceImage = new WeSpaceImage(weSpace.getWe_space_idx(), true);
            WeSpaceImage modSpaceImage = (WeSpaceImage) entityService.getRowEntity(weSpaceImage);
            modSpaceImage.processImageInfo(imageInfo, weSpace.getWe_upload_imgName());
            return entityService.updateEntity(modSpaceImage);
        } else {
            WeSpaceImage weSpaceImage = new WeSpaceImage(weSpace.getWe_space_idx());
            weSpaceImage.processImageInfo(imageInfo, weSpace.getWe_upload_imgName());
            return entityService.insertEntity(weSpaceImage);
        }
    }
    return 0;
}


@SuppressWarnings("unchecked")
public SpaceInfoVo getSpaceInfo(int spaceIdx){
    SpaceInfoVo spaceInfoVo = spaceDao.getSpaceInfo(spaceIdx);
    // 해당하는 공간이 없다면
    if (spaceInfoVo == null) {
        throw new ResourceNotFoundException(SpaceInfoVo.class, spaceIdx);
    }
    // 공간조회 그룹 조회
    spaceInfoVo.setViewParticipant(getAuthorityParticipant("view", spaceInfoVo.getViewPrivacy(), spaceInfoVo.getSpaceIdx()));
    // 공간 생성.조회 그룹조회
    spaceInfoVo.setEditParticipant(getAuthorityParticipant("edit", spaceInfoVo.getEditPrivacy(), spaceInfoVo.getSpaceIdx()));
    return spaceInfoVo;
}


@Transactional(readOnly = false)
public void saveSpaceUser(int spaceIdx,int userIdx,String authorityPrivacy){
    WeSpaceUser weSpaceUser = null;
    weSpaceUser = new WeSpaceUser(spaceIdx, userIdx, authorityPrivacy);
    entityService.insertEntity(weSpaceUser);
}


public List<WeFavorite> getMyFavoriteList(int userIdx){
    WeFavorite weFavorite = new WeFavorite();
    weFavorite.setWe_user_idx(userIdx);
    return entityService.getListEntity(weFavorite);
}


public List<Map<String,String>> getAllSpaceList(Integer weUserIdx,Integer weGrade){
    return spaceDao.getAllSpaceList(weUserIdx, weGrade);
}


public Map<String,String> tempFileUpload(MultipartFile file,String tempRootPath,String userId){
    Map<String, String> param = Maps.newHashMap();
    String today = DateUtil.getToday();
    double maxSize = Double.parseDouble(uploadMaxSize);
    TempUploadVo tempFile = FileUploader.uploadTempFile(file, tempRootPath, userId, today, maxSize);
    if (tempFile.isUploaded()) {
        param.put("result", "1");
        param.put("msg", "성공");
        param.put("saveFileName", tempFile.getFileSaveName());
        param.put("filePath", tempFile.getFilePath());
    } else {
        param.put("result", "-1");
        param.put("msg", "파일 업로드에 실패 하였습니다.");
        param.put("realFileName", tempFile.getFileRealName());
    }
    return param;
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = false)
public void create(WeSpace weSpace,Integer userIdx,ImageInfo imageInfo){
    WeSpace addSpaceInfo = new WeSpace(weSpace.getWe_space_name(), weSpace.getWe_space_desc(), weSpace.getWe_space_exposed(), weSpace.getWe_view_privacy(), weSpace.getWe_edit_privacy(), userIdx, userIdx);
    entityService.insertEntity(addSpaceInfo);
    // 생성, 수정권한에 대한 DB처리
    WeSpace savedSpaceInfo = (WeSpace) entityService.getRowEntity(addSpaceInfo);
    savedSpaceInfo.setWe_view_data(weSpace.getWe_view_data());
    savedSpaceInfo.setWe_edit_data(weSpace.getWe_edit_data());
    savedSpaceInfo.setWe_upload_imgName(weSpace.getWe_upload_imgName());
    processSpaceAuthorityPolicy(savedSpaceInfo);
    // 프로필관련 DB처리
    processSpaceImage(savedSpaceInfo, imageInfo);
}


@Transactional(readOnly = false)
public void processSpaceAuthorityPolicy(WeSpace weSpace){
    if (weSpace.isAllGroupViewPrivacy()) {
        AuthorityProcessor processor = new AuthorityProcessor() {

            @Override
            public Map<String, Object> getPrivacyData() {
                Map<String, Object> result = Maps.newHashMap();
                result.put("authorityType", weSpace.getWe_view_privacy());
                result.put("authorityData", weSpace.getWe_view_data());
                result.put("authorityPrivacy", VIEW_AUTHORITY);
                return result;
            }
        };
        divideFromAuthrityData(weSpace.getWe_space_idx(), processor);
    }
    if (weSpace.isAllGroupEditPrivacy()) {
        AuthorityProcessor processor = new AuthorityProcessor() {

            @Override
            public Map<String, Object> getPrivacyData() {
                Map<String, Object> result = Maps.newHashMap();
                result.put("authorityType", weSpace.getWe_edit_privacy());
                result.put("authorityData", weSpace.getWe_edit_data());
                result.put("authorityPrivacy", EDIT_AUTHORITY);
                return result;
            }
        };
        divideFromAuthrityData(weSpace.getWe_space_idx(), processor);
    }
}


public void makePolicyData(Map<String,String> result,AuthorityType selectedType,Integer spaceIdx,String policy){
    Map<String, String> resultMap = null;
    if (AuthorityType.GROUP == selectedType) {
        List<Map<String, Object>> selectedGroup = spaceDao.getGroupParticipant(policy, spaceIdx);
        resultMap = iteratorData(selectedGroup);
    } else {
        List<Map<String, Object>> selectedUser = spaceDao.getUserParticipant(policy, spaceIdx);
        resultMap = iteratorData(selectedUser);
    }
    result.put(policy + "Data", resultMap.get("data"));
    result.put(policy + "Name", resultMap.get("name"));
}


public Map<String,String> iteratorData(List<Map<String,Object>> policyData){
    Map<String, String> maps = Maps.newHashMap();
    int dataSize = policyData.size();
    String data = "";
    String name = "";
    for (int i = 0; i < dataSize; i++) {
        data += policyData.get(i).get("idx").toString();
        name += policyData.get(i).get("name");
        if (i != (dataSize - 1)) {
            data += ",";
            name += ",";
        }
    }
    maps.put("data", data);
    maps.put("name", name);
    return maps;
}


public Map<String,String> getAuthorityData(WeSpace weSpace){
    Map<String, String> result = Maps.newHashMap();
    AuthorityType viewPolicy = weSpace.getWe_view_privacy();
    AuthorityType editPolicy = weSpace.getWe_edit_privacy();
    if (!StringUtils.equals("ALLGROUP", viewPolicy.name())) {
        makePolicyData(result, viewPolicy, weSpace.getWe_space_idx(), "view");
    }
    if (!StringUtils.equals("ALLGROUP", editPolicy.name())) {
        makePolicyData(result, editPolicy, weSpace.getWe_space_idx(), "edit");
    }
    return result;
}


public List<Map<String,String>> getUpdatedList(int spaceIdx,Integer startRow,Integer endRow){
    return spaceDao.getUpdatedList(spaceIdx, startRow, endRow);
}


public void copySpaceImageFileToReal(ImageInfo imageInfo,String fileName){
    File tempFile = new File(imageInfo.getTempImgRoot() + fileName);
    File realFile = new File(imageInfo.getRealImgRoot() + fileName);
    try {
        fileService.copyFile(tempFile, realFile);
    } catch (IOException e) {
        throw new GliderwikiException("FileCopy Error!", e);
    }
}


public List<GroupUserVo> getGroupUserList(int groupIdx){
    return spaceDao.getGroupUserList(groupIdx);
}


@Transactional(readOnly = false)
public int entryReject(int spaceJoinIdx){
    return spaceDao.entryReject(spaceJoinIdx);
}


@Transactional(readOnly = false)
public int joinRequest(Integer userIdx,int spaceIdx){
    int result = 0;
    // 기존에 가입신청한게 있는지 먼저 체크함
    if (checkDuplicateJoinUser(spaceIdx, userIdx)) {
        WeSpaceJoin weSpaceJoin = new WeSpaceJoin(spaceIdx, JoinType.REQUEST, userIdx, JoinStatus.REQUEST, null);
        result = entityService.insertEntity(weSpaceJoin);
    }
    // 알림추가
    WeSpace weSpace = getWeSpaceInfo(spaceIdx);
    WeUser weUser = commonService.getUserInfo(userIdx);
    commonService.requestAlarmInfo(userIdx, weUser.getWe_user_nick(), SystemConst.SPACE_JOIN, weSpace.getWe_ins_user(), null, spaceIdx);
    return result;
}


public List<Map<String,String>> getRecentFileList(int spaceIdx){
    return spaceDao.getRecentFileList(spaceIdx);
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = false)
public int userDeleted(Integer spaceIdx,Integer userIdx,String type){
    WeSpaceUser weSpaceUser = new WeSpaceUser();
    weSpaceUser.setWe_space_idx(spaceIdx);
    weSpaceUser.setWe_user_idx(userIdx);
    weSpaceUser.setAuthorityType(type);
    return entityService.deleteEntity(weSpaceUser);
}


public List<Map<String,String>> getMyEntryRequestedList(int spaceIdx){
    return spaceDao.getMyEntryRequestedList(spaceIdx);
}


public List<Map<String,Object>> getAuthorityParticipant(String tranType,AuthorityType type,Integer spaceIdx){
    if (type.equals(AuthorityType.GROUP)) {
        return spaceDao.getGroupParticipant(tranType, spaceIdx);
    }
    return spaceDao.getUserParticipant(tranType, spaceIdx);
}


public String getSpaceImageInfo(Integer spaceIdx){
    WeSpaceImage weSpaceImage = new WeSpaceImage();
    weSpaceImage.setWe_space_idx(spaceIdx);
    weSpaceImage.setWe_used(true);
    WeSpaceImage resultImage = (WeSpaceImage) entityService.getRowEntity(weSpaceImage);
    if (resultImage == null) {
        throw new ResourceNotFoundException(WeSpaceImage.class, spaceIdx);
    }
    return weSpaceImage.getRealImagePath();
}


public List<Map<String,String>> getMyEntryList(int userIdx){
    return spaceDao.getMyEntryList(userIdx);
}


public WebConstant checkSearchSpaceInfo(AuthorityType type,int spaceIdx,int userIdx,String authorityType){
    WeProfile userInfo = commonService.getUserProfileInfo(userIdx);
    if (StringUtils.equals("GROUP", type.name())) {
        // 관리자는 권한 검사를 하지 않는다.
        if (userInfo.getWe_grade() != 9) {
            List<Map<String, Object>> result = spaceDao.checkGroupIntoMe(spaceIdx, userIdx, authorityType);
            if (CollectionUtils.isEmpty(result)) {
                return WebConstant.FAIL;
            }
        }
        return WebConstant.SUCCESS;
    }
    if (StringUtils.equals("USER", type.name())) {
        logger.debug("loglog : {}{}", spaceIdx, userIdx);
        if (userInfo.getWe_grade() != 9) {
            Integer result = spaceDao.checkUserIntoMe(spaceIdx, userIdx, authorityType);
            if (result == 0) {
                return WebConstant.FAIL;
            }
        }
        return WebConstant.SUCCESS;
    }
    return WebConstant.FAIL;
}


public List<Map<String,String>> getMyInviteList(int spaceIdx){
    return spaceDao.getMyInviteList(spaceIdx);
}


@RemoteMethod
public List<Map<String,Object>> groupSelectedList(int spaceIdx){
    logger.debug("##groupSelectedList");
    return spaceDao.groupSelectedList(spaceIdx);
}


public void deleteSpaceUserList(int spaceIdx,String authorityTitle,AuthorityType authorityType){
    if (AuthorityType.GROUP == authorityType) {
        WeSpaceGroup deleteGroup = new WeSpaceGroup(spaceIdx, authorityTitle);
        entityService.deleteEntity(deleteGroup);
    } else {
        WeSpaceUser deleteUser = new WeSpaceUser(spaceIdx, authorityTitle);
        entityService.deleteEntity(deleteUser);
    }
}


@Override
public Map<String,Object> getPrivacyData(){
    Map<String, Object> result = Maps.newHashMap();
    result.put("authorityType", weSpace.getWe_edit_privacy());
    result.put("authorityData", weSpace.getWe_edit_data());
    result.put("authorityPrivacy", EDIT_AUTHORITY);
    return result;
}


public WeSpace getSpace(String spaceName){
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_space_name(spaceName);
    WeSpace resultSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    // 해당하는 공간이 없다면
    if (resultSpace == null) {
        throw new ResourceNotFoundException(SpaceInfoVo.class, spaceName);
    }
    return resultSpace;
}


@Transactional(readOnly = false)
public void divideFromAuthrityData(int spaceIdx,AuthorityProcessor processor){
    String authorityData = (String) processor.getPrivacyData().get("authorityData");
    AuthorityType authorityType = (AuthorityType) processor.getPrivacyData().get("authorityType");
    String authorityPrivacy = (String) processor.getPrivacyData().get("authorityPrivacy");
    Iterable<String> userList = Splitter.on(',').trimResults().omitEmptyStrings().split(authorityData);
    Iterator<String> iter = userList.iterator();
    while (iter.hasNext()) {
        int authorityUser = Integer.parseInt(iter.next());
        if (AuthorityType.GROUP == authorityType) {
            saveSpaceGroup(spaceIdx, authorityUser, authorityPrivacy);
        } else {
            saveSpaceUser(spaceIdx, authorityUser, authorityPrivacy);
        }
    }
}


@Transactional(readOnly = false)
public WebConstant inviteRequest(Integer loginUserIdx,int spaceIdx,String arrUserIdx){
    int result = 0;
    if (StringUtils.isEmpty(arrUserIdx)) {
        return WebConstant.FAIL;
    }
    Iterable<String> arrTargetUserList = Splitter.on(',').trimResults().omitEmptyStrings().split(arrUserIdx);
    Iterator<String> iter = arrTargetUserList.iterator();
    while (iter.hasNext()) {
        int targetUserIdx = Integer.parseInt(iter.next());
        // 기존에 동료초대 한게 있는지 먼저 체크함
        if (checkDuplicateInvitedUser(spaceIdx, targetUserIdx)) {
            WeSpaceJoin weSpaceJoin = new WeSpaceJoin(spaceIdx, JoinType.INVITE, targetUserIdx, JoinStatus.REQUEST, loginUserIdx);
            entityService.insertEntity(weSpaceJoin);
            // 알림추가
            WeSpace weSpace = getWeSpaceInfo(spaceIdx);
            WeUser weUser = commonService.getUserInfo(loginUserIdx);
            commonService.requestAlarmInfo(loginUserIdx, weUser.getWe_user_nick(), SystemConst.SPACE_INVITE, targetUserIdx, null, spaceIdx);
            result++;
        }
    }
    if (result > 0) {
        return WebConstant.SUCCESS;
    }
    return WebConstant.FAIL;
}


}