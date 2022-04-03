package com.ushahidi.swiftriver.core.api.service;
 import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ushahidi.swiftriver.core.api.controller.RiversController;
import com.ushahidi.swiftriver.core.api.dao.AccountDao;
import com.ushahidi.swiftriver.core.api.dao.ChannelDao;
import com.ushahidi.swiftriver.core.api.dao.LinkDao;
import com.ushahidi.swiftriver.core.api.dao.PlaceDao;
import com.ushahidi.swiftriver.core.api.dao.RiverCollaboratorDao;
import com.ushahidi.swiftriver.core.api.dao.RiverDao;
import com.ushahidi.swiftriver.core.api.dao.RiverDropDao;
import com.ushahidi.swiftriver.core.api.dao.RiverDropFormDao;
import com.ushahidi.swiftriver.core.api.dao.RuleDao;
import com.ushahidi.swiftriver.core.api.dao.TagDao;
import com.ushahidi.swiftriver.core.api.dto.ChannelUpdateNotification;
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
import com.ushahidi.swiftriver.core.api.dto.RuleUpdateNotification;
import com.ushahidi.swiftriver.core.api.exception.BadRequestException;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
import com.ushahidi.swiftriver.core.api.exception.ForbiddenException;
import com.ushahidi.swiftriver.core.api.exception.NotFoundException;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.api.filter.TrendFilter;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.ActivityType;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.BucketDrop;
import com.ushahidi.swiftriver.core.model.Channel;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Link;
import com.ushahidi.swiftriver.core.model.Place;
import com.ushahidi.swiftriver.core.model.River;
import com.ushahidi.swiftriver.core.model.RiverCollaborator;
import com.ushahidi.swiftriver.core.model.RiverDrop;
import com.ushahidi.swiftriver.core.model.RiverDropComment;
import com.ushahidi.swiftriver.core.model.RiverDropForm;
import com.ushahidi.swiftriver.core.model.RiverTagTrend;
import com.ushahidi.swiftriver.core.model.Rule;
import com.ushahidi.swiftriver.core.model.Tag;
import com.ushahidi.swiftriver.core.solr.DropDocument;
import com.ushahidi.swiftriver.core.solr.repository.DropDocumentRepository;
import com.ushahidi.swiftriver.core.util.ErrorUtil;
import com.ushahidi.swiftriver.core.util.MD5Util;
import com.ushahidi.swiftriver.core.Interface.RiverDao;
import com.ushahidi.swiftriver.core.Interface.AccountDao;
import com.ushahidi.swiftriver.core.Interface.AccountService;
import com.ushahidi.swiftriver.core.Interface.RiverCollaboratorDao;
import com.ushahidi.swiftriver.core.Interface.RiverDropDao;
import com.ushahidi.swiftriver.core.Interface.RiverDropFormDao;
import com.ushahidi.swiftriver.core.Interface.RuleDao;
import com.ushahidi.swiftriver.core.Interface.TagDao;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
import com.ushahidi.swiftriver.core.Interface.PlaceDao;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepository;
import com.ushahidi.swiftriver.core.DTO.Account;
import com.ushahidi.swiftriver.core.DTO.RiverCollaborator;
import com.ushahidi.swiftriver.core.DTO.BadRequestException;
import com.ushahidi.swiftriver.core.DTO.Rule;
import com.ushahidi.swiftriver.core.DTO.TrendFilter;
import com.ushahidi.swiftriver.core.DTO.Tag;
import com.ushahidi.swiftriver.core.DTO.FollowerDTO;
import com.ushahidi.swiftriver.core.DTO.Place;
import com.ushahidi.swiftriver.core.DTO.Link;
import com.ushahidi.swiftriver.core.DTO.DropFilter;
import com.ushahidi.swiftriver.core.DTO.DropDocument;
@Service
@Transactional(readOnly = true)
public class RiverService {

 final  Logger logger;

@Autowired
 private  RiverDao riverDao;

@Autowired
 private  AccountDao accountDao;

@Autowired
 private  AccountService accountService;

@Autowired
 private  ChannelDao channelDao;

@Autowired
 private  RiverCollaboratorDao riverCollaboratorDao;

@Autowired
 private  Mapper mapper;

@Autowired
 private  RiverDropDao riverDropDao;

@Autowired
 private  RiverDropFormDao riverDropFormDao;

@Autowired
 private  RuleDao ruleDao;

@Autowired
 private  TagDao tagDao;

@Autowired
 private  LinkDao linkDao;

@Autowired
 private  PlaceDao placeDao;

@Autowired
 private  AmqpTemplate amqpTemplate;

@Autowired
 private  DropDocumentRepository repository;

 private  int dropQuota;


@Transactional
public void deleteDropLink(Long riverId,Long dropId,Long linkId,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    Link link = linkDao.findById(linkId);
    if (link == null) {
        throw new NotFoundException(String.format("Link %d does not exist", linkId));
    }
    if (!riverDropDao.deleteLink(riverDrop, link)) {
        throw new NotFoundException(String.format("Drop %d does not have link %d", dropId, linkId));
    }
}


public boolean hasAccess(River river,Account queryingAccount){
    if (river.getRiverPublic())
        return true;
    return river.getAccount().equals(queryingAccount) || (riverDao.findCollaborator(river.getId(), queryingAccount.getId()) != null);
}


@Transactional(readOnly = false)
public FormValueDTO addDropForm(Long riverId,Long dropId,FormValueDTO createDTO,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDrop drop = getRiverDrop(riverId, dropId);
    RiverDropForm dropForm = mapper.map(createDTO, RiverDropForm.class);
    dropForm.setDrop(drop);
    try {
        riverDropFormDao.create(dropForm);
    } catch (DataIntegrityViolationException e) {
        throw ErrorUtil.getBadRequestException("id", "duplicate");
    }
    return mapper.map(dropForm, FormValueDTO.class);
}


public void setRiverDao(RiverDao riverDao){
    this.riverDao = riverDao;
}


@Transactional(readOnly = false)
public void deleteChannel(Long riverId,Long channelId,String authUser){
    Channel channel = getRiverChannel(riverId, channelId, authUser);
    int channelDropCount = channel.getDropCount();
    River river = channel.getRiver();
    channelDao.delete(channel);
    // Update the river drop count
    logger.debug("Reducing the drop count of river {} by {} drops", riverId, channelDropCount);
    river.setDropCount(river.getDropCount() - channelDropCount);
    riverDao.update(river);
    // Construct the routing key
    String routingKey = String.format("web.channel.%s.delete", channel.getChannel());
    ChannelUpdateNotification notification = new ChannelUpdateNotification();
    notification.setId(channelId);
    notification.setChannel(channel.getChannel());
    notification.setRiverId(riverId);
    notification.setParameters(channel.getParameters());
    amqpTemplate.convertAndSend(routingKey, notification);
    logger.debug("Sending {} message for deleted '{}' parameter", routingKey, channel.getParameters());
}


public GetCollaboratorDTO addCollaborator(Long riverId,CreateCollaboratorDTO createCollaboratorTO,String authUser) throws NotFoundException{
    // Check if the river exists
    River river = getRiver(riverId);
    // Check if the authenticating user has permission to add a collaborator
    Account authAccount = accountDao.findByUsernameOrEmail(authUser);
    if (!isOwner(river, authAccount))
        throw new ForbiddenException("Permission denied.");
    // Is the account already collaborating on the river
    if (riverDao.findCollaborator(riverId, createCollaboratorTO.getAccount().getId()) != null)
        throw new BadRequestException("The account is already collaborating on the river");
    Account account = accountDao.findById(createCollaboratorTO.getAccount().getId());
    if (account == null)
        throw new NotFoundException("Account not found");
    RiverCollaborator collaborator = riverDao.addCollaborator(river, account, createCollaboratorTO.isReadOnly());
    accountService.logActivity(authAccount, ActivityType.INVITE, collaborator);
    return mapCollaboratorDTO(collaborator);
}


public List<GetCommentDTO> getDropComments(Long riverId,Long dropId,String authUser){
    River river = getRiver(riverId);
    if (!river.getRiverPublic() && !isOwner(river, authUser))
        throw new ForbiddenException("Permission Denied");
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    List<GetCommentDTO> commentsList = new ArrayList<GetCommentDTO>();
    for (RiverDropComment dropComment : riverDrop.getComments()) {
        GetCommentDTO commentDTO = mapper.map(dropComment, GetCommentDTO.class);
        commentsList.add(commentDTO);
    }
    return commentsList;
}


public GetCommentDTO addDropComment(Long riverId,Long dropId,CreateCommentDTO createDTO,String authUser){
    if (createDTO.getCommentText() == null || createDTO.getCommentText().trim().length() == 0) {
        throw new BadRequestException("The no comment text specified");
    }
    River river = getRiver(riverId);
    if (!river.getRiverPublic() && !isOwner(river, authUser))
        throw new ForbiddenException("Permission Denied");
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    Account account = accountDao.findByUsernameOrEmail(authUser);
    RiverDropComment dropComment = riverDropDao.addComment(riverDrop, account, createDTO.getCommentText());
    return mapper.map(dropComment, GetCommentDTO.class);
}


@Transactional
public void deleteDropTag(Long riverId,Long dropId,Long tagId,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    Tag tag = tagDao.findById(tagId);
    if (tag == null) {
        throw new NotFoundException(String.format("Tag %d does not exist", tagId));
    }
    if (!riverDropDao.deleteTag(riverDrop, tag)) {
        throw new NotFoundException(String.format("Drop %d does not have tag %d", dropId, tagId));
    }
}


public GetChannelDTO createChannel(Long riverId,CreateChannelDTO createChannelTO){
    River river = getRiver(riverId);
    Channel channel = mapper.map(createChannelTO, Channel.class);
    channel.setRiver(river);
    channel.setActive(Boolean.TRUE);
    channelDao.create(channel);
    // Construct the routing key
    String routingKey = String.format("web.channel.%s.add", channel.getChannel().toLowerCase());
    ChannelUpdateNotification notification = new ChannelUpdateNotification();
    notification.setId(channel.getId());
    notification.setChannel(channel.getChannel());
    notification.setRiverId(riverId);
    notification.setParameters(channel.getParameters());
    amqpTemplate.convertAndSend(routingKey, notification);
    logger.debug("Sending {} message for new '{}' parameter", routingKey, channel.getParameters());
    return mapper.map(channel, GetChannelDTO.class);
}


public boolean isOwner(River river,Account account){
    RiverCollaborator collaborator = riverDao.findCollaborator(river.getId(), account.getId());
    return river.getAccount() == account || (collaborator != null && !collaborator.isReadOnly());
}


public void setMapper(Mapper mapper){
    this.mapper = mapper;
}


@Transactional(readOnly = false)
public void deleteDrop(Long id,Long dropId,String authUser){
    River river = getRiver(id);
    if (!isOwner(river, authUser)) {
        throw new ForbiddenException("Permission denied");
    }
    RiverDrop riverDrop = getRiverDrop(id, dropId);
    // Update the river drop count
    river.setDropCount(river.getDropCount() - 1);
    riverDao.update(river);
    // Update the channel drop count
    Channel channel = riverDrop.getChannel();
    channel.setDropCount(channel.getDropCount() - 1);
    channelDao.update(channel);
    // Delete the river drop
    riverDropDao.delete(riverDrop);
}


public void setAccountDao(AccountDao accountDao){
    this.accountDao = accountDao;
}


@Transactional(readOnly = false)
public GetRiverDTO createRiver(CreateRiverDTO riverTO,String authUser){
    Account account = accountDao.findByUsernameOrEmail(authUser);
    if (!(account.getRiverQuotaRemaining() > 0))
        throw new ForbiddenException("River quota exceeded");
    if (riverDao.findByName(riverTO.getRiverName()) != null) {
        BadRequestException ex = new BadRequestException("Duplicate river name");
        List<ErrorField> errors = new ArrayList<ErrorField>();
        errors.add(new ErrorField("name", "duplicate"));
        ex.setErrors(errors);
        throw ex;
    }
    River river = mapper.map(riverTO, River.class);
    river.setAccount(account);
    river.setActive(Boolean.TRUE);
    river.setDropQuota(dropQuota);
    riverDao.create(river);
    accountDao.decreaseRiverQuota(account, 1);
    accountService.logActivity(account, ActivityType.CREATE, river);
    return mapper.map(river, GetRiverDTO.class);
}


public void deleteDropComment(Long riverId,Long dropId,Long commentId,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission Denied");
    getRiverDrop(riverId, dropId);
    if (!riverDropDao.deleteComment(commentId)) {
        throw new NotFoundException(String.format("Comment %d does not exist", commentId));
    }
}


public ChannelDao getChannelDao(){
    return channelDao;
}


public GetCollaboratorDTO mapCollaboratorDTO(RiverCollaborator collaborator){
    GetCollaboratorDTO collaboratorDTO = mapper.map(collaborator.getAccount(), GetCollaboratorDTO.class);
    collaboratorDTO.setActive(collaborator.isActive());
    collaboratorDTO.setReadOnly(collaborator.isReadOnly());
    return collaboratorDTO;
}


@Transactional(readOnly = false)
public GetRuleDTO addRule(Long riverId,CreateRuleDTO createRuleDTO,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser)) {
        throw new ForbiddenException("Permission denied");
    }
    Rule rule = mapper.map(createRuleDTO, Rule.class);
    rule.setRiver(river);
    rule.setDateAdded(new Date());
    ruleDao.create(rule);
    // Send add_rule message on the MQ
    RuleUpdateNotification notification = mapper.map(rule, RuleUpdateNotification.class);
    amqpTemplate.convertAndSend("web.river.rules.add", notification);
    return mapper.map(rule, GetRuleDTO.class);
}


@Transactional
public void addFollower(Long id,Long accountId){
    // Does the river exist?
    River river = getRiver(id);
    Account account = accountDao.findById(accountId);
    if (account == null) {
        throw new NotFoundException("Account not found");
    }
    river.getFollowers().add(account);
    riverDao.update(river);
    accountService.logActivity(account, ActivityType.FOLLOW, river);
}


@Transactional(readOnly = false)
public FormValueDTO modifyDropForm(Long riverId,Long dropId,Long formId,ModifyFormValueDTO modifyFormTo,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDropForm dropForm = riverDropDao.findForm(dropId, formId);
    if (dropForm == null)
        throw new NotFoundException("The specified form was not found");
    mapper.map(modifyFormTo, dropForm);
    riverDropFormDao.update(dropForm);
    return mapper.map(dropForm, FormValueDTO.class);
}


public void setLinkDao(LinkDao linkDao){
    this.linkDao = linkDao;
}


public void setAmqpTemplate(AmqpTemplate amqpTemplate){
    this.amqpTemplate = amqpTemplate;
}


public void setRiverDropDao(RiverDropDao riverDropDao){
    this.riverDropDao = riverDropDao;
}


@Transactional(readOnly = false)
public GetCollaboratorDTO modifyCollaborator(Long riverId,Long accountId,ModifyCollaboratorDTO modifyCollaboratorTO,String authUser){
    River river = getRiver(riverId);
    Account authAccount = accountDao.findByUsernameOrEmail(authUser);
    if (!isOwner(river, authAccount))
        throw new ForbiddenException("Permission denied.");
    RiverCollaborator collaborator = riverDao.findCollaborator(riverId, accountId);
    // Collaborator exists?
    if (collaborator == null) {
        throw new NotFoundException("Collaborator not found");
    }
    if (modifyCollaboratorTO.getActive() != null) {
        collaborator.setActive(modifyCollaboratorTO.getActive());
    }
    if (modifyCollaboratorTO.getReadOnly() != null) {
        collaborator.setReadOnly(modifyCollaboratorTO.getReadOnly());
    }
    // Post changes to the DB
    riverDao.updateCollaborator(collaborator);
    return mapCollaboratorDTO(collaborator);
}


@Transactional
public void deleteDropPlace(Long riverId,Long dropId,Long placeId,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    Place place = placeDao.findById(placeId);
    if (place == null) {
        throw new NotFoundException(String.format("Place %d does not exist", placeId));
    }
    if (!riverDropDao.deletePlace(riverDrop, place)) {
        throw new NotFoundException(String.format("Drop %d does not have place %d", dropId, placeId));
    }
}


public List<GetTagTrend> getTrendingTags(Long riverId,TrendFilter trendFilter,String authUser){
    River river = getRiver(riverId);
    Account queryingAccount = accountDao.findByUsernameOrEmail(authUser);
    if (!hasAccess(river, queryingAccount)) {
        throw new ForbiddenException("Permission denied");
    }
    // If no dates specified, get data for the last 1 week
    if (trendFilter.getDateFrom() == null && trendFilter.getDateTo() == null) {
        trendFilter.setDateTo(new Date());
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, -7);
        trendFilter.setDateFrom(now.getTime());
    }
    List<RiverTagTrend> tagTrends = riverDao.getTrendingTags(riverId, trendFilter);
    if (tagTrends == null) {
        throw new NotFoundException(String.format("No trending tags found for river %d", riverId));
    }
    List<GetTagTrend> tagTrendDtos = new ArrayList<GetTagTrend>();
    for (RiverTagTrend trend : tagTrends) {
        GetTagTrend trendDto = mapper.map(trend, GetTagTrend.class);
        tagTrendDtos.add(trendDto);
    }
    return tagTrendDtos;
}


public River getRiver(Long id){
    River river = riverDao.findById(id);
    if (river == null) {
        throw new NotFoundException(String.format("River with id %d not found", id));
    }
    return river;
}


@Transactional(readOnly = false)
public boolean deleteRiver(Long id,String authUser){
    River river = getRiver(id);
    Account account = accountDao.findByUsernameOrEmail(authUser);
    // Only the creator can delete the river
    if (!river.getAccount().equals(account)) {
        throw new ForbiddenException("Access denied");
    }
    // Delete the river
    riverDao.delete(river);
    // Update the river remaining quota
    accountDao.increaseRiverQuota(account, 1);
    return true;
}


public RiverDrop getRiverDrop(Long riverId,Long dropId){
    RiverDrop riverDrop = riverDao.findRiverDrop(riverId, dropId);
    if (riverDrop == null) {
        throw new NotFoundException(String.format("Drop %d does not exist in river %d", dropId, riverId));
    }
    return riverDrop;
}


public List<GetPlaceTrend> getTredingPlaces(Long riverId,TrendFilter trendFilter,String authUser){
    River river = getRiver(riverId);
    Account queryingAccount = accountDao.findByUsernameOrEmail(authUser);
    if (!hasAccess(river, queryingAccount)) {
        throw new ForbiddenException("Permission denied");
    }
    // If no dates specified, get data for the last 1 week
    if (trendFilter.getDateFrom() == null && trendFilter.getDateTo() == null) {
        trendFilter.setDateTo(new Date());
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, -7);
        trendFilter.setDateFrom(now.getTime());
    }
    List<RiverTagTrend> placeTrends = riverDao.getTrendingPlaces(riverId, trendFilter);
    if (placeTrends == null) {
        throw new NotFoundException(String.format("No trending places found for river %d", riverId));
    }
    List<GetPlaceTrend> placeTrendDtos = new ArrayList<GetPlaceTrend>();
    for (RiverTagTrend trend : placeTrends) {
        GetPlaceTrend trendDto = mapper.map(trend, GetPlaceTrend.class);
        placeTrendDtos.add(trendDto);
    }
    return placeTrendDtos;
}


@Transactional(readOnly = false)
public GetRiverDTO modifyRiver(Long riverId,ModifyRiverDTO modifyRiverTO,String authUser){
    River river = getRiver(riverId);
    Account account = accountDao.findByUsernameOrEmail(authUser);
    if (!isOwner(river, account))
        throw new ForbiddenException("Authenticated user does not own the river");
    if (modifyRiverTO.getRiverName() != null && !modifyRiverTO.getRiverName().equals(river.getRiverName())) {
        if (riverDao.findByName(modifyRiverTO.getRiverName()) != null) {
            BadRequestException ex = new BadRequestException("Duplicate river name");
            List<ErrorField> errors = new ArrayList<ErrorField>();
            errors.add(new ErrorField("name", "duplicate"));
            ex.setErrors(errors);
            throw ex;
        }
    }
    mapper.map(modifyRiverTO, river);
    riverDao.update(river);
    return mapper.map(river, GetRiverDTO.class);
}


@Transactional
public List<FollowerDTO> getFollowers(Long id,Long accountId){
    River river = getRiver(id);
    List<FollowerDTO> followerList = new ArrayList<FollowerDTO>();
    if (accountId != null) {
        Account account = accountDao.findById(accountId);
        if (account == null) {
            throw new NotFoundException(String.format("Account %d does not exist", accountId));
        }
        if (river.getFollowers().contains(account)) {
            followerList.add(mapFollowerDTO(account));
        } else {
            throw new NotFoundException(String.format("Account %d does not follow river %d", accountId, id));
        }
    } else {
        for (Account account : river.getFollowers()) {
            followerList.add(mapFollowerDTO(account));
        }
    }
    return followerList;
}


@Transactional(readOnly = false)
public void deleteDropForm(Long riverId,Long dropId,Long formId,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDropForm dropForm = riverDropDao.findForm(dropId, formId);
    if (dropForm == null)
        throw new NotFoundException("The specified form was not found");
    riverDropFormDao.delete(dropForm);
}


@Transactional
public GetTagDTO addDropTag(Long riverId,Long dropId,CreateTagDTO createDTO,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    // Get the bucket drop
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    String hash = MD5Util.md5Hex(createDTO.getTag() + createDTO.getTagType());
    Tag tag = tagDao.findByHash(hash);
    if (tag == null) {
        tag = new Tag();
        tag.setTag(createDTO.getTag());
        tag.setType(createDTO.getTagType());
        tagDao.create(tag);
    } else {
        // Check if the tag exists in the bucket drop
        if (riverDropDao.findTag(riverDrop, tag) != null) {
            throw new BadRequestException(String.format("Tag %s of type %s has already been added to drop %d", tag.getTag(), tag.getType(), dropId));
        }
    }
    riverDropDao.addTag(riverDrop, tag);
    return mapper.map(tag, GetTagDTO.class);
}


public void setRiverCollaboratorDao(RiverCollaboratorDao riverCollaboratorDao){
    this.riverCollaboratorDao = riverCollaboratorDao;
}


public void setPlaceDao(PlaceDao placeDao){
    this.placeDao = placeDao;
}


@Transactional(readOnly = false)
public GetChannelDTO modifyChannel(Long riverId,Long channelId,ModifyChannelDTO modifyChannelTO,String authUser){
    Channel channel = getRiverChannel(riverId, channelId, authUser);
    // Get the channel before modification for a deletion notification
    ChannelUpdateNotification beforeNotification = new ChannelUpdateNotification();
    beforeNotification.setId(channelId);
    beforeNotification.setChannel(channel.getChannel());
    beforeNotification.setRiverId(riverId);
    beforeNotification.setParameters(channel.getParameters());
    mapper.map(modifyChannelTO, channel);
    channelDao.update(channel);
    // Get the channel after modification for an add notification
    ChannelUpdateNotification afterNotification = new ChannelUpdateNotification();
    afterNotification.setId(channelId);
    afterNotification.setChannel(channel.getChannel());
    afterNotification.setRiverId(riverId);
    afterNotification.setParameters(channel.getParameters());
    amqpTemplate.convertAndSend("web.channel." + beforeNotification.getChannel() + ".delete", beforeNotification);
    amqpTemplate.convertAndSend("web.channel." + afterNotification.getChannel() + ".add", afterNotification);
    return mapper.map(channel, GetChannelDTO.class);
}


public void setAccountService(AccountService accountService){
    this.accountService = accountService;
}


public FollowerDTO mapFollowerDTO(Account account){
    FollowerDTO accountDto = mapper.map(account, FollowerDTO.class);
    accountDto.setName(account.getOwner().getName());
    accountDto.setEmail(account.getOwner().getEmail());
    return accountDto;
}


public List<GetRuleDTO> getRules(Long riverId,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser)) {
        throw new ForbiddenException("Permission denied");
    }
    List<GetRuleDTO> rulesDTOList = new ArrayList<GetRuleDTO>();
    for (Rule rule : river.getRules()) {
        rulesDTOList.add(mapper.map(rule, GetRuleDTO.class));
    }
    return rulesDTOList;
}


public void setRiverDropFormDao(RiverDropFormDao riverDropFormDao){
    this.riverDropFormDao = riverDropFormDao;
}


public void setChannelDao(ChannelDao channelDao){
    this.channelDao = channelDao;
}


public void setTagDao(TagDao tagDao){
    this.tagDao = tagDao;
}


@Transactional(readOnly = false)
public void markDropAsRead(Long riverId,Long dropId,String authUser){
    River river = getRiver(riverId);
    Account account = accountDao.findByUsernameOrEmail(authUser);
    if (!river.getRiverPublic() && !this.isOwner(river, account)) {
        throw new ForbiddenException("Access denied");
    }
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    // Only add drop to the list if it doesn't exist
    if (!riverDropDao.isRead(riverDrop, account)) {
        account.getReadRiverDrops().add(riverDrop);
        accountDao.update(account);
    }
}


@Transactional
public GetPlaceDTO addDropPlace(Long riverId,Long dropId,CreatePlaceDTO createDTO,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    String hashInput = createDTO.getName();
    hashInput += Float.toString(createDTO.getLongitude());
    hashInput += Float.toString(createDTO.getLatitude());
    String hash = MD5Util.md5Hex(hashInput);
    // Generate a hash for the place name
    Place place = placeDao.findByHash(hash);
    if (place == null) {
        place = new Place();
        place.setPlaceName(createDTO.getName());
        place.setLatitude(createDTO.getLatitude());
        place.setLongitude(createDTO.getLongitude());
        placeDao.create(place);
    } else {
        if (riverDropDao.findPlace(riverDrop, place) != null) {
            throw new BadRequestException(String.format("Drop %d already has the place %s with coordinates [%f, %f]", dropId, place.getPlaceName(), place.getLatitude(), place.getLongitude()));
        }
    }
    riverDropDao.addPlace(riverDrop, place);
    return mapper.map(place, GetPlaceDTO.class);
}


public List<GetCollaboratorDTO> getCollaborators(Long riverId) throws NotFoundException{
    River river = getRiver(riverId);
    List<GetCollaboratorDTO> collaborators = new ArrayList<GetCollaboratorDTO>();
    for (RiverCollaborator collaborator : river.getCollaborators()) {
        collaborators.add(mapCollaboratorDTO(collaborator));
    }
    return collaborators;
}


@Transactional(readOnly = false)
public GetRuleDTO modifyRule(Long riverId,Long ruleId,CreateRuleDTO createRuleDTO,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser)) {
        throw new ForbiddenException("Permission denied");
    }
    Rule rule = ruleDao.findById(ruleId);
    if (rule == null || (rule != null && !rule.getRiver().equals(river))) {
        throw new NotFoundException(String.format("Rule %d not found", ruleId));
    }
    mapper.map(createRuleDTO, rule);
    ruleDao.update(rule);
    RuleUpdateNotification notification = mapper.map(rule, RuleUpdateNotification.class);
    amqpTemplate.convertAndSend("web.river.rules.update", notification);
    return mapper.map(rule, GetRuleDTO.class);
}


public void setDropQuota(int dropQuota){
    this.dropQuota = dropQuota;
}


public GetRiverDTO getRiverById(Long id) throws NotFoundException{
    River river = getRiver(id);
    return mapper.map(river, GetRiverDTO.class);
}


@Transactional
public GetLinkDTO addDropLink(Long riverId,Long dropId,CreateLinkDTO createDTO,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser))
        throw new ForbiddenException("Permission denied");
    RiverDrop riverDrop = getRiverDrop(riverId, dropId);
    String hash = MD5Util.md5Hex(createDTO.getUrl());
    Link link = linkDao.findByHash(hash);
    if (link == null) {
        link = new Link();
        link.setUrl(createDTO.getUrl());
        link.setHash(hash);
        linkDao.create(link);
    } else {
        // Has the link already been added ?
        if (riverDropDao.findLink(riverDrop, link) != null) {
            throw new BadRequestException(String.format("%s has already been added to drop %d", link.getUrl(), dropId));
        }
    }
    riverDropDao.addLink(riverDrop, link);
    return mapper.map(link, GetLinkDTO.class);
}


@Transactional(readOnly = false)
public void deleteRule(Long riverId,Long ruleId,String authUser){
    River river = getRiver(riverId);
    if (!isOwner(river, authUser)) {
        throw new ForbiddenException("Permission denied");
    }
    Rule rule = ruleDao.findById(ruleId);
    if (rule == null || (rule != null && !rule.getRiver().equals(river))) {
        throw new NotFoundException(String.format("Rule %d not found", ruleId));
    }
    RuleUpdateNotification notification = mapper.map(rule, RuleUpdateNotification.class);
    ruleDao.delete(rule);
    amqpTemplate.convertAndSend("web.river.rules.delete", notification);
}


@Transactional
public void deleteCollaborator(Long riverId,Long accountId,String authUser){
    River river = getRiver(riverId);
    Account authAccount = accountDao.findByUsernameOrEmail(authUser);
    RiverCollaborator collaborator = riverDao.findCollaborator(riverId, accountId);
    if (collaborator == null)
        throw new NotFoundException("Collaborator not found.");
    // Check if the collaborator's account is the same as
    // the authenticating account
    if (!collaborator.getAccount().equals(authAccount)) {
        if (!isOwner(river, authAccount))
            throw new ForbiddenException("Permission denied.");
    }
    riverCollaboratorDao.delete(collaborator);
}


public List<GetRiverDTO> findRivers(String searchTerm,int count,int page){
    List<River> rivers = riverDao.findAll(searchTerm, count, page);
    List<GetRiverDTO> riverDTOs = new ArrayList<GetRiverDTO>();
    for (River river : rivers) {
        riverDTOs.add(mapper.map(river, GetRiverDTO.class));
    }
    return riverDTOs;
}


public Channel getRiverChannel(Long riverId,long channelId,String authUser){
    Channel channel = channelDao.findById(channelId);
    if (channel == null)
        throw new NotFoundException("The given channel was not found");
    River river = channel.getRiver();
    if (!river.getId().equals(riverId))
        throw new NotFoundException("The given river does not countain the given channel.");
    Account account = accountDao.findByUsernameOrEmail(authUser);
    if (!isOwner(river, account))
        throw new ForbiddenException("Logged in user does not own the river.");
    return channel;
}


@Transactional
public void deleteFollower(Long riverId,Long accountId){
    // Load the river and check if it exists
    River river = getRiver(riverId);
    // Load the account and check if it exists
    Account account = accountDao.findById(accountId);
    if (account == null) {
        throw new NotFoundException("Account not found");
    }
    river.getFollowers().remove(account);
    riverDao.update(river);
}


public List<GetDropDTO> getDrops(Long id,DropFilter dropFilter,int page,int dropCount,String username) throws NotFoundException{
    // Get the river
    River river = getRiver(id);
    // Get the querying account
    Account queryingAccount = accountDao.findByUsernameOrEmail(username);
    if (!hasAccess(river, queryingAccount))
        throw new ForbiddenException("Access denied");
    List<GetDropDTO> getDropDTOs = new ArrayList<GetDropDTO>();
    // Farm fulltext and geospatial search to Solr
    if (dropFilter.getKeywords() != null || dropFilter.getBoundingBox() != null) {
        PageRequest pageRequest = new PageRequest(page - 1, dropCount);
        List<DropDocument> dropDocuments = repository.findInRiver(id, dropFilter, pageRequest);
        if (dropDocuments.isEmpty()) {
            return getDropDTOs;
        }
        List<Long> dropIds = new ArrayList<Long>();
        for (DropDocument document : dropDocuments) {
            dropIds.add(Long.parseLong(document.getId()));
        }
        // Set page number to 1
        page = 1;
        dropFilter.setDropIds(dropIds);
    }
    // Get the drops
    List<Drop> drops = riverDao.getDrops(id, dropFilter, page, dropCount, queryingAccount);
    if (drops == null) {
        throw new NotFoundException("No drops found");
    }
    for (Drop drop : drops) {
        getDropDTOs.add(mapper.map(drop, GetDropDTO.class));
    }
    return getDropDTOs;
}


public List<River> filterVisible(List<River> rivers,Account queryingAccount){
    List<River> visible = new ArrayList<River>();
    for (River river : rivers) {
        if (isOwner(river, queryingAccount) || river.getRiverPublic()) {
            visible.add(river);
        }
    }
    return visible;
}


}