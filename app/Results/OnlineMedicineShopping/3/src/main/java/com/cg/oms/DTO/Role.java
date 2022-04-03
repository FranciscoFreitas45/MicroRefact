package com.cg.oms.DTO;

public class Role
{

	
	private Long roleId;

	private String roleName;

	public Role()
	{
		super();
	}

	/**
	 * getters and setters of entity table
	 * 
	 * @param roleName
	 */

	public Role(String roleName)
	{
		super();
		this.roleName = roleName;
	}

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

	@Override
	public String toString()
	{
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
}
