package com.sprint2.utility;

public class ValidateAdmin {
	
	public static String passwordregex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#&%]).{8,}$";  //password should contain atleast 
	                                                               //one lowercase, one uppercase, one specialcharacter,one digit
	public static String nameregex="[A-za-z]{3,}";// username  should contain only uppercase or lowercase letters and minimum of 3 characters.

}
