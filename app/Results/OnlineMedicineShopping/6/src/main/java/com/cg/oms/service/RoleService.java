package com.cg.oms.service;

import com.cg.oms.exception.RoleNotFoundException;
import com.cg.oms.vo.RoleVo;

/**
 * 
 * @author Muthuraja Arumugam
 *
 */
public interface RoleService
{
	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String saveRole(RoleVo roleVo);

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String deleteRole(Long id) throws RoleNotFoundException;

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public RoleVo getRoleById(Long id) throws RoleNotFoundException;

}
