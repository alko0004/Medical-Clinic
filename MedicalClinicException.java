package assign4;

import java.io.Serializable;

/* File Name: BadDataException
 * Course Name: CST8284 Object Oriented Programming
 * Lab Section: 300
 * Student Name: Mohamad Al Kodmani
 * Date: November 25th 2018
 */

public class MedicalClinicException extends RuntimeException implements Serializable{
	
	private String message;
	
	public MedicalClinicException() {
		super();
		message = "unkown";
	}
	
	public MedicalClinicException(String message) {
		super(message);
	}

}