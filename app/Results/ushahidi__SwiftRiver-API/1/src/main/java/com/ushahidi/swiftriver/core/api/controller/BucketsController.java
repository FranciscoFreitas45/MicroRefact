package com.ushahidi.swiftriver.core.api.controller;
 import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ushahidi.swiftriver.core.api.dto.CreateBucketDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateCollaboratorDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateCommentDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateLinkDTO;
import com.ushahidi.swiftriver.core.api.dto.CreatePlaceDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateTagDTO;
import com.ushahidi.swiftriver.core.api.dto.FollowerDTO;
import com.ushahidi.swiftriver.core.api.dto.FormValueDTO;
import com.ushahidi.swiftriver.core.api.dto.GetBucketDTO;
import com.ushahidi.swiftriver.core.api.dto.GetCollaboratorDTO;
import com.ushahidi.swiftriver.core.api.dto.GetCommentDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO.GetLinkDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO.GetPlaceDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO.GetTagDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyCollaboratorDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyFormValueDTO;
import com.ushahidi.swiftriver.core.api.exception.BadRequestException;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
import com.ushahidi.swiftriver.core.api.exception.InvalidFilterException;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.api.service.BucketService;
import com.ushahidi.swiftriver.core.model.BucketDrop;
@Controller
@RequestMapping("/v1/buckets")
public class BucketsController extends AbstractController{

@Autowired
 private  BucketService bucketService;


@RequestMapping(value = "/{id}/drops/{dropId}/places/{placeId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropPlace(Long id,Long dropId,Long placeId,Principal principal){
    bucketService.deleteDropPlace(id, dropId, placeId, principal.getName());
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteBucket(Long id,Principal principal){
    bucketService.deleteBucket(id, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/links/{linkId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropLink(Long id,Long dropId,Long linkId,Principal principal){
    bucketService.deleteDropLink(id, dropId, linkId, principal.getName());
}


@RequestMapping(value = "{id}/comments/{commentId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteComment(Long id,Long commentId,Principal principal){
    bucketService.deleteBucketComment(id, commentId, principal.getName());
}


@RequestMapping(value = "/{id}/subscriptions/{subscriptionId}", method = RequestMethod.DELETE)
public void deleteSubscription(Long id,Long subscriptionId){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> addSubscription(Map<String,Object> body,Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(method = RequestMethod.POST)
@ResponseBody
public GetBucketDTO createBucket(CreateBucketDTO createDTO,Principal principal){
    return bucketService.createBucket(createDTO, principal.getName());
}


@RequestMapping(value = "/{id}/followers", method = RequestMethod.GET)
@ResponseBody
public List<FollowerDTO> getFollowers(Long id,Long accountId){
    return bucketService.getFollowers(id, accountId);
}


@RequestMapping(value = "/{bucketId}/drops/{dropId}/forms", method = RequestMethod.POST)
@ResponseBody
public FormValueDTO addDropForm(Long bucketId,Long dropId,FormValueDTO createDTO,Principal principal){
    return bucketService.addDropForm(bucketId, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "{bucketId}/drops/{dropId}/forms/{formId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropForm(Long bucketId,Long dropId,Long formId,Principal principal){
    bucketService.deleteDropForm(bucketId, dropId, formId, principal.getName());
}


@RequestMapping(value = "/{id}/collaborators", method = RequestMethod.POST)
@ResponseBody
public GetCollaboratorDTO addCollaborator(CreateCollaboratorDTO body,Long id,Principal principal){
    List<ErrorField> errors = new ArrayList<ErrorField>();
    if (body.getAccount() == null) {
        errors.add(new ErrorField("account", "missing"));
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    return bucketService.addCollaborator(id, body, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/tags", method = RequestMethod.POST)
@ResponseBody
public GetTagDTO addDropTag(Long id,Long dropId,CreateTagDTO createDTO,Principal principal){
    return bucketService.addDropTag(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "{id}/drops/{dropId}/comments", method = RequestMethod.GET)
@ResponseBody
public List<GetCommentDTO> getDropComments(Long id,Long dropId,Principal principal){
    return bucketService.getDropComments(id, dropId, principal.getName());
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
public GetBucketDTO getBucket(Long id,Principal principal){
    return bucketService.getBucket(id, principal.getName());
}


@RequestMapping(value = "{id}/drops/{dropId}/comments", method = RequestMethod.POST)
@ResponseBody
public GetCommentDTO addDropComment(Long id,Long dropId,CreateCommentDTO createDTO,Principal principal){
    return bucketService.addDropComment(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
@ResponseBody
public GetBucketDTO modifyBucket(CreateBucketDTO modifiedBucket,Long id,Principal principal){
    return bucketService.modifyBucket(id, modifiedBucket, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/tags/{tagId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropTag(Long id,Long dropId,Long tagId,Principal principal){
    bucketService.deleteDropTag(id, dropId, tagId, principal.getName());
}


@RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.GET)
@ResponseBody
public List<Map<String,Object>> getSubscriptions(Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "{id}/comments", method = RequestMethod.GET)
@ResponseBody
public List<GetCommentDTO> getComments(Long id,Principal principal){
    return bucketService.getBucketComments(id, principal.getName());
}


@RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.PUT)
@ResponseBody
public Map<String,Object> modifySubscription(Map<String,Object> body,Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "/{id}/drops/{dropId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDrop(Long id,Long dropId,Principal principal){
    bucketService.deleteDrop(id, dropId, principal.getName());
}


@RequestMapping(value = "{id}/comments", method = RequestMethod.POST)
@ResponseBody
public GetCommentDTO addComment(Long id,CreateCommentDTO createDTO,Principal principal){
    return bucketService.addBucketComment(id, createDTO, principal.getName());
}


@RequestMapping(value = "{id}/drops/read/{dropId}", method = RequestMethod.PUT)
@ResponseBody
public void markDropAsRead(Long id,Long dropId,Principal principal){
    bucketService.markDropAsRead(id, dropId, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/places", method = RequestMethod.POST)
@ResponseBody
public GetPlaceDTO addDropPlace(Long id,Long dropId,CreatePlaceDTO createDTO,Principal principal){
    return bucketService.addDropPlace(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "/{id}/collaborators", method = RequestMethod.GET)
@ResponseBody
public List<GetCollaboratorDTO> getCollaborators(Long id){
    return bucketService.getCollaborators(id);
}


@RequestMapping(value = "{id}/drops/{dropId}/comments/{commentId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropComment(Long id,Long dropId,Long commentId,Principal principal){
    bucketService.deleteDropComment(id, dropId, commentId, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/links", method = RequestMethod.POST)
@ResponseBody
public GetLinkDTO addDropLink(Long id,Long dropId,CreateLinkDTO createDTO,Principal principal){
    return bucketService.addDropLink(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "/{id}/collaborators/{accountId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteCollaborator(Long id,Long accountId,Principal principal){
    bucketService.deleteCollaborator(id, accountId, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}", method = RequestMethod.PUT)
@ResponseBody
public void addDrop(long id,long dropId,Principal principal){
    bucketService.addDrop(id, dropId, principal.getName());
}


@RequestMapping(value = "/{id}/followers/{accountId}", method = RequestMethod.PUT)
@ResponseBody
public void addFollower(long id,long accountId,Principal principal){
    bucketService.addFollower(id, accountId, principal.getName());
}


@RequestMapping(value = "/{bucketId}/drops/{dropId}/forms/{formId}", method = RequestMethod.PUT)
@ResponseBody
public FormValueDTO modifyDropForm(Principal principal,Long bucketId,Long dropId,Long formId,ModifyFormValueDTO modifyFormTo){
    return bucketService.modifyDropForm(bucketId, dropId, formId, modifyFormTo, principal.getName());
}


@RequestMapping(value = "/{id}/followers/{accountId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteFollower(Long id,Long accountId){
    bucketService.deleteFollower(id, accountId);
}


@RequestMapping(value = "/{id}/drops", method = RequestMethod.GET)
@ResponseBody
public List<GetDropDTO> getDrops(Long id,Integer count,Integer page,Long maxId,Long sinceId,String dateFromS,String dateToS,String keywords,String channels,Boolean photos,String state,String locations,Principal principal){
    if (maxId == null) {
        maxId = Long.MAX_VALUE;
    }
    List<ErrorField> errors = new ArrayList<ErrorField>();
    List<String> channelList = new ArrayList<String>();
    if (channels != null) {
        channelList.addAll(Arrays.asList(channels.split(",")));
    }
    Boolean isRead = null;
    if (state != null) {
        if (!state.equals("read") && !state.equals("unread")) {
            errors.add(new ErrorField("state", "invalid"));
        } else {
            isRead = state.equals("read");
        }
    }
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
    Date dateFrom = null;
    if (dateFromS != null) {
        try {
            dateFrom = dateFormat.parse(dateFromS);
        } catch (ParseException e) {
            errors.add(new ErrorField("date_from", "invalid"));
        }
    }
    Date dateTo = null;
    if (dateToS != null) {
        try {
            dateTo = dateFormat.parse(dateToS);
        } catch (ParseException e) {
            errors.add(new ErrorField("date_to", "invalid"));
        }
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    DropFilter dropFilter = new DropFilter();
    dropFilter.setMaxId(maxId);
    dropFilter.setSinceId(sinceId);
    dropFilter.setChannels(channelList);
    try {
        dropFilter.setDateFrom(dateFrom);
    } catch (InvalidFilterException e) {
        errors.add(new ErrorField("date_from", e.getMessage()));
    }
    try {
        dropFilter.setDateTo(dateTo);
    } catch (InvalidFilterException e) {
        errors.add(new ErrorField("date_to", e.getMessage()));
    }
    dropFilter.setRead(isRead);
    dropFilter.setPhotos(photos);
    dropFilter.setKeywords(keywords);
    dropFilter.setBoundingBox(locations);
    // Check for errors
    if (!errors.isEmpty()) {
        BadRequestException exception = new BadRequestException();
        exception.setErrors(errors);
        throw exception;
    }
    return bucketService.getDrops(id, dropFilter, page, count, principal.getName());
}


@RequestMapping(value = "/{id}/collaborators/{accountId}", method = RequestMethod.PUT)
@ResponseBody
public GetCollaboratorDTO modifyCollaborator(Long id,Long accountId,ModifyCollaboratorDTO body,Principal principal){
    if (body.getReadOnly() == null && body.getActive() == null) {
        List<ErrorField> errors = new ArrayList<ErrorField>();
        errors.add(new ErrorField("read_only", "missing"));
        errors.add(new ErrorField("active", "missing"));
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    return bucketService.modifyCollaborator(id, accountId, body, principal.getName());
}


}