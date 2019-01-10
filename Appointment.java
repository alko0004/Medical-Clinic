package assign4;

import java.io.Serializable;

/* File Name: Appointment
 * Course Name: CST8284 Object Oriented Programming
 * Lab Section: 300
 * Student Name: Mohamad Al Kodmani
 * Date: November 25th 2018
 */

import java.util.GregorianCalendar;

/*Appointment class to register appointments*/
public class Appointment implements Serializable{

	private Doctor doctor;
	private Patient patient;
	private OurDate appDate;
	
	private static final GregorianCalendar cal = new GregorianCalendar();

	public Appointment() { 
		this(new Doctor(), new Patient(), new OurDate());
	}

	public Appointment(Doctor doctor, Patient patient, OurDate appDate) { 
		setDoctor(doctor);
		setPatient(patient);
		setAppointmentDate(appDate);
	}

	@Override
	public String toString() {
		return "Appointment: \n" + doctor + "\n" + patient + "Appointment date: " + appDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	private void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	private void setPatient(Patient patient) {
		this.patient = patient;
	}

	public OurDate getAppointmentDate() {
		return appDate;
	}

	private void setAppointmentDate(OurDate appDate) {


		//Current day
		if(		
				appDate.getYear() == cal.get(GregorianCalendar.YEAR) &&
				appDate.getMonth() == (cal.get(GregorianCalendar.MONTH)+1) &&
				appDate.getDay() == cal.get(GregorianCalendar.DATE)
				) {
			throw new MedicalClinicException("Appointment cannot be today. \n");
		}

		//Past date
		if(		//less than current year OR
				appDate.getYear() < cal.get(GregorianCalendar.YEAR) ||

				//same year or less AND same month or less OR
				appDate.getYear() <= cal.get(GregorianCalendar.YEAR) && 
				appDate.getMonth() < (cal.get(GregorianCalendar.MONTH)+1) ||

				appDate.getYear() <= cal.get(GregorianCalendar.YEAR) && 
				appDate.getMonth() <= (cal.get(GregorianCalendar.MONTH)+1) &&
				appDate.getDay() < cal.get(GregorianCalendar.DATE)
				) {
			throw new MedicalClinicException("Appointment cannot be in the past. \n");
		}

		this.appDate = appDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appDate == null) ? 0 : appDate.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Appointment)) {
			return false;
		}

		Appointment a = new Appointment();
		a.equals(obj);

		if(this.getAppointmentDate() == a.getAppointmentDate() && this.getDoctor() == a.getDoctor()
				&& this.getPatient() == a.getPatient()) {
			return true;
		}

		return false;
	}



}//end class
