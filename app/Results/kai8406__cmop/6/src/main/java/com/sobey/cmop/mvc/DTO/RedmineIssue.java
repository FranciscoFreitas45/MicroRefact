package com.sobey.cmop.mvc.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class RedmineIssue {

 private  Integer id;

 private  Integer issueId;

 private  Integer trackerId;

 private  Integer projectId;

 private  String subject;

 private  Integer assignee;

 private  Integer status;

 private  String resourceId;

 private  Integer applyId;

 private  Integer serviceTagId;

 private  Set<Failure> failures;

 private  Set<Attachment> attachments;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

// Constructors
/**
 * default constructor
 */
public RedmineIssue() {
}/**
 * minimal constructor
 */
public RedmineIssue(Integer trackerId, Integer projectId, String subject, Integer assignee, Integer status) {
    this.trackerId = trackerId;
    this.projectId = projectId;
    this.subject = subject;
    this.assignee = assignee;
    this.status = status;
}/**
 * full constructor
 */
public RedmineIssue(Integer issueId, Integer trackerId, Integer projectId, String subject, Integer assignee, Integer status, String resourceId, Integer applyId, Integer serviceTagId, Set<Failure> failures, Set<Attachment> attachments) {
    this.issueId = issueId;
    this.trackerId = trackerId;
    this.projectId = projectId;
    this.subject = subject;
    this.assignee = assignee;
    this.status = status;
    this.resourceId = resourceId;
    this.applyId = applyId;
    this.serviceTagId = serviceTagId;
    this.failures = failures;
    this.attachments = attachments;
}
@Column(name = "subject", nullable = false, length = 255)
public String getSubject(){
    return this.subject;
}


@Column(name = "tracker_id", nullable = false)
public Integer getTrackerId(){
    return this.trackerId;
}


@Column(name = "project_id", nullable = false)
public Integer getProjectId(){
    return this.projectId;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "assignee")
public Integer getAssignee(){
    return this.assignee;
}


@Column(name = "status")
public Integer getStatus(){
    return this.status;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "redmineIssue")
public Set<Attachment> getAttachments(){
    return this.attachments;
}


@Column(name = "service_tag_id")
public Integer getServiceTagId(){
    return serviceTagId;
}


@Column(name = "resource_id", length = 45)
public String getResourceId(){
    return this.resourceId;
}


@Column(name = "apply_id")
public Integer getApplyId(){
    return applyId;
}


@Column(name = "issue_id")
public Integer getIssueId(){
    return this.issueId;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "redmineIssue")
public Set<Failure> getFailures(){
    return this.failures;
}


public void setAssignee(Integer assignee){
    this.assignee = assignee;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssignee"))

.queryParam("assignee",assignee)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(Integer status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


}