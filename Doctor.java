package assign4;

import java.io.Serializable;

/* File Name: Doctor
* Course Name: CST8284 Object Oriented Programming
* Lab Section: 300
* Student Name: Mohamad Al Kodmani
* Date: November 25th 2018
*/

/*Doctor class to register doctors*/
public class Doctor implements Serializable{

	private String firstName;
	private String lastName;
	private String speciality;

	public Doctor() {
		this("unknown", "unknown", "unknown");
		firstName = null;
		lastName = null;
		speciality = null;
	}

	public Doctor(String firstName, String lastName, String speciality) {
		setFirstName(firstName);
		setLastName(lastName);
		setSpeciality(speciality);
	}

	@Override 
	public String toString() {
		return "Doctor- " + firstName + " " + lastName + ", Speciality: " + speciality;
	}

	public String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	} 

	public String getSpeciality() {
		return speciality;
	}

	private void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Doctor)) {
			return false;
		}
		
		Doctor d = new Doctor();
		d.equals(obj);
		
		if( this.getFirstName() == d.getFirstName() && this.getLastName() == d.getLastName() && 
				this.getSpeciality() == d.getSpeciality() ) {
			return true;
		}
		
		
		return false;
	}


}//end class


