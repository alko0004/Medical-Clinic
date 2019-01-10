package assign4;
/* File Name: Patient
 * Course Name: CST8284 Object Oriented Programming
 * Lab Section: 300
 * Student Name: Mohamad Al Kodmani
 * Date: November 25th 2018
 */

import java.io.Serializable;
import java.util.GregorianCalendar;

/*Patient class to register Patient*/
public class Patient implements Serializable {

	private String firstName;
	private String lastName;
	private String healthCardNumber;
	private OurDate birthDate;
	private Appointment appointment;

	private static final GregorianCalendar cal = new GregorianCalendar();

	public Patient() {
		this("unknown", "unknown", "unknown", new OurDate(), new Appointment());
	}

	public Patient(String firstName, String lastName, String healthCardNumber, OurDate birthDate) {
		setFirstName(firstName);
		setLastName(lastName);
		setHealthCardNumber(healthCardNumber);
		setBirthDate(birthDate);
	}

	public Patient(String firstName, String lastName, String healthCardNumber, OurDate birthDate, Appointment appointment) {
		setFirstName(firstName);
		setLastName(lastName);
		setHealthCardNumber(healthCardNumber);
		setBirthDate(birthDate);
		setAppointment(appointment); 
	}

	public Patient(String firstName) { 
		setFirstName(firstName);
	}

	public Patient(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
	}

	public Patient(String firstName, String lastName, String healthCardNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setHealthCardNumber(healthCardNumber);
	}

	@Override
	public String toString() {
		return "Patient- Name: " + firstName + " " + lastName + ", Health Card Number: " + healthCardNumber
				+ ", Birth date: " + birthDate; 
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

	public String getHealthCardNumber() {
		return healthCardNumber;
	}

	private void setHealthCardNumber(String healthCardNumber) {


		if(healthCardNumber.length() != 9) {
			throw new MedicalClinicException("Health card number must be 9 digits.");
		}
		
		if(!healthCardNumber.matches("^[0-9]*$")) {
			throw new MedicalClinicException("Health card number can only be made of digits 0-9");
		}

		this.healthCardNumber = healthCardNumber;

	}

	public OurDate getBirthDate() {

		return birthDate;

	}

	private void setBirthDate(OurDate birthDate) {

		//Current day
		if(
				birthDate.getYear() == cal.get(GregorianCalendar.YEAR) && 
				birthDate.getMonth() == (cal.get(GregorianCalendar.MONTH)+1) &&
				birthDate.getDay() == cal.get(GregorianCalendar.DATE) 
				) {
			throw new MedicalClinicException("Birth date cannot be today. \n");
		}

		//Future date
		if(		
				birthDate.getYear() > cal.get(GregorianCalendar.YEAR) ||

				birthDate.getYear() >= cal.get(GregorianCalendar.YEAR) && 
				birthDate.getMonth() > (cal.get(GregorianCalendar.MONTH)+1) ||

				birthDate.getYear() >= cal.get(GregorianCalendar.YEAR) && 
				birthDate.getMonth() >= (cal.get(GregorianCalendar.MONTH)+1) &&
				birthDate.getDay() > cal.get(GregorianCalendar.DATE) 
				) {
			throw new MedicalClinicException("Birth date cannot be in the future. \n");
		}

		this.birthDate = birthDate;

	}

	public Appointment getAppointment() {
		return appointment;
	}

	private void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointment == null) ? 0 : appointment.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((healthCardNumber == null) ? 0 : healthCardNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Patient)) {
			return false;
		}

		Patient p = new Patient();
		p.equals(obj);

		if(this.getFirstName() == p.getFirstName() && this.getLastName() == p.getLastName() && 
				this.getHealthCardNumber() == p.getHealthCardNumber() && this.getBirthDate() == this.getBirthDate() && 
				this.getAppointment() == p.getAppointment()) {
			return true;
		}

		return false;
	}



}//end class
