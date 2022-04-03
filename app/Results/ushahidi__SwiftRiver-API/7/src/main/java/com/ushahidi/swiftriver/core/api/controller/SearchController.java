package com.ushahidi.swiftriver.core.api.controller;
 import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ushahidi.swiftriver.core.api.dto.GetAccountDTO;
import com.ushahidi.swiftriver.core.api.dto.GetBucketDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
import com.ushahidi.swiftriver.core.api.dto.GetRiverDTO;
import com.ushahidi.swiftriver.core.api.exception.BadRequestException;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
import com.ushahidi.swiftriver.core.api.exception.NotFoundException;
import com.ushahidi.swiftriver.core.api.service.AccountService;
import com.ushahidi.swiftriver.core.api.service.BucketService;
import com.ushahidi.swiftriver.core.api.service.DropIndexService;
import com.ushahidi.swiftriver.core.api.service.RiverService;
import com.ushahidi.swiftriver.core.Interface.DropIndexService;
import com.ushahidi.swiftriver.core.Interface.BucketService;
import com.ushahidi.swiftriver.core.Interface.RiverService;
@Controller
@RequestMapping("/v1/search")
public class SearchController extends AbstractController{

@Autowired
 private  DropIndexService dropIndexService;

@Autowired
 private  BucketService bucketService;

@Autowired
 private  RiverService riverService;

@Autowired
 private  AccountService accountService;

 final  Logger LOGGER;


@RequestMapping(value = "/drops", method = RequestMethod.GET)
@ResponseBody
public List<GetDropDTO> searchDrops(String searchTerm,int count,int page){
    validateBasicParameters(count, page);
    // Find and return the drops
    List<GetDropDTO> drops = dropIndexService.findDrops(searchTerm, count, page);
    if (drops.isEmpty())
        throw new NotFoundException(String.format("Did not find drops matching '%s' on page %d", searchTerm, page));
    return drops;
}


@RequestMapping(value = "/rivers", method = RequestMethod.GET)
@ResponseBody
public List<GetRiverDTO> searchRivers(String searchTerm,int count,int page){
    validateBasicParameters(count, page);
    List<GetRiverDTO> rivers = riverService.findRivers(searchTerm, count, page);
    if (rivers.isEmpty()) {
        throw new NotFoundException(String.format("Did not find any rivers matching '%s' on page %d", searchTerm, page));
    }
    return rivers;
}


@RequestMapping(value = "/buckets", method = RequestMethod.GET)
@ResponseBody
public List<GetBucketDTO> searchBuckets(String searchTerm,int count,int page){
    validateBasicParameters(count, page);
    List<GetBucketDTO> buckets = bucketService.findBuckets(searchTerm, count, page);
    if (buckets.isEmpty()) {
        throw new NotFoundException(String.format("Did not find any bucktes matching '%s' on page %d", searchTerm, page));
    }
    return buckets;
}


@RequestMapping(value = "/accounts", method = RequestMethod.GET)
@ResponseBody
public List<GetAccountDTO> searchAccounts(String searchTerm,int count,int page){
    validateBasicParameters(count, page);
    List<GetAccountDTO> accounts = accountService.searchAccounts(searchTerm, count, page);
    if (accounts.isEmpty()) {
        throw new NotFoundException(String.format("Did not find any accounts matching '%s' on page %d", searchTerm, page));
    }
    return accounts;
}


public void validateBasicParameters(int count,int page){
    List<ErrorField> errors = new ArrayList<ErrorField>();
    // Count must be > 1
    if (count < 1)
        errors.add(new ErrorField("count", "invalid"));
    // Page must be >= 0
    if (page < 0)
        errors.add(new ErrorField("page", "invalid"));
    // Do we have any errors
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid search parameters");
        e.setErrors(errors);
        throw e;
    }
}


}