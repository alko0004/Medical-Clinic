package assign4;

import java.io.IOException;

/* File Name: MedicalClinic
 * Course Name: CST8284 Object Oriented Programming
 * Lab Section: 300
 * Student Name: Mohamad Al Kodmani
 * Date: November 25th 2018
 */

import java.util.Scanner;

/*Represents a MedicalClinic to keep track of appointments between doctors and patients.*/
public class MedicalClinicUserInterface {

	//Declared variables
	private MedicalClinic clinic;
	
	private Scanner input = new Scanner(System.in); 
	private int option;
	private int patientType;
	String firstName;
	String lastName;
	String healthCardNumber;

	//Default constructor
	public MedicalClinicUserInterface() { 
		clinic = new MedicalClinic();
	}

	//Main
	public static void main(String[] args) throws IOException {
		
		MedicalClinicUserInterface ob = new MedicalClinicUserInterface();
		
		ob.menu();
		
	}
	
	public void writePatientsOut() throws ClassNotFoundException, IOException	{
		
		clinic.filePatientsOut();
		
	}
	
	public void readPatientsIn() throws ClassNotFoundException, IOException {
		
		clinic.filePatientsIn();
		
	}

	//Menu display and options
	public void menu() { 

		//Declared variables
		boolean quit = false;

		//Menu
		while(quit == false) {

			//Options
			System.out.println("Please make a choice: ");
			System.out.println("1. Enter a new patient.");
			System.out.println("2. Make an appointment for a patient.");
			System.out.println("3. Cancel appointment.");
			System.out.println("4. List all appointments.");
			System.out.println("5. Write patient to data file.");
			System.out.println("6. Load patient data.");
			System.out.println("7. Quit");
			System.out.println("8. List all doctors");
			System.out.println("9. List all patients");

			//Choose option
			System.out.print("\nOption: ");
			option = input.nextInt();
			System.out.println();

			if(option == 1) {
				addPatient();
			} 
			else if (option == 2) {
				addAppointment();
			}
			else if(option == 3) {
				cancelAppointment();
			}
			else if(option == 4) {
				listAppointments();
			}
			else if(option == 5) {
				try {
					writePatientsOut();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(option == 6) {
				try {
					try {
						readPatientsIn();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(option == 7) {
				System.out.println("Good bye");
				quit = true;
			}
			else if(option == 8) {
				printDoctors();
			}
			else if(option == 9) {
				printPatients();
			}
			else {
				System.out.println("Invalid option. Choose one of the following: " + "\n");	
			}
		}

	}//end menu

	//Add a new patient
	public void addPatient() { 

		//Declared variables
		int birthDay, birthMonth, birthYear;
		String bDate = new String();
		OurDate bDate2 = new OurDate();
		OurDate dueDate2 = new OurDate();
		boolean registered = false;
		boolean validBdate = false;
		boolean validDdate = false;

		//If not full, add new patient
		if(clinic.patients.size() < clinic.getMaxPatients()) {

			//Register new patient information
			System.out.print("First name: ");
			firstName = input.next();

			System.out.print("Last name: ");
			lastName = input.next();
		
			System.out.print("\nHealth card number: ");
			healthCardNumber = input.next();

			//Search if patient is registered
			for(Patient p: clinic.getPatients()) {
				if(healthCardNumber.equals(p.getHealthCardNumber())) {
					System.out.println(p);
					registered = true;
				}
			}

			//Confirm patient
			if (registered == true) {

				System.out.println("Patient is already registered. \n");

			} else {
				//Continue if not registered
				do {
					try {
						System.out.print("Birth date (DDMMYYYY): "); 
						bDate = input.next();
						System.out.println();

						birthDay = Integer.parseInt(bDate.substring(0, 2));
						birthMonth = Integer.parseInt(bDate.substring(2, 4));
						birthYear = Integer.parseInt(bDate.substring(4, 8));

						bDate2 = new OurDate(birthDay, birthMonth, birthYear);
						validBdate = true;
					}
					catch(MedicalClinicException e) {
						e.printStackTrace();
					}
				}
				while(!validBdate);

				//Patient type
				System.out.println("Enter the type of patient: ");
				System.out.println("1. Maternity patient");
				System.out.println("2. Out patient");
				System.out.println("3. Regular patient");

				System.out.println();
				System.out.print("Option: ");
				patientType = input.nextInt();
				System.out.println();	

				//Register new maternity patient
				if(patientType == 1) {
					do {
						try {
							System.out.print("Due date (DDMMYYYY): ");
							String dueDate = input.next();

							int dueDay = Integer.parseInt(dueDate.substring(0, 2));
							int dueMonth = Integer.parseInt(dueDate.substring(2, 4));
							int dueYear = Integer.parseInt(dueDate.substring(4, 8));

							dueDate2 = new OurDate(dueDay, dueMonth, dueYear);
							validDdate = true;
						}
						catch(MedicalClinicException e) {
							e.printStackTrace();
						}
					}
					while(!validDdate);
					
					System.out.print("Nutrition testing (1 for true, 0 for false): ");
					int nt = input.nextInt();

					boolean nutritionTesting = false;

					if(nt == 1 ) {
						nutritionTesting = true;
					} else {
						nutritionTesting = false;
					}

					do {
						try { 
							clinic.addPatient(firstName, lastName, healthCardNumber, bDate2, dueDate2, nutritionTesting);
							System.out.println("Patient registered. \n");
							validBdate = true;
						} catch (MedicalClinicException e) { 
							e.printStackTrace(); 
							menu();
						}
					}
					while(!validBdate);

				}//

				//Register new out patient
				if(patientType == 2) {

					System.out.print("Distance from clinic: ");
					double distance = input.nextDouble();

					System.out.print("Mobility (1 for true, 0 for false): ");
					int mob = input.nextInt();
					boolean mobility = false;

					if(mob == 1 ) {
						mobility = true;
					} else {
						mobility = false;
					}

					do {
						try {
							clinic.addPatient(firstName, lastName, healthCardNumber, bDate2, distance, mobility);
							System.out.println("Patient registered. \n");
							validBdate = true;
						} catch (MedicalClinicException e) { 
							e.printStackTrace(); 
							menu();
						}
					}
					while(!validBdate);

				}//

				//Register new regular patient
				if(patientType == 3) {
					do {
						try {
							clinic.addPatient(firstName, lastName, healthCardNumber, bDate2);
							System.out.println("Patient registered. \n");
							validBdate = true;
						} catch (MedicalClinicException e) { 
							e.printStackTrace(); 
							menu();
						}
					}
					while(!validBdate);

				}//

			}//End registered true

		} else {
			System.out.println("Patient list is full.\n");
		}
	}

	//Add a new appointment
	public void addAppointment() { 

		//Declared variables
		int docChoice; 
		int appDay, appMonth, appYear;
		String appDate = new String();
		OurDate appDate2 = new OurDate();
		boolean registered = false; 
		int selectPatient = 0;
		boolean hasAppointment = false;
		boolean hasAppointment2 = false;
		boolean validAdate = false;

		//If not full, add new appointment
		if(clinic.appointments.size() < clinic.getMaxAppointments()) {

			System.out.print("Health card number: ");
			healthCardNumber = input.next();
			System.out.println(); 

			//Search if patient is registered and select that patient
			for (int i = 0; i < clinic.patients.size(); i++) {
				if (healthCardNumber.contains(clinic.patients.get(i).getHealthCardNumber())) { 
					System.out.print(clinic.patients.get(i));
					registered = true; 
					selectPatient = i;
				}
			}

			//Confirm patient
			if (registered == false) {
				System.out.println("Patient is not registered." +"\n");
			} 

			//search if patient has appointment
			for(Appointment a: clinic.appointments) {
				if(healthCardNumber.contains(a.getPatient().getHealthCardNumber())) {
					hasAppointment = true;
					System.out.println("The patient already has an appointment." + "\n");
				} else {
					hasAppointment = false;
				}
			}

			//Continue if registered and does not have appointment
			if(registered == true && hasAppointment == false) {

				//List doctors
				System.out.println();
				for(int i = 0; i < clinic.doctors.size(); i++) {
					System.out.println((i+1) + ". " + clinic.doctors.get(i));
				}

				//Choose a doctor
				System.out.print("\nChoose a doctor selection: "); 
				docChoice = input.nextInt();	

				while(docChoice < 1 || docChoice > 5) {
					System.out.println("Invalid doctor option. Choose one of the following. \n");

					for(int i = 0; i < clinic.doctors.size(); i++) {
						System.out.println((i+1) + ". " + clinic.doctors.get(i));
					}
					System.out.println();

					System.out.print("Select a doctor: ");
					docChoice = input.nextInt();
				}//End registered true and no appointment

				//Choose appointment date
				System.out.print("Appointment date (DDMMYYYY): ");
				appDate = input.next();
				System.out.println();

				//Convert and set date
				appDay = Integer.parseInt(appDate.substring(0, 2));
				appMonth = Integer.parseInt(appDate.substring(2, 4));
				appYear = Integer.parseInt(appDate.substring(4, 8));

				appDate2 = new OurDate(appDay, appMonth, appYear);

				//Search if doctor has an appointment on that day 
				for(int i = 0; i < clinic.appointments.size(); i++) {
					if(
							clinic.doctors.get(docChoice-1) == clinic.appointments.get(i).getDoctor() &&
							appDate2.getDay() == clinic.appointments.get(i).getAppointmentDate().getDay() &&
							appDate2.getMonth() == clinic.appointments.get(i).getAppointmentDate().getMonth() &&
							appDate2.getYear() == clinic.appointments.get(i).getAppointmentDate().getYear()) {

						hasAppointment2 = true;
					}
				}

				//If not doctor is not busy on that day, set new appointment
				if(hasAppointment2 == false) { 

					do {
						try {
							clinic.addAppointment(clinic.doctors.get(docChoice-1), clinic.patients.get(selectPatient), appDate2); 
							System.out.println("Appointment set. \n");
							validAdate = true;
						} catch (MedicalClinicException e) { 
							e.printStackTrace(); 
							menu();
						}
					} while(!validAdate);
				} else {
					System.out.println("The doctor is busy on that date. \n");
				}
			}

		} else {
			System.out.println("Appointment list is full. \n");
		}

	}//

	public void cancelAppointment() {

		//Declared variables
		boolean registered = false; 
		boolean hasAppointment = false; 
		boolean hasAppointment2 = false; 
		int selectPatient = 0;
		int docChoice; 
		String appDate = new String();
		OurDate appDate2 = new OurDate();
		int appDay, appMonth, appYear;

		//Enter appointment details
		System.out.print("Health card number: ");
		healthCardNumber = input.next();

		//Search if patient is registered
		for(int i = 0; i < clinic.patients.size(); i++) {
			if(healthCardNumber.contains(clinic.patients.get(i).getHealthCardNumber())) {
				System.out.println(clinic.patients.get(i));
				registered = true;
			}
		}

		if(registered == false) {
			System.out.println("Patient is not registered. \n");
			menu();
		}

		//Search if patient has appointment
		for (int i = 0; i < clinic.appointments.size(); i++) {
			if (healthCardNumber.contains(clinic.appointments.get(i).getPatient().getHealthCardNumber())) { 
				selectPatient = i;
				hasAppointment = true;
			} 
		}

		if(hasAppointment == false) {
			System.out.println("No appointment found. \n");
		}

		//Continue if patient is registered and has an appointment
		if(registered == true && hasAppointment == true) {

			//Print doctors
			for(int i = 0; i < clinic.doctors.size(); i++) {
				System.out.println((i+1) + ". " + clinic.doctors.get(i));
			}
			System.out.println();

			//Choose appointment
			System.out.print("Choose a doctor: ");
			docChoice = input.nextInt();

			while(docChoice < 1 || docChoice > 5) {
				System.out.println("Invalid doctor option. Choose one of the following: \n");

				for(int i = 0; i < clinic.doctors.size(); i++) {
					System.out.println((i+1) + ". " + clinic.doctors.get(i));
				}
				System.out.println();

				System.out.print("Select a doctor: ");
				docChoice = input.nextInt();
			}

			System.out.print("Appointment date (DDMMYYYY): ");
			appDate = input.next();
			System.out.println();

			//Convert and set date
			appDay = Integer.parseInt(appDate.substring(0, 2));
			appMonth = Integer.parseInt(appDate.substring(2, 4));
			appYear = Integer.parseInt(appDate.substring(4, 8));

			appDate2 = new OurDate(appDay, appMonth, appYear); 

			//Find patient and doctor have an appointment that day
			for(int i = 0; i < clinic.appointments.size(); i++) {
				if(
						clinic.doctors.get(docChoice-1) == clinic.appointments.get(i).getDoctor() &&
						clinic.patients.get(selectPatient) == clinic.appointments.get(i).getPatient() &&
						
						healthCardNumber.contains(clinic.appointments.get(i).getPatient().getHealthCardNumber()) &&
						clinic.appointments.get(i).getAppointmentDate().getDay() == appDate2.getDay() &&
						clinic.appointments.get(i).getAppointmentDate().getMonth() == appDate2.getMonth() &&
						clinic.appointments.get(i).getAppointmentDate().getYear() == appDate2.getYear()) {

					hasAppointment2 = true;
				} 			
			}

			//Remove appointment if found
			if(hasAppointment2 == true) {
				try {
					clinic.appointments.remove(selectPatient);
					System.out.println("Appointment canceled.\n");
				}
				catch(MedicalClinicException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("No appointment found with that doctor to cancel on that day. \n");

			}
		}

	}//

	//List appointments
	public void listAppointments() { 

		//List all appointments
		if(clinic.appointments.size() < 1) {
			System.out.println("No appointments found.\n");
		} else {
			for(Appointment a: clinic.appointments) {
				System.out.println(a);
			} 
		}

	}//

	public void printPatients() { 

		//List all patients
		if(clinic.patients.size() < 1) {
			System.out.println("No patients registered.\n");
		} else {
			for(Patient p: clinic.patients) {
				System.out.println(p); 
			}
			System.out.println();
		}

	}//

	public void printDoctors() {

		//List all doctors
		if(clinic.doctors.size() < 1) {
			System.out.println("No doctors found.\n");	
		} else {
			for(Doctor d: clinic.doctors) {
				System.out.println(d.toString());
			}
			System.out.println();
		}

	}//

}//end class








