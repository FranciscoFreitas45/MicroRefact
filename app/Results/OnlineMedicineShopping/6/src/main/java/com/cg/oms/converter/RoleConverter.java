package com.cg.oms.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cg.oms.model.Role;
import com.cg.oms.vo.RoleVo;


/**
 * This function is used to convert the Role object to the RoleVo Object and vise versa
 * this is used for getting and returning the object for security purpose.
 * @author Inam - PC
 *
 */

@Component
public class RoleConverter
{
	/**
	 *  This method converts model object to value object
	 * @param role
	 * @return
	 */

	public RoleVo modelToVo(Role role)
	{
		RoleVo roleVo = new RoleVo();
		roleVo.setRoleId(role.getRoleId());
		roleVo.setRoleName(role.getRoleName());
		return roleVo;
	}
	
	
	/**
	 * This method converts model object to value object
	 * @param role
	 * @return
	 */

	public List<RoleVo> modelToVo(List<Role> role)
	{
		return role.stream().map(x -> modelToVo(x)).collect(Collectors.toList());
	}
	
	/**
	 * This method converts value object to model object
	 * @param roleVo
	 * @return
	 */

	public Role voToModel(RoleVo roleVo)
	{
		Role role = new Role();
		role.setRoleId(roleVo.getRoleId());
		role.setRoleName(roleVo.getRoleName());
		return role;
	}
	
	/**
	 * This method converts value object to model object
	 * @param roleVo
	 * @return
	 */

	public List<Role> voToModel(List<RoleVo> roleVo)
	{
		return roleVo.stream().map(x -> voToModel(x)).collect(Collectors.toList());
	}

}
