/**
 * 
 */
package com.evolvingreality.onleave.DTO;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * @author Derek Reynolds
 * @since 1.0
 */

public class SecurityGroup {

   
    private Long id;
	
    
	private String groupName;

    private List<SecurityGroupAuthority> authorities = new LinkedList<SecurityGroupAuthority>();
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SecurityGroupAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<SecurityGroupAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
