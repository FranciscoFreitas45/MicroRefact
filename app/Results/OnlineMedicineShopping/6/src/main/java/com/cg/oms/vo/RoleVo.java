
package com.cg.oms.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class RoleVo implements Serializable
{
	/**
	 * Generated serialVersionUID
	 */
	private static final Long serialVersionUID = 6452080042606681713L;

	@JsonProperty(value = "roleId", access = Access.READ_ONLY)
	private Long roleId;
	@JsonProperty(value = "roleName")
	private String roleName;

	/**
	 * Getters and Setter
	 * 
	 * @return
	 */

	public Long getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Long roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

}