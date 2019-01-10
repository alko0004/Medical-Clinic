package assign4;
/* File Name: OurDate
 * Course Name: CST8284 Object Oriented Programming
 * Lab Section: 300
 * Student Name: Mohamad Al Kodmani
 * Date: November 25th 2018
 */

import java.io.Serializable;
import java.util.GregorianCalendar;

/*OurDate class to set a date*/
public class OurDate implements Serializable{

	private int year;
	private int month;
	private int day;

	private static final GregorianCalendar cal = new GregorianCalendar();

	public OurDate() { 

		this( cal.get(GregorianCalendar.DATE), cal.get(GregorianCalendar.MONTH) + 1, cal.get(GregorianCalendar.YEAR) );

	}

	public OurDate( int day, int month, int year) { 
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	@Override
	public String toString() {
		return day + "/" + month + "/" + year + "\n";
	}

	public int getDay() {
		return day;
	}

	private void setDay(int day) {

		
		boolean leapYear = (
				(year % 4 == 0) && (year % 100 != 0) ) || 
				(year % 400 == 0);
		
		if (leapYear) {
			if (month == 2 && day > 29)
				throw new MedicalClinicException("Invalid Day. (1-29)");
		} 
		else {
			if (month == 2 && day > 28)
				throw new MedicalClinicException("Invalid Day. (1-28)");
		}

		if(day > 31 || day < 1) {
			throw new MedicalClinicException("Invalid day. (1-31) \n");
		}
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	private void setMonth(int month) {

		if(month > 12 || month < 1) {
			throw new MedicalClinicException("Invalid month. (1-12) \n");
		}
		this.month = month;

	}

	public int getYear() {
		return year;
	}

	private void setYear(int year) {

		if(year < 1900) {
			throw new MedicalClinicException("Birth year cannot be less than 1900. \n");
		}
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof OurDate)) {
			return false;
		}

		OurDate d = (OurDate)obj;

		if(this.getDay() == d.getDay() && this.getMonth() == d.getMonth() && this.getYear() == d.getYear()) {
			return true;
		}

		return false;
	}



}
//REFERENCE
//https://www.mkyong.com/java/java-how-to-calculate-leap-year/
