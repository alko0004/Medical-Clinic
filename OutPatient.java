package assign4;

import java.io.Serializable;

/* File Name: OutPatient
* Course Name: CST8284 Object Oriented Programming
* Lab Section: 300
* Student Name: Mohamad Al Kodmani
* Date: November 25th 2018
*/


/*OutPatient class to register OutPatient*/
public class OutPatient extends Patient implements Serializable{
	
	double distanceFromClinic;
	boolean mobility;
	
	public OutPatient() {
		this("unknown", "unknown", "-9", new OurDate(), -1, false);
	}
	
	public OutPatient(String firstName, String lastName, String healthCardNumber, OurDate birthDate, double distanceFromClinic, boolean mobility) {
		super(firstName, lastName, healthCardNumber, birthDate);
		setDistanceFromClinic(distanceFromClinic);
		setMobility(mobility);
	}

	@Override
	public String toString() {
		return super.toString() + "Out patient- Distance from clinic: " + distanceFromClinic + ", Mobility: " + mobility + "\n";
	}

	public double getDistanceFromClinic() {
		return distanceFromClinic;
	}

	private void setDistanceFromClinic(double distanceFromClinic) {
		this.distanceFromClinic = distanceFromClinic;
	}

	public boolean isMobility() {
		return mobility;
	}

	private void setMobility(boolean mobility) {
		this.mobility = mobility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distanceFromClinic);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (mobility ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj == null) {
			return false;
		}
		if(!(obj instanceof OutPatient)) {
			return false;
		}
		
		OutPatient o = new OutPatient();
		o.equals(obj);
		
		if(this.getFirstName() == o.getFirstName() && this.getLastName() == o.getLastName() && 
				this.getHealthCardNumber() == o.getHealthCardNumber() && this.getBirthDate() == this.getBirthDate() && 
				this.getAppointment() == o.getAppointment() && this.getDistanceFromClinic() == o.getDistanceFromClinic() 
				&& this.isMobility() == o.isMobility()) {
			return true;
		}
		
		return false;
	}
	
}
