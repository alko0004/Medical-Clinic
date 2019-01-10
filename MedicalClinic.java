package assign4;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* File Name: MedicalClinic
 * Course Name: CST8284 Object Oriented Programming
 * Lab Section: 300
 * Student Name: Mohamad Al Kodmani
 * appDay: November 25th 2018
 */

import java.util.ArrayList;

/*Represents a MedicalClinic to keep track of appointments between doctors and patients.*/
public class MedicalClinic implements Serializable{

	//Declared variables
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	ArrayList<Patient> patients = new ArrayList<Patient>();
	ArrayList<Doctor> doctors = new ArrayList<Doctor>();

	private final int maxAppointments;
	private final int maxPatients;
	private final int maxDoctors;

	private static int numberAppointments;
	private static int numberPatients;
	private static int numberDoctors; 

	//Default constructor
	public MedicalClinic() { 

		//Declared variables
		numberPatients=2;
		numberAppointments=1;
		numberDoctors=5;
		maxAppointments = 7;
		maxPatients = 7;
		maxDoctors = 5;

		//Patients added to patients list
		patients.add(new Patient("Mohamad", "Kodmani", "123456789", new OurDate(22, 11, 2000)));
		patients.add(new Patient("Johnny", "Donuts", "123456788", new OurDate(13, 12, 1901)));

		//Doctors added to doctors list
		doctors.add(new Doctor("Vikash", "Singh", "Neurology"));
		doctors.add(new Doctor("Susan", "Miller", "Surgery"));
		doctors.add(new Doctor("Thanh", "Do", "Family medicine"));
		doctors.add(new Doctor("Francois", "DaSilva", "Sports medicine"));
		doctors.add(new Doctor("Judy", "Chin", "Physical therapy"));

		//Appointments added to appointment list
		appointments.add(new Appointment(doctors.get(0), patients.get(0), new OurDate(12, 12, 2020)));
	}//

	//Add patients
	public void addPatient(String firstName, String lastName, String healthCardNumber, OurDate birthDate) {
		patients.add( new Patient(firstName, lastName, healthCardNumber, birthDate) );
		numberPatients++;
	}//

	public void addPatient(String firstName, String lastName, String healthCardNumber, OurDate birthDate, OurDate dueDate, boolean nutritionTesting) {
		patients.add(new MaternityPatient(firstName, lastName, healthCardNumber, birthDate, dueDate, nutritionTesting) );
		numberPatients++;
	}//

	public void addPatient(String firstName, String lastName, String healthCardNumber, OurDate birthDate, double distanceFromClinic, boolean mobility) {
		patients.add(new OutPatient(firstName, lastName, healthCardNumber, birthDate, distanceFromClinic, mobility) );
		numberPatients++;
	}//

	//Add appointment
	public void addAppointment(Doctor doctor, Patient patient, OurDate appDate) {
		appointments.add(new Appointment(doctor, patient, appDate));
		numberAppointments++;
	}//

	public void cancelAppointment(int index) {
		appointments.remove(index);
		numberAppointments--;
	}//

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	public final int getMaxAppointments() {
		return maxAppointments;
	}

	public final int getMaxPatients() {
		return maxPatients;
	}

	public final int getMaxDoctors() {
		return maxDoctors;
	}

	public int getNumberAppointments() {
		return numberAppointments;
	}

	public int getNumberPatients() {
		return numberPatients;
	}

	public int getNumberDoctors() {
		return numberDoctors;
	}

	public void filePatientsOut() throws IOException{
		
		try{
		    FileOutputStream writeData = new FileOutputStream("C:\\Users\\mnkod\\Desktop\\PatientData.ser");
		    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

		    writeStream.writeObject(patients);
		    writeStream.writeObject(appointments);
		    writeStream.flush();
		    writeStream.close();

		}catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}

	public void filePatientsIn() throws IOException, ClassNotFoundException{
		
		ObjectInputStream ois = null; 
		
		
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\mnkod\\Desktop\\PatientData.ser");
			ois = new ObjectInputStream(fis);
			
			patients = (ArrayList) ois.readObject();
			appointments = (ArrayList) ois.readObject();
			ois.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		

	}

}//end class


