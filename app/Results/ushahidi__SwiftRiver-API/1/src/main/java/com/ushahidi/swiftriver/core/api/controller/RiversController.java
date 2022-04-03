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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ushahidi.swiftriver.core.api.dto.CreateChannelDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateCollaboratorDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateCommentDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateLinkDTO;
import com.ushahidi.swiftriver.core.api.dto.CreatePlaceDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateRiverDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateRuleDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateTagDTO;
import com.ushahidi.swiftriver.core.api.dto.FollowerDTO;
import com.ushahidi.swiftriver.core.api.dto.FormValueDTO;
import com.ushahidi.swiftriver.core.api.dto.GetChannelDTO;
import com.ushahidi.swiftriver.core.api.dto.GetCollaboratorDTO;
import com.ushahidi.swiftriver.core.api.dto.GetCommentDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO.GetLinkDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO.GetPlaceDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO.GetTagDTO;
import com.ushahidi.swiftriver.core.api.dto.GetPlaceTrend;
import com.ushahidi.swiftriver.core.api.dto.GetRiverDTO;
import com.ushahidi.swiftriver.core.api.dto.GetRuleDTO;
import com.ushahidi.swiftriver.core.api.dto.GetTagTrend;
import com.ushahidi.swiftriver.core.api.dto.ModifyChannelDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyCollaboratorDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyFormValueDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyRiverDTO;
import com.ushahidi.swiftriver.core.api.exception.BadRequestException;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
import com.ushahidi.swiftriver.core.api.exception.InvalidFilterException;
import com.ushahidi.swiftriver.core.api.exception.NotFoundException;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.api.filter.TrendFilter;
import com.ushahidi.swiftriver.core.api.service.RiverService;
import com.ushahidi.swiftriver.core.model.Account;
@Controller
@RequestMapping("/v1/rivers")
public class RiversController extends AbstractController{

 final  Logger logger;

@Autowired
 private  RiverService riverService;


@RequestMapping(value = "/{id}/drops/{dropId}/places/{placeId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropPlace(Long id,Long dropId,Long placeId,Principal principal){
    riverService.deleteDropPlace(id, dropId, placeId, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/links/{linkId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropLink(Long id,Long dropId,Long linkId,Principal principal){
    riverService.deleteDropLink(id, dropId, linkId, principal.getName());
}


@RequestMapping(value = "{id}/trends/tags", method = RequestMethod.GET)
@ResponseBody
public List<GetTagTrend> getTrendingTags(Long id,Principal principal,int count,int page,String since,String until){
    List<ErrorField> errors = new ArrayList<ErrorField>();
    // Validate the dates
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateFrom = null;
    Date dateTo = null;
    if (since != null) {
        try {
            dateFrom = dateFormat.parse(since);
        } catch (ParseException e) {
            errors.add(new ErrorField("since", "invalid"));
        }
    }
    if (until != null) {
        try {
            dateTo = dateFormat.parse(until);
        } catch (ParseException e) {
            errors.add(new ErrorField("until", "invalid"));
        }
    }
    // Do we have any validation errors
    if (!errors.isEmpty()) {
        BadRequestException exception = new BadRequestException();
        exception.setErrors(errors);
        throw exception;
    }
    TrendFilter trendFilter = new TrendFilter();
    trendFilter.setCount(count);
    trendFilter.setPage(page);
    trendFilter.setDateFrom(dateFrom);
    trendFilter.setDateTo(dateTo);
    return riverService.getTrendingTags(id, trendFilter, principal.getName());
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
public GetRiverDTO getRiver(Long id) throws NotFoundException{
    return riverService.getRiverById(id);
}


@RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.DELETE)
public void deleteSubscription(Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteRiver(Long id,Principal principal){
    riverService.deleteRiver(id, principal.getName());
}


@RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.POST)
public Account addSubscription(Map<String,Object> body,Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "/{riverId}", method = RequestMethod.PUT)
@ResponseBody
public GetRiverDTO modifyRiver(Principal principal,Long riverId,ModifyRiverDTO modifyRiverTO){
    return riverService.modifyRiver(riverId, modifyRiverTO, principal.getName());
}


@RequestMapping(value = "/{id}/followers", method = RequestMethod.GET)
@ResponseBody
public List<FollowerDTO> getFollowers(Long id,Long accountId){
    return riverService.getFollowers(id, accountId);
}


@RequestMapping(value = "/{riverId}/drops/{dropId}/forms", method = RequestMethod.POST)
@ResponseBody
public FormValueDTO addDropForm(Long riverId,Long dropId,FormValueDTO createDTO,Principal principal){
    return riverService.addDropForm(riverId, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "{riverId}/drops/{dropId}/forms/{formId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropForm(Long riverId,Long dropId,Long formId,Principal principal){
    riverService.deleteDropForm(riverId, dropId, formId, principal.getName());
}


@RequestMapping(value = "/{riverId}/channels/{channelId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteChannel(Principal principal,Long riverId,Long channelId){
    riverService.deleteChannel(riverId, channelId, principal.getName());
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
    return riverService.addCollaborator(id, body, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/tags", method = RequestMethod.POST)
@ResponseBody
public GetTagDTO addDropTag(Long id,Long dropId,CreateTagDTO createDTO,Principal principal){
    return riverService.addDropTag(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "{id}/drops/{dropId}/comments", method = RequestMethod.GET)
@ResponseBody
public List<GetCommentDTO> getDropComments(Long id,Long dropId,Principal principal){
    return riverService.getDropComments(id, dropId, principal.getName());
}


@RequestMapping(value = "{id}/drops/{dropId}/comments", method = RequestMethod.POST)
@ResponseBody
public GetCommentDTO addDropComment(Long id,Long dropId,CreateCommentDTO createDTO,Principal principal){
    return riverService.addDropComment(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/tags/{tagId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropTag(Long id,Long dropId,Long tagId,Principal principal){
    riverService.deleteDropTag(id, dropId, tagId, principal.getName());
}


@RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.GET)
@ResponseBody
public List<Map<String,Object>> getSubscriptions(Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "{id}/trends/places", method = RequestMethod.GET)
@ResponseBody
public List<GetPlaceTrend> getTrendingPlaces(Long id,Principal principal,int count,int page,String since,String until){
    List<ErrorField> errors = new ArrayList<ErrorField>();
    // Validate the dates
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateFrom = null;
    Date dateTo = null;
    if (since != null) {
        try {
            dateFrom = dateFormat.parse(since);
        } catch (ParseException e) {
            errors.add(new ErrorField("since", "invalid"));
        }
    }
    if (until != null) {
        try {
            dateTo = dateFormat.parse(until);
        } catch (ParseException e) {
            errors.add(new ErrorField("until", "invalid"));
        }
    }
    // Do we have any validation errors
    if (!errors.isEmpty()) {
        BadRequestException exception = new BadRequestException();
        exception.setErrors(errors);
        throw exception;
    }
    TrendFilter trendFilter = new TrendFilter();
    trendFilter.setCount(count);
    trendFilter.setPage(page);
    trendFilter.setDateFrom(dateFrom);
    trendFilter.setDateTo(dateTo);
    return riverService.getTredingPlaces(id, trendFilter, principal.getName());
}


@RequestMapping(value = "/{id}/channels", method = RequestMethod.POST)
@ResponseBody
public GetChannelDTO createChannel(CreateChannelDTO createChannelTO,Long id){
    return riverService.createChannel(id, createChannelTO);
}


@RequestMapping(value = "/{riverId}/channels/{channelId}", method = RequestMethod.PUT)
@ResponseBody
public GetChannelDTO modifyChannel(Principal principal,Long riverId,Long channelId,ModifyChannelDTO modifyChannelTO){
    return riverService.modifyChannel(riverId, channelId, modifyChannelTO, principal.getName());
}


@RequestMapping(value = "{id}/rules", method = RequestMethod.GET)
@ResponseBody
public List<GetRuleDTO> getRules(Long id,Principal principal){
    return riverService.getRules(id, principal.getName());
}


@RequestMapping(value = "/{id}/subscriptions", method = RequestMethod.PUT)
public void modifySubscription(Map<String,Object> body,Long id){
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "/{id}/drops/{dropId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDrop(Long id,Long dropId,Principal principal){
    riverService.deleteDrop(id, dropId, principal.getName());
}


@RequestMapping(value = "/{id}/drops/read/{dropId}", method = RequestMethod.PUT)
@ResponseBody
public void markDropAsRead(Long id,Long dropId,Principal principal){
    riverService.markDropAsRead(id, dropId, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/places", method = RequestMethod.POST)
@ResponseBody
public GetPlaceDTO addDropPlace(Long id,Long dropId,CreatePlaceDTO createDTO,Principal principal){
    return riverService.addDropPlace(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "/{id}/collaborators", method = RequestMethod.GET)
@ResponseBody
public List<GetCollaboratorDTO> getCollaborators(Long id){
    return riverService.getCollaborators(id);
}


@RequestMapping(value = "{id}/rules/{ruleId}", method = RequestMethod.PUT)
@ResponseBody
public GetRuleDTO modifyRule(Long id,Long ruleId,CreateRuleDTO createRuleDTO,Principal principal){
    return riverService.modifyRule(id, ruleId, createRuleDTO, principal.getName());
}


@RequestMapping(method = RequestMethod.POST)
@ResponseBody
public GetRiverDTO createRiver(Principal principal,CreateRiverDTO riverTO){
    return riverService.createRiver(riverTO, principal.getName());
}


@RequestMapping(value = "{id}/drops/{dropId}/comments/{commentId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteDropComment(Long id,Long dropId,Long commentId,Principal principal){
    riverService.deleteDropComment(id, dropId, commentId, principal.getName());
}


@RequestMapping(value = "/{id}/drops/{dropId}/links", method = RequestMethod.POST)
@ResponseBody
public GetLinkDTO addDropLink(Long id,Long dropId,CreateLinkDTO createDTO,Principal principal){
    return riverService.addDropLink(id, dropId, createDTO, principal.getName());
}


@RequestMapping(value = "{id}/rules/{ruleId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteRule(Long id,Long ruleId,Principal principal){
    riverService.deleteRule(id, ruleId, principal.getName());
}


@RequestMapping(value = "/{id}/collaborators/{accountId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteCollaborator(Long id,Long accountId,Principal principal){
    riverService.deleteCollaborator(id, accountId, principal.getName());
}


@RequestMapping(value = "{id}/rules", method = RequestMethod.POST)
@ResponseBody
public GetRuleDTO addRule(Long id,CreateRuleDTO createRuleDTO,Principal principal){
    return riverService.addRule(id, createRuleDTO, principal.getName());
}


@RequestMapping(value = "/{id}/followers/{accountId}", method = RequestMethod.PUT)
@ResponseBody
public void addFollower(Long id,Long accountId){
    riverService.addFollower(id, accountId);
}


@RequestMapping(value = "/{riverId}/drops/{dropId}/forms/{formId}", method = RequestMethod.PUT)
@ResponseBody
public FormValueDTO modifyDropForm(Principal principal,Long riverId,Long dropId,Long formId,ModifyFormValueDTO modifyFormTo){
    return riverService.modifyDropForm(riverId, dropId, formId, modifyFormTo, principal.getName());
}


@RequestMapping(value = "/{id}/followers/{followerId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteFollower(Long id,Long followerId){
    riverService.deleteFollower(id, followerId);
}


@RequestMapping(value = "/{id}/drops", method = RequestMethod.GET, headers = "X-Stream")
public Account getDropsStream(Long id){
    // TODO: redirect to streaming server.
    throw new UnsupportedOperationException("Method Not Yet Implemented");
}


@RequestMapping(value = "/{id}/drops", method = RequestMethod.GET)
@ResponseBody
public List<GetDropDTO> getDrops(Long id,Principal principal,Integer count,Long maxId,Integer page,Long sinceId,String dateFromS,String dateToS,String keywords,String channels,String cIds,String state,Boolean photos,String locations) throws NotFoundException{
    if (maxId == null) {
        maxId = Long.MAX_VALUE;
    }
    List<ErrorField> errors = new ArrayList<ErrorField>();
    List<Long> channelIds = new ArrayList<Long>();
    if (cIds != null) {
        for (String cId : cIds.split(",")) {
            try {
                channelIds.add(Long.parseLong(cId));
            } catch (NumberFormatException ex) {
                errors.add(new ErrorField("channel_ids", "invalid"));
            }
        }
    }
    List<String> channelList = new ArrayList<String>();
    if (channels != null) {
        channelList.addAll(Arrays.asList(channels.split(",")));
    }
    Boolean isRead = null;
    if (state != null) {
        if (state.equalsIgnoreCase("read") || state.equalsIgnoreCase("unread")) {
            isRead = state.equalsIgnoreCase("read");
        } else {
            errors.add(new ErrorField("state", "invalid"));
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
    // Build the drop filter
    DropFilter dropFilter = new DropFilter();
    dropFilter.setMaxId(maxId);
    dropFilter.setSinceId(sinceId);
    dropFilter.setChannels(channelList);
    dropFilter.setChannelIds(channelIds);
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
    return riverService.getDrops(id, dropFilter, page, count, principal.getName());
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
    return riverService.modifyCollaborator(id, accountId, body, principal.getName());
}


}