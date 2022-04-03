package com.sprint2.utility;

public class Validations {
	 
	//ValidateName  checks whether the given Name matches the conditions are not//
		 	public static String nameregex="[A-za-z]{4,}+";
		 	
	/*validateEmail checks whether the given Email matches the conditions are not */
		 		 public static  String email="[A-za-z0-9!@#$%^&*]+[@]+[a-z]+[.]+com";
		 		
	/*validatePassword checks whether the given Password matches the conditions are not*/
		 	public static  String password="[A-za-z0-9@#$%^&*!]+";
		 		
	/* validate Postalcode checks whether the  given Postalcode matches the condition are not*/
		 		 public static  String postalCode="[0-9]{6}+";
	/*validate Contact  checks whether the given  Contact matches the condition are not*/
		 public static  String contact="[0-9]{10}";
		 }



