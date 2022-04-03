package com.ushahidi.swiftriver.core.api.service;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ushahidi.swiftriver.core.api.controller.BucketsController;
import com.ushahidi.swiftriver.core.api.dao.AccountDao;
import com.ushahidi.swiftriver.core.api.dao.BucketCollaboratorDao;
import com.ushahidi.swiftriver.core.api.dao.BucketCommentDao;
import com.ushahidi.swiftriver.core.api.dao.BucketDao;
import com.ushahidi.swiftriver.core.api.dao.BucketDropDao;
import com.ushahidi.swiftriver.core.api.dao.BucketDropFormDao;
import com.ushahidi.swiftriver.core.api.dao.DropDao;
import com.ushahidi.swiftriver.core.api.dao.LinkDao;
import com.ushahidi.swiftriver.core.api.dao.PlaceDao;
import com.ushahidi.swiftriver.core.api.dao.RiverDropDao;
import com.ushahidi.swiftriver.core.api.dao.TagDao;
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
import com.ushahidi.swiftriver.core.api.exception.ForbiddenException;
import com.ushahidi.swiftriver.core.api.exception.NotFoundException;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.ActivityType;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.BucketCollaborator;
import com.ushahidi.swiftriver.core.model.BucketComment;
import com.ushahidi.swiftriver.core.model.BucketDrop;
import com.ushahidi.swiftriver.core.model.BucketDropComment;
import com.ushahidi.swiftriver.core.model.BucketDropForm;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Link;
import com.ushahidi.swiftriver.core.model.Place;
import com.ushahidi.swiftriver.core.model.Tag;
import com.ushahidi.swiftriver.core.solr.DropDocument;
import com.ushahidi.swiftriver.core.solr.repository.DropDocumentRepository;
import com.ushahidi.swiftriver.core.util.MD5Util;
import com.ushahidi.swiftriver.core.Interface.BucketDao;
import com.ushahidi.swiftriver.core.Interface.AccountDao;
import com.ushahidi.swiftriver.core.Interface.BucketDropDao;
import com.ushahidi.swiftriver.core.Interface.TagDao;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
import com.ushahidi.swiftriver.core.Interface.PlaceDao;
import com.ushahidi.swiftriver.core.Interface.BucketCollaboratorDao;
import com.ushahidi.swiftriver.core.Interface.RiverDropDao;
import com.ushahidi.swiftriver.core.Interface.BucketDropFormDao;
import com.ushahidi.swiftriver.core.Interface.BucketCommentDao;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepository;
import com.ushahidi.swiftriver.core.Interface.AccountService;
import com.ushahidi.swiftriver.core.Interface.BucketDropDao;
import com.ushahidi.swiftriver.core.DTO.BucketDrop;
import com.ushahidi.swiftriver.core.DTO.BucketCollaborator;
import com.ushahidi.swiftriver.core.DTO.BucketComment;
@Service
@Transactional(readOnly = true)
public class BucketService {

@Autowired
 private  BucketDao bucketDao;

@Autowired
 private  Mapper mapper;

@Autowired
 private  AccountDao accountDao;

@Autowired
 private  BucketDropDao bucketDropDao;

@Autowired
 private  TagDao tagDao;

@Autowired
 private  LinkDao linkDao;

@Autowired
 private  PlaceDao placeDao;

@Autowired
 private  BucketCollaboratorDao bucketCollaboratorDao;

@Autowired
 private  RiverDropDao riverDropDao;

@Autowired
 private  BucketDropFormDao bucketDropFormDao;

@Autowired
 private  BucketCommentDao bucketCommentDao;

@Autowired
 private  DropDocumentRepository repository;

@Autowired
 private  AccountService accountService;

@Autowired
 private  DropDao dropDao;

 final  Logger logger;


@Transactional
public void deleteDropLink(Long bucketId,Long dropId,Long linkId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    Link link = linkDao.findById(linkId);
    if (link == null) {
        throw new NotFoundException(String.format("Link %d does not exist", linkId));
    }
    if (!bucketDropDao.deleteLink(bucketDrop, link)) {
        throw new NotFoundException(String.format("Drop %d does not have link %d", dropId, linkId));
    }
}


public boolean hasAccess(Bucket bucket,Account queryingAccount){
    if (bucket.isPublished())
        return true;
    return bucket.getAccount().equals(queryingAccount) || (bucketDao.findCollaborator(bucket.getId(), queryingAccount.getId()) != null);
}


public void setBucketDropFormDao(BucketDropFormDao bucketDropFormDao){
    this.bucketDropFormDao = bucketDropFormDao;
}


@Transactional(readOnly = false)
public GetBucketDTO createBucket(CreateBucketDTO createDTO,String username){
    // Get the bucket name
    String bucketName = createDTO.getName();
    if (bucketName == null) {
        throw new BadRequestException("The bucket name must be specified");
    }
    Account account = accountDao.findByUsernameOrEmail(username);
    // Check if a bucket with the specified name already exists
    if (bucketDao.findBucketByName(account, bucketName) != null) {
        throw new BadRequestException(String.format("User %s already has a bucket named %s", username, bucketName));
    }
    Bucket bucket = new Bucket();
    bucket.setName(bucketName);
    if (createDTO.isPublished() != null) {
        bucket.setPublished(createDTO.isPublished());
    }
    bucket.setAccount(account);
    bucket.setDateAdded(new Date());
    // Save bucket
    bucketDao.create(bucket);
    accountService.logActivity(account, ActivityType.CREATE, bucket);
    return mapper.map(bucket, GetBucketDTO.class);
}


@Transactional(readOnly = false)
public FormValueDTO addDropForm(Long bucketId,Long dropId,FormValueDTO createDTO,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDrop drop = getBucketDrop(bucketId, dropId);
    BucketDropForm dropForm = mapper.map(createDTO, BucketDropForm.class);
    dropForm.setDrop(drop);
    bucketDropFormDao.create(dropForm);
    return mapper.map(dropForm, FormValueDTO.class);
}


public GetCollaboratorDTO addCollaborator(Long bucketId,CreateCollaboratorDTO createCollaboratorTO,String authUser) throws NotFoundException{
    // Check if the bucket exists
    Bucket bucket = getBucket(bucketId);
    // Check if the authenticating user has permission to add a collaborator
    Account authAccount = accountDao.findByUsernameOrEmail(authUser);
    if (!isOwner(bucket, authAccount))
        throw new ForbiddenException("Permission denied.");
    // Is the account already collaborating on the bucket
    if (bucketDao.findCollaborator(bucketId, createCollaboratorTO.getAccount().getId()) != null)
        throw new BadRequestException("The account is already collaborating on the bucket");
    Account account = accountDao.findById(createCollaboratorTO.getAccount().getId());
    if (account == null)
        throw new NotFoundException("Account not found");
    BucketCollaborator collaborator = bucketDao.addCollaborator(bucket, account, createCollaboratorTO.isReadOnly());
    accountService.logActivity(authAccount, ActivityType.INVITE, collaborator);
    return mapCollaboratorDTO(collaborator);
}


@Transactional
public List<GetCommentDTO> getDropComments(Long bucketId,Long dropId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!bucket.isPublished() && !isOwner(bucket, authUser))
        throw new ForbiddenException("Permission Denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    List<GetCommentDTO> commentsList = new ArrayList<GetCommentDTO>();
    for (BucketDropComment dropComment : bucketDrop.getComments()) {
        GetCommentDTO commentDTO = mapper.map(dropComment, GetCommentDTO.class);
        commentsList.add(commentDTO);
    }
    return commentsList;
}


public Bucket getBucket(Long id){
    Bucket bucket = bucketDao.findById(id);
    if (bucket == null) {
        throw new NotFoundException(String.format("Bucket %d does not exist", id));
    }
    return bucket;
}


@Transactional
public GetCommentDTO addDropComment(Long bucketId,Long dropId,CreateCommentDTO createDTO,String authUser){
    if (createDTO.getCommentText() == null || createDTO.getCommentText().trim().length() == 0) {
        throw new BadRequestException("The no comment text specified");
    }
    Bucket bucket = getBucket(bucketId);
    if (!bucket.isPublished() && !isOwner(bucket, authUser))
        throw new ForbiddenException("Permission Denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    Account account = accountDao.findByUsernameOrEmail(authUser);
    BucketDropComment dropComment = bucketDropDao.addComment(bucketDrop, account, createDTO.getCommentText());
    return mapper.map(dropComment, GetCommentDTO.class);
}


@Transactional(readOnly = false)
public GetBucketDTO modifyBucket(Long id,CreateBucketDTO modifiedBucket,String username){
    Bucket bucket = bucketDao.findById(id);
    if (bucket == null) {
        throw new NotFoundException();
    }
    Account account = accountDao.findByUsernameOrEmail(username);
    // Is the account the creator of the bucket or a collaborator with edit
    // privileges?
    if (!isOwner(bucket, account))
        throw new ForbiddenException("Permission denied.");
    // Get the submitted name
    String bucketName = modifiedBucket.getName();
    if (bucketName == null) {
        throw new BadRequestException("The bucket name must be specified");
    }
    // Check if the owner already has a bucket with the
    // specified name
    Bucket existingBucket = bucketDao.findBucketByName(bucket.getAccount(), bucketName);
    if (!bucket.getName().equals(bucketName) && existingBucket != null && existingBucket.getId() != bucket.getId()) {
        throw new BadRequestException(String.format("Another bucket named %s already exists", bucketName));
    }
    bucket.setName(bucketName);
    // Have the privacy settings changed?
    if (modifiedBucket.isPublished() != null) {
        bucket.setPublished(modifiedBucket.isPublished());
    }
    // Has the layout changed?
    if (modifiedBucket.getDefaultLayout() != null) {
        bucket.setDefaultLayout(modifiedBucket.getDefaultLayout());
    }
    // Update the bucket
    bucketDao.update(bucket);
    return mapper.map(bucket, GetBucketDTO.class);
}


@Transactional
public void deleteDropTag(Long bucketId,Long dropId,Long tagId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    Tag tag = tagDao.findById(tagId);
    if (tag == null) {
        throw new NotFoundException(String.format("Tag %d does not exist", tagId));
    }
    if (!bucketDropDao.deleteTag(bucketDrop, tag)) {
        throw new NotFoundException(String.format("Drop %d does not have tag %d", dropId, tagId));
    }
}


public List<GetBucketDTO> findBuckets(String searchTerm,int count,int page){
    List<Bucket> buckets = bucketDao.findAll(searchTerm, count, page);
    List<GetBucketDTO> bucketDTOs = new ArrayList<GetBucketDTO>();
    for (Bucket bucket : buckets) {
        bucketDTOs.add(mapper.map(bucket, GetBucketDTO.class));
    }
    return bucketDTOs;
}


public Mapper getMapper(){
    return mapper;
}


public boolean isOwner(Bucket bucket,Account account){
    BucketCollaborator collaborator = bucketDao.findCollaborator(bucket.getId(), account.getId());
    return bucket.getAccount() == account || (collaborator != null && !collaborator.isReadOnly());
}


public void setMapper(Mapper mapper){
    this.mapper = mapper;
}


@Transactional(readOnly = false)
public void deleteDrop(Long bucketId,Long dropId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    // Delete the drop and decrease bucket drop count
    bucketDropDao.delete(bucketDrop);
    bucketDao.decreaseDropCount(bucket);
}


public BucketCollaboratorDao getBucketCollaboratorDao(){
    return bucketCollaboratorDao;
}


public BucketDropFormDao getBucketDropFormDao(){
    return bucketDropFormDao;
}


public void setAccountDao(AccountDao accountDao){
    this.accountDao = accountDao;
}


@Transactional
public void deleteDropComment(Long bucketId,Long dropId,Long commentId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission Denied");
    getBucketDrop(bucketId, dropId);
    if (!bucketDropDao.deleteComment(commentId)) {
        throw new NotFoundException(String.format("Comment %d does not exist", commentId));
    }
}


public void setBucketDropDao(BucketDropDao bucketDropDao){
    this.bucketDropDao = bucketDropDao;
}


public GetCollaboratorDTO mapCollaboratorDTO(BucketCollaborator collaborator){
    GetCollaboratorDTO collaboratorDTO = mapper.map(collaborator.getAccount(), GetCollaboratorDTO.class);
    collaboratorDTO.setActive(collaborator.isActive());
    collaboratorDTO.setReadOnly(collaborator.isReadOnly());
    return collaboratorDTO;
}


@Transactional
public void addDrop(Long bucketId,Long dropId,String username){
    Bucket bucket = getBucket(bucketId);
    Account account = accountDao.findByUsernameOrEmail(username);
    if (!isOwner(bucket, account))
        throw new ForbiddenException("Permission denied.");
    Drop drop = dropDao.findById(dropId);
    if (dropId == null) {
        throw new NotFoundException(String.format("Drop %d does not exist", dropId));
    }
    BucketDrop bucketDrop = bucketDao.findBucketDrop(bucketId, dropId);
    if (bucketDrop != null) {
        // Increase the veracity
        bucketDropDao.increaseVeracity(bucketDrop);
        return;
    }
    // Create the bucket drop
    bucketDrop = new BucketDrop();
    bucketDrop.setBucket(bucket);
    bucketDrop.setDrop(drop);
    bucketDropDao.create(bucketDrop);
    bucketDao.increaseDropCount(bucket);
}


@Transactional
public void addFollower(long id,long accountId,String username){
    Bucket bucket = getBucket(id);
    Account account = accountDao.findById(accountId);
    if (account == null) {
        throw new NotFoundException();
    }
    // Verify that the account following the bucket is tied to the
    // currently logged in user
    if (!account.getOwner().getUsername().equals(username))
        throw new ForbiddenException("Permission denied.");
    // Is the account already following the bucket
    if (bucket.getFollowers().contains(account)) {
        throw new BadRequestException(String.format("%s  is already following bucket %d", accountId, id));
    }
    bucket.getFollowers().add(account);
    bucketDao.update(bucket);
    accountService.logActivity(account, ActivityType.FOLLOW, bucket);
}


@Transactional(readOnly = false)
public FormValueDTO modifyDropForm(Long bucketId,Long dropId,Long formId,ModifyFormValueDTO modifyFormTo,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDropForm dropForm = bucketDropDao.findForm(dropId, formId);
    if (dropForm == null)
        throw new NotFoundException("The specified form was not found");
    mapper.map(modifyFormTo, dropForm);
    bucketDropFormDao.update(dropForm);
    return mapper.map(dropForm, FormValueDTO.class);
}


public void setLinkDao(LinkDao linkDao){
    this.linkDao = linkDao;
}


public void setRiverDropDao(RiverDropDao riverDropDao){
    this.riverDropDao = riverDropDao;
}


@Transactional(readOnly = false)
public GetCollaboratorDTO modifyCollaborator(Long bucketId,Long accountId,ModifyCollaboratorDTO modifyCollaboratorTO,String authUser){
    Bucket bucket = bucketDao.findById(bucketId);
    if (bucket == null)
        throw new NotFoundException("Bucket not found.");
    Account authAccount = accountDao.findByUsernameOrEmail(authUser);
    if (!isOwner(bucket, authAccount))
        throw new ForbiddenException("Permission denied.");
    // Find the collaborator
    BucketCollaborator collaborator = bucketDao.findCollaborator(bucketId, accountId);
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
    bucketDao.updateCollaborator(collaborator);
    return mapCollaboratorDTO(collaborator);
}


public BucketDrop getBucketDrop(Long bucketId,Long dropId){
    BucketDrop bucketDrop = bucketDao.findBucketDrop(bucketId, dropId);
    if (bucketDrop == null) {
        throw new NotFoundException(String.format("Drop %d does is not in bucket %d", dropId, bucketId));
    }
    return bucketDrop;
}


@Transactional
public void deleteDropPlace(Long bucketId,Long dropId,Long placeId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    Place place = placeDao.findById(placeId);
    if (place == null) {
        throw new NotFoundException(String.format("Place %d does not exist", placeId));
    }
    if (!bucketDropDao.deletePlace(bucketDrop, place)) {
        throw new NotFoundException(String.format("Drop %d does not have place %d", dropId, placeId));
    }
}


@Transactional(readOnly = false)
public void deleteBucket(Long id,String username){
    Bucket bucket = getBucket(id);
    Account queryingAccount = accountDao.findByUsernameOrEmail(username);
    if (!bucket.getAccount().equals(queryingAccount)) {
        throw new ForbiddenException();
    }
    bucketDao.delete(bucket);
}


public AccountDao getAccountDao(){
    return accountDao;
}


public void setBucketDao(BucketDao bucketDao){
    this.bucketDao = bucketDao;
}


@Transactional
public List<FollowerDTO> getFollowers(Long id,Long accountId){
    Bucket bucket = getBucket(id);
    List<FollowerDTO> followers = new ArrayList<FollowerDTO>();
    // Has the accountId parameter been specified
    if (accountId != null) {
        Account follower = accountDao.findById(accountId);
        if (follower == null) {
            throw new NotFoundException(String.format("The account %d does not exist", accountId));
        }
        if (bucket.getFollowers().contains(follower)) {
            followers.add(mapFollowerDTO(follower));
        } else {
            throw new NotFoundException(String.format("Account %d does not follow bucket %d", accountId, id));
        }
    } else {
        for (Account account : bucket.getFollowers()) {
            followers.add(mapFollowerDTO(account));
        }
    }
    return followers;
}


@Transactional(readOnly = false)
public void deleteDropForm(Long bucketId,Long dropId,Long formId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDropForm dropForm = bucketDropDao.findForm(dropId, formId);
    if (dropForm == null)
        throw new NotFoundException("The specified form was not found");
    bucketDropFormDao.delete(dropForm);
}


@Transactional
public GetTagDTO addDropTag(Long bucketId,Long dropId,CreateTagDTO createDTO,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    // Get the bucket drop
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    String hash = MD5Util.md5Hex(createDTO.getTag() + createDTO.getTagType());
    Tag tag = tagDao.findByHash(hash);
    if (tag == null) {
        tag = new Tag();
        tag.setTag(createDTO.getTag());
        tag.setType(createDTO.getTagType());
        tagDao.create(tag);
    } else {
        // Check if the tag exists in the bucket drop
        if (bucketDropDao.findTag(bucketDrop, tag) != null) {
            throw new BadRequestException(String.format("Tag %s of type %s has already been added to drop %d", tag.getTag(), tag.getType(), dropId));
        }
    }
    bucketDropDao.addTag(bucketDrop, tag);
    return mapper.map(tag, GetTagDTO.class);
}


public void setPlaceDao(PlaceDao placeDao){
    this.placeDao = placeDao;
}


public void setAccountService(AccountService accountService){
    this.accountService = accountService;
}


public FollowerDTO mapFollowerDTO(Account account){
    FollowerDTO dto = mapper.map(account, FollowerDTO.class);
    // Set the name and email address
    dto.setName(account.getOwner().getName());
    dto.setEmail(account.getOwner().getEmail());
    return dto;
}


public void setTagDao(TagDao tagDao){
    this.tagDao = tagDao;
}


@Transactional(readOnly = false)
public void markDropAsRead(Long bucketId,Long dropId,String authUser){
    Bucket bucket = getBucket(bucketId);
    Account account = accountDao.findByUsernameOrEmail(authUser);
    if (!bucket.isPublished() && !this.isOwner(bucket, account)) {
        throw new ForbiddenException("Access denied");
    }
    // Only add a drop to the list if it doesn't exist
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    if (!bucketDropDao.isRead(bucketDrop, account)) {
        account.getReadBucketDrops().add(bucketDrop);
        accountDao.update(account);
    }
}


@Transactional
public GetPlaceDTO addDropPlace(Long bucketId,Long dropId,CreatePlaceDTO createDTO,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    String hashInput = createDTO.getName();
    hashInput += Float.toString(createDTO.getLongitude());
    hashInput += Float.toString(createDTO.getLatitude());
    String hash = MD5Util.md5Hex(hashInput);
    // Generate a hash for the place name
    Place place = placeDao.findByHash(hash);
    if (place == null) {
        place = new Place();
        place.setPlaceName(createDTO.getName());
        place.setPlaceNameCanonical(createDTO.getName().toLowerCase());
        place.setHash(hash);
        place.setLatitude(createDTO.getLatitude());
        place.setLongitude(createDTO.getLongitude());
        placeDao.create(place);
    } else {
        if (bucketDropDao.findPlace(bucketDrop, place) != null) {
            throw new BadRequestException(String.format("Drop %d already has the place %s with coordinates [%f, %f]", dropId, place.getPlaceName(), place.getLatitude(), place.getLongitude()));
        }
    }
    bucketDropDao.addPlace(bucketDrop, place);
    return mapper.map(place, GetPlaceDTO.class);
}


public void setBucketCollaboratorDao(BucketCollaboratorDao bucketCollaboratorDao){
    this.bucketCollaboratorDao = bucketCollaboratorDao;
}


public List<GetCollaboratorDTO> getCollaborators(Long bucketId) throws NotFoundException{
    Bucket bucket = bucketDao.findById(bucketId);
    if (bucket == null) {
        throw new NotFoundException("Bucket not found");
    }
    List<GetCollaboratorDTO> collaborators = new ArrayList<GetCollaboratorDTO>();
    for (BucketCollaborator collaborator : bucket.getCollaborators()) {
        collaborators.add(mapCollaboratorDTO(collaborator));
    }
    return collaborators;
}


public void setDropDao(DropDao dropDao){
    this.dropDao = dropDao;
}


@Transactional
public GetLinkDTO addDropLink(Long bucketId,Long dropId,CreateLinkDTO createDTO,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission denied");
    BucketDrop bucketDrop = getBucketDrop(bucketId, dropId);
    String hash = MD5Util.md5Hex(createDTO.getUrl());
    Link link = linkDao.findByHash(hash);
    if (link == null) {
        link = new Link();
        link.setUrl(createDTO.getUrl());
        link.setHash(hash);
        linkDao.create(link);
    } else {
        // Has the link already been added ?
        if (bucketDropDao.findLink(bucketDrop, link) != null) {
            throw new BadRequestException(String.format("%s has already been added to drop %d", link.getUrl(), dropId));
        }
    }
    bucketDropDao.addLink(bucketDrop, link);
    return mapper.map(link, GetLinkDTO.class);
}


@Transactional
public GetCommentDTO addBucketComment(Long bucketId,CreateCommentDTO createDTO,String authUser){
    if (createDTO.getCommentText() == null) {
        throw new BadRequestException("The comment text has not been specified");
    }
    Bucket bucket = getBucket(bucketId);
    if (!bucket.isPublished() && !isOwner(bucket, authUser))
        throw new ForbiddenException("Permission Denied");
    Account account = accountDao.findByUsernameOrEmail(authUser);
    BucketComment bucketComment = bucketDao.addComment(bucket, createDTO.getCommentText(), account);
    return mapper.map(bucketComment, GetCommentDTO.class);
}


@Transactional
public void deleteBucketComment(Long bucketId,Long commentId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!isOwner(bucket, authUser))
        throw new ForbiddenException("Permission Denied");
    BucketComment bucketComment = bucketCommentDao.findById(commentId);
    if (bucketComment == null || !bucketComment.getBucket().equals(bucket)) {
        throw new NotFoundException(String.format("Comment %d does not exist", commentId));
    }
    bucketCommentDao.delete(bucketComment);
}


@Transactional
public List<GetCommentDTO> getBucketComments(Long bucketId,String authUser){
    Bucket bucket = getBucket(bucketId);
    if (!bucket.isPublished() && !isOwner(bucket, authUser))
        throw new ForbiddenException("Permission Denied");
    List<GetCommentDTO> dtoComments = new ArrayList<GetCommentDTO>();
    for (BucketComment bucketComment : bucket.getComments()) {
        dtoComments.add(mapper.map(bucketComment, GetCommentDTO.class));
    }
    return dtoComments;
}


@Transactional
public void deleteCollaborator(Long bucketId,Long accountId,String authUser){
    Bucket bucket = bucketDao.findById(bucketId);
    if (bucket == null)
        throw new NotFoundException("Bucket not found.");
    Account authAccount = accountDao.findByUsernameOrEmail(authUser);
    // Find the collaborator entry associated with the collaborating
    // accountId
    BucketCollaborator collaborator = bucketDao.findCollaborator(bucketId, accountId);
    if (collaborator == null)
        throw new NotFoundException("Collaborator not found.");
    // If the authenticating user is not a collaborator,
    // check for permissions
    if (!collaborator.getAccount().equals(authAccount)) {
        if (!isOwner(bucket, authAccount))
            throw new ForbiddenException("Permission denied.");
    }
    bucketCollaboratorDao.delete(collaborator);
}


@Transactional
public void deleteFollower(Long id,Long accountId){
    Bucket bucket = getBucket(id);
    Account account = accountDao.findById(accountId);
    if (account == null) {
        throw new NotFoundException(String.format("Account %d does not exist", accountId));
    }
    // Does the account exist as a follower?
    if (bucket.getFollowers().contains(account)) {
        bucket.getFollowers().remove(account);
        bucketDao.update(bucket);
    } else {
        throw new NotFoundException(String.format("Account %d does not follow bucket %d", accountId, id));
    }
}


public List<GetDropDTO> getDrops(Long id,DropFilter dropFilter,int page,int dropCount,String authUser){
    Bucket bucket = getBucket(id);
    Account queryingAccount = accountDao.findByUsernameOrEmail(authUser);
    if (!hasAccess(bucket, queryingAccount)) {
        throw new ForbiddenException("Access denied");
    }
    List<GetDropDTO> getDropDTOs = new ArrayList<GetDropDTO>();
    if (dropFilter.getKeywords() != null || dropFilter.getBoundingBox() != null) {
        PageRequest pageRequest = new PageRequest(page - 1, dropCount);
        List<DropDocument> dropDocuments = repository.findInBucket(id, dropFilter, pageRequest);
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
    List<Drop> drops = bucketDao.getDrops(id, dropFilter, page, dropCount, queryingAccount);
    for (Drop drop : drops) {
        GetDropDTO dropDto = mapper.map(drop, GetDropDTO.class);
        getDropDTOs.add(dropDto);
    }
    return getDropDTOs;
}


public List<Bucket> filterVisible(List<Bucket> buckets,Account queryingAccount){
    List<Bucket> visible = new ArrayList<Bucket>();
    for (Bucket bucket : buckets) {
        if (isOwner(bucket, queryingAccount) || bucket.isPublished()) {
            visible.add(bucket);
        }
    }
    return visible;
}


public BucketDao getBucketDao(){
    return bucketDao;
}


}