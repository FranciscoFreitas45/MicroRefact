package com.cg.oms.validator;

import org.springframework.stereotype.Component;

import com.cg.oms.exception.InvalidInputException;
import com.cg.oms.vo.UserVo;

/**
 * 
 * @author Arivazhagan
 *
 */
@Component
public class UserValidator
{
	/**
	 * 
	 * @param userVo
	 * @return true or false based on validation
	 * @throws InvalidInputException
	 */
	public boolean validateUser(UserVo userVo) throws InvalidInputException
	{
		boolean flag = false;
		String phoneRegex = "^[0-9]{10}$";
		String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String mobileNumber = userVo.getUserPhoneNumber();
		String emailId = userVo.getEmailId();
		String password = userVo.getUserPassword();
		if (emailId.matches(emailRegex))
		{
			if (password.length() >= 8)
			{
				if (mobileNumber.matches(phoneRegex))
				{
					flag = true;
				}
				else
				{
					throw new InvalidInputException("The MobileNumber must be of 10 digits");
				}
			}
			else
			{
				throw new InvalidInputException("The given password doesn't meet the expections");
			}
		}
		else
		{
			throw new InvalidInputException("The given Email is not a valid one");
		}
		return flag;
	}
}
