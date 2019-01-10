package assign4;

public class HealthCardNumber {

	private String hcn;
	
	public HealthCardNumber() {
		
	}
	
	public HealthCardNumber(String hcn) {
		
	}

	@Override
	public String toString() {
		return "Health card number: " + hcn;
	}

	public String getHcn() {
		return hcn;
	}

	public void setHcn(String hcn) {
		this.hcn = hcn;
	}
	
}
