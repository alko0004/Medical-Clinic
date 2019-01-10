package assign4;

import java.io.Serializable;
import java.util.GregorianCalendar;

/* File Name: MaternityPatient
 * Course Name: CST8284 Object Oriented Programming
 * Lab Section: 300
 * Student Name: Mohamad Al Kodmani
 * Date: November 25th 2018
 */

/*MaternityPatient class to register MaternityPatient*/
public class MaternityPatient extends Patient implements Serializable{

	OurDate dueDate;
	boolean nutritionTesting;

	private static final GregorianCalendar cal = new GregorianCalendar();

	public MaternityPatient() {
		this	("unknown", "unknown", "-9", new OurDate(), new OurDate(), false);
	}

	public MaternityPatient(String firstName, String lastName, String healthCardNumber, OurDate birthDate, OurDate dueDate, boolean nutritionTesting) {

		super(firstName, lastName, healthCardNumber, birthDate);
		setDueDate(dueDate);
		setNutritionTesting(nutritionTesting);
	}

	@Override
	public String toString() {
		return super.toString() + "Maternity patient- Due date: " + dueDate + "Nutrition testing: " + nutritionTesting + "\n";
	}

	public OurDate getDueDate() {
		return dueDate;
	}

	private void setDueDate(OurDate dueDate) {

		if(
				dueDate.getYear() == cal.get(GregorianCalendar.YEAR) &&
				dueDate.getMonth() == (cal.get(GregorianCalendar.MONTH)+1) &&
				dueDate.getDay() == cal.get(GregorianCalendar.DATE)
				) {
			throw new MedicalClinicException("Due date cannot be today. \n");
		}

		//Past
		if(		
				dueDate.getYear() < cal.get(GregorianCalendar.YEAR) ||

				dueDate.getYear() <= cal.get(GregorianCalendar.YEAR) && 
				dueDate.getMonth() < (cal.get(GregorianCalendar.MONTH)+1) ||

				dueDate.getYear() <= cal.get(GregorianCalendar.YEAR) && 
				dueDate.getMonth() <= (cal.get(GregorianCalendar.MONTH)+1) &&
				dueDate.getDay() < cal.get(GregorianCalendar.DATE)
				) {
			throw new MedicalClinicException("Due date cannot be in the past. \n");
		}


		this.dueDate = dueDate;
	}

	public boolean isNutritionTesting() {
		return nutritionTesting;
	}

	private void setNutritionTesting(boolean nutritionTesting) {
		this.nutritionTesting = nutritionTesting;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + (nutritionTesting ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj == null) {
			return false;
		}
		if(!(obj instanceof MaternityPatient)) {
			return false;
		}

		MaternityPatient m = new MaternityPatient();

		if(this.getFirstName() == m.getFirstName() && this.getLastName() == m.getLastName() && 
				this.getHealthCardNumber() == m.getHealthCardNumber() && this.getBirthDate() == this.getBirthDate() && 
				this.getAppointment() == m.getAppointment() && this.isNutritionTesting() == m.isNutritionTesting() &&
				this.getDueDate() == this.getDueDate()) {
			return true;
		}
		return false;
	}

}//
